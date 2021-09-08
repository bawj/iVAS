package com.yn.electricity.qto;

import lombok.Data;

/**
 * @author admin
 * Date 2021/3/17 15:05
 * Description 查询设备信息 条件
 **/
@Data
public class PlatformQueryDTO {

    /**
     * 分页
     */
    private Integer pageNum;

    /**
     * 分页
     */
    private Integer pageSize;

    /**
     * 设备名称
     */
    private String name;

    /**
     * 设备类型 id
     */
    private Integer deviceTypeId;

    /**
     * 创建日期 开始时间
     */
    private String startTime;

    /**
     * 创建日期 结束时间
     */
    private String endTime;

}
