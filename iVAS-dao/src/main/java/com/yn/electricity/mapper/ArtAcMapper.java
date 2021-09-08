package com.yn.electricity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yn.electricity.dao.ArtAcDAO;
import com.yn.electricity.vo.ResponseTypeVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author alarm_response_type 和 alarm_configuration 中间表
 */
public interface ArtAcMapper extends BaseMapper<ArtAcDAO> {


    /**
     * 批量保存
     * @param artAcList list
     * @return int
     */
    Integer insertList(@Param("artAcList") List<ArtAcDAO> artAcList);

    /**
     * 查询报警配置的响应类型
     * @param alarmConfigurationId 配置类型id
     * @return List
     */
    List<ResponseTypeVO> findArtAcByAlarmConfigurationId(Integer alarmConfigurationId);
}