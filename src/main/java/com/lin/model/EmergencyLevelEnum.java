package com.lin.model;

/**
 * Created by Lin on 2019/10/27.
 */
public enum EmergencyLevelEnum {

    INVALID(-1, "失效"),
    OK(0, "正常"),
    BLUE(4, "蓝色预警"),
    YELLOW(3, "黄色预警"),
    ORANGE(2, "橙色预警"),
    RED(1, "红色预警"),

    ;

    int level;

    String desc;

    public static boolean validCode(int level) {
        for (EmergencyLevelEnum emergencyLevelEnum : EmergencyLevelEnum.values()) {
            if (emergencyLevelEnum.getLevel() == level) {
                return true;
            }
        }

        return false;
    }

    EmergencyLevelEnum(int level, String desc) {
        this.level = level;
        this.desc = desc;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
