package logic.yjkbreath.device.service;

import boat.web.service.BaseService;
import logic.yjkbreath.breathData.model.ReceiveDataModel;
import logic.yjkbreath.breathData.model.ResponseDataModel;
import logic.yjkbreath.device.model.DeviceUserModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @Auther fanhanxi
 * @Date 2019/1/2
 * @Description:
 */
public interface DeviceUserService extends BaseService<DeviceUserModel> {
    /**
     * 按设备编码查找
     *
     * @Param [deviceNo] 设备编码
     * @return logic.device.model.DeviceModel
     */
    DeviceUserModel findByDeviceNo(String deviceNo);

    /**
     * 开机连接设备  呼吸仪
     *
     * @Param [receiveDataModel]
     * @return logic.breathData.model.ResponseDataModel
     */
    ResponseDataModel connectDevice(ReceiveDataModel receiveDataModel);

    /**
     * 分页
     *
     * @Param [model, pageable]
     * @return org.springframework.data.domain.Page<logic.device.model.DeviceModel>
     */
    Page<DeviceUserModel> findPage(DeviceUserModel model, Pageable pageable);

    /**
     * 用户绑定设备  登记设备信息
     *
     * @Param [model, serverId]
     * @return logic.device.model.DeviceModel
     */
    DeviceUserModel saveDevice(DeviceUserModel model, Integer userServerId);

    /**
     * 查找用户最新绑定的设备
     *
     * @Param [userId]
     * @return logic.device.model.DeviceModel
     */
    DeviceUserModel findLatestByUserId(DeviceUserModel model);

    /**
     * 用户删除设备
     * @Param [id]
     * @return
     */
    void deleteDevice(Integer id);

    /**
     * 更新用户设备状态，内部调用
     * @Param [apiPatch]
     * @return
     */
    void updateUserDevice(Integer userId, String coding, Boolean serviceStatus);

    /**
     * 根据设备编码和用户id查找信息
     * @Param [deviceNo, userId]
     * @return
     */
    DeviceUserModel findByDeviceNoAndUserId(String deviceNo, Integer userId);

    /**
     * 查询设备数据是否异常
     *
     * @Param [apiPatch]
     * @return
     */
    Boolean findDeviceStatus(DeviceUserModel model);
}
