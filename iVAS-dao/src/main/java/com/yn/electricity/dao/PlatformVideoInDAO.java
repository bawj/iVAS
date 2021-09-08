package com.yn.electricity.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author 平台镜头
 */
@Data
@TableName (value = "platform_video_in")
public class PlatformVideoInDAO {

    @TableId(value = "id" , type = IdType.AUTO)
    private Long id;

    /**
     * 视频输入通道号
     */
    @TableField(value = "channel_no")
    private Integer channelNo;

    /**
     * 平台上报的镜头组 id
     */
    @TableField(value = "t_camera_group_no")
    private String tCameraGroupNo;

    /**
     * 视频输入通道创建时间
     */
    @TableField(value = "create_date")
    private Date createDate;

    /**
     * 通道名称
     */
    private String name;

    /**
     * 通道所在服务唯一标识
     */
    @TableField(value = "service_id")
    private String serviceId;

    /**
     * 通道状态
     */
    private String status;

    @TableField(value = "platform_id")
    private String platformId;

    /**
     * 经度
     */
    private String longitude;

    /**
     * 维度
     */
    private String latitude;

    /**
     * 平台-1，设备0，rtsp1，抓拍机 2，本地视频 3
     */
    private Integer flag;

    @TableField(value = "device_gb_id")
    private String deviceGbId;

}