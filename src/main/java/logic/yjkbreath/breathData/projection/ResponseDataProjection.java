package logic.yjkbreath.breathData.projection;

import io.swagger.annotations.ApiModelProperty;

/**
 * @Auther fanhanxi
 * @Date 2019/1/3
 * @Description:
 */
public interface ResponseDataProjection {

    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id", required = true)
    String getDetected_no();

    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名", required = true)
    String getDetected_name();

    /**
     * 设备编号
     */
    @ApiModelProperty(value = "设备编号", required = true)
    String getDevice_no();

    /**
     * 绑定情况(2:设备未登记;3:设备未绑定;4:设备已绑定),
     */
    String getBind_satus();

    /**
     * 处理情况(0：错误;1：已绑定 ;2:设备 没有绑定)
     */
    @ApiModelProperty(value = "处理情况(0：错误;1：已绑定 ;2:设备 没有绑定)", required = true)
    String getProcess_satus();

    /**
     * 性别（male 男 ;  female 女）
     */
    @ApiModelProperty(value = "性别（male 男 ;  female 女）", required = true)
    String getSex();

    /**
     * 出生日期(yyyy-MM-dd)
     */
    @ApiModelProperty(value = "出生日期(yyyy-MM-dd)", required = true)
    String getBirthdate();

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    Double getHeight();

    /**
     * 身高(cm)
     */
    @ApiModelProperty(value = "身高(cm)", required = true)
    Double getWeight();

    /**
     * 体重(kg)
     */
    @ApiModelProperty(value = "体重(kg)", required = true)
    String getMobile();

    /**
     * 检测人PEF指标预计值
     */
    @ApiModelProperty(value = "检测人PEF指标预计值")
    String getParam1();

    /**
     * 检测人FEV1指标预计值
     */
    @ApiModelProperty(value = "检测人FEV1指标预计值")
    String getParam2();

    /**
     * 检测人FVC指标预计值
     */
    @ApiModelProperty(value = "检测人FVC指标预计值")
    String getParam3();

    /**
     * 预留输出参数，没有定义
     */
    @ApiModelProperty(value = "预留输出参数，没有定义")
    String getParam4();

    /**
     * 检测人fef25_75指标预计值
     */
    @ApiModelProperty(value = "检测人fef25_75指标预计值")
    Double getPrefef25_75();

    /**
     * 检测人mef75指标预计值
     */
    @ApiModelProperty(value = "检测人mef75指标预计值")
    Double getPremef75();

    /**
     * 检测人mef50指标预计值
     */
    @ApiModelProperty(value = "检测人mef50指标预计值")
    Double getPremef50();

    /**
     * 检测人mef25指标预计值
     */
    @ApiModelProperty(value = "检测人mef25指标预计值")
    Double getPremef25();
}
