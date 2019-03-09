package logic.yjkbreath.breathData.service.impl;

import logic.yjkbreath.breathData.model.BreathPreDataModel;
import logic.yjkbreath.breathData.repository.BreathPreDataRepository;
import logic.yjkbreath.breathData.service.BreathPreDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther fanhanxi
 * @Date 2019/1/2
 * @Description:
 */
@Service
public class BreathPreDataServiceImpl implements BreathPreDataService {

    @Autowired
    private BreathPreDataRepository breathPreDataRepository;

    @Override
    public BreathPreDataModel save(BreathPreDataModel model){
        breathPreDataRepository.save(model);

        return this.findById(model.getId());
    };

    @Override
    public BreathPreDataModel update(BreathPreDataModel model){
        breathPreDataRepository.update(model);

        return this.findById(model.getId());
    };

    @Override
    public void delete(int id){
        BreathPreDataModel model = this.findById(id);

        breathPreDataRepository.delete(model);
    };

    @Override
    public BreathPreDataModel findById(int id){
        BreathPreDataModel model = new BreathPreDataModel();
        model.setId(id);
        return breathPreDataRepository.findById(model);
    };

    @Override
    public void saveData(BreathPreDataModel preDataModel) {
        BreathPreDataModel oldModel = new BreathPreDataModel();
        oldModel.setUserId(preDataModel.getUserId());
        oldModel.setDeviceId(preDataModel.getDeviceId());

        oldModel = breathPreDataRepository.findOne(oldModel);

        if (oldModel == null){
            this.save(preDataModel);
        }else{
            preDataModel.setId(oldModel.getId());
            this.update(preDataModel);
        }
    }

    @Override
    public BreathPreDataModel findByUserIdAndDeviceId(Integer userId, Integer deviceId) {
        BreathPreDataModel model = new BreathPreDataModel();
        model.setDeviceId(deviceId);
        model.setUserId(userId);

        model = breathPreDataRepository.findByUserIdAndDeviceId(model);

        return model;
    }
}
