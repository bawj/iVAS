package com.yn.electricity.vo;

import lombok.Data;

import java.util.List;

/**
 * @author admin
 * Date 2021/3/18 9:38
 * Description 根据id 查询下级数据
 **/
@Data
public class OrganizationSubordinateVO {

    /**
     * 主键id
     */
    private Integer id;

    /**
     * 组织名称
     */
    private String name;

    private Integer sport;

    private String depCode;

    /**
     * 下级
     */
    private List<OrganizationSubordinateVO> childOrganization;

}
