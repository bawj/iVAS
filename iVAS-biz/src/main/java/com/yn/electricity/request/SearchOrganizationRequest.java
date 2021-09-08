package com.yn.electricity.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author admin
 * Date 2021/7/28 11:10
 * Description
 **/
@Data
public class SearchOrganizationRequest {

    @NotEmpty(message = "名称不能为空")
    private String name;

}
