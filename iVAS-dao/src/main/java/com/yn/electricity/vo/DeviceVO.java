package com.yn.electricity.vo;

import cn.gjing.tools.excel.Excel;
import cn.gjing.tools.excel.ExcelField;
import lombok.Data;

/**
 * @author admin
 * Date 2021/3/17 15:14
 * Description 查询设备信息
 **/
@Data
@Excel
public class DeviceVO {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 服务id
     */
    private Integer serviceId;

    /**
     * 组织机构id
     */
    private Integer organizationId;

    /**
     * 镜头组id
     */
    private Integer cameraGroupId;

    /**
     * 设备名称
     */
    @ExcelField(value = "设备名称")
    private String name;

    /**
     * 设备类型id
     */
    private Integer deviceTypeId;

    /**
     * 设备类型名称
     */
    @ExcelField(value = "设备类型")
    private String deviceTypeName;



    /**
     * 所属镜头组名称
     */
    private String cameraGroupName;

    /**
     * 注册IP
     */
    @ExcelField(value = "IP地址")
    private String ip;

    /**
     * 端口
     */
    @ExcelField(value = "端口")
    private String port;


    @ExcelField(value = "在线状态（0下线,1上线）")
    private Integer online;

    /**
     * 注册账号
     */
    @ExcelField(value = "用户名/注册id")
    private String registerAccount;

    /**
     * 接入服务名称
     */
    @ExcelField(value = "接入服务")
    private String serviceName;


    /**
     * 注册密码
     */
    private String registerPassword;

    /**
     * 国标编码
     */
    @ExcelField(value = "统一编码")
    private String code;

    /**
     * 经度
     */
    @ExcelField(value = "经度")
    private String longitude;

    /**
     * 纬度
     */
    @ExcelField(value = "纬度")
    private String latitude;

    /**
     * 所属组织名称
     */
    @ExcelField(value = "所属组织")
    private String organizationName;

    /**
     * 创建时间
     */
    @ExcelField(value = "创建时间")
    private String createTime;

}
