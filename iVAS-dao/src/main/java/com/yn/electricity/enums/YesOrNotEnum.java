package com.yn.electricity.enums;

public enum YesOrNotEnum {

    Y("y", "y", "是", "是"),
    N("n", "n", "否", "否"),
    ;

    private String code;

    private String englishName;

    private String chineseName;

    private String msg;

    YesOrNotEnum(String code, String englishName, String chineseName, String msg) {
        this.code = code;
        this.englishName = englishName;
        this.chineseName = chineseName;
        this.msg = msg;
    }

    public static String getByCode(String code){
        for (YesOrNotEnum lock: YesOrNotEnum.values()){
            if(lock.code.equals(code)){
                return lock.chineseName;
            }
        }
        return null;
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
