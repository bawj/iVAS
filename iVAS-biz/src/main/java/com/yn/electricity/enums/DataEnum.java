package com.yn.electricity.enums;

/**
 * @author zzs
 */

public enum DataEnum {

    D_0("0", "0", "顶级父code", "顶级父code"),

    ;

    private String code;

    private String englishName;

    private String chineseName;

    private String msg;

    DataEnum(String code, String englishName, String chineseName, String msg) {
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
