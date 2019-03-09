package logic.yjkbreath.breathData.projection;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Auther fanhanxi
 * @Date 2019/1/3
 * @Description:
 */
public interface BreathDataProjection {

    @ApiModelProperty(value = "Id", required = true)
    Integer getId();

    /**
     * PEF值 最高呼吸流速
     */
    @ApiModelProperty(value = "PEF值 最高呼吸流速", required = true)
    BigDecimal getPefValue();

    /**
     * FEV1值 一秒呼气最高量
     */
    @ApiModelProperty(value = "FEV1值 一秒呼气最高量", required = true)
    BigDecimal getFev1Value();

    /**
     * FVC值 用力肺活量
     */
    @ApiModelProperty(value = "FVC值 用力肺活量", required = true)
    BigDecimal getFvcValue();

    /**
     * 呼气中断流速
     */
    @ApiModelProperty(value = "呼气中断流速")
    BigDecimal getFef25_75Value();

    /**
     * 呼气75%肺活量的瞬间流量
     */
    @ApiModelProperty(value = "呼气75%肺活量的瞬间流量")
    BigDecimal getMef75Value();

    /**
     * 呼气50%肺活量的瞬间流量
     */
    @ApiModelProperty(value = "呼气50%肺活量的瞬间流量")
    BigDecimal getMef50Value();

    /**
     * 呼气25%肺活量的瞬间流量
     */
    @ApiModelProperty(value = "呼气25%肺活量的瞬间流量")
    BigDecimal getMef25Value();

    /**
     * 数据测量状态
     */
    @ApiModelProperty(value = "测量数据状态 true异常 false 正常")
    Boolean getDetectedStatus();

    /**
     * PEF% 值
     */
    @ApiModelProperty(value = "PEF% 值", required = true)
    Integer getPefRate();

    /**
     * FEV1% 值
     */
    @ApiModelProperty(value = "FEV1% 值", required = true)
    Integer getFev1Rate();

    /**
     * PEV1/FVC
     */
    @ApiModelProperty(value = "PEV1/FVC 值", required = true)
    Integer getFev1Fvc();

    /**
     * PEF 数据状态 0正常 1警告 2危险
     */
    @ApiModelProperty(value = "PEF 数据状态 哮喘  0正常 1警告 2危险", required = true)
    Integer getPefStatus();

    /**
     * Fev1 数据状态 0正常 1轻度  2中度 3重度
     */
    @ApiModelProperty(value = "Fev1 数据状态 阻塞指数 0正常 1轻度  2中度 3重度", required = true)
    Integer getFev1Status();

    /**
     * FEV1 / FVC 数据状态 0正常 1轻度 2中度 3重度 4极重度
     */
    @ApiModelProperty(value = "FEV1 / FVC 数据状态 慢性阻塞性肺病 0正常 1轻度 2中度 3重度 4极重度", required = true)
    Integer getFev1FvcStatus();

    /**
     * PEF日变异率
     */
    @ApiModelProperty(value = "PEF日变异率", required = true)
    Integer getPefDayMutationRate();

    /**
     * 检测结果提示信息
     */
    String getTip();

    /**
     * 图表数据
     */
    @ApiModelProperty(value = "图表数据")
    String getChartData();

    /**
     * 测量时间
     */
    @ApiModelProperty(value = "测量时间", required = true)
    Date getDetectedTime();

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    Date getCreateTime();

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
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
