package logic.yjkbreath.breathData.service.impl;

import logic.yjkbreath.breathData.model.BreathDataModel;
import logic.yjkbreath.breathData.model.BreathPreDataModel;
import logic.yjkbreath.breathData.model.ReceiveDataModel;
import logic.yjkbreath.breathData.model.ResponseDataModel;
import logic.yjkbreath.breathData.repository.BreathDataRepository;
import logic.yjkbreath.breathData.service.BreathDataService;
import logic.yjkbreath.breathData.service.BreathPreDataService;
import logic.yjkbreath.breathData.support.MyMapperUtil;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther fanhanxi
 * @Date 2019/1/2
 * @Description:
 */
@Service
public class BreathDataServiceImpl implements BreathDataService {

    @Autowired
    private BreathDataRepository breathDataRepository;

    @Autowired
    private BreathPreDataService breathPreDataService;

    @Override
    public BreathDataModel save(BreathDataModel model) {

        breathDataRepository.save(model);

        return this.findById(model.getId());
    }

    @Override
    public BreathDataModel update(BreathDataModel model) {
        breathDataRepository.update(model);

        return this.findById(model.getId());
    }

    @Override
    public void delete(int id) {
        BreathDataModel model = this.findById(id);

        breathDataRepository.delete(model);
    }

    @Override
    public BreathDataModel findById(int id) {
        BreathDataModel model = new BreathDataModel();
        model.setId(id);
        return breathDataRepository.findById(model);
    }

    @Override
    public Page<BreathDataModel> findPage(BreathDataModel model, Pageable pageable) {
        model.pageable(pageable);

        if (model.getStartQueryTime() == null || model.getEndQueryTime() == null) {
            DateTime nowDay = new DateTime();

            if (model.getStartQueryTime() == null) {
                nowDay = nowDay.withHourOfDay(0);
                nowDay = nowDay.withMinuteOfHour(0);
                nowDay = nowDay.withSecondOfMinute(0);
                model.setStartQueryTime(nowDay.toDate());
            }

            if (model.getEndQueryTime() == null) {
                model.setEndQueryTime(nowDay.toDate());
            }
        }

        List<BreathDataModel> resultModel = breathDataRepository.findPage(model);
        long total = breathDataRepository.findTotal(model);

        return new PageImpl<>(resultModel, pageable, total);
    }

    @Transactional
    @Override
    public ResponseDataModel saveData(ReceiveDataModel receiveDataModel) {

        String deviceNo = receiveDataModel.getDevice_no();

        //应答数据 model
        ResponseDataModel responseDataModel = new ResponseDataModel();

        //获取用户指标估计值
        BreathPreDataModel preDataModel = new BreathPreDataModel();
        preDataModel = MyMapperUtil.receiveDataToPreDataModel(receiveDataModel, preDataModel);
        preDataModel.setUserId(-1);
        preDataModel.setDeviceId(-1);

        //获取用户测量数据
        BreathDataModel dataModel = new BreathDataModel();
        dataModel = MyMapperUtil.receiveDataToDataModel(receiveDataModel, dataModel);
        dataModel.setUserId(-1);
        dataModel.setDeviceId(-1);

        // 数据计算 PEF%   FEV1%   Fev1 / FVC  PEF日变异率
        dataModel = dataCalculation(dataModel, preDataModel);

        // 初始数据测量状态为正常  后根据数据值改变状态
        dataModel.setDetectedStatus(false);

        // 异常状态判断
        dataModel = abnormalStateDetermination(dataModel);

        //保存用户测量数据
        this.save(dataModel);

        //保存用户预计值数据
        breathPreDataService.saveData(preDataModel);

        //组建返回信息
        responseDataModel.setProcess_satus("1");                                            //已绑定

        return responseDataModel;
}

    @Override
    public Map<String, Object> dataCountResult(Integer userId, Integer deviceId, Date startQueryTime, Date endQueryTime) {
        Map<String, Object> result = new HashMap<>();

        // 统计时间范围内，数据正常次数
        result.putAll(breathDataRepository.dataNormalCount(userId, deviceId, startQueryTime, endQueryTime));

        // 统计时间范围内，数据异常次数
        result.putAll(breathDataRepository.dataAbnormalCount(userId, deviceId, startQueryTime, endQueryTime));

        return result;
    }

    /*************************************  辅助方法 *******************************************************************/
    /**
     * 相关百分率数据计算
     *
     * @return logic.breathData.model.BreathDataModel
     * @Param [model, preDataModel]
     */
    private BreathDataModel dataCalculation(BreathDataModel model, BreathPreDataModel preDataModel) {
        // 计算PEF%值 实际测量PEF值 / 预计PEF值 * 100%
        model.setPefRate(model.getPefValue()
                .divide(preDataModel.getPrePefValue(), 5, RoundingMode.HALF_UP)
                .multiply(new BigDecimal(100))
                .intValue());

        //  计算FEV1%值 实际测量 FEV1值 / 预计PEV1值 *100%
        model.setFev1Rate(model.getFev1Value()
                .divide(preDataModel.getPreFev1Value(), 5, RoundingMode.HALF_UP)
                .multiply(new BigDecimal(100))
                .intValue());

        // 计算FEV1/FVC值  FEV1/FVC * 100%;
        model.setFev1Fvc(model.getFev1Value()
                .divide(model.getFvcValue(), 5, RoundingMode.HALF_UP)
                .multiply(new BigDecimal(100))
                .intValue());

        // 计算PEF日变异率 （PEF最高值 - PEF最低值）/ ((PEF最高值 + PEF最低值) / 2) *100%
        Map<String, Object> maxAndMin = breathDataRepository.findMaxAndMin();
        String maxStr;
        String minStr;
        if (maxAndMin == null) {
            maxStr = model.getPefValue().toString();
            minStr = model.getPefValue().toString();
        } else {
            maxStr = (maxAndMin.get("max") != null) ? (String) maxAndMin.get("max").toString() : model.getPefValue().setScale(2, BigDecimal.ROUND_HALF_UP).toString();
            minStr = (maxAndMin.get("min") != null) ? (String) maxAndMin.get("min").toString() : model.getPefValue().setScale(2, BigDecimal.ROUND_HALF_UP).toString();
        }

        BigDecimal max = new BigDecimal(maxStr);
        BigDecimal min = new BigDecimal(minStr);

        model.setPefDayMutationRate(
                max.subtract(min)
                        .divide(max.add(min).divide(new BigDecimal("2")), 5, RoundingMode.HALF_UP)
                        .multiply(new BigDecimal("100"))
                        .intValue()
        );

        return model;
    }

    /**
     * 异常状态判定
     *
     * @return
     * @Param [dataModel]
     */
    private BreathDataModel abnormalStateDetermination(BreathDataModel dataModel) {
        // 呼吸数据正异常判定
        Integer temp;
        //PEF
        temp = dataModel.getPefRate();
        if (temp >= 80) {
            dataModel.setPefStatus(0);
        } else if (temp < 60) {
            dataModel.setPefStatus(2);
            dataModel.setDetectedStatus(true);
        } else {
            dataModel.setPefStatus(1);
            dataModel.setDetectedStatus(true);
        }

        //fev1
        temp = dataModel.getFev1Rate();
        if (temp >= 80) {
            dataModel.setFev1Status(0);
        } else if (temp >= 50 && temp < 80) {
            dataModel.setFev1Status(1);
        } else if (temp >= 30 && temp < 50) {
            dataModel.setFev1Status(2);
            dataModel.setDetectedStatus(true);
        } else {
            dataModel.setFev1Status(3);
            dataModel.setDetectedStatus(true);
        }

        // FEV1/FVC
        temp = dataModel.getFev1Fvc();
        if (temp > 70) {
            dataModel.setFev1FvcStatus(0);
        } else {
            temp = dataModel.getFev1Rate();
            if (temp >= 80) {
                dataModel.setFev1FvcStatus(1);
            } else if (temp >= 50 && temp < 80) {
                dataModel.setFev1FvcStatus(2);
                dataModel.setDetectedStatus(true);
            } else if (temp >= 30 && temp < 50) {
                dataModel.setFev1FvcStatus(3);
                dataModel.setDetectedStatus(true);
            } else {
                dataModel.setFev1FvcStatus(4);
                dataModel.setDetectedStatus(true);
            }
        }
        return dataModel;
    }
}
