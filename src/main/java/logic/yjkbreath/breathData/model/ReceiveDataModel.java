package logic.yjkbreath.breathData.model;

import lombok.Data;

/**
 * @Auther fanhanxi
 * @Date 2019/1/5
 * @Description:
 */
@Data
public class ReceiveDataModel {

    String code_version;

    String device_no;

    String sale_channel;

    String connect_time;

    String detected_time;

    Integer pef_value;

    Double fev1_value;

    Double fvc_value;

    Double fef25_75_value;

    Double  mef75_value;

    Double mef50_value;

    Double mef25_value;

    String tips;

    String param1;

    String  param2;

    String param3;

    String param4;

    Double  prefef25_75;

    Double premef75;

    Double premef50;

    Double premef25;

    String timestamp;

    String sign;

}
