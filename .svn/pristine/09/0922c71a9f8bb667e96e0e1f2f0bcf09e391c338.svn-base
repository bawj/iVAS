package com.yn.electricity.enums.dict;

/**
 * @author
 *
 */
public enum SexEnum {

    S_0(0, "0", "女", "女"),
    S_1(1, "1", "男", "男"),
    ;

    private int code;

    private String englishName;

    private String chineseName;

    private String msg;

    SexEnum(int code, String englishName, String chineseName, String msg) {
        this.code = code;
        this.englishName = englishName;
        this.chineseName = chineseName;
        this.msg = msg;
    }

    public static String getByCode(int code){
        for (SexEnum sexEnum: SexEnum.values()){
            if(sexEnum.code == code){
                return sexEnum.chineseName;
            }
        }
        return null;
    }

    public int getCode() {
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
