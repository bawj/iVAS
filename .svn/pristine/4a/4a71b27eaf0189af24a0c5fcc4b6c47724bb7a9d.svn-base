package com.yn.electricity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yn.electricity.dao.CameraGroupDAO;
import com.yn.electricity.qto.BaseQuery;
import com.yn.electricity.vo.CameraGroupResultVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 镜头组相关sql
 */
public interface CameraGroupMapper extends BaseMapper<CameraGroupDAO > {

    /**
     * 根据name查询 不等于当前id的
     * @param name name
     * @param id id
     * @return CameraGroupDAO
     */
    CameraGroupDAO selectByNameNotId(String name, Integer id);

    /**
     * 删除多个
     * @param ids id
     * @return Integer
     */
    Integer deleteByIdList(@Param("ids") List<Integer> ids);

    /**
     * 根据id查询多个
     * @param ids ids
     * @return List
     */
    List<CameraGroupDAO> selectListById(List<Integer> ids);

    /**
     * 查询所有分组及分组下的设备
     * @return
     */
    List<CameraGroupResultVO> queryAllGroupByIpId(String ipId);


    /**
     * 根据分组id查询
     * @param cameraGroupId cameraGroupId
     * @return List
     */
    List<CameraGroupResultVO> selectByCameraGroupId(Integer cameraGroupId,String ipId);

    List<CameraGroupDAO> selectByCameraGroupIdAndUserIpId(String ipId, Integer cameraGroupId);

}