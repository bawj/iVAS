package com.yn.electricity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yn.electricity.dao.ServiceDAO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 服务表
 */
public interface ServiceMapper extends BaseMapper<ServiceDAO> {

    /**
     * 根据
     * @param deviceType
     * @return
     */
    List<ServiceDAO> selectByOnlineAndVendor(@Param("deviceType") String deviceType);

}