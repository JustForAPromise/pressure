package logic.yjkbreath.fileTest.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import logic.yjkbreath.api.ApiResult;
import logic.yjkbreath.api.DeviceDataApiDo;
import org.springframework.http.MediaType;
import org.springframework.util.ResourceUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;


/**
 * @Auther fanhanxi
 * @Date 2018/12/24
 * @Description:
 */
@Api(tags = "test-api")
@RequestMapping("test")
@RestController
public class TestController {


    @ApiOperation(httpMethod = "POST", value = "设备开机获取绑定情况信息", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PostMapping(value = "/bind_status.json", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ApiResult bindStatus(@RequestBody MultipartFile file) {
        ApiResult apiResult = new ApiResult();

        if (file.isEmpty()) {
            apiResult.setCode(-1);
            apiResult.setMessage("Please select a file to upload");
            return apiResult;
        }

        File path = null;
        try {
            path = new File(ResourceUtils.getURL("classpath:").getPath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String filePath = path.getParentFile().getParentFile().getParent() + File.separator + "test" + File.separator;

        try {
            byte[] bytes = file.getBytes();
            Path save = Paths.get(filePath + file.getOriginalFilename());
            Files.write(save, bytes);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return apiResult;
    }

    @ApiOperation(httpMethod = "GET", value = "设备采集数据同步(无附加同步图形数据)", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @GetMapping(value = "/device_data.json", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ApiResult deviceDataGet(HttpServletResponse response) {

        String path = "testt";
        File file = new File(path);
        if (!file.exists()){
            file.mkdirs();
            Logger.getLogger(this.getClass().getName()).info(file.getAbsolutePath());
        }

        return null;

    }

    @ApiOperation(httpMethod = "POST", value = "设备采集数据同步(附加同步图形数据)", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PostMapping(value = "/device_data.json", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE})
    ApiResult deviceDataPost(@Validated DeviceDataApiDo apiDo) {

        return null;

    }
}
