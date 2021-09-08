package com.yn.electricity.service.alarm.impl;

import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.yn.electricity.dao.AlarmConfigurationTypeDAO;
import com.yn.electricity.dao.AlarmInfoDAO;
import com.yn.electricity.dao.CameraDAO;
import com.yn.electricity.enums.AlarmGradeEnum;
import com.yn.electricity.mapper.AlarmConfigurationTypeMapper;
import com.yn.electricity.mapper.AlarmInfoMapper;
import com.yn.electricity.mapper.CameraMapper;
import com.yn.electricity.qto.AlarmManagerDTO;
import com.yn.electricity.qto.BaseQuery;
import com.yn.electricity.result.UserResult;
import com.yn.electricity.service.RedisInfoService;
import com.yn.electricity.service.alarm.AlarmManagerService;
import com.yn.electricity.util.DataPermissionUtil;
import com.yn.electricity.util.RedisInfoUtil;
import com.yn.electricity.utils.BizBusinessUtils;
import com.yn.electricity.vo.AlarmConfigurationTypeVO;
import com.yn.electricity.vo.AlarmInfoExportVO;
import com.yn.electricity.vo.AlarmInfoVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author admin
 * Date 2021/5/31 13:38
 * Description
 **/
@Service
public class AlarmManagerServiceImpl implements AlarmManagerService {

    @Resource
    private AlarmInfoMapper alarmInfoMapper;
    @Resource
    private RedisInfoUtil redisInfoUtil;
    @Resource
    private AlarmConfigurationTypeMapper alarmConfigurationTypeMapper;
    @Resource
    private DataPermissionUtil dataPermissionUtil;

    @Override
    public PageInfo<AlarmInfoVO> findAlarm(AlarmManagerDTO alarmManager) {
        List<String> depCodeList = dataPermissionUtil.getDataPermission().getDepCodeList();
        alarmManager.setDepCodeList(depCodeList);
        PageHelper.startPage(alarmManager.getPageNum() , alarmManager.getPageSize());
        return new PageInfo<>(alarmInfoMapper.findAlarm(alarmManager));
    }

    @Override
    public List<AlarmInfoExportVO> alarmConfigurationExport(AlarmManagerDTO alarmManager) {

        List<String> depCodeList = dataPermissionUtil.getDataPermission().getDepCodeList();
        alarmManager.setDepCodeList(depCodeList);
        List<AlarmInfoVO> alarm = alarmInfoMapper.findAlarm(alarmManager);

        if (CollectionUtils.isEmpty(alarm)){
            BizBusinessUtils.bizBusinessException("没有可导出的数据");
        }

        List<AlarmInfoExportVO> exportList = new ArrayList<>();
        for (AlarmInfoVO alarmInfoVO : alarm) {
            AlarmInfoExportVO alarmInfoExport = new AlarmInfoExportVO();
            alarmInfoExport.setAlarmTime(alarmInfoVO.getAlarmTime());
            alarmInfoExport.setCameraName(alarmInfoVO.getCameraName());
            alarmInfoExport.setOrganizationName(alarmInfoVO.getOrganizationName());

            Integer alarmGrade = alarmInfoVO.getAlarmGrade();
            if (alarmGrade.equals(AlarmGradeEnum.ALARM_GRADE_0.getCode())){
                alarmInfoExport.setAlarmGrade(AlarmGradeEnum.ALARM_GRADE_0.getMsg());
            }else if (alarmGrade.equals(AlarmGradeEnum.ALARM_GRADE_1.getCode())){
                alarmInfoExport.setAlarmGrade(AlarmGradeEnum.ALARM_GRADE_1.getMsg());
            }else if (alarmGrade.equals(AlarmGradeEnum.ALARM_GRADE_2.getCode())){
                alarmInfoExport.setAlarmGrade(AlarmGradeEnum.ALARM_GRADE_2.getMsg());
            }else if (alarmGrade.equals(AlarmGradeEnum.ALARM_GRADE_3.getCode())){
                alarmInfoExport.setAlarmGrade(AlarmGradeEnum.ALARM_GRADE_3.getMsg());
            }
            alarmInfoExport.setTypeName(alarmInfoVO.getTypeName());
            alarmInfoExport.setAlarmContent(alarmInfoVO.getAlarmContent());
            alarmInfoExport.setConfirmStatus(alarmInfoVO.getConfirmStatus()? "已确认":"未确认");
            alarmInfoExport.setConfirmPeople(alarmInfoVO.getConfirmPeople());
            alarmInfoExport.setConfirmTime(DateUtil.formatDateTime(alarmInfoVO.getConfirmTime()));
            alarmInfoExport.setRemarks(alarmInfoVO.getRemarks());
            exportList.add(alarmInfoExport);
        }
        return exportList;
    }

    @Override
    public List<AlarmConfigurationTypeVO> findAlarmConfigurationType() {
        List<AlarmConfigurationTypeVO> typeList = new ArrayList<>();
        List<AlarmConfigurationTypeDAO> configurationTypeList = alarmConfigurationTypeMapper.selectList(null);
        configurationTypeList.forEach(configurationTypeDAO -> {
            AlarmConfigurationTypeVO type = new AlarmConfigurationTypeVO();
            BeanUtils.copyProperties(configurationTypeDAO , type);
            typeList.add(type);
        });
        return typeList;
    }

    @Resource
    private CameraMapper cameraMapper;
    @Resource
    private RedisInfoService redisInfoService;

    @Override
    public String alarmConfirm(Integer id, String remarks) {
        AlarmInfoDAO alarmInfoDAO = alarmInfoMapper.selectById(id);
        if (alarmInfoDAO == null){
            BizBusinessUtils.bizBusinessException("id 错误");
        }
        alarmInfoDAO.setRemarks(remarks);
        UserResult userResult = redisInfoUtil.getUserResult();
        alarmInfoDAO.setConfirmPeople(userResult.getUserName());
        alarmInfoDAO.setConfirmStatus(true);
        alarmInfoDAO.setConfirmTime(new Date());
        alarmInfoMapper.updateById(alarmInfoDAO);

        Integer cameraId = alarmInfoDAO.getCameraId();
        CameraDAO cameraDAO = cameraMapper.selectById(cameraId);
        if (cameraDAO != null){
            redisInfoService.redisUpdatePreloadingDevice(cameraDAO.getDevId());
        }
        return null;
    }

}
