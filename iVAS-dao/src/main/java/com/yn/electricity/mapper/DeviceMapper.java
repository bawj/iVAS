package com.yn.electricity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yn.electricity.dao.DeviceDAO;
import com.yn.electricity.dao.ServiceDAO;
import com.yn.electricity.qto.BaseQuery;
import com.yn.electricity.qto.DeviceDTO;
import com.yn.electricity.qto.DeviceQueryDTO;
import com.yn.electricity.vo.DeviceResultVO;
import com.yn.electricity.vo.DeviceVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 设备相关查询
 */
public interface DeviceMapper extends BaseMapper<DeviceDAO> {


    /**
     * 根据name 查询 不等于当前id的
     * @param name name
     * @param id id
     * @return device
     */
    DeviceDAO selectByNameNotId(String name, Integer id);


    /**
     * 查询设备信息
     * @param deviceQuery 条件
     * @param dataPermission 权限
     * @return list
     */
    List<DeviceVO> selectDevice(@Param("deviceQuery") DeviceQueryDTO deviceQuery,@Param("dataPermission") BaseQuery dataPermission);

    /**
     * 批量修改
     * @param deviceList deviceList
     * @return Integer
     */
    Integer updateByIdList(@Param(value = "deviceList") List<DeviceDAO> deviceList);

    /**
     * 批量保存
     * @param dList 数据
     * @return Integer
     */
    Integer insertList(@Param(value = "dList") List<DeviceDAO> dList);

    /**
     * 根据code 查询 不等于当前id的
     * @param code code
     * @param id id
     * @return List
     */
    List<DeviceDAO> selectByCodeNotId(String code, Integer id);

    /**
     * 根据组织机构id查询设备
     * @param organizationId
     * @return
     */
    List<DeviceDAO> selectByOrganizationId(@Param("organizationId") Integer organizationId);

    /**
     * 根据分组id查询设备
     * @param id
     * @return
     */
    List<DeviceResultVO> selectByCameraGroupId(@Param("query") BaseQuery query, @Param("id") Integer id);


    /**
     * 查询接入服务是否在线
     * @param ids ids
     * @return List
     */
    List<ServiceDAO> selectByIdAndServiceOnline(@Param("ids") List<Integer> ids);

    /**
     * 查询所有的设备和设备下的镜头
     * @return List
     */
    List<DeviceDTO> selectDeviceAndCamera();

    /**
     * 根据分组id查询设备
     * @param organizationId organizationId
     * @return List
     */
    List<DeviceDAO> findByOrganizationId(@Param("organizationId") Integer organizationId);

    /**
     * 根据devId查询设备信息和镜头信息
     * @param devId devId
     * @return list
     */
    DeviceDTO findDeviceAndCameraByDeviceId(Integer devId);
}