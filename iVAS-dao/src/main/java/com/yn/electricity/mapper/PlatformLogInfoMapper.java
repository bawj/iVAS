package com.yn.electricity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yn.electricity.dao.PlatformLogInfoDAO;
import com.yn.electricity.vo.PlatformLogInfoVO;

import java.util.List;

public interface PlatformLogInfoMapper extends BaseMapper<PlatformLogInfoDAO> {

    List<PlatformLogInfoVO> selectByPlatformId(Integer platformId);

}