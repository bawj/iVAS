package com.yn.electricity.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName: cameraDAO
 * @Author: zzs
 * @Date: 2021/3/25 16:14
 * @Description: 镜头实体类
 */
@Data
@TableName(value = "camera")
public class CameraDAO {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 设备表主键
     */
    private Integer devId;
    /**
     * 名称
     */
    private String name;
    /**
     * 通道编号
     */
    private Integer channelNo;
    /**
     * 纬度
     */
    private double latitude;
    /**
     * 经度
     */
    private double longitude;
    /**
     * 状态（0下线,1上线）
     */
    private Integer isOnline;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 镜头播放url
     */
    private String rtspUrl;

}
