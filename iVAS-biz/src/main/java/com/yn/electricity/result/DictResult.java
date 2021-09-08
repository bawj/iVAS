package com.yn.electricity.result;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: DictResult
 * @Author: zzs
 * @Date: 2021/3/17 16:34
 * @Description: 返回类
 */
@Data
public class DictResult implements Serializable {
    private static final long serialVersionUID = 2791051873592016320L;
    private Integer id;
    /**
     * 名称
     */
    private String name;
    /**
     * 字典类型
     */
    private String dictType;
    /**
     * 创建时间
     */
    private Date createTime;

}
