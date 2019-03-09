package logic.yjkbreath.device.repository;

import boat.mybatis.lang.SimpleDeleteLangDriver;
import boat.mybatis.lang.SimpleInsertLangDriver;
import boat.mybatis.lang.SimpleSelectLangDriver;
import boat.mybatis.lang.SimpleUpdateLangDriver;
import boat.mybatis.repository.MybatisRepository;
import logic.yjkbreath.device.model.DeviceSystemModel;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Auther fanhanxi
 * @Date 2019/1/10
 * @Description:
 */
@Mapper
@Component
public interface DeviceSystemRepository extends MybatisRepository<DeviceSystemModel> {

    @Override
    @Lang(SimpleInsertLangDriver.class)
    @Insert("[#{model}]")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int save(DeviceSystemModel model);

    @Override
    @Lang(SimpleUpdateLangDriver.class)
    @Update("[#{model}]")
    int update(DeviceSystemModel model);

    @Override
    @Lang(SimpleDeleteLangDriver.class)
    @Delete("[#{model}]")
    int delete(DeviceSystemModel model);

    @Override
    @Lang(SimpleSelectLangDriver.class)
    @Select("[#{model}]")
    DeviceSystemModel  findById(DeviceSystemModel model);

    @Override
    @Lang(SimpleSelectLangDriver.class)
    @Select("[#{model}] LIMIT 1")
    DeviceSystemModel  findOne(DeviceSystemModel model);

    @Override
    @Lang(SimpleSelectLangDriver.class)
    @Select("[#{model}] ORDER BY create_time DESC LIMIT #{start}, #{size}")
    List<DeviceSystemModel > findPage(DeviceSystemModel model);

    @Override
    @Lang(SimpleSelectLangDriver.class)
    @Select("SELECT COUNT(1) [#{model}]")
    long findTotal(DeviceSystemModel model);

    @Lang(SimpleSelectLangDriver.class)
    @Select("[#{model}]")
    DeviceSystemModel findByDeviceNo(DeviceSystemModel deviceSystemModel);

    @Lang(SimpleSelectLangDriver.class)
    @Select("[#{model}]")
    DeviceSystemModel findByDeviceNoAndType(DeviceSystemModel model);
}
