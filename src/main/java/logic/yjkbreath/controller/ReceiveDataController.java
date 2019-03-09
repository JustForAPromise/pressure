package logic.yjkbreath.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import logic.yjkbreath.api.ApiResult;
import logic.yjkbreath.api.DeviceDataApiDo;
import logic.yjkbreath.api.DeviceDataApiGet;
import logic.yjkbreath.breathData.model.ReceiveDataModel;
import logic.yjkbreath.breathData.model.ResponseDataModel;
import logic.yjkbreath.breathData.service.BreathDataService;
import logic.yjkbreath.device.service.DeviceUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

import static boat.commons.orika.Orika.map;


/**
 * @Auther fanhanxi
 * @Date 2018/12/24
 * @Description:
 */
@Api(tags = "呼吸仪-API")
@RequestMapping("api/device")
@RestController
public class ReceiveDataController {

    @Autowired
    private DeviceUserService deviceService;

    @Autowired
    private BreathDataService dataService;


    @ApiOperation(httpMethod = "GET",value = "设备开机获取绑定情况信息", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code_version", value = "协议版本", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "device_no", value = "终端设备编号", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "sale_channel", value = "设备对应渠道商编号", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "connect_time", value = "设备开机连接时间:yyyy-MM-dd HH:mm:ss", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "timestamp", value = "时间戳", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "sign", value = "sign", dataType = "String", paramType = "query")
    })
    @GetMapping(value = "/bind_status.json", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ApiResult bindStatus(@RequestParam String code_version,
                         @RequestParam String device_no,
                         @RequestParam String sale_channel,
                         @RequestParam String connect_time,
                         @RequestParam(required = false) String timestamp,
                         @RequestParam(required = false) String sign){

        //打印接收的数据
        Logger.getLogger(this.getClass().getName()).info("\napi接口：api/device/bind_status.json   HttpMethod:GET" +
                "\n接收数据："
                +"\ncode_version = "+code_version
                +"\ndevice_no = "+device_no
                +"\nsale_channel = "+sale_channel
                +"\nconnect_time = "+connect_time
                +"\ntimestamp = "+timestamp
                +"\nsign = "+sign+'\n');

        //组装接收数据model ReceiveDataModel
        ReceiveDataModel receiveDataModel = new ReceiveDataModel();
        receiveDataModel.setCode_version(code_version);
        receiveDataModel.setConnect_time(connect_time);
        receiveDataModel.setDevice_no(device_no);
        receiveDataModel.setSale_channel(sale_channel);
        receiveDataModel.setTimestamp(timestamp);
        receiveDataModel.setSign(sign);

        ResponseDataModel responseDataModel = deviceService.connectDevice(receiveDataModel);
        System.out.println(ApiResult.createSuccessResult("返回设备绑定情况", responseDataModel).toString());

        return ApiResult.createSuccessResult("返回设备绑定情况", responseDataModel);
    }

    @ApiOperation(httpMethod = "GET",value = "设备采集数据同步(无附加同步图形数据)", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @GetMapping(value = "/device_data.json", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ApiResult deviceDataGet(@Validated DeviceDataApiGet apiGet){

        //打印接收的数据
        Logger.getLogger(this.getClass().getName()).info("\napi接口：api/device/device_data.json   HttpMethod:GET" +
                "\n接收数据："+apiGet.toString()+'\n');

        ReceiveDataModel receiveDataModel = map(apiGet, ReceiveDataModel.class);

        ResponseDataModel responseDataModel = dataService.saveData(receiveDataModel);

        return ApiResult.createSuccessResult("data successfully receive，return user's information!", responseDataModel);
    }

    @ApiOperation(httpMethod = "POST",value = "设备采集数据同步(附加同步图形数据)", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PostMapping(value = "/device_data.json", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes ={MediaType.APPLICATION_FORM_URLENCODED_VALUE,MediaType.APPLICATION_JSON_UTF8_VALUE})
    ApiResult deviceDataPost(@Validated DeviceDataApiDo apiDo) {

        //打印接收的数据
        Logger.getLogger(this.getClass().getName()).info("\napi接口：api/device/device_data.json   HttpMethod:POST" +
                "\n接收数据："+apiDo.toString()+'\n');

        ReceiveDataModel receiveDataModel = map(apiDo, ReceiveDataModel.class);

        ResponseDataModel responseDataModel = dataService.saveData(receiveDataModel);

        return ApiResult.createSuccessResult("data successfully receive，return user's information!", responseDataModel);
    }
}
