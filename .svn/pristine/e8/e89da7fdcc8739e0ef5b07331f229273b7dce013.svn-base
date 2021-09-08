package com.yn.electricity.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yn.electricity.dao.TvWallDAO;
import com.yn.electricity.qto.BaseQuery;
import com.yn.electricity.vo.TvWallVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 电视墙数据查询
 */
public interface TvWallMapper extends BaseMapper<TvWallDAO> {

    /**
     * 查询是否有重复的名称
     *
     * @param id   id
     * @param name name
     * @return TvWallDAO
     */
    TvWallDAO selectByNameNotId(Integer id, String name);

    /**
     * 根据配置名称和状态查询
     *
     * @param name   配置名称
     * @param status 配置状态
     * @return list
     */
    List<TvWallVO> selectByNameAndStatus(@Param("dataPermission") BaseQuery dataPermission, @Param("name") String name,@Param("status") Boolean status);
}