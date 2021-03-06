package com.yn.electricity.vo;

import lombok.Data;

/**
 * @author admin
 * Date 2021/5/21 11:03
 * Description 响应类型查询
 **/
@Data
public class ResponseTypeVO {

    private Integer id;

    /**
     * 报警响应类型名称
     */
    private String typeName;
}
