package com.yn.electricity.service.alarm;

import com.github.pagehelper.PageInfo;
import com.yn.electricity.qto.AlarmManagerDTO;
import com.yn.electricity.vo.AlarmConfigurationTypeVO;
import com.yn.electricity.vo.AlarmInfoExportVO;
import com.yn.electricity.vo.AlarmInfoVO;

import java.util.List;

/**
 * @author 报警管理
 */
public interface AlarmManagerService {

    /**
     * 查询报警信息
     *
     * @param alarmManager alarmManager
     * @return List
     */
    PageInfo<AlarmInfoVO> findAlarm(AlarmManagerDTO alarmManager);

    /**
     * 查询报警配置类型信息
     *
     * @return list
     */
    List<AlarmConfigurationTypeVO> findAlarmConfigurationType();


    /**
     * 确认报警状态
     *
     * @param id      id
     * @param remarks 备注
     * @return string
     */
    String alarmConfirm(Integer id, String remarks);

    /**
     * 报警信息导出
     * @param alarmManager alarmManager
     * @return List
     */
    List<AlarmInfoExportVO> alarmConfigurationExport(AlarmManagerDTO alarmManager);
}
