package logic.yjkbreath.breathData.service;

import boat.web.service.BaseService;
import logic.yjkbreath.breathData.model.BreathDataModel;
import logic.yjkbreath.breathData.model.ReceiveDataModel;
import logic.yjkbreath.breathData.model.ResponseDataModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.Map;

/**
 * @Auther fanhanxi
 * @Date 2019/1/2
 * @Description:
 */
public interface BreathDataService extends BaseService<BreathDataModel> {
    /**
     * 分页
     *
     * @Param [model, pageable]
     * @return org.springframework.data.domain.Page<logic.breath.model.BreathDataModel>
     */
    Page<BreathDataModel> findPage(BreathDataModel model, Pageable pageable);

    /**
     * 保存接收数据 返回应答数据
     *
     * @Param [receiveDataModel]
     * @return logic.breathData.model.ResponseDataModel
     */
    ResponseDataModel saveData(ReceiveDataModel receiveDataModel);

    /**
     * 统计数据的异常情况 正常次数，异常次数
     * @Param [userId, deviceId, startQueryTime, endQueryTime]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    Map<String, Object> dataCountResult(Integer userId, Integer deviceId, Date startQueryTime, Date endQueryTime);
}
