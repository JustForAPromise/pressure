package logic.yjkbreath.device.repository;

import boat.mybatis.lang.SimpleDeleteLangDriver;
import boat.mybatis.lang.SimpleInsertLangDriver;
import boat.mybatis.lang.SimpleSelectLangDriver;
import boat.mybatis.lang.SimpleUpdateLangDriver;
import boat.mybatis.repository.MybatisRepository;
import logic.yjkbreath.device.model.DeviceUserModel;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Auther fanhanxi
 * @Date 2019/1/2
 * @Description:
 */
@Mapper
@Component
public interface DeviceUserRepository extends MybatisRepository<DeviceUserModel> {

    @Override
    @Lang(SimpleInsertLangDriver.class)
    @Insert("[#{model}]")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int save(DeviceUserModel model);

    @Override
    @Lang(SimpleUpdateLangDriver.class)
    @Update("[#{model}]")
    int update(DeviceUserModel model);

    @Override
    @Lang(SimpleDeleteLangDriver.class)
    @Delete("[#{model}]")
    int delete(DeviceUserModel model);

    @Override
    @Lang(SimpleSelectLangDriver.class)
    @Select("[#{model}]")
    DeviceUserModel findById(DeviceUserModel model);

    @Override
    @Lang(SimpleSelectLangDriver.class)
    @Select("[#{model}] LIMIT 1")
    DeviceUserModel findOne(DeviceUserModel model);

    @Override
    @Lang(SimpleSelectLangDriver.class)
    @Select("[#{model}]")
    List<DeviceUserModel> findAll(DeviceUserModel model);

    @Override
    @Lang(SimpleSelectLangDriver.class)
    @Select("[#{model}] LIMIT #{start}, #{size}")
    List<DeviceUserModel> findPage(DeviceUserModel model);

    @Override
    @Lang(SimpleSelectLangDriver.class)
    @Select("SELECT COUNT(1) [#{model}]")
    long findTotal(DeviceUserModel model);

    @Lang(SimpleSelectLangDriver.class)
    @Select("[#{model}] ORDER BY binding_status DESC LIMIT 1")
    DeviceUserModel findLatestByUserId(DeviceUserModel model);

    @Lang(SimpleSelectLangDriver.class)
    @Select("[#{model}]")
    DeviceUserModel findByDeviceNoAndUserId(DeviceUserModel model);
}
