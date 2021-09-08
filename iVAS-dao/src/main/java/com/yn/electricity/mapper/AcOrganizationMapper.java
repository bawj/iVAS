package com.yn.electricity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yn.electricity.dao.AcOrganizationDAO;
import com.yn.electricity.entity.RepeatConfigurationTypeEntity;
import com.yn.electricity.vo.AcOrganizationVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author alarm_configuration 和 organization 中间表
 */
public interface AcOrganizationMapper extends BaseMapper<AcOrganizationDAO> {

    /**
     * 批量保存
     *
     * @param acList list
     * @return int
     */
    Integer insertList(@Param("acList") List<AcOrganizationDAO> acList);

    /**
     * 检测组织机构是否有重复添加 配置类型
     *
     * @param organizationIds 组织机构id
     * @return list
     */
    List<RepeatConfigurationTypeEntity> repeatConfigurationType(@Param("organizationIds") List<Integer> organizationIds);

    /**
     * 检测组织机构是否有重复添加 配置类型
     *
     * @param alarmConfigurationId     alarmConfigurationId
     * @param alarmConfigurationTypeId alarmConfigurationId
     * @param organizationIds      组织机构id
     * @return list
     */
    List<Integer> repeatOrganizationIds(@Param("alarmConfigurationId") Integer alarmConfigurationId,
                                        @Param("alarmConfigurationTypeId") Integer alarmConfigurationTypeId,
                                        @Param("organizationIds") List<Integer> organizationIds);

    /**
     * 根据报警配置查询
     *
     * @param configurationId configurationId
     * @return List
     */
    List<AcOrganizationVO> findAcOrganizationByConfigurationId(Integer configurationId);

    /**
     * 修改时检测组织机构是否有重复添加 配置类型
     *
     * @param organizationIds     组织机构id
     * @param configurationTypeId 配置类型
     * @param id                  配置规则id
     * @return list
     */
    List<RepeatConfigurationTypeEntity> updateRepeatConfigurationType(@Param("organizationIds") List<Integer> organizationIds,
                                                                      @Param("configurationTypeId") Integer configurationTypeId,
                                                                      @Param("id") Integer id);
}