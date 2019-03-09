package logic.yjkbreath.device.contants;

public enum DeviceTypeEnum {

    RESPIRATORY_APPARATUS("呼吸家远程监测呼吸仪"),
    ORDINARY_BLOOD_PRESSURE_METER("普通脉搏波远程监测血压仪"),
    AMBULATORY_BLOOD_PRESSURE_METER("24小时动态血压仪");

    private final String name;

    DeviceTypeEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}