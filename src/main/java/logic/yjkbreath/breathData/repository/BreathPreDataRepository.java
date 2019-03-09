package logic.yjkbreath.breathData.repository;

import boat.mybatis.lang.SimpleDeleteLangDriver;
import boat.mybatis.lang.SimpleInsertLangDriver;
import boat.mybatis.lang.SimpleSelectLangDriver;
import boat.mybatis.lang.SimpleUpdateLangDriver;
import boat.mybatis.repository.MybatisRepository;
import logic.yjkbreath.breathData.model.BreathPreDataModel;
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
public interface BreathPreDataRepository extends MybatisRepository<BreathPreDataModel> {

    @Override
    @Lang(SimpleInsertLangDriver.class)
    @Insert("[#{model}]")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int save(BreathPreDataModel model);

    @Override
    @Lang(SimpleUpdateLangDriver.class)
    @Update("[#{model}]")
    int update(BreathPreDataModel model);

    @Override
    @Lang(SimpleDeleteLangDriver.class)
    @Delete("[#{model}]")
    int delete(BreathPreDataModel model);

    @Override
    @Lang(SimpleSelectLangDriver.class)
    @Select("[#{model}]")
    BreathPreDataModel findById(BreathPreDataModel model);

    @Override
    @Lang(SimpleSelectLangDriver.class)
    @Select("[#{model}] LIMIT 1")
    BreathPreDataModel findOne(BreathPreDataModel model);

    @Override
    @Lang(SimpleSelectLangDriver.class)
    @Select("[#{model}] LIMIT #{start}, #{size}")
    List<BreathPreDataModel> findPage(BreathPreDataModel model);

    @Override
    @Lang(SimpleSelectLangDriver.class)
    @Select("SELECT COUNT(1) [#{model}]")
    long findTotal(BreathPreDataModel model);

    @Lang(SimpleSelectLangDriver.class)
    @Select("[#{model}]")
    BreathPreDataModel findByUserIdAndDeviceId(BreathPreDataModel model);
}
