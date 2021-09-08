package com.yn.electricity.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yn.electricity.constant.AccountConstant;
import com.yn.electricity.dao.User;
import com.yn.electricity.dao.UserRole;
import com.yn.electricity.enums.ExecutionResultEnum;
import com.yn.electricity.enums.UserLockStatusEnum;
import com.yn.electricity.enums.YesOrNotEnum;
import com.yn.electricity.mapper.CompanyMapper;
import com.yn.electricity.mapper.DepartmentMapper;
import com.yn.electricity.mapper.UserMapper;
import com.yn.electricity.mapper.UserRoleMapper;
import com.yn.electricity.qto.BaseQuery;
import com.yn.electricity.qto.UserDTO;
import com.yn.electricity.request.UserAlterRequest;
import com.yn.electricity.request.UserSaveRequest;
import com.yn.electricity.result.UserResult;
import com.yn.electricity.service.UserService;
import com.yn.electricity.util.*;
import com.yn.electricity.utils.BizBusinessUtils;
import com.yn.electricity.utils.cron.StringUtils;
import com.yn.electricity.vo.UserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName: UserServiceImpl
 * @Author: zzs
 * @Date: 2021/2/23 14:02
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private UserRoleMapper userRoleMapper;
    @Resource
    private DataPermissionUtil dataPermissionUtil;
    @Resource
    private RedisInfoUtil redisInfoUtil;

    @Override
    public String insert(UserSaveRequest entity) {
        ValidationUtils.checkParam(entity);

        User userPhone = checkPhoneOrMail(entity.getPhone(), Integer.valueOf(entity.getAppId()), null);
        if (null != userPhone) {
            BizBusinessUtils.bizBusinessException("手机号已存在 phone:{}", entity.getPhone());
        }

        User userEMail = checkPhoneOrMail(null, Integer.valueOf(entity.getAppId()), entity.getEmail());
        if (null != userEMail) {
            BizBusinessUtils.bizBusinessException("邮箱已存在 eMail:{}", entity.getEmail());
        }

        User user = buildSaveUserRequest(entity);

        String salt = PasswordHelper.generatorSalt();
        user.setSalt(salt);
        user.setPassword(PasswordHelper.encryptPassword(entity.getPassword(), user.getSalt()));

        int insert = userMapper.insert(user);
//        if(!CollectionUtils.isEmpty(entity.getRoleIdList())){
//            for (Integer roleId : entity.getRoleIdList()) {
//                UserRole userRole = new UserRole();
//                userRole.setUserId(user.getId());
//                userRole.setRoleId(roleId);
//                userRoleMapper.insert(userRole);
//            }
//        }

        UserRole userRole = new UserRole();
        userRole.setUserId(user.getId());
        userRole.setRoleId(entity.getRoleId());
        userRoleMapper.insert(userRole);


        if (insert < 1) {
            BizBusinessUtils.bizBusinessException(ExecutionResultEnum.FAIL.getChineseName());
        }
        return ExecutionResultEnum.SUCCESS.getEnglishName();
    }

    @Override
    public String updateById(UserAlterRequest entity) {
        User userDao = userMapper.selectById(entity.getId());
        if (null == userDao) {
            BizBusinessUtils.bizBusinessException("用户不存在 phone:{}", entity.getPhone());
        }
        UserResult userResult = redisInfoUtil.getUserResult();
        if(AccountConstant.USER_NAME.equals(userDao.getPhone())){
            BizBusinessUtils.bizBusinessException("安全考虑 超级管理员由工程师创建不可修改");
        }
        if(StringUtils.isNotEmpty(entity.getDepCode())){
            if(userResult.getPhone().equals(userDao.getPhone()) && !entity.getDepCode().equals(userDao.getDepCode())){
                BizBusinessUtils.bizBusinessException("不可修改自身部门,如需修改请联系上级");
            }
        }
        if(null != entity.getRoleId()){
            List<Integer> list = userRoleMapper.selectByUserId(userDao.getId());
            if(!CollectionUtils.isEmpty(list)){
                list.forEach(roleId -> {
                    if(userResult.getPhone().equals(userDao.getPhone()) && !entity.getRoleId().equals(roleId)){
                        BizBusinessUtils.bizBusinessException("不可修改自身角色,如需修改请联系上级");
                    }
                });
            }
        }

        User user = new User();
        BeanUtils.copyProperties(entity, user);
        user.setEMail(entity.getEmail());

        if (StringUtils.isNotEmpty(user.getPhone())) {
            if (!user.getPhone().equals(userDao.getPhone())) {
                User userPhone = checkPhoneOrMail(user.getPhone(), userDao.getAppId(), null);
                if (null != userPhone && !userPhone.getId().equals(user.getId())) {
                    BizBusinessUtils.bizBusinessException("手机号已被其他用户使用 phone:{}", user.getPhone());
                }
            }
        }
        if (StringUtils.isNotEmpty(user.getEMail())) {
            if (!user.getEMail().equals(userDao.getEMail())) {
                User useEMail = checkPhoneOrMail(null, userDao.getAppId(), user.getEMail());
                if (null != useEMail && !useEMail.getId().equals(user.getId())) {
                    BizBusinessUtils.bizBusinessException("邮箱已被其他用户使用 eMail:{}", user.getEMail());
                }
            }
        }

        // 密码不等于空 就是修改密码
        if (StringUtils.isNotEmpty(user.getPassword())) {
            String salt = PasswordHelper.generatorSalt();
            user.setSalt(salt);
            user.setPassword(PasswordHelper.encryptPassword(entity.getPassword(), user.getSalt()));
        }

        int i = userMapper.updateById(user);
//        if(!CollectionUtils.isEmpty(entity.getRoleIdList())){
//            UserRole delUserRole = new UserRole();
//            delUserRole.setUserId(entity.getId());
//            Wrapper<UserRole> wrapper = new QueryWrapper<>(delUserRole);
//            userRoleMapper.delete(wrapper);
//
//            for (Integer roleId : entity.getRoleIdList()) {
//                UserRole userRole = new UserRole();
//                userRole.setUserId(user.getId());
//                userRole.setRoleId(roleId);
//                userRoleMapper.insert(userRole);
//            }
//        }
        Integer roleId = entity.getRoleId();
        if (roleId != null) {
            UserRole delUserRole = new UserRole();
            delUserRole.setUserId(user.getId());
            Wrapper<UserRole> wrapper = new QueryWrapper<>(delUserRole);
            userRoleMapper.delete(wrapper);

            UserRole userRole = new UserRole();
            userRole.setUserId(user.getId());
            userRole.setRoleId(roleId);
            userRoleMapper.insert(userRole);
        }

        if (i < 1) {
            BizBusinessUtils.bizBusinessException(ExecutionResultEnum.FAIL.getChineseName());
        }
        return ExecutionResultEnum.SUCCESS.getEnglishName();
    }

    @Override
    public String updateBatchIds(List<Integer> idList) {
        if (CollectionUtils.isEmpty(idList)) {
            BizBusinessUtils.bizBusinessException("idList is not null");
        }
        idList.forEach(id -> {
            User userDao = userMapper.selectById(id);
            if(null == userDao){
                BizBusinessUtils.bizBusinessException("用户不存在");
            }
            if(AccountConstant.USER_NAME.equals(userDao.getPhone())){
                BizBusinessUtils.bizBusinessException("安全考虑超级管理员由工程师创建不可删除");
            }
        });

        int i = userMapper.deleteBatchIds(idList);
        if (i < 1) {
            BizBusinessUtils.bizBusinessException(ExecutionResultEnum.FAIL.getChineseName());
        }
        return ExecutionResultEnum.SUCCESS.getEnglishName();
    }

    /**
     * 检查邮箱或者手机号是否存在
     *
     * @param phone
     * @param appId
     * @return
     */
    private User checkPhoneOrMail(String phone, Integer appId, String eMail) {
        User userDto = new User();
        userDto.setPhone(phone);
        userDto.setEMail(eMail);
        userDto.setAppId(appId);
        Wrapper<User> queryWrapper = new QueryWrapper<>(userDto);
        return userMapper.selectOne(queryWrapper);
    }

    @Override
    public PageInfo<UserResult> selectListPage(UserDTO userDTO) {

        List<String> idList = null;
        if (StringUtils.isNotEmpty(userDTO.getIdList())) {
            String[] split = userDTO.getIdList().split(",");
            idList = Arrays.asList(split);
        }

//        Company company = companyMapper.selectByCode(userDTO.getDepCode());
//        if(null != company && company.getIsTop().equals(YesOrNotEnum.Y.getCode())){
//            List<Company> companyList = companyMapper.selectByParentCode(company.getCode());
//            companyList.addAll(Collections.singletonList(company));
//            List<String> list = companyList.stream().map(Company::getCode).collect(Collectors.toList());
//
//            userDTO.setCompanyCodeList(list);
//        }else {
//            List<Department> departmentList = departmentMapper.selectByCode(Collections.singletonList(userDTO.getDepCode()), null);
//            if(!CollectionUtils.isEmpty(departmentList)){
//                List<Department> child = departmentMapper.selectByParentCode(Collections.singletonList(userDTO.getDepCode()), null);
//                departmentList.addAll(child);
//                while (!CollectionUtils.isEmpty(child)) {
//                    List<String> stringList = child.stream().map(Department::getCode).collect(Collectors.toList());
//                    child = departmentMapper.selectByParentCode(stringList, null);
//                }
//            }
//            List<String> list = departmentList.stream().map(Department::getCode).collect(Collectors.toList());
//
//            userDTO.setDepCodeList(list);
//        }

        if(StringUtils.isEmpty(userDTO.getDepCode())){
            BaseQuery dataPermission = dataPermissionUtil.getDataPermission();
            userDTO.setCompanyCodeList(dataPermission.getCompanyCodeList());
            userDTO.setDepCodeList(dataPermission.getDepCodeList());
        }else {
            userDTO.setDepCodeList(Collections.singletonList(userDTO.getDepCode()));
        }

        PageHelper.startPage(userDTO.getPageNum(), userDTO.getPageSize());
        List<UserVo> users = userMapper.selectByPage(userDTO, idList);

        if (!CollectionUtils.isEmpty(users)){
            for (UserVo user : users) {
                List<Integer> roleIdList = user.getRoleIdList();
                if (!CollectionUtils.isEmpty(roleIdList)){
                    Integer integer = roleIdList.get(0);
                    user.setRoleId(integer);
                }
            }
        }

        PageInfo<UserVo> page = new PageInfo<>(users);

        List<UserResult> resultList = users.stream().map(u -> {
            UserResult result = new UserResult();
            BeanUtils.copyProperties(u, result);
            result.setEmail(u.getEMail());
            return result;
        }).collect(Collectors.toList());

        PageInfo<UserResult> pageInfo = new PageInfo(resultList);
        pageInfo.setTotal(page.getTotal());
        pageInfo.setPageNum(page.getPageNum());
        pageInfo.setPageSize(page.getPageSize());
        return pageInfo;
    }

    @Override
    public User selectOne(UserDTO userDTO) {

        User user = new User();
        user.setUserName(userDTO.getUserName());

        Wrapper<User> queryWrapper = new QueryWrapper<>(user);
        user = userMapper.selectOne(queryWrapper);

        return user;
    }

    /**
     * 构建新增用户对象
     *
     * @param entity
     * @return
     */
    private User buildSaveUserRequest(UserSaveRequest entity) {
        User user = new User();

        BeanUtils.copyProperties(entity, user);

        user.setEMail(entity.getEmail());
        user.setIpId(SequenceGenerator.sequence());
        user.setAppId(Integer.valueOf(entity.getAppId()));
        user.setAvailable(entity.getAvailable());
        user.setLockStatus(UserLockStatusEnum.NORMAL.getCode());
        user.setCreateTime(new Date());
        user.setModifyTime(new Date());
        user.setIsDelete(YesOrNotEnum.N.getCode());
        return user;
    }

}
