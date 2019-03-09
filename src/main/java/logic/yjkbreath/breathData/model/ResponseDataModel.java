package logic.yjkbreath.breathData.model;

import lombok.Data;

/**
 * @Auther fanhanxi
 * @Date 2019/1/3
 * @Description:
 */
@Data
public class ResponseDataModel {
    /**
     * 用户id
     */
    private String detected_no;

    /**
     * 姓名
     */
    private String detected_name;

    /**
     * 设备编号
     */
    private String device_no;

    /**
     * 绑定情况(2:设备未登记;3:设备未绑定;4:设备已绑定),
     */
    private String bind_satus;


    /**
     * 处理情况(0：错误;1：已绑定 ;2:设备 没有绑定)
     */
    private String process_satus;

    /**
     * 性别（male 男 ;  female 女）
     */
    private String sex;

    /**
     * 出生日期(yyyy-MM-dd)
     */
    private String birthdate;

    /**
     * 身高(cm)
     */
    private Double height;

    /**
     * 体重(kg)
     */
    private Double weight;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 检测人PEF指标预计值
     */
    private String param1;

    /**
     * 检测人FEV1指标预计值
     */
    private String param2;

    /**
     * 检测人FVC指标预计值
     */
    private String param3;

    /**
     * 预留输出参数，没有定义
     */
    private String param4;

    /**
     * 检测人fef25_75指标预计值
     */
    private Double prefef25_75;

    /**
     * 检测人mef75指标预计值
     */
    private Double premef75;

    /**
     * 检测人mef50指标预计值
     */
    private Double premef50;

    /**
     * 检测人mef25指标预计值
     */
    private Double premef25;
}
