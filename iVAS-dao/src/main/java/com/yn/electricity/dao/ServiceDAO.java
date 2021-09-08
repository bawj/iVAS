package com.yn.electricity.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author 服务表
 */
@Data
@TableName(value = "service")
public class ServiceDAO {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 服务上报Id
     */
    @TableField(value = "service_id")
    private String serviceId;

    /**
     * 服务名称
     */
    private String name;

    /**
     * 服务类型 pas 接入服务 vas分析服务
     */
    private String type;

    /**
     * 服务地址
     */
    private String ip;

    /**
     * 服务端口
     */
    private Integer port;

    /**
     * 接入的设备类型
     */
    private String vendor;

    /**
     * 负载值
     */
    private Double payload;

    /**
     * 能力级-路数
     */
    private Integer ability;

    /**
     * 支持算法类型
     */
    @TableField(value = "alg_type")
    private String algType;

    /**
     * 状态（0下线,1上线）
     */
    private Short online;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;
}