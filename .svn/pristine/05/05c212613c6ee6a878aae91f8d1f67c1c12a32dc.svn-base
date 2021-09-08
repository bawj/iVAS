package com.yn.electricity.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author admin
 * Date 2021/4/10 17:19
 * Description
 **/
@Data
@TableName(value = "camera_group_camera")
public class CameraGroupCameraDAO {

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * camera_group 表中的id 0代表最顶层
     */
    @TableField(value = "camera_id")
    private Integer cameraId;

    /**
     * camera_group 表中的id 0代表最顶层
     */
    @TableField(value = "camera_group_id")
    private Integer cameraGroupId;

}
