package com.yn.electricity.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author 解码器输出通道表
 */
@Data
@TableName(value = "decoder_passageway")
public class DecoderPassagewayDAO {
    /**
     * 主键id
     */
    @TableId(value = "id" , type = IdType.AUTO)
    private Integer id;

    /**
     * decoder表主键
     */
    @TableField(value = "decoder_id")
    private Integer decoderId;

    /**
     * tv_wall表主键
     */
    @TableField(value = "tv_wall_id")
    private Integer tvWallId;

    /**
     * 解码器通道编号
     */
    @TableField(value = "channel_no")
    private Integer channelNo;

    /**
     * 状态（0下线,1上线）
     */
    private Integer status;

    /**
     * 输出通道名称
     */
    private String name;

    /**
     * 添加时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 最后修改时间
     */
    @TableField(value = "update_time")
    private Date updateTime;
}