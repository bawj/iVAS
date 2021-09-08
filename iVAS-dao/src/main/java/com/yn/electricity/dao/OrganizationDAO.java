package com.yn.electricity.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author 组织机构
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "organization")
public class OrganizationDAO extends BaseDAO{
    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 组织名称
     */
    private String name;

    /**
     * 公司company主键
     */
    @TableField(value = "organization_id")
    private Integer organizationId;

    /**
     * 排序
     */
    private Integer sport;


    /**
     * 添加时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 最后修改时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

}