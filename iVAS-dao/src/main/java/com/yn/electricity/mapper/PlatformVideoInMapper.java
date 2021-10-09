package com.yn.electricity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yn.electricity.dao.PlatformVideoInDAO;
import com.yn.electricity.vo.PlatformCameraGroupVO;
import com.yn.electricity.vo.PlatformGroupVO;
import com.yn.electricity.vo.PlatformVideoInVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 平台镜头
 */
public interface PlatformVideoInMapper extends BaseMapper<PlatformVideoInDAO> {

    void updatePlatformVideoInById(@Param("list") List<Long> cameraIds, int status);

    /**
     * 根据 parentId 查询
     * @param parentId parentId
     * @return List
     */
    List<PlatformGroupVO> selectByParentId(String parentId);

    /**
     * 查询镜头
     * @param no no
     * @return
     */
    List<PlatformVideoInVO> selectByCameraGroupNo(String no);

    /**
     * 根据平台查询镜头
     * @param platformId
     * @return
     */
    List<PlatformVideoInVO> selectCameraByPlatformId(Integer platformId);
}