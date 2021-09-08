package com.yn.electricity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yn.electricity.dao.User;
import com.yn.electricity.qto.BaseQuery;
import com.yn.electricity.vo.UserRoleMenuVo;
import com.yn.electricity.vo.UserVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName: UserMapper
 * @Author: zzs
 * @Date: 2021/1/18 14:48
 * @Description:
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 逻辑删除用户
     * @param idList
     * @return
     */
    int updateBatchIds(List idList);

    /**
     * 获取用户列表
     * @param query
     * @return
     */
    List<UserVo> selectByPage(@Param("query") BaseQuery query, @Param("idList") List<String> idList);

    /**
     * 查询用户及权限
     */
    UserRoleMenuVo selectByLoginName(BaseQuery query);

    /**
     * 查询部门下是否存在用户
     * @param list
     * @return
     */
    int selectByDepCode(List<Integer> list);

}
