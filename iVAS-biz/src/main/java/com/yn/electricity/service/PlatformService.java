package com.yn.electricity.service;

import com.github.pagehelper.PageInfo;
import com.yn.electricity.qto.PlatformQueryDTO;
import com.yn.electricity.request.PlatformAlterRequest;
import com.yn.electricity.request.PlatformSaveRequest;
import com.yn.electricity.vo.PlatformCameraGroupVO;
import com.yn.electricity.vo.PlatformVO;

import java.util.List;

public interface PlatformService {

    /**
     * 添加平台
     * @param platformSaveRequest platformSaveRequest
     * @return String
     */
    String addPlatform(PlatformSaveRequest platformSaveRequest);

    /**
     * 删除平台
     * @param ids ids
     * @return String
     */
    String deletePlatform(List<Integer> ids);

    /**
     * 修改平台信息
     * @param platformAlterRequest platformAlterRequest
     * @return String
     */
    String alterPlatform(PlatformAlterRequest platformAlterRequest);

    /**
     * 查询平台
     * @param deviceQuery
     * @return
     */
    PageInfo<PlatformVO> findPlatform(PlatformQueryDTO deviceQuery);

    /**
     * 查询平台的镜头组和镜头
     * @param platformId platformId
     * @return PlatformCameraGroupVO
     */
    PlatformCameraGroupVO findPlatformCameraGroup(String platformId);
}
