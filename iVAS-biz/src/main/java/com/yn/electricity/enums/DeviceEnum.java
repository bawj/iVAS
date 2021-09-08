package com.yn.electricity.enums;

/**
 * @author admin
 * Date 2021/3/20 11:10
 * Description 常用字段
 **/
public enum DeviceEnum {

    /**常用字段*/
    TV_WALL_RESOURCE_ORGANIZATION(1, "关联组织机构"),
    TV_WALL_RESOURCE_CAMERA_GROUP(2, "关联镜头组"),
    ;

    private Integer status;
    private String msg;

    DeviceEnum(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
