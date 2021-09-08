package com.yn.electricity.entity;

import com.yn.electricity.vo.ResponseTypeVO;
import lombok.Data;

import java.util.List;

/**
 * @author admin
 * Date 2021/5/28 10:37
 * Description redis 缓存中的报警配置类型
 **/
@Data
public class AlarmConfigurationEntity {

    /**
     * 报警配置类型 alarm_configuration_type 表id
     */
    private Integer alarmConfigurationTypeId;

    /**
     * 配置类型名称
     */
    private String alarmConfigurationTypeName;

    /**
     * 布防时间 json格式存储
     */
    private String distributionTime;

    /**
     * 摄像头名称
     */
    private String cameraName;

    /**
     * 摄像头id
     */
    private Integer cameraId;

    /**
     * 所属组织
     */
    private Integer organizationId;

    /**
     * 所属组织名称
     */
    private String organizationName;

    /**
     * 响应类型
     */
    private List<ResponseTypeVO> responseTypes;

    /**
     * 报警等级 0一般 1重要 2紧急 3非常紧急
     */
    private Integer alarmGrade;
}
