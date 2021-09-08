package com.yn.electricity.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName: Department
 * @Author: zzs
 * @Date: 2021/3/11 15:55
 * @Description: 部门实体类
 */
@Data
@TableName(value = "department")
public class Department {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 部门编号
     */
    private String code;
    /**
     * 部门名称
     */
    private String name;
    /**
     * 父级code
     */
    private String parentCode;
    /**
     * 是否启用，y是n否
     */
    private String available;
    /**
     * 级别
     */
    private Integer level;
    /**
     * 排序
     */
    private String sort;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date modifyTime;

}
