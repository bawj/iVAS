package com.yn.electricity.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.yn.electricity.constant.AccountConstant;
import com.yn.electricity.dao.*;
import com.yn.electricity.enums.ExecutionResultEnum;
import com.yn.electricity.mapper.*;
import com.yn.electricity.qto.RoleDTO;
import com.yn.electricity.request.RoleAlterRequest;
import com.yn.electricity.request.RoleSaveRequest;
import com.yn.electricity.result.RoleResult;
import com.yn.electricity.service.RoleService;
import com.yn.electricity.util.SequenceGenerator;
import com.yn.electricity.util.ValidationUtils;
import com.yn.electricity.utils.BizBusinessUtils;
import com.yn.electricity.utils.cron.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName: RoleServiceImpl
 * @Author: zzs
 * @Date: 2021/2/23 14:03
 * @Description:
 */
@Service
public class RoleServiceImpl implements RoleService {
    private final static String RIGHT_CENTRE_K = "\\[";
    private final static String LEFT_CENTRE_K = "]";
    private final static String SEPARATOR = ",";
    private final static String DOUBLE_QUOTING = "";

    @Resource
    private RoleMapper roleMapper;
    @Resource
    private MenuMapper menuMapper;
    @Resource
    private RoleMenuMapper roleMenuMapper;
    @Resource
    private RoleDeptMapper roleDeptMapper;
    @Resource
    private UserRoleMapper userRoleMapper;
    @Resource
    private DepartmentMapper departmentMapper;

    @Override
    public String insert(RoleSaveRequest entity) {
        ValidationUtils.checkParam(entity);

        String roleName = entity.getRoleName();
        HashMap<String, Object> roleMap = Maps.newHashMap();
        roleMap.put("role_name" , roleName);
        List<Role> roles = roleMapper.selectByMap(roleMap);
        if (!CollectionUtils.isEmpty(roles)){
            BizBusinessUtils.bizBusinessException("?????????????????? roleName:{}", "" + roleName);
        }

        Role role = new Role();
        role.setRoleName(entity.getRoleName());
        role.setAppId(entity.getAppId());
        role.setIpRoleId(SequenceGenerator.sequence());
        role.setAvailable(entity.getAvailable());
        role.setCreateTime(new Date());
        role.setModifyTime(new Date());
        role.setDescription(entity.getDescription());
        role.setExt(entity.getMenuId());
        int id = roleMapper.insert(role);

        Role roleDao = roleMapper.selectById(role.getId());
        if(null == roleDao){
            BizBusinessUtils.bizBusinessException("???????????????????????? id:{}", "" +roleDao.getId());
        }

        // ??????????????????
        if(StringUtils.isNotEmpty(entity.getMenuId())){
            List<String> idList = convert(entity.getMenuId());

            List<Menu> menuList = menuMapper.selectBatchIds(idList);
            if (null == menuList || idList.size() > menuList.size()) {
                BizBusinessUtils.bizBusinessException("??????????????????id");
            }
            roleMenuMapper.insertList(idList, roleDao.getIpRoleId());
        }

        // ??????????????????
        if(StringUtils.isNotEmpty(entity.getDataType())){
            List<RoleDept> deptList = buildRoleDept(entity.getDepCode(), roleDao.getIpRoleId(), entity.getDataType());
            roleDeptMapper.insert(deptList);
        }

        if(id < 1){
            BizBusinessUtils.bizBusinessException(ExecutionResultEnum.FAIL.getChineseName());
        }
        return ExecutionResultEnum.SUCCESS.getEnglishName();
    }

    private static List<String> convert(String menuId){
        String s1 = menuId.replaceAll(RIGHT_CENTRE_K, DOUBLE_QUOTING).replaceAll(LEFT_CENTRE_K, DOUBLE_QUOTING);
        s1 = s1.replaceAll("\\\"", "").replaceAll(" ", "");
        List<String> stringList = Arrays.asList(s1.split(SEPARATOR));
        List<String> idList = stringList.stream().distinct().collect(Collectors.toList());
        try {
            idList.stream().map(Integer::parseInt).collect(Collectors.toList());
        }catch (Exception e){
            BizBusinessUtils.bizBusinessException("menuId???????????????int ???????????????????????????????????? menuId:{}", idList.toString());
        }
        return idList;
    }

    @Override
    public String updateById(RoleAlterRequest entity) {

        Role roleDao = roleMapper.selectById(entity.getId());
        if(null == roleDao){
            BizBusinessUtils.bizBusinessException("????????????????????? id:{}", "" +entity.getId());
        }
        if(AccountConstant.IP_ROLE_ID.equals(roleDao.getIpRoleId())){
            BizBusinessUtils.bizBusinessException("????????????????????????????????????????????????");
        }

        HashMap<String, Object> roleMap = Maps.newHashMap();
        roleMap.put("role_name" , entity.getRoleName());
        List<Role> roles = roleMapper.selectByMap(roleMap);
        if (!CollectionUtils.isEmpty(roles)){
            roles.stream().forEach(r->{
                if(!r.getId().equals(entity.getId())){
                    BizBusinessUtils.bizBusinessException("?????????????????? roleName:{}", "" + entity.getRoleName());
                }
            });
        }

        Role role = new Role();
        role.setId(entity.getId());
        role.setRoleName(entity.getRoleName());
        role.setAvailable(entity.getAvailable());
        role.setModifyTime(new Date());
        role.setDescription(entity.getDescription());
        role.setExt(entity.getMenuId());
        int i = roleMapper.updateById(role);

        // ??????????????????
        if(StringUtils.isNotEmpty(entity.getMenuId())){
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setIpRoleId(roleDao.getIpRoleId());
            Wrapper<RoleMenu> wrapper = new QueryWrapper<>(roleMenu);
            roleMenuMapper.delete(wrapper);
            List<String> idList = convert(entity.getMenuId());
            roleMenuMapper.insertList(idList, roleDao.getIpRoleId());
        }

        // ??????????????????
        if(StringUtils.isNotEmpty(entity.getDataType())){
            RoleDept roleDeptDel = new RoleDept();
            roleDeptDel.setIpRoleId(roleDao.getIpRoleId());
            Wrapper<RoleDept> wrapper = new QueryWrapper<>(roleDeptDel);
            roleDeptMapper.delete(wrapper);

            List<RoleDept> deptList = buildRoleDept(entity.getDepCode(), roleDao.getIpRoleId(), entity.getDataType());
            roleDeptMapper.insert(deptList);
        }

        if(i < 1){
            BizBusinessUtils.bizBusinessException(ExecutionResultEnum.FAIL.getChineseName());
        }
        return ExecutionResultEnum.SUCCESS.getEnglishName();
    }

    /**
     * ????????????????????????
     * @param depCode
     * @param ipRoleId
     * @param dataType
     * @return
     */
    private List<RoleDept> buildRoleDept(String depCode, String ipRoleId, String dataType){
        String[] depCodeList = depCode.split(",");
        List<RoleDept> list = Arrays.stream(depCodeList).map(c -> {
            RoleDept roleDept = new RoleDept();
            roleDept.setIpRoleId(ipRoleId);
            roleDept.setDepCode(c);
            roleDept.setDataType(dataType);
            roleDept.setCreateTime(new Date());
            roleDept.setModifyTime(new Date());
            return roleDept;
        }).collect(Collectors.toList());
        return list;
    }

    @Override
    public PageInfo selectPage(RoleDTO roleDTO) {
        PageHelper.startPage(roleDTO.getPageNum(), roleDTO.getPageSize());
        List<Role> roles = roleMapper.selectByPage(roleDTO);
        PageInfo<Role> page = new PageInfo<>(roles);

        List<RoleResult> resultList = roles.stream().map(u -> {
            RoleResult result = new RoleResult();
            BeanUtils.copyProperties(u, result);
            return result;
        }).collect(Collectors.toList());

        PageInfo<RoleResult> pageInfo = new PageInfo(resultList);
        BeanUtils.copyProperties(page, pageInfo);
        return pageInfo;
    }

    @Override
    public List<String> selectByIpRoleId(String ipRoleId) {
        if(StringUtils.isEmpty(ipRoleId)){
            BizBusinessUtils.bizBusinessException("ipRoleId is not empty");
        }

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("ip_role_id", ipRoleId);
        List<Role> roles = roleMapper.selectByMap(map);
        if(CollectionUtils.isEmpty(roles)){
            return new ArrayList<>();
        }
        return roles.stream().map(Role::getExt).collect(Collectors.toList());
    }

    @Override
    public List<String> selectByRoleDep(String ipRoleId) {
        if(StringUtils.isEmpty(ipRoleId)){
            BizBusinessUtils.bizBusinessException("ipRoleId is not empty");
        }

        List<RoleDept> roleDept = roleDeptMapper.selectByIpRoleId(Arrays.asList(ipRoleId));
        List<String> list = roleDept.stream().map(r->r.getDepCode()).collect(Collectors.toList());
//        list = list.stream().distinct().collect(Collectors.toList());
        return list;
    }

    private List<String> getDepCode(List<String> list, List<String> lists){
        List<String> stringList = Lists.newArrayList();
        list.stream().forEach(l->{
            Department department = new Department();
            department.setCode(l);
            Wrapper<Department> queryWrapper = new QueryWrapper<>(department);
            Department depart = departmentMapper.selectOne(queryWrapper);
            if(null != depart){
                lists.add(depart.getParentCode());
                stringList.add(depart.getParentCode());
            }
        });

        if(!CollectionUtils.isEmpty(stringList)){
           return getDepCode(stringList, lists);
        }
        return lists;
    }

    @Override
    public RoleResult selectOne(Integer id) {
        if(null == id){
            BizBusinessUtils.bizBusinessException("id is not null");
        }

        Role role = roleMapper.selectById(id);
        if(null == role){
            BizBusinessUtils.bizBusinessException("???????????????");
        }
        RoleResult result = new RoleResult();
        BeanUtils.copyProperties(role, result);

        List<RoleDept> roleDept = roleDeptMapper.selectByIpRoleId(Arrays.asList(role.getIpRoleId()));
        if(!CollectionUtils.isEmpty(roleDept)){
            result.setDataType(roleDept.get(0).getDataType());
        }
        return result;
    }

    @Override
    public String deleteById(List<Integer> idList) {
        if(CollectionUtils.isEmpty(idList)){
            BizBusinessUtils.bizBusinessException("idList is not null");
        }
        for (Integer id: idList) {
            Role role = roleMapper.selectById(id);
            if(null == role){
                BizBusinessUtils.bizBusinessException("??????id?????????");
            }
            if(AccountConstant.IP_ROLE_ID.equals(role.getIpRoleId())){
                BizBusinessUtils.bizBusinessException("????????????????????????????????????????????????");
            }
        }

        List<Role> roleList = roleMapper.selectBatchIds(idList);
        if(CollectionUtils.isEmpty(roleList)){
            BizBusinessUtils.bizBusinessException("??????id?????????");
        }

        // ??????????????????
        List<String> stringList = roleList.stream().map(r -> r.getIpRoleId()).collect(Collectors.toList());
        roleMenuMapper.deleteByIpRoleId(stringList);
        roleDeptMapper.selectByIpRoleId(stringList);
        userRoleMapper.deleteBatchIds(idList);
        int i = roleMapper.deleteBatchIds(idList);
        if(i < 1){
            BizBusinessUtils.bizBusinessException(ExecutionResultEnum.FAIL.getChineseName());
        }
        return ExecutionResultEnum.SUCCESS.getEnglishName();
    }
}
