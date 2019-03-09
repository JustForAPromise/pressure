package logic.yjkbreath.device.api.provider;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * @Auther fanhanxi
 * @Date 2019/1/11
 * @Description:
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProviderDeviceUserApiPatch {

    @NotNull(message = "{ProviderDeviceUserApiPatch.userId.none}")
    @ApiModelProperty(value = "用户id", required = true)
    private Integer userId;

    @NotBlank(message = "{ProviderDeviceUserApiPatch.coding.none}")
    @ApiModelProperty(value = "设备编码")
    private String deviceNo;

    @ApiModelProperty(value = "服务状态")
    private Integer serviceStatus;
}
