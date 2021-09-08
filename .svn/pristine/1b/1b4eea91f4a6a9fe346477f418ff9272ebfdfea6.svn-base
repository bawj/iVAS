package com.yn.electricity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yn.electricity.dao.AlarmConfigurationDAO;
import com.yn.electricity.entity.AlarmConfigurationEntity;
import com.yn.electricity.qto.AlarmConfigurationDTO;
import com.yn.electricity.qto.ConfigurationDTO;
import com.yn.electricity.vo.AlarmConfigurationOneVO;
import com.yn.electricity.vo.AlarmConfigurationVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 报警配置
 */
public interface AlarmConfigurationMapper extends BaseMapper<AlarmConfigurationDAO> {


    /**
     * 查询配置列表
     *
     * @param alarmConfiguration alarmConfigurationDTO
     * @return List
     */
    List<AlarmConfigurationVO> findConfiguration(@Param("alarmConfiguration") AlarmConfigurationDTO alarmConfiguration);


    /**
     * 查询报警类型 对应的 配置规则
     *
     * @param alarmConfigurationIds alarmConfigurationIds
     * @param alarmTypeId           alarmTypeId
     * @return AlarmConfigurationDAO
     */
    AlarmConfigurationDAO selectBatchIdsAndAlarmType(@Param("alarmConfigurationIds") List<Integer> alarmConfigurationIds,
                                                     @Param("alarmTypeId") Integer alarmTypeId);


    /**
     * 根据devId 和 配置类型查询 对应的配置
     * @param devId       devId
     * @param alarmTypeId 配置类型id
     * @param channelNo   camera 通道号
     * @return alarmConfiguration
     */
    AlarmConfigurationEntity findConfigurationByDevIdAndAlarmTypeId(Integer devId, Integer alarmTypeId, Integer channelNo);


    /**
     * 根据报警配置查询 对应的设备和报警类型
     * @param ids ids
     * @return List
     */
    List<ConfigurationDTO> findConfigurationTypeAndOrganization(@Param("ids") List<Integer> ids);

    /**
     * 根据配置id查询
     * @param id id
     * @return AlarmConfigurationOneVO
     */
    AlarmConfigurationOneVO findConfigurationOne(Integer id);
}