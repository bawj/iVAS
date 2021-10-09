package com.yn.electricity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yn.electricity.dao.DeviceLogInfoDAO;
import com.yn.electricity.vo.DeviceLogInfoVO;

import java.util.List;

public interface DeviceLogInfoMapper extends BaseMapper<DeviceLogInfoDAO> {

    List<DeviceLogInfoVO> selectByDevId(Integer devId);

}