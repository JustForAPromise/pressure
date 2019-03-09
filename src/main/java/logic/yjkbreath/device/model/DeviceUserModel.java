package logic.yjkbreath.device.model;

import boat.data.model.Paging;
import boat.mybatis.annotation.*;
import logic.yjkbreath.device.contants.DeviceTypeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

/**
 * @Auther fanhanxi
 * @Date 2019/1/2
 * @Description:
 */
@Table(name = "device_user")
@EqualsAndHashCode(callSuper = false)
@Data
public class DeviceUserModel extends Paging {

    @Column(id = true)
    private Integer id;

    /**
     * 设备编码
     */
    @Column(name = "device_no")
    private String deviceNo;

    /**
     * 设备公司编码/设备对应渠道商编号
     */
    @Column(name = "device_company")
    private String deviceCompany;

    /**
     * 设备类型
     * 24小时动态血压仪
     * 普通脉搏波远程监测血压仪
     * 呼吸家远程监测呼吸仪
     */
    @Column(name = "device_type")
    private DeviceTypeEnum deviceType;

    /**
     * SIM卡号码
     */
    @Column(name = "device_sim")
    private String deviceSim;

    /**
     * API接口
     */
    @Column(name = "connect_api")
    private String connectApi;

    /**
     * 激活码
     */
    @Column(name = "activation_code")
    private String activationCode;

    /**
     * 激活码使用状态  0未使用 1已使用
     */
    @Column(name = "activation_code_use_status")
    private Boolean activationCodeUseStatus;

    /**
     * 绑定状态
     *  0 未绑定
     *  1 已绑定
     */
    @Column(name = "binding_status")
    private Boolean bindingStatus;

    /**
     * 数据传输状态
     *  0 关闭
     *  1 开启
     */
    @Column(name = "data_transfer_status")
    private Boolean dataTransferStatus;

    /**
     * 协议版本号
     */
    @Column(name = "code_version")
    private String codeVersion;

    /**
     * 设备使用说明
     */
    @Column(name = "instruction")
    private String instruction;

    /**
     * 绑定时间
     */
    @Column(name = "bind_time")
    private Date bindTime;

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
     * 绑定用户id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 用户服务id
     */
    @Column(name = "user_server_id")
    private Integer userServerId;

    /*************************  关联信息 **************************************/
    /**
     * 关联系统设备 内置信息
     */
    private DeviceSystemModel deviceSystem;

    /*************************  辅助参数 ***********************************/
    /**
     * 设备服务状态  0已过期  1使用中
     */
    private Boolean ServiceStatus;

    /************************  辅助查询 ***************************************/
    /**
     * IN 查询  设备编码
     */
    @QueryIn(column = "device_no")
    private List<String> inDeviceNos;
}
