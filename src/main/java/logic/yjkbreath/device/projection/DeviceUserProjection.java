package logic.yjkbreath.device.projection;

import io.swagger.annotations.ApiModelProperty;
import logic.yjkbreath.device.contants.DeviceTypeEnum;

import java.util.Date;

/**
 * @Auther fanhanxi
 * @Date 2019/1/5
 * @Description:
 */
public interface DeviceUserProjection {

    @ApiModelProperty(value = "id", required = true)
    Integer getId();

    /**
     * 设备编码
     */
    @ApiModelProperty(value = "设备编码", required = true)
    String getDeviceNo();

    /**
     * 设备公司编码/设备对应渠道商编号
     */
    @ApiModelProperty(value = "设备公司编码/设备对应渠道商编号")
    String getDeviceCompany();

    /**
     * 设备类型
     *  24小时动态血压仪
     *  普通脉搏波远程监测血压仪
     *  呼吸家远程监测呼吸仪
     */
    DeviceTypeEnum getDeviceType();

    /**
     * SIM卡号码
     */
    @ApiModelProperty(value = "SIM卡号码")
    String getDeviceSim();

    /**
     * API接口
     */
    @ApiModelProperty(value = "API接口")
    String getConnectApi();

    /**
     * 绑定状态
     *  0 未绑定
     *  1 已绑定
     */
    @ApiModelProperty(value = "绑定状态  false 未绑定 true 已绑定", required = true)
    Boolean getBindingStatus();

    /**
     * 数据传输状态
     *  0 关闭
     *  1 开启
     */
    @ApiModelProperty(value = "数据传输状态  false 关闭  true 开启")
    Boolean getDataTransferStatus();

    /**
     * 协议版本号
     */
    @ApiModelProperty(value = "协议版本号")
    String getCodeVersion();

    /**
     * 设备使用说明
     */
    @ApiModelProperty(value = "设备使用说明")
    String getInstruction();

    /**
     * 绑定时间
     */
    @ApiModelProperty(value = "绑定时间")
    Date getBindTime();


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
     * 用户服务id
     */
    Integer getUserServerId();

    /**
     * 绑定用户id
     */
    @ApiModelProperty(value = "绑定用户id")
    Integer getUserId();

    /**
     * 关联系统设备 内置信息
     */
    @ApiModelProperty(value = "系统设备信息")
    DeviceSystemProjection getDeviceSystem();
}
