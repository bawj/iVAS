package com.yn.electricity.enums;

import org.springframework.http.HttpStatus;

public enum ErrorCommonEnum {

    DEFAULT_ERROR("500", "DEFAULT_ERROR", "业务异常", "业务异常"),
    PARAM_ERROR("501", "PARAM_ERROR", "参数异常", "参数异常"),
    REMOTE_INVOKE_ERROR("502", "REMOTE_INVOKE_ERROR", "远程调用服务异常", "远程调用服务异常"),

    NOT_PERMISSION_ERROR(String.valueOf(HttpStatus.METHOD_NOT_ALLOWED.value()), "NOT_PERMISSION_ERROR", "没有访问权限", "没有访问权限"),
    NOT_login_ERROR(String.valueOf(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED.value()), "NOT_login_ERROR", "用户未登陆", "用户未登陆"),
    LOGIN_ERROR("512", "LOGIN_ERROR", "重复登录", "您的账号在另一台设备上登录,如非本人操作，请立即修改密码！"),


    ;

    private String code;

    private String englishName;

    private String chineseName;

    private String msg;

    ErrorCommonEnum(String code, String englishName, String chineseName, String msg) {
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
