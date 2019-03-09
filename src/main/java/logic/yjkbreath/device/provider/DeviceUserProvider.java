package logic.yjkbreath.device.provider;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import logic.yjkbreath.device.api.provider.ProviderDeviceUserApiPatch;
import logic.yjkbreath.device.model.DeviceUserModel;
import logic.yjkbreath.device.service.DeviceUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import static boat.commons.orika.Orika.map;

/**
 * @Auther fanhanxi
 * @Date 2019/1/11
 * @Description:
 */
@Api(tags = "设备-用户设备provider-API")
@RequestMapping("/provider/device:userDevice")
@RestController
public class DeviceUserProvider {

    @Autowired
    private DeviceUserService deviceUserService;

    @ApiOperation(httpMethod = "PATCH", value = "更新设备状态", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "coding", value = "设备编码", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "serviceStatus", value = "服务状态(0 关闭,1 开启)", dataType = "boolean", paramType = "query", required = true)
    })
    @PatchMapping(value = "/updateUserDevice", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void updateUserDevice(@RequestParam(value = "userId") Integer userId,
                                 @RequestParam(value = "coding") String coding,
                                 @RequestParam(value = "serviceStatus") Boolean serviceStatus) {

        deviceUserService.updateUserDevice(userId, coding, serviceStatus);
    }

    @ApiOperation(httpMethod = "GET", value = "查询设备数据是否异常", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value ="用户id", dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "deviceNo", value ="设备编码", dataType = "String", paramType = "query", required = true)
    })
    @PatchMapping(value = "/updateUserDevice", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Boolean findDeviceStatus(@Validated @ApiIgnore ProviderDeviceUserApiPatch apiPatch) {
        return deviceUserService.findDeviceStatus(map(apiPatch, DeviceUserModel.class));
    }
}
