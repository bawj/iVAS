package com.yn.electricity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yn.electricity.dao.DeviceDAO;
import com.yn.electricity.dao.PlatformDAO;
import com.yn.electricity.qto.BaseQuery;
import com.yn.electricity.qto.PlatformQueryDTO;
import com.yn.electricity.vo.PlatformMapVO;
import com.yn.electricity.vo.PlatformVO;

import java.util.List;

/**
 * @author 平台表
 */
public interface PlatformMapper extends BaseMapper<PlatformDAO> {

    /**
     * 查询名称是否重复
     * @param name name
     * @param id id
     * @return PlatformDAO
     */
    PlatformDAO selectByNameNotId(String name, Integer id);

    /**
     * 国标编码不能重复
     * @param code code
     * @param id id
     * @return List
     */
    List<DeviceDAO> selectByCodeNotId(String code, Integer id);

    /**
     * 查询平台
     * @param deviceQuery deviceQuery
     * @param dataPermission dataPermission
     * @return List
     */
    List<PlatformVO> selectPlatform(PlatformQueryDTO deviceQuery, BaseQuery dataPermission);

    /**
     * 查询平台经纬度信息
     * @return List
     */
    List<PlatformMapVO> mapFindPlatform();

}