package com.yn.electricity.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.yn.electricity.dao.*;
import com.yn.electricity.enums.DataPermissonTypeEnum;
import com.yn.electricity.enums.ExecutionResultEnum;
import com.yn.electricity.mapper.DepartmentMapper;
import com.yn.electricity.mapper.RoleDeptMapper;
import com.yn.electricity.mapper.RoleMapper;
import com.yn.electricity.mapper.UserMapper;
import com.yn.electricity.request.DepartmentAlterRequest;
import com.yn.electricity.request.DepartmentSaveRequest;
import com.yn.electricity.result.UserResult;
import com.yn.electricity.service.DepartmentService;
import com.yn.electricity.util.RedisInfoUtil;
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
 * @ClassName: DepartmentService
 * @Author: zzs
 * @Date: 2021/3/12 9:48
 * @Description:
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Resource
    private DepartmentMapper departmentMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private RoleDeptMapper roleDeptMapper;
    @Resource
    private RedisInfoUtil redisInfoUtil;

    @Override
    public String insert(DepartmentSaveRequest request) {
        ValidationUtils.checkParam(request);

        checkDepNameExist(request.getName(), request.getParentCode());

        Department department = new Department();
        BeanUtils.copyProperties(request, department);
        department.setCreateTime(new Date());
        department.setModifyTime(new Date());
        department.setCode(SequenceGenerator.sequence());

        int i = departmentMapper.insert(department);
        if(i < 1){
            BizBusinessUtils.bizBusinessException(ExecutionResultEnum.FAIL.getChineseName());
        }
        return ExecutionResultEnum.SUCCESS.getEnglishName();
    }

    @Override
    public String updateById(DepartmentAlterRequest request) {
        ValidationUtils.checkParam(request);

        Department department = departmentMapper.selectById(request.getId());
        if(null == department){
            BizBusinessUtils.bizBusinessException("??????????????? id:{}", "" + request.getId());
        }

        if(StringUtils.isNotEmpty(request.getName()) && !department.getName().equals(request.getName())){
            checkDepNameExist(request.getName(), department.getParentCode());
        }
        String parentCode = request.getParentCode();
        String code = department.getCode();
        if (parentCode.equals(code)){
            BizBusinessUtils.bizBusinessException("??????????????????");
        }

        BeanUtils.copyProperties(request, department);

        int i = departmentMapper.updateById(department);
        if(i < 1){
            BizBusinessUtils.bizBusinessException(ExecutionResultEnum.FAIL.getChineseName());
        }
        return ExecutionResultEnum.SUCCESS.getEnglishName();
    }

    @Override
    public String deleteById(List<Integer> idList) {
        if(CollectionUtils.isEmpty(idList)){
            BizBusinessUtils.bizBusinessException("idList is not empty");
        }

        List<Department> batchIds = departmentMapper.selectBatchIds(idList);
        if(!CollectionUtils.isEmpty(batchIds)){
            for (Department dep: batchIds ) {
                Map<String, Object> columnMap = Maps.newHashMap();
                columnMap.put("parent_code", dep.getCode());
                List<Department> list = departmentMapper.selectByMap(columnMap);
                if(!CollectionUtils.isEmpty(list)){
                    BizBusinessUtils.bizBusinessException("???????????????????????????????????????");
                }
            }
        }

        // ???????????????????????????????????????
        int count = userMapper.selectByDepCode(idList);
        if(count > 0){
            BizBusinessUtils.bizBusinessException("??????????????????????????????,????????????!");
        }

        int i = departmentMapper.deleteById(idList);
        if(i < 1){
            BizBusinessUtils.bizBusinessException(ExecutionResultEnum.FAIL.getChineseName());
        }
        return ExecutionResultEnum.SUCCESS.getEnglishName();
    }

    @Override
    public List<Map<String, Object>> queryList(String disabled, String available) {
        UserResult userResult = redisInfoUtil.getUserResult();
        User user = userMapper.selectById(userResult.getId());

        // ????????????????????????
        List<Role> roleList = roleMapper.queryByUserId(user.getId(), available);
        if(CollectionUtils.isEmpty(roleList)){
            return Lists.newArrayList();
        }
        List<String> ipRoleIdList = roleList.stream().map(Role::getIpRoleId).collect(Collectors.toList());

        // ?????????????????????
        List<RoleDept> roleDeptList = roleDeptMapper.selectByIpRoleId(ipRoleIdList);
        if(CollectionUtils.isEmpty(roleDeptList)){
            return Lists.newArrayList();
        }
        List<String> list = roleDeptList.stream().map(RoleDept::getDepCode).collect(Collectors.toList());

        // ??????????????? ?????????????????????????????????????????????????????? ?????????????????????????????? ???????????????????????????????????????
        Set<String> set = new TreeSet<>(String::compareTo);
        set.addAll(list);
        List<String> depCodeList = new ArrayList<>(set);

        // ?????????????????????????????????
        List<RoleDept> all = roleDeptList.stream().filter(d -> DataPermissonTypeEnum.ALL_DATA.getCode()
                .equals(d.getDataType())).collect(Collectors.toList());
        // ?????????????????????
        List<RoleDept> current = roleDeptList.stream().filter(d -> DataPermissonTypeEnum.CURRENT_DEP_DATA.getCode()
                .equals(d.getDataType())).collect(Collectors.toList());
        if(!CollectionUtils.isEmpty(all)){
            // ??????????????????????????????????????????(???????????????)
            List<Department> departmentList = departmentMapper.selectByParentCode(Collections.singletonList(user.getCompanyCode()), available);
            departmentList = departmentList.stream().sorted(Comparator.comparing(Department::getSort)).collect(Collectors.toList());

            return buildDepTree(departmentList, user.getCompanyCode(), disabled, available, DataPermissonTypeEnum.ALL_DATA.getCode());
        }else if(!CollectionUtils.isEmpty(current)){
            // ??????????????????????????????????????????(???????????????)
            List<Department> departmentList = departmentMapper.selectByCode(Collections.singletonList(user.getDepCode()), available);

            return buildDepTree(departmentList, user.getDepCode(), disabled, available, DataPermissonTypeEnum.CURRENT_DEP_DATA.getCode());
        }else {
            // ???????????????????????????????????????
            List<Department> departmentList = departmentMapper.selectByCode(depCodeList, available);
            if(CollectionUtils.isEmpty(departmentList)){
                BizBusinessUtils.bizBusinessException("???????????????????????? userName:{}", "" + user.getUserName());
            }
            departmentList = departmentList.stream().sorted(Comparator.comparing(Department::getSort)).collect(Collectors.toList());

            return buildDepTree(departmentList, user.getDepCode(), disabled, available, DataPermissonTypeEnum.CUS_DEFINITION_DATA.getCode());
        }
    }

    /**
     * ???????????????
     * @param departmentList
     * @param depCode
     * @return
     */
    private List<Map<String, Object>> buildDepTree(List<Department> departmentList, String depCode, String disabled, String available, String dataCode){
        List<Department> arrayList = Lists.newArrayList();
        List<Department> list1 = getDepartChild(departmentList, arrayList, available);
        list1.addAll(departmentList);
        // ??????
        Set<Department> set = new TreeSet<>(Comparator.comparing(Department::getCode));
        set.addAll(list1);
        list1 = new ArrayList<>(set);

        list1.sort(Comparator.comparing(Department::getSort));
        // ????????????
        List<Map<String, Object>> depListTop ;
        if(DataPermissonTypeEnum.ALL_DATA.getCode().equals(dataCode)){
            depListTop = list1.stream().filter(m ->
                    depCode.equals(m.getParentCode())).map(dep -> buildMap(dep, disabled)).collect(Collectors.toList());
        }else{
            depListTop = list1.stream().filter(m ->
                    depCode.equals(m.getCode())).map(dep -> buildMap(dep, disabled)).collect(Collectors.toList());
        }

        // ????????????
        return getMaps(list1, depListTop, disabled);
    }

    private List<Map<String, Object>> getMaps(List<Department> list1, List<Map<String, Object>> depListTop, String disabled) {
        depListTop.forEach(dep -> {
            List<Map<String, Object>> child = getChildDep(list1, dep.get("code").toString(), disabled);
            if(!CollectionUtils.isEmpty(child)) {
                dep.put("child", child);
            }
        });
        return depListTop;
    }

    private List<Map<String, Object>> getChildDep(List<Department> depList, String code, String disabled){
        // ??????
        List<Map<String, Object>> mapList = new ArrayList<>();
        for (Department department : depList) {
            if (String.valueOf(department.getParentCode()).equals(code)) {
                Map<String, Object> map = buildMap(department, disabled);
                mapList.add(map);
            }
        }

        // ??????????????????????????????
        return getMaps(depList, mapList, disabled);
    }

    private Map<String, Object> buildMap(Department department, String disabled){
        Map<String, Object> map = Maps.newHashMap();
        map.put("id", department.getId());
        map.put("name", department.getName());
        map.put("available", department.getAvailable());
        map.put("code", department.getCode());
        map.put("parentCode", department.getParentCode());
        map.put("sort", department.getSort());
        map.put("createTime", department.getCreateTime());
        if(StringUtils.isNotEmpty(disabled) && department.getCode().equals(disabled)){
            map.put("disabled", true);
        }
        return map;
    }

    /**
     * ??????????????????????????????
     * @param departList
     * @param departmentList
     * @return
     */
    private List<Department> getDepartChild(List<Department> departList, List<Department> departmentList, String available){
        // ??????????????????
        List<Department> listItm = Lists.newArrayList();
        for (Department department : departList) {
            List<Department> depart = departmentMapper.selectByParentCode(Collections.singletonList(department.getCode()), available);
            if (!CollectionUtils.isEmpty(depart)) {
                departmentList.addAll(depart);
                listItm.addAll(depart);
            }
        }

        if(!CollectionUtils.isEmpty(listItm)){
            return getDepartChild(listItm, departmentList, available);
        }
        return departmentList;
    }

    /**
     * ????????????????????????
     * @param name
     * @param parentCode
     */
    private void checkDepNameExist(String name, String parentCode){
        Department depDto = new Department();
        depDto.setName(name);
        depDto.setParentCode(parentCode);
        Wrapper<Department> queryWrapper = new QueryWrapper<>(depDto);
        Department checkName = departmentMapper.selectOne(queryWrapper);
        if(null != checkName){
            BizBusinessUtils.bizBusinessException("????????????????????? name:{}", name);
        }
    }
}
