package com.yn.electricity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yn.electricity.dao.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName: MenuMapper
 * @Author: zzs
 * @Date: 2021/2/23 14:00
 * @Description: 权限管理
 */
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 通过父级code查询子集
     * @param parentCode
     * @return
     */
    List<Menu> selectByPCod(String parentCode);

    /**
     * 通过子集pcode查询父级
     * @param code
     * @returnc
     */
    List<Menu> selectByCod(String code);

    /**
     * 根据id删除菜单
     * @param list
     * @return
     */
    int deleteById(List<String> list);

    /**
     * 查询权限
     * @param ipRoleId
     * @return
     */
    List<Menu> selectByRoleId(@Param("ipRoleId") String ipRoleId, @Param("available") String available);

}
