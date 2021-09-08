package com.yn.electricity.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yn.electricity.dao.VideoPlanDAO;

import java.util.List;

/**
 * @author 录像计划
 */
public interface VideoPlanMapper extends BaseMapper<VideoPlanDAO> {

    /**
     * 根据镜头id查询录像计划
     * @param cameraId id
     * @return list
     */
    List<VideoPlanDAO> selectByCameraId(Integer cameraId);


}