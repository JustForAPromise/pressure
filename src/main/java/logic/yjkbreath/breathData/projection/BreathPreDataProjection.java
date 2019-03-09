package logic.yjkbreath.breathData.projection;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Auther fanhanxi
 * @Date 2019/1/3
 * @Description:
 */
public interface BreathPreDataProjection {

    @ApiModelProperty(value = "id", required = true)
    Integer getId();

    /**
     * 检测人 PEF值预计值
     */
    @ApiModelProperty(value = "检测人 PEF值预计值", required = true)
    BigDecimal getPrePefValue();

    /**
     * 检测人 FEV1值预计值
     */
    @ApiModelProperty(value = "检测人 FEV1值预计值", required = true)
    BigDecimal getPreFev1Value();

    /**
     * 检测人 FVC值预计值
     */
    @ApiModelProperty(value = "检测人 FVC值预计值", required = true)
    BigDecimal getPreFvcValue();

    /**
     * 检测人 fef25_75指标预计值
     */
    @ApiModelProperty(value = "检测人 fef25_75指标预计值", required = true)
    BigDecimal getPreFef25_75();

    /**
     * 检测人 mef75指标预计值  premef75
     */
    @ApiModelProperty(value = "检测人 mef75指标预计值", required = true)
    BigDecimal getPreMef75();

    /**
     * 检测人 mef50指标预计值 premef50
     */
    @ApiModelProperty(value = "检测人 mef50指标预计值", required = true)
    BigDecimal getPreMef50();

    /**
     * 检测人 mef25指标预计值 premef25
     */
    @ApiModelProperty(value = "检测人 mef25指标预计值", required = true)
    BigDecimal getPreMef25();

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", required = true)
    Date getCreateTime();

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间", required = true)
    Date getUpdateTime();

    /**
     * 用户Id
     */
    @ApiModelProperty(value = "用户Id", required = true)
    Integer getUserId();

    /**
     * 设备Id
     */
    @ApiModelProperty(value = "设备Id", required = true)
    Integer getDeviceId();
}