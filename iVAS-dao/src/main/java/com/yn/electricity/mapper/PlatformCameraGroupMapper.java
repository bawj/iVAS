package com.yn.electricity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yn.electricity.dao.PlatformCameraGroupDAO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 平台镜头组
 */
public interface PlatformCameraGroupMapper extends BaseMapper<PlatformCameraGroupDAO> {

    /**
     * 批量添加
     * @param platformCameraGroupList platformCameraGroupList
     * @return Integer
     */
    Integer insertList(@Param(value = "platformList") List<PlatformCameraGroupDAO> platformCameraGroupList);

}