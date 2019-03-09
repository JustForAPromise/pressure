package logic.yjkbreath.device.service.impl;

import boat.commons.util.CommUtil;
import boat.web.util.BSUtil;
import logic.yjkbreath.breathData.model.ReceiveDataModel;
import logic.yjkbreath.breathData.model.ResponseDataModel;
import logic.yjkbreath.breathData.repository.BreathDataRepository;
import logic.yjkbreath.breathData.service.BreathPreDataService;
import logic.yjkbreath.device.model.DeviceSystemModel;
import logic.yjkbreath.device.model.DeviceUserModel;
import logic.yjkbreath.device.repository.DeviceUserRepository;
import logic.yjkbreath.device.service.DeviceSystemService;
import logic.yjkbreath.device.service.DeviceUserService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @Auther fanhanxi
 * @Date 2019/1/2
 * @Description:
 */
@Service
public class DeviceUserServiceImpl implements DeviceUserService {

    @Autowired
    private DeviceUserRepository deviceUserRepository;

    @Autowired
    private DeviceSystemService deviceSystemService;

    @Autowired
    private BreathPreDataService preDataService;

    @Autowired
    private BreathDataRepository breathDataRepository;

    //关联系统设备信息
    private Converter<DeviceUserModel, DeviceUserModel> associateDeviceSystemToUser = model -> {
        model.setDeviceSystem(deviceSystemService.findByDeviceNoAndType(model.getDeviceNo(), model.getDeviceType()));

        return model;
    };


    @Override
    public DeviceUserModel save(DeviceUserModel model) {
        deviceUserRepository.save(model);

        return this.findById(model.getId());
    }

    @Override
    public DeviceUserModel update(DeviceUserModel model) {
        deviceUserRepository.update(model);

        return this.findById(model.getId());
    }

    @Override
    public void delete(int id) {
        DeviceUserModel model = this.findById(id);
        BSUtil.notNull(model, "该设备不存在");

        deviceUserRepository.delete(model);
    }

    ;

    @Override
    public DeviceUserModel findById(int id) {
        DeviceUserModel model = new DeviceUserModel();
        model.setId(id);
        return deviceUserRepository.findById(model);
    }

    @Override
    public DeviceUserModel findByDeviceNo(String deviceNo) {
        DeviceUserModel model = new DeviceUserModel();
        model.setDeviceNo(deviceNo);

        return deviceUserRepository.findOne(model);
    }

    @Override
    public ResponseDataModel connectDevice(ReceiveDataModel receiveDataModel) {

        String deviceNo = receiveDataModel.getDevice_no();

        //应答数据 model
        ResponseDataModel responseDataModel = new ResponseDataModel();
        responseDataModel.setDevice_no(deviceNo);
        System.out.println(deviceNo);
        responseDataModel.setBind_satus("4");
        responseDataModel.setDetected_no("45613456");
        responseDataModel.setDetected_name("zous");
        responseDataModel.setSex("male");
        responseDataModel.setBirthdate("1995-08-08");
        responseDataModel.setHeight(180.0);
        responseDataModel.setWeight(55.0);

        return responseDataModel;
    }

    @Override
    public Page<DeviceUserModel> findPage(DeviceUserModel model, Pageable pageable) {
        model.pageable(pageable);

        List<DeviceUserModel> resultModel = new ArrayList<>();

        // in查询参数
        List<String> inDeviceNos;


        // inDeviceNos.size() == 0； 则说明serviceStatus 状态下 的设备不存在, 结束此次查询
        if (model.getInDeviceNos().size() == 0) {
            return new PageImpl<>(resultModel, pageable, 0);
        }

        // 添加查询参数 绑定状态
        model.setBindingStatus(true);

        resultModel = deviceUserRepository.findPage(model);

        long total = deviceUserRepository.findTotal(model);

        // 关联用户服务有效信息
        for (int i = 0, j = resultModel.size(); i < j; i++) {
            resultModel.set(i, associateDeviceSystemToUser.convert(resultModel.get(i)));

        }

        return new PageImpl<>(resultModel, pageable, total);
    }

    @Transactional
    @Override
    public DeviceUserModel saveDevice(DeviceUserModel model, Integer userServerId) {

        // 获取用户设备记录
        DeviceUserModel deviceUserModel = this.findByDeviceNoAndUserId(model.getDeviceNo(), model.getUserId());

        // 获取系统设备信息
        DeviceSystemModel deviceSystemModel = deviceSystemService.findByDeviceNo(model.getDeviceNo());

        // 当前时间
        DateTime now = new DateTime();

        // 添加设备类型
        model.setDeviceType(deviceSystemModel.getDeviceType());

        //添加激活码
        model.setActivationCode(getActivationNo());
        model.setActivationCodeUseStatus(false);

        // 添加绑定状态
        model.setBindingStatus(true);
        model.setBindTime(now.toDate());

        // 更新 OR 增添判定
        if (deviceUserModel == null) {

            //保存设备信息
            model = this.save(model);

        } else {
            BSUtil.isTrue(!deviceUserModel.getBindingStatus(), "该设备已被绑定！");

            // 设置设备记录id
            model.setId(deviceUserModel.getId());

            // 更新用户服务id
            model.setUserServerId(userServerId);

            // 数据更新
            model = this.update(model);
        }


        return model;
    }

    @Override
    public DeviceUserModel findLatestByUserId(DeviceUserModel model) {

        return deviceUserRepository.findLatestByUserId(model);
    }

    @Override
    public void deleteDevice(Integer id) {
        //查询设备信息
        DeviceUserModel oldModel = this.findById(id);

        // 更型设备信息
        DeviceUserModel newModel = oldModel;
        newModel.setActivationCode(null);
        newModel.setActivationCodeUseStatus(null);
        newModel.setBindingStatus(false);
        newModel.setDataTransferStatus(false);
        newModel.setBindTime(null);

        // 取消设备与用户的关联
        deviceUserRepository.delete(oldModel);

        // 保存设备现存状态
        this.save(newModel);
    }

    @Override
    public DeviceUserModel findByDeviceNoAndUserId(String deviceNo, Integer userId) {
        DeviceUserModel model = new DeviceUserModel();

        model.setDeviceNo(deviceNo);
        model.setUserId(userId);

        return deviceUserRepository.findByDeviceNoAndUserId(model);
    }

    /**
     * 更新用户设备状态 内部调用
     */
    @Override
    public void updateUserDevice(Integer userId, String coding, Boolean serviceStatus) {

        // 设备编号获取设备
        DeviceUserModel model = new DeviceUserModel();
        model.setUserId(userId);
        model.setDeviceNo(coding);
        model = deviceUserRepository.findOne(model);
        BSUtil.notNull(model, "用户该设备不存在");

        // 更新
        DeviceUserModel deviceUserModel = new DeviceUserModel();
        deviceUserModel.setId(model.getId());
        deviceUserModel.setDataTransferStatus(serviceStatus);
        deviceUserRepository.update(deviceUserModel);
    }

    //TODO 用户设备数据异常的判定  暂时条件 2天内出现3次以上异常
    @Override
    public Boolean findDeviceStatus(DeviceUserModel model) {
        model = deviceUserRepository.findByDeviceNoAndUserId(model);

        DateTime now = new DateTime();

        Date startQueryTime = now.toDate();
        Date endQueryTime = now.minusDays(2).toDate();

        Map<String, Object> count = new HashMap<>();

        Boolean isAbnormal = null;

        if (model.getDeviceType().getName().equals("呼吸家远程监测呼吸仪")) {
            count.putAll(breathDataRepository.dataAbnormalCount(model.getUserId(), model.getId(), startQueryTime, endQueryTime));
        } else {
//            count.putAll(sphymomanometerDataRepository.dataAbnormalCount(model.getUserId(), model.getId(), startQueryTime, endQueryTime));
        }

        if (count == null) {
            isAbnormal = false;
        } else {
            if (((int) count.get("abnormal")) > 3) {
                isAbnormal = true;
            }else{
                isAbnormal = false;
            }
        }

        return isAbnormal;
    }


    /************************** 辅助方法 *******************************/
    /**
     * 根据用户服务的有效状态  设置 设备数据传输状态
     */
    private DeviceUserModel updateServerAndDataTransferInfo(DeviceUserModel model, Integer userServerStatus) {
        if (userServerStatus > 1) {
            model.setDataTransferStatus(false);
        } else {
            model.setDataTransferStatus(true);
        }

        return model;
    }

    /**
     * 生成随机六位激活码
     */
    private String getActivationNo() {
        return CommUtil.genRandomNum(6);
    }
}
