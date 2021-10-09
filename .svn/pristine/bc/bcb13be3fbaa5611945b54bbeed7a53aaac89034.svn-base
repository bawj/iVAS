package com.yn.electricity.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author 录像计划
 */
@Data
@TableName(value = "video_plan")
public class VideoPlanDAO {

    @TableId(value = "id" , type = IdType.AUTO)
    private Integer id;

    /**
     * 录像计划 0 代表周1 、1代表周2 ... 6代表周日
     */
    private Integer type;

    /**
     * 镜头id
     */
    @TableField(value = "camera_id")
    private Integer cameraId;

    /**
     * 主辅码流 0主1辅
     */
    @TableField(value = "sub_type")
    private Integer subType;

    /**
     * 开始录像时间 格式 HH:mm:ss
     */
    @TableField(value = "begin_time")
    private String beginTime;

    /**
     * 结束录像时间 格式 HH:mm:ss
     */
    @TableField(value = "end_time")
    private String endTime;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

}