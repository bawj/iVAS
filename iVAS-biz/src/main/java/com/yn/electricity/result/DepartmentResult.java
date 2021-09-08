package com.yn.electricity.result;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName: Department
 * @Author: zzs
 * @Date: 2021/3/11 15:55
 * @Description: 部门返回实体类
 */
@Data
public class DepartmentResult {

    /**
     * 主键
     */
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
     * 排序
     */
    private String sort;
    /**
     * 创建时间
     */
    private Date createTime;

}
