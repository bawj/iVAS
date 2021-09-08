package com.yn.electricity.service.alarm;

import com.github.pagehelper.PageInfo;
import com.yn.electricity.qto.AlarmConfigurationDTO;
import com.yn.electricity.request.AlarmConfigurationAlterRequest;
import com.yn.electricity.request.AlarmConfigurationRequest;
import com.yn.electricity.vo.AlarmConfigurationOneVO;
import com.yn.electricity.vo.AlarmConfigurationVO;
import com.yn.electricity.vo.ConfigurationTypeVO;
import com.yn.electricity.vo.ResponseTypeVO;

import java.util.List;

/**
 * @author 报警配置service
 */
public interface AlarmConfigurationService {

    /**
     * 添加配置
     * @param configurationRequest configurationRequest
     * @return String
     */
    String addConfiguration(AlarmConfigurationRequest configurationRequest);

    /**
     * 报警配置
     * @return ConfigurationTypeVO
     */
    List<ConfigurationTypeVO> findConfigurationType();

    /**
     * 响应类型
     * @param configurationTypeId 配置类型id
     * @return list
     */
    List<ResponseTypeVO> findResponseType(Integer configurationTypeId);

    /**
     * 修改配置
     * @param configurationRequest configurationRequest
     * @return String
     */
    String updateConfiguration(AlarmConfigurationAlterRequest configurationRequest);

    /**
     * 删除配置
     * @param ids ids
     * @return string
     */
    String configurationDel(List<Integer> ids);

    /**
     * 查询报警配置
     * @param alarmConfigurationDTO 查询条件
     * @return list
     */
    PageInfo<AlarmConfigurationVO> findConfiguration(AlarmConfigurationDTO alarmConfigurationDTO);

    /**
     * 根据id查询报警配置
     * @param id id
     * @return AlarmConfigurationOneVO
     */
    AlarmConfigurationOneVO findConfigurationOne(Integer id);

}
