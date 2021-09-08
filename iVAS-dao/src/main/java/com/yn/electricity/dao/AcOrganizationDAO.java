package com.yn.electricity.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author alarm_configuration 和 organization  中间表
 */
@Data
@TableName(value = "ac_organization")
public class AcOrganizationDAO {

    @TableId(value = "id" , type = IdType.AUTO)
    private Integer id;

    /**
     * alarm_configuration id
     */
    @TableField(value = "alarm_configuration_id")
    private Integer alarmConfigurationId;

    /**
     * 组织机构id 如果为空 则camera_group_id 不为空
     */
    @TableField(value = "organization_id")
    private Integer organizationId;
    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;
}