package logic.yjkbreath.device.service;

import boat.web.service.BaseService;
import logic.yjkbreath.device.contants.DeviceTypeEnum;
import logic.yjkbreath.device.model.DeviceSystemModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @Auther fanhanxi
 * @Date 2019/1/10
 * @Description:
 */

public interface DeviceSystemService extends BaseService<DeviceSystemModel> {
    /**
     *  分页
     * @Param [model, pageable]
     * @return org.springframework.data.domain.Page<logic.device.model.DeviceModel>
     */
    Page<DeviceSystemModel > findPage(DeviceSystemModel model, Pageable pageable);

    /**
     * 保存设备
     * @Param [model]
     * @return logic.device.model.DeviceSystemModel
     */
    DeviceSystemModel saveDevice(DeviceSystemModel model);

    /**
     * 根据设备mac码查找设备
     * @Param [deviceNo]
     * @return logic.device.model.DeviceSystemModel
     */
    DeviceSystemModel findByDeviceNo(String deviceNo);

    /**
     * 根据MAC码和类型查找设备
     * @Param [deviceNo, deviceType]
     * @return
     */
    DeviceSystemModel findByDeviceNoAndType(String deviceNo, DeviceTypeEnum deviceType);
}
