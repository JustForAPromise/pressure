package logic.yjkbreath.breathData.support;

import logic.yjkbreath.breathData.model.BreathDataModel;
import logic.yjkbreath.breathData.model.BreathPreDataModel;
import logic.yjkbreath.breathData.model.ReceiveDataModel;

import java.math.BigDecimal;
import java.util.Base64;

/**
 * @Auther fanhanxi
 * @Date 2019/1/3
 * @Description:
 */
public class MyMapperUtil {

    public static BreathDataModel receiveDataToDataModel(ReceiveDataModel receiveDataModel, BreathDataModel model) {

        model.setDetectedTime(MyDateUtil.strToDate(receiveDataModel.getDetected_time()));

        if (receiveDataModel.getPef_value() != null) {
            model.setPefValue(BigDecimal.valueOf(receiveDataModel.getPef_value()));
        }

        if (receiveDataModel.getFev1_value() != null) {
            model.setFev1Value(BigDecimal.valueOf(receiveDataModel.getFev1_value()));
        }

        if (receiveDataModel.getFvc_value() != null) {
            model.setFvcValue(BigDecimal.valueOf(receiveDataModel.getFvc_value()));
        }

        if (receiveDataModel.getFef25_75_value() != null) {
            model.setFef25_75Value(BigDecimal.valueOf(receiveDataModel.getFef25_75_value()));
        }

        if (receiveDataModel.getMef75_value() != null) {
            model.setMef75Value(BigDecimal.valueOf(receiveDataModel.getMef75_value()));
        }

        if (receiveDataModel.getMef50_value() != null) {
            model.setMef50Value(BigDecimal.valueOf(receiveDataModel.getMef50_value()));
        }

        if (receiveDataModel.getMef25_value() != null) {
            model.setMef25Value(BigDecimal.valueOf(receiveDataModel.getMef25_value()));
        }

        if (receiveDataModel.getTips() != null) {
            model.setTip(receiveDataModel.getTips());
        }

        if (receiveDataModel.getParam4() != null) {

            model.setChartData(new String(Base64.getDecoder().decode(receiveDataModel.getParam4().replace("\n",""))));
        }

        return model;
    }

    public static BreathPreDataModel receiveDataToPreDataModel(ReceiveDataModel receiveDataModel, BreathPreDataModel model) {

        if (receiveDataModel.getParam1() != null) {
            model.setPrePefValue(BigDecimal.valueOf(Double.valueOf((receiveDataModel.getParam1()))));
        }

        if (receiveDataModel.getParam2() != null) {
            model.setPreFev1Value(BigDecimal.valueOf(Double.valueOf(receiveDataModel.getParam2())));
        }

        if (receiveDataModel.getParam3() != null) {
            model.setPreFvcValue(BigDecimal.valueOf(Double.valueOf(receiveDataModel.getParam3())));
        }

        if (receiveDataModel.getPrefef25_75() != null) {
            model.setPreFef25_75(BigDecimal.valueOf(Double.valueOf(receiveDataModel.getPrefef25_75())));
        }

        if (receiveDataModel.getPremef25() != null) {
            model.setPreMef25(BigDecimal.valueOf(Double.valueOf(receiveDataModel.getPremef25())));
        }

        if (receiveDataModel.getPremef50() != null) {
            model.setPreMef50(BigDecimal.valueOf(Double.valueOf(receiveDataModel.getPremef50())));
        }

        if (receiveDataModel.getPremef75() != null) {
            model.setPreMef75(BigDecimal.valueOf(Double.valueOf(receiveDataModel.getPremef75())));
        }

        return model;
    }
}
