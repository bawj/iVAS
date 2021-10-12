package com.yn.electricity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yn.electricity.dao.PlatformLogInfoDAO;
import com.yn.electricity.vo.PlatformLogInfoVO;

import java.util.List;

public interface PlatformLogInfoMapper extends BaseMapper<PlatformLogInfoDAO> {

    /**
     * 查询日志
     * @param platformId id
     * @param startTime  date
     * @param endTime date
     * @return List
     */
    List<PlatformLogInfoVO> selectByPlatformId(Integer platformId, String startTime, String endTime);

    /**
     * 删除
     */
    void deleteTime();

}