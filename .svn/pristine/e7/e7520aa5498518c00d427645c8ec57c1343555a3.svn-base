package com.yn.electricity.service;

import com.github.pagehelper.PageInfo;
import com.yn.electricity.dao.User;
import com.yn.electricity.qto.UserDTO;
import com.yn.electricity.request.UserAlterRequest;
import com.yn.electricity.request.UserSaveRequest;
import com.yn.electricity.result.UserResult;

import java.util.List;


/**
 * @ClassName: UserService
 * @Author: zzs
 * @Date: 2021/2/23 14:02
 * @Description: 用户信息管理
 */
public interface UserService {
    /**
     * 新增应用
     * @param entity
     * @return
     */
    String insert(UserSaveRequest entity);

    /**
     * 修改应用
     * @param entity
     * @return
     */
    String updateById(UserAlterRequest entity);


    /**
     * 逻辑删除用户 一个或多个
     * @param idList
     * @return
     */
    String updateBatchIds(List<Integer> idList);

    /**
     * 列表查询 分页
     * @param userDTO
     * @return
     */
    PageInfo<UserResult> selectListPage(UserDTO userDTO);

    /**
     * 查询一个用户
     * @param userDTO
     * @return
     */
    User selectOne(UserDTO userDTO);

}
