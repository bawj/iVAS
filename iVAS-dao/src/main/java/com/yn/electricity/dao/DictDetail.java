package com.yn.electricity.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName: dictDetail
 * @Author: zzs
 * @Date: 2021/3/17 15:58
 * @Description: 字典详情
 */
@Data
@TableName(value = "dict_detail")
public class DictDetail {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 名称
     */
    private String name;
    /**
     * 值
     */
    private String code;
    /**
     * 所属字典类型
     */
    private String dictType;
    /**
     * 是否启用
     */
    private String available;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date modifyTime;
    /**
     * 是否删除
     */
    private String isDelete;
}
