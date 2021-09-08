package com.yn.electricity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yn.electricity.dao.PlatformVideoInDAO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 平台镜头
 */
public interface PlatformVideoInMapper extends BaseMapper<PlatformVideoInDAO> {

    void updatePlatformVideoInById(@Param("list") List<Long> cameraIds, int status);

}