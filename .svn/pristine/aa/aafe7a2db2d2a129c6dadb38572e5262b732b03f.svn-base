package com.yn.electricity.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author 解码器通道和camera的中间表
 */
@Data
@TableName(value = "decoder_passageway_camera")
public class DecoderPassagewayCameraDAO {
    /**
     * 主键id
     */
    @TableId(value = "id" , type = IdType.AUTO)
    private Integer id;

    /**
     * camera主键id
     */
    @TableField(value = "camera_id")
    private Integer cameraId;

    /**
     * decoder_passageway主键id
     */
    @TableField(value = "decoder_passageway_id")
    private Integer decoderPassagewayId;
}