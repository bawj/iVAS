package com.yn.electricity.dao;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author 平台镜头组
 */
@Data
@TableName(value = "platform_camera_group")
public class PlatformCameraGroupDAO {
    /**
     * ID主键
     */
    @TableId(value = "id" , type = IdType.AUTO)
    private Long id;

    /**
     * 摄像头组名称
     */
    @TableField(value = "group_name")
    private String groupName;

    private String no;

    /**
     * 父组Id(-1表示根组)
     */
    @TableField(value = "parent_id")
    private String parentId;

    /**
     * 摄像头编号（平台接入时有值）
     */
    @TableField(value = "camera_no")
    private String cameraNo;

    /**
     * 父摄像头编号（平台接入时有值）
     */
    @TableField(value = "parent_camera_no")
    private String parentCameraNo;

    /**
     * 平台ID（t_platform.id）（平台接入时有值）
     */
    @TableField(value = "platform_id")
    private Long platformId;

    private String type;

}