package logic.yjkbreath.device.projection;

import boat.web.jackson.serializer.StaticResLocateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModelProperty;
import logic.yjkbreath.device.contants.DeviceTypeEnum;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

/**
 * @Auther fanhanxi
 * @Date 2019/1/10
 * @Description:
 */
public interface DeviceSystemProjection {

    @ApiModelProperty(value = "id", required = true)
    Integer getId();

    /**
     * 设备类型
     * 1 24小时动态血压仪
     * 2 普通脉搏波远程监测血压仪
     * 3 呼吸家远程监测呼吸仪
     */
    DeviceTypeEnum getDeviceType();

    /**
     * 设备名称
     */
    String getDeviceName();

    /**
     * 设备MAC码
     */
    String getDeviceNo();

    /**
     * 设备图片
     */
    String getDeviceImage();

    @Value("#{target.deviceImage}")
    @JsonSerialize(using = StaticResLocateSerializer.class)
    @ApiModelProperty(value = "设备图片Full")
    String getDeviceImageFull();

    /**
     * api接口
     */
    String getApiConnect();

    /**
     * 创建时间
     */
    Date getCreateTime();

    /**
     * 更新时间
     */
    Date getUpdateTime();
}
