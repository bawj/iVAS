package com.yn.electricity.service;

import com.github.pagehelper.PageInfo;
import com.yn.electricity.request.OrganizationAlterRequest;
import com.yn.electricity.request.OrganizationSaveRequest;
import com.yn.electricity.request.SearchOrganizationRequest;
import com.yn.electricity.result.OrganizationResult;
import com.yn.electricity.vo.*;

import java.util.List;

/**
 * @author 组织机构管理
 */
public interface OrganizationService {

    /**
     * 添加组织机构
     * @param organizationRequest 组织机构
     * @return string
     */
    String addOrganization(OrganizationSaveRequest organizationRequest);

    /**
     * 修改组织机构
     * @param organizationRequest 组织机构
     * @return String
     */
    String alterOrganization(OrganizationAlterRequest organizationRequest);

    /**
     * 删除组织机构
     * @param ids id
     * @return String
     */
    String deleteOrganization(List<Integer> ids);

    /**
     * 查询组织机构
     * @param pageNum 分页
     * @param pageSize 分页
     * @param name 名称
     * @return PageInfo<OrganizationVO>
     */
    PageInfo<OrganizationVO> findOrganization(Integer pageNum, Integer pageSize, String name);


    /**
     * 根据 id查询下级
     * @param id id
     * @return list
     */
    OrganizationSubordinateVO organizationSubordinate(Integer id);

    /**
     * 根据id查询上级
     * @param id id
     * @return OrganizationSuperiorVO
     */
    OrganizationSuperiorVO superiorOrganization(Integer id);

    /**
     * 根据组织机构id查询所有子集以及设备
     * @param id             id
     * @return OrganizationResult
     */
    OrganizationResult findOrganizationAndDevice(Integer id);


    /**
     * 根据组织机构id 查询所有镜头
     * @param id id
     * @return List
     */
    List<CameraVO> findOrganizationCamera(Integer id);


    /**
     * 根据组织机构名称、设备名称、镜头名称、搜索
     * @param searchOrganizationRequest 查询条件
     * @return List
     */
    List<SearchOrganDevCameraVO> searchOrganizationDevCamera(SearchOrganizationRequest searchOrganizationRequest);

}
