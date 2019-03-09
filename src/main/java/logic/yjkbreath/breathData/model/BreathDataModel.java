package logic.yjkbreath.breathData.model;

import boat.data.model.Paging;
import boat.mybatis.annotation.Column;
import boat.mybatis.annotation.QueryGte;
import boat.mybatis.annotation.QueryLte;
import boat.mybatis.annotation.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Auther fanhanxi
 * @Date 2019/1/2
 * @Description:
 */
@Data
@Table(name = "breath_device_data")
@EqualsAndHashCode(callSuper = false)
public class BreathDataModel extends Paging {

    @Column(id = true)
    private Integer id;

    /**
     * PEF值 最高呼吸流速
     */
    @Column(name = "pef_value")
    private BigDecimal pefValue;

    /**
     * FEV1值 一秒呼气最高量
     */
    @Column(name = "fev1_value")
    private BigDecimal fev1Value;

    /**
     * FVC值 用力肺活量
     */
    @Column(name = "fvc_value")
    private BigDecimal fvcValue;

    /**
     * 呼气中断流速
     */
    @Column(name = "fef25_75_value")
    private BigDecimal fef25_75Value;

    /**
     * 呼气75%肺活量的瞬间流量
     */
    @Column(name = "mef75_value")
    private BigDecimal mef75Value;

    /**
     * 呼气50%肺活量的瞬间流量
     */
    @Column(name = "mef50_value")
    private BigDecimal mef50Value;

    /**
     * 呼气25%肺活量的瞬间流量
     */
    @Column(name = "mef25_value")
    private BigDecimal mef25Value;

    /**
     * 测量数据状态 true 异常 false 正常
     */
    @Column(name = "detected_status")
    private Boolean detectedStatus;

    /**
     * PEF% 值
     */
    @Column(name = "pef_rate")
    private Integer pefRate;

    /**
     * PEF 数据状态 哮喘 0正常 1警告 2危险
     */
    @Column(name = "pef_status")
    private Integer pefStatus;

    /**
     * FEV1% 值
     */
    @Column(name = "fev1_rate")
    private Integer fev1Rate;

    /**
     * Fev1 数据状态 0正常 1轻度  2中度 3重度
     */
    @Column(name = "fev1_status")
    private Integer fev1Status;

    /**
     * PEV1 / FVC
     */
    @Column(name = "fev1_fvc")
    private Integer fev1Fvc;

    /**
     * FEV1 / FVC 数据状态 0正常 1轻度 2中度 3重度 4极重度
     */
    @Column(name = "fev1_fvc_status")
    private Integer fev1FvcStatus;

    /**
     * PEF日变异率
     */
    @Column(name = "pef_day_mutation_rate")
    private Integer pefDayMutationRate;

    /**
     * 图表数据
     */
    @Column(name = "chart_data")
    private String chartData;

    /**
     * 检测结果提示信息
     */
    @Column(name = "tip")
    private String tip;

    /**
     * 测量时间
     */
    @Column(name = "detected_time")
    private Date detectedTime;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 用户Id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 设备Id
     */
    @Column(name = "device_id")
    private Integer deviceId;


    /******************  辅助查询 **********************/
    /**
     * 时间查询起
     */
    @QueryGte(column = "detected_time")
    private Date startQueryTime;

    /**
     * 时间查询止
     */
    @QueryLte(column = "detected_time")
    private Date endQueryTime;
}
