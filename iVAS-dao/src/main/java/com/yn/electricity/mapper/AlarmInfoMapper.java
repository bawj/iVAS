package com.yn.electricity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yn.electricity.dao.AlarmInfoDAO;
import com.yn.electricity.qto.AlarmManagerDTO;
import com.yn.electricity.vo.AlarmInfoVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 报警信息处理
 */
public interface AlarmInfoMapper extends BaseMapper<AlarmInfoDAO> {

    /**
     * 查询报警信息
     * @param alarmManager alarm
     * @return list
     */
    List<AlarmInfoVO> findAlarm(AlarmManagerDTO alarmManager);

    /**
     * 根据镜头id查询报警数量
     * @param cameraId cameraId
     * @return Integer
     */
    Integer alarmCount(@Param("cameraId") Integer cameraId);

}