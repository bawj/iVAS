package com.yn.electricity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yn.electricity.dao.DeviceLogInfoDAO;
import com.yn.electricity.vo.DeviceLogInfoVO;

import java.util.List;

public interface DeviceLogInfoMapper extends BaseMapper<DeviceLogInfoDAO> {

    /**
     * 查询日志
     * @param devId id
     * @param startTime  date
     * @param endTime date
     * @return List
     */
    List<DeviceLogInfoVO> selectByDevId(Integer devId, String startTime, String endTime);

    /**
     * 删除
     */
    void deleteTime();
}