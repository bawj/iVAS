package com.yn.electricity.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yn.electricity.dao.VideoPlanDAO;
import com.yn.electricity.request.VideoPlanSaveRequest;
import com.yn.electricity.vo.VideoPlanVO;

import java.util.List;

/**
 * @author 录像计划service
 */
public interface VideoPlanService extends IService<VideoPlanDAO> {

    /**
     * 添加录像计划
     * @param videoPlanSaveRequest videoPlanSaveRequest
     * @return String
     */
    String addVideoPlan(List<VideoPlanSaveRequest> videoPlanSaveRequest);

    /**
     * 更加组织机构查询镜头
     * @param organizationId organizationId
     * @return List
     */
    List<VideoPlanVO> findVideoPlan(Integer organizationId);

}
