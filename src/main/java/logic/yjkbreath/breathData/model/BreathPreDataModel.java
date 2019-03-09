package logic.yjkbreath.breathData.model;

import boat.data.model.Paging;
import boat.mybatis.annotation.Column;
import boat.mybatis.annotation.CreationTimestamp;
import boat.mybatis.annotation.Table;
import boat.mybatis.annotation.UpdateTimestamp;
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
@Table(name = "breath_user_predata")
@EqualsAndHashCode(callSuper = false)
public class BreathPreDataModel extends Paging {

    @Column(id = true)
    private Integer id;

    /**
     * 检测人 PEF值预计值
     */
    @Column(name = "prepef_value")
    private BigDecimal prePefValue;

    /**
     * 检测人 FEV1值预计值
     */
    @Column(name = "prefev1_value")
    private BigDecimal preFev1Value;

    /**
     * 检测人 FVC值预计值
     */
    @Column(name = "prefvc_value")
    private BigDecimal preFvcValue;

    /**
     * 检测人 fef25_75指标预计值
     */
    @Column(name = "prefef25_75")
    private BigDecimal preFef25_75;

    /**
     * 检测人 mef75指标预计值  premef75
     */
    @Column(name = "premef75")
    private BigDecimal preMef75;

    /**
     * 检测人 mef50指标预计值 premef50
     */
    @Column(name = "premef50")
    private BigDecimal preMef50;

    /**
     * 检测人 mef25指标预计值 premef25
     */
    @Column(name = "premef25")
    private BigDecimal preMef25;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @CreationTimestamp
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    @UpdateTimestamp
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
}
