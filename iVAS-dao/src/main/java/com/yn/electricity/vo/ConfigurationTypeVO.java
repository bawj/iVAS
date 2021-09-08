package com.yn.electricity.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

/**
 * @author admin
 * Date 2021/5/21 10:01
 * Description
 **/
@Data
@ApiModel(value = "报警配置类型")
public class ConfigurationTypeVO {

    private Integer id;

    private String typeName;
}
