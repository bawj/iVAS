package com.yn.electricity.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName: LogInfo
 * @Author: zzs
 * @Date: 2021/3/18 9:23
 * @Description: 日志实体类
 */
@Data
@TableName(value = "log_info")
public class LogInfo {
    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 操作者ip
     */
    private String ip;
    /**
     * 操作路由
     */
    private String menuRoute;
    /**
     * 操作名称
     */
    private String menuName;
    /**
     * 操作人名称
     */
    private String operateName;
    /**
     * 操作人id
     */
    private String operateId;
    /**
     * 扩展字段
     */
    private String ext;
    /**
     * 描述
     */
    private String description;
    /**
     * 创建时间
     */
    private Date createTime;

}
