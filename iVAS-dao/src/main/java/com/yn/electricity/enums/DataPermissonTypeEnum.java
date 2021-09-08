package com.yn.electricity.enums;

/**
 * @Author: ZZS
 */
public enum DataPermissonTypeEnum {

    ALL_DATA("ALL_DATA", "ALL_DATA", "全部数据", "全部数据"),
    CURRENT_DEP_DATA("CURRENT_DEP_DATA", "CURRENT_DEP_DATA", "本部门数据", "本部门数据"),
    CUS_DEFINITION_DATA("CUS_DEFINITION_DATA", "CUS_DEFINITION_DATA", "自定义", "自定义"),
    ;

    private String code;

    private String englishName;

    private String chineseName;

    private String msg;

    DataPermissonTypeEnum(String code, String englishName, String chineseName, String msg) {
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
