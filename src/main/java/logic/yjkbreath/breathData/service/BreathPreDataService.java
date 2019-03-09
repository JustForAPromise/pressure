package logic.yjkbreath.breathData.service;

import boat.web.service.BaseService;
import logic.yjkbreath.breathData.model.BreathPreDataModel;

/**
 * @Auther fanhanxi
 * @Date 2019/1/2
 * @Description:
 */
public interface BreathPreDataService extends BaseService<BreathPreDataModel> {

    /**
     * 保存接收的检测人呼吸指标预计值
     *
     * @Param [preDataModel]  检测人呼吸指标预计值
     * @return void
     */
    void saveData(BreathPreDataModel preDataModel);

    /**
     * 根据用户id与设备id 获取呼吸预计值
     * @Param [userId, deviceId]
     * @return
     */
    BreathPreDataModel findByUserIdAndDeviceId(Integer userId, Integer deviceId);
}
