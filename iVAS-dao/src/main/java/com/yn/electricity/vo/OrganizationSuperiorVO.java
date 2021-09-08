package com.yn.electricity.vo;

import lombok.Data;

/**
 * @author admin
 * Date 2021/3/22 16:25
 * Description 根据id 查询上级
 **/
@Data
public class OrganizationSuperiorVO {

    /**
     * 主键id
     */
    private Integer id;

    /**
     * 组织名称
     */
    private String name;

    /**
     * 所属部门id
     */
    private Integer depId;

    /**
     * 所属部门名称
     */
    private String depName;


    private Integer sport;
}
