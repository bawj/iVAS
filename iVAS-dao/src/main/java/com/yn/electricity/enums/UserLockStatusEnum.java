package com.yn.electricity.enums;

public enum UserLockStatusEnum {
    NORMAL("normal", "normal", "正常", "正常"),
    LOCK("lock", "lock", "锁", "锁"),
    ;

    private String code;

    private String englishName;

    private String chineseName;

    private String msg;

    UserLockStatusEnum(String code, String englishName, String chineseName, String msg) {
        this.code = code;
        this.englishName = englishName;
        this.chineseName = chineseName;
        this.msg = msg;
    }

    public static String getByCode(String code){
        for (UserLockStatusEnum lock: UserLockStatusEnum.values()){
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
