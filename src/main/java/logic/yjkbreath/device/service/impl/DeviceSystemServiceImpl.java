package logic.yjkbreath.device.service.impl;

import boat.web.util.BSUtil;
import logic.yjkbreath.device.contants.DeviceTypeEnum;
import logic.yjkbreath.device.model.DeviceSystemModel;
import logic.yjkbreath.device.repository.DeviceSystemRepository;
import logic.yjkbreath.device.service.DeviceSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther fanhanxi
 * @Date 2019/1/10
 * @Description:
 */
@Service
public class DeviceSystemServiceImpl implements DeviceSystemService {

    @Autowired
    private DeviceSystemRepository deviceSystemRepository;

    @Override
    public DeviceSystemModel  save(DeviceSystemModel  model) {
        deviceSystemRepository.save(model);

        return this.findById(model.getId());
    }

    @Override
    public DeviceSystemModel  update(DeviceSystemModel  model) {
        BSUtil.notNull(this.findById(model.getId()), "该设备不存在");

        deviceSystemRepository.update(model);

        return this.findById(model.getId());
    }

    @Override
    public void delete(int id) {
        DeviceSystemModel  model = this.findById(id);
        BSUtil.notNull(model, "该设备不存在");

        deviceSystemRepository.delete(model);
    }

    ;

    @Override
    public DeviceSystemModel  findById(int id) {
        DeviceSystemModel  model = new DeviceSystemModel ();
        model.setId(id);

        return deviceSystemRepository.findById(model);
    }

    @Override
    public Page<DeviceSystemModel > findPage(DeviceSystemModel  model, Pageable pageable) {
        model.pageable(pageable);

        List<DeviceSystemModel > resultModel = deviceSystemRepository.findPage(model);

        long total = deviceSystemRepository.findTotal(model);

        return new PageImpl<>(resultModel, pageable, total);
    }

    @Override
    public DeviceSystemModel saveDevice(DeviceSystemModel model) {
        DeviceSystemModel deviceSystemModel = new DeviceSystemModel();
        deviceSystemModel.setDeviceNo(model.getDeviceNo());
        deviceSystemModel.setDeviceType(model.getDeviceType());

        deviceSystemModel = deviceSystemRepository.findByDeviceNo(deviceSystemModel);

        BSUtil.isNull(deviceSystemModel, "该设备mac已存在");

        return  this.save(model);
    }

    @Override
    public DeviceSystemModel findByDeviceNo(String deviceNo) {
        DeviceSystemModel model = new DeviceSystemModel();
        model.setDeviceNo(deviceNo);

        model = deviceSystemRepository.findByDeviceNo(model);
        BSUtil.notNull(model,"该设备未登记！");

        return model;
    }

    public DeviceSystemModel findByDeviceNoAndType(String deviceNo, DeviceTypeEnum deviceType) {
        DeviceSystemModel model = new DeviceSystemModel();
        model.setDeviceNo(deviceNo);
        model.setDeviceType(deviceType);

        model = deviceSystemRepository.findByDeviceNoAndType(model);
        BSUtil.notNull(model,"该设备未登记！");

        return model;
    }
}
