package com.yn.electricity.enums;

/**
 * @author admin
 */

public enum TabTypeEnum {

    NORMAL("0", "NORMAL", "正常页面", "正常页面"),
    VERIFY("1", "VERIFY", "参数验证异常", "参数验证异常"),
    ERROR("2", "ERROR", "异常", "异常"),

    ;

    private String code;

    private String englishName;

    private String chineseName;

    private String msg;

    TabTypeEnum(String code, String englishName, String chineseName, String msg) {
        this.code = code;
        this.englishName = englishName;
        this.chineseName = chineseName;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getEnglishName() {
        return englishName;
    }

    public String getChineseName() {
        return chineseName;
    }

    public String getMsg() {
        return msg;
    }

}
