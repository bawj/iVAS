package com.yn.electricity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yn.electricity.dao.OrganizationDAO;
import com.yn.electricity.qto.BaseQuery;
import com.yn.electricity.vo.OrganizationAndDeviceVO;
import com.yn.electricity.vo.OrganizationSubordinateVO;
import com.yn.electricity.vo.OrganizationVO;
import com.yn.electricity.vo.SearchOrganDevCameraVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 组织结构
 */
public interface OrganizationMapper extends BaseMapper<OrganizationDAO> {

    /**
     * 根据name查询 不等于 id的
     *
     * @param name 名称
     * @param id   id
     * @return OrganizationDAO
     */
    OrganizationDAO selectByNameNotId(String name, Integer id);

    /**
     * 根据OrganizationId和名称模糊查询
     *
     * @param organizationId organizationId
     * @param name           名称
     * @param dataPermission dataPermission
     * @return list
     */
    List<OrganizationVO> selectByOrganizationIdAndName(@Param("organizationId") Integer organizationId, @Param("name") String name, @Param("dataPermission") BaseQuery dataPermission);

    /**
     * 根据OrganizationId 查询下级数据
     *
     * @param organizationId OrganizationId
     * @return List
     */
    List<OrganizationSubordinateVO> selectByOrganizationId(@Param("organizationId") Integer organizationId);

    /**
     * 删除多个
     *
     * @param ids ids
     * @return integer
     */
    Integer deleteByIdList(@Param("ids") List<Integer> ids);

    /**
     * 批量查询id
     *
     * @param ids ids
     * @return List
     */
    List<OrganizationDAO> selectListById(List<Integer> ids);

    /**
     * 根据id查询所有子集
     *
     * @param depCode          query
     * @param pId            pId
     * @return List
     */
    List<OrganizationAndDeviceVO> selectByPId(@Param("depCode") String depCode, @Param("pId") Integer pId);


    /**
     * 根据id查询组织机构
     * @param id id
     * @return OrganizationDAO
     */
    OrganizationDAO selectOrganizationById(String id);

    /**
     * 根据组织机构名称、设备名称、镜头名称、搜索
     * @param dataPermission 数据权限
     * @param name 查询条件
     * @return List
     */
    List<SearchOrganDevCameraVO> searchOrganizationDevCamera(BaseQuery dataPermission,String name);

    /**
     * 查询组织机构根节点
     * @param id id
     * @param depCode depCode
     * @return OrganizationDAO
     */
    OrganizationDAO selectByDepCode(Integer id, String depCode);

    /**
     * 查询组织机构根节点
     * @param id id
     * @param depCodeList depCode
     * @return OrganizationDAO
     */
    OrganizationDAO selectByDepCodeList(Integer id, List<String> depCodeList);
}