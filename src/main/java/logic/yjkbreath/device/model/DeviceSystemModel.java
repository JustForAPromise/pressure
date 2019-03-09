package logic.yjkbreath.device.model;

import boat.data.model.Paging;
import boat.mybatis.annotation.Column;
import boat.mybatis.annotation.CreationTimestamp;
import boat.mybatis.annotation.Table;
import boat.mybatis.annotation.UpdateTimestamp;
import logic.yjkbreath.device.contants.DeviceTypeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @Auther fanhanxi
 * @Date 2019/1/10
 * @Description:
 */
@Table(name = "device_system")
@EqualsAndHashCode(callSuper = false)
@Data
public class DeviceSystemModel extends Paging {

    @Column(id = true)
    private Integer id;

    /**
     * 设备类型
     * 24小时动态血压仪
     * 普通脉搏波远程监测血压仪
     * 呼吸家远程监测呼吸仪
     */
    @Column(name = "device_type")
    private DeviceTypeEnum deviceType;

    /**
     * 设备名称
     */
    @Column(name = "device_name")
    private String deviceName;

    /**
     * 设备MAC码
     */
    @Column(name = "device_no")
    private String deviceNo;

    /**
     * 设备图片
     */
    @Column(name = "device_image")
    private String deviceImage;

    /**
     * api接口
     */
    @Column(name = "api_connect")
    private String apiConnect;

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
}
