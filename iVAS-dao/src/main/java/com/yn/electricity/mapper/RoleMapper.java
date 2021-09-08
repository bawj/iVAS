package com.yn.electricity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yn.electricity.dao.Role;
import com.yn.electricity.qto.BaseQuery;
import com.yn.electricity.vo.RoleMenuVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName: RoleMapper
 * @Author: zzs
 * @Date: 2021/2/23 13:59
 * @Description:
 */
public interface RoleMapper extends BaseMapper<Role> {
    /**
     * 角色列表查询
     * @param query
     * @return
     */
    List<Role> selectByPage(BaseQuery query);

    /**
     * 获取用户角色及权限
     * @param userId
     * @return
     */
    List<RoleMenuVo> selectByUserId(@Param("userId") Integer userId, @Param("available") String available);
    /**
     * 获取用户角色及权限
     * @param userId
     * @return
     */
    List<RoleMenuVo> selectByUserIdExt(@Param("userId") Integer userId);

    /**
     * 获取用户角色
     * @param userId
     * @return
     */
    List<Role> queryByUserId(@Param("userId") Integer userId, @Param("available") String available);
}
