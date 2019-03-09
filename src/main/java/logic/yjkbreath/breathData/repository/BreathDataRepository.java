package logic.yjkbreath.breathData.repository;

import boat.mybatis.lang.SimpleDeleteLangDriver;
import boat.mybatis.lang.SimpleInsertLangDriver;
import boat.mybatis.lang.SimpleSelectLangDriver;
import boat.mybatis.lang.SimpleUpdateLangDriver;
import boat.mybatis.repository.MybatisRepository;
import logic.yjkbreath.breathData.model.BreathDataModel;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Auther fanhanxi
 * @Date 2019/1/2
 * @Description:
 */
@Mapper
@Component
public interface BreathDataRepository extends MybatisRepository<BreathDataModel> {

    @Override
    @Lang(SimpleInsertLangDriver.class)
    @Insert("[#{model}]")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int save(BreathDataModel model);

    @Override
    @Lang(SimpleUpdateLangDriver.class)
    @Update("[#{model}]")
    int update(BreathDataModel model);

    @Override
    @Lang(SimpleDeleteLangDriver.class)
    @Delete("[#{model}]")
    int delete(BreathDataModel model);

    @Override
    @Lang(SimpleSelectLangDriver.class)
    @Select("[#{model}]")
    BreathDataModel findById(BreathDataModel model);

    @Override
    @Lang(SimpleSelectLangDriver.class)
    @Select("[#{model}] LIMIT 1")
    BreathDataModel findOne(BreathDataModel model);

    @Override
    @Lang(SimpleSelectLangDriver.class)
    @Select("[#{model}] LIMIT #{start}, #{size}")
    List<BreathDataModel> findPage(BreathDataModel model);

    @Override
    @Lang(SimpleSelectLangDriver.class)
    @Select("SELECT COUNT(1) [#{model}]")
    long findTotal(BreathDataModel model);

    /**
     *  返回PEF 当天的最大值 与 最小值
     */
    @ResultType(Map.class)
    @Select("SELECT max(pef_value) AS 'max', min(pef_value) AS 'min' FROM breath_device_data WHERE to_days(detected_time) = to_days(now());")
    Map<String, Object> findMaxAndMin();

    /**
     * 返回时间段内 数据正常的次数
     */
    @Select("SELECT count(detected_status) AS 'normal' " +
            "FROM breath_device_data " +
            "WHERE detected_status = 0 AND user_id = #{userId} AND device_id = #{deviceId} AND detected_time >= #{start} AND detected_time <= #{end};")
    Map<? extends String,?> dataNormalCount(@Param("userId") Integer userId, @Param("deviceId") Integer deviceId, @Param("start") Date startQueryTime, @Param("end") Date endQueryTime);

    /**
     * 返回时间段内 数据异常的次数
     */
    @Select("SELECT count(detected_status) AS 'abnormal' " +
            "FROM breath_device_data " +
            "WHERE detected_status = 1 AND user_id = #{userId} AND device_id = #{deviceId} AND detected_time >= #{start} AND detected_time <= #{end};")
    Map<? extends String,?> dataAbnormalCount(@Param("userId") Integer userId, @Param("deviceId") Integer deviceId, @Param("start") Date startQueryTime, @Param("end") Date endQueryTime);
}
