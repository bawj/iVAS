package com.yn.electricity.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.yn.electricity.constant.AccountConstant;
import com.yn.electricity.dao.Menu;
import com.yn.electricity.dao.RoleMenu;
import com.yn.electricity.enums.DataEnum;
import com.yn.electricity.enums.ExecutionResultEnum;
import com.yn.electricity.enums.MenuTypeEnum;
import com.yn.electricity.enums.YesOrNotEnum;
import com.yn.electricity.mapper.MenuMapper;
import com.yn.electricity.mapper.RoleMapper;
import com.yn.electricity.mapper.RoleMenuMapper;
import com.yn.electricity.qto.MenuDTO;
import com.yn.electricity.request.MenuAlterRequest;
import com.yn.electricity.request.MenuSaveRequest;
import com.yn.electricity.result.UserResult;
import com.yn.electricity.service.MenuService;
import com.yn.electricity.util.RedisInfoUtil;
import com.yn.electricity.utils.BizBusinessUtils;
import com.yn.electricity.utils.cron.StringUtils;
import com.yn.electricity.vo.RoleMenuVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName: MenuServiceImpl
 * @Author: zzs
 * @Date: 2021/2/23 14:04
 * @Description:
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Resource
    private MenuMapper menuMapper;
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private RoleMenuMapper roleMenuMapper;
    @Resource
    private RedisInfoUtil redisInfoUtil;

    @Override
    public String insert(MenuSaveRequest entity) {
        String code = entity.getCode();
        String parentCode = entity.getParentCode();

        HashMap<String, Object> map = Maps.newHashMap();
        map.put("code" , code);
        map.put("parent_code" , parentCode);
        List<Menu> menus = menuMapper.selectByMap(map);
        if (!CollectionUtils.isEmpty(menus)){
            BizBusinessUtils.bizBusinessException("????????????");
        }

        Menu menu = new Menu();
        BeanUtils.copyProperties(entity, menu);
        menu.setCreateTime(new Date());
        int i = menuMapper.insert(menu);
        if(i < 1){
            BizBusinessUtils.bizBusinessException(ExecutionResultEnum.FAIL.getChineseName());
        }

        Integer id = menu.getId();
        RoleMenu roleMenu = new RoleMenu();
        roleMenu.setIpRoleId("20213011616138338422195382670");
        roleMenu.setMenuId(String.valueOf(id));
        roleMenuMapper.insert(roleMenu);
        return ExecutionResultEnum.SUCCESS.getEnglishName();
    }

    @Override
    public String updateById(MenuAlterRequest entity) {
        UserResult userResult = redisInfoUtil.getUserResult();
        Menu menu1 = menuMapper.selectById(entity.getId());
        if(null == menu1){
            BizBusinessUtils.bizBusinessException("??????????????? id:{}", "" + entity.getId());
        }
        if(!AccountConstant.USER_NAME.equals(userResult.getPhone())){
            BizBusinessUtils.bizBusinessException("?????????????????????????????????????????????????????????");
        }

        Menu queryParent = new Menu();
        queryParent.setMenuName(entity.getMenuName());
        queryParent.setParentCode(entity.getParentCode());
        Wrapper<Menu> query = new QueryWrapper<>(queryParent);
        List<Menu> menuList = menuMapper.selectList(query);

        if(!CollectionUtils.isEmpty(menuList)){
            if(!menu1.getId().equals(menuList.get(0).getId()) ){
                BizBusinessUtils.bizBusinessException("?????????????????????????????? menuName:{}", "" + entity.getMenuName());
            }
        }


        Menu querySort = new Menu();
        querySort.setSort(entity.getSort());
        querySort.setParentCode(entity.getParentCode());
        Wrapper<Menu> queryWrapper = new QueryWrapper<>(querySort);
        List<Menu> menuLists = menuMapper.selectList(queryWrapper);
        if (!CollectionUtils.isEmpty(menuLists)){
            menuLists.stream().forEach(m->{
                if(!m.getId().equals(entity.getId())){
                    BizBusinessUtils.bizBusinessException("???????????????????????? sort:{}", "" + entity.getSort());
                }
            });
        }

        Menu menu = new Menu();
        BeanUtils.copyProperties(entity, menu);

        int i = menuMapper.updateById(menu);
        if(i < 1){
            BizBusinessUtils.bizBusinessException(ExecutionResultEnum.FAIL.getChineseName());
        }
        return ExecutionResultEnum.SUCCESS.getEnglishName();
    }

    @Override
    public PageInfo selectListPage(MenuDTO menuDTO) {
        UserResult userResult = redisInfoUtil.getUserResult();
        if(!AccountConstant.USER_NAME.equals(userResult.getPhone())){
            menuDTO.setAvailable(YesOrNotEnum.Y.getCode());
        }

        List<RoleMenuVo> roleMenuVos = null;
        // ????????????????????????
        if(StringUtils.isNotEmpty(menuDTO.getAvailable())){
            roleMenuVos = roleMapper.selectByUserId(userResult.getId(), menuDTO.getAvailable());
        }else {
            roleMenuVos = roleMapper.selectByUserIdExt(userResult.getId());
        }
        if(CollectionUtils.isEmpty(roleMenuVos)){
            return new PageInfo();
        }

        List<Menu> listMenu = Lists.newArrayList();
        for (RoleMenuVo roleMenuVo: roleMenuVos) {
            List<Menu> list = roleMenuVo.getMenuList();
            if(CollectionUtils.isEmpty(list)){
                return new PageInfo();
            }
            for (Menu menu: list) {
                listMenu.add(menu);
            }
        }

        // ??????????????????
        // ??????????????????
        List<Menu> pList = new ArrayList<>();

        for (Menu menu : listMenu) {
            // ?????????????????????????????????????????????
            if(DataEnum.D_0.getCode().equals(menu.getParentCode())){
                continue;
            }
            // ??????????????????
            List<Menu> list = menuMapper.selectByCod(menu.getParentCode());
            if(!org.apache.shiro.util.CollectionUtils.isEmpty(list)){
                // ????????????
                pList.addAll(list);
                // ?????????
                for (;;){
                    list = menuMapper.selectByCod(list.get(0).getParentCode());
                    // ??????????????? ?????????????????????
                    if(org.apache.shiro.util.CollectionUtils.isEmpty(list)){
                        break;
                    }
                    // ????????????
                    pList.addAll(list);
                }
            }
        }
        pList.addAll(listMenu);

        if(!org.apache.shiro.util.CollectionUtils.isEmpty(pList)){
            // ??????
            Set<Menu> set = new TreeSet<>(Comparator.comparing(Menu::getCode));
            set.addAll(pList);
            listMenu = new ArrayList<>(set);
        }

        List<Menu> subset = getSubset(listMenu, Lists.newArrayList());
        subset.addAll(listMenu);
        // ??????????????????????????????
        if(StringUtils.isNotEmpty(menuDTO.getType())){
            subset = subset.stream().filter(menu -> null != menu && MenuTypeEnum.NORMAL.getCode().equals(menu.getMenuType())).collect(Collectors.toList());
        }
        // ??????
        Set<Menu> set = new TreeSet<>(Comparator.comparing(Menu::getCode));
        set.addAll(subset);
        List<Menu> menuList = new ArrayList<>(set);
        menuList = menuList.stream().sorted(Comparator.comparing(Menu::getSort)).collect(Collectors.toList());

        // ????????????
        List<Map<String, Object>> menuListTop = getMenuTree(menuList, DataEnum.D_0.getCode(), menuDTO.getDisabled());

        return PageInfo.of(menuListTop);
    }

    @Override
    public String deleteById(String idList) {
        if(StringUtils.isEmpty(idList)){
            BizBusinessUtils.bizBusinessException("idList is not empty");
        }
        List<String> list = Arrays.asList(idList.split(","));

        // ??????????????????????????????
        roleMenuMapper.deleteByMenuId(list);

        // ????????????
        int i = menuMapper.deleteById(list);
        if(i < 1){
            BizBusinessUtils.bizBusinessException(ExecutionResultEnum.FAIL.getChineseName());
        }
        return ExecutionResultEnum.SUCCESS.getEnglishName();
    }

    /**
     * ????????????????????????
     * @param menuList
     * @param id
     * @return
     */
    private List<Map<String, Object>> getChildMenu(List<Menu> menuList, String id, String disabled){
        // ??????
        List<Map<String, Object>> mapList = Lists.newLinkedList();
        for (int i = 0; i < menuList.size(); i++) {
            if(String.valueOf(menuList.get(i).getParentCode()).equals(id)){
                Map<String, Object> map =  buildMap(menuList.get(i), disabled);
                mapList.add(map);
            }
        }

        // ??????????????????????????????
        return getMaps(menuList, mapList, disabled);
    }


    /**
     * ??????????????????
     * @param menus
     * @return
     */
    private List<Menu> getSubset(List<Menu> menus, List<Menu> menus2){
        menus.remove(null);
        List<Menu> menuList = Lists.newArrayList();
        menus.stream().forEach( m->{
            // leaf??????????????????????????????????????????
            if(null != m && !MenuTypeEnum.LEAF.getCode().equals(m.getMenuType()) && !DataEnum.D_0.getCode().equals(m.getParentCode())){
                List<Menu> list = menuMapper.selectByPCod(m.getCode());
                menuList.addAll(list);
            }
        });
        if(!CollectionUtils.isEmpty(menuList)){
            menus2.addAll(menuList);
            return getSubset(menuList, menus2);
        }

        return menus2;
    }

    private List<Map<String, Object>> getMenuTree(List<Menu> menuList, String code, String disabled){
        // ????????????
        List<Map<String, Object>> menuTop = menuList.stream().filter(m -> code.equals(m.getParentCode())).map(mu-> buildMap(mu, disabled)).collect(Collectors.toList());

        // ????????????
        return getMaps(menuList, menuTop, disabled);
    }

    private List<Map<String, Object>> getMaps(List<Menu> menuList, List<Map<String, Object>> menuTop, String disabled) {
        menuTop.stream().forEach(m -> {
            List<Map<String, Object>> childMenu = getChildMenu(menuList, m.get("code").toString(), disabled);
            if(!CollectionUtils.isEmpty(childMenu)){
                m.put("child", childMenu);
            }
        });

        return menuTop;
    }

    @Override
    public List<Map<String, Object>> getOperatePermission(String code, String menuType){
        List<RoleMenuVo> permission = redisInfoUtil.getPermission();

        List<Menu> list = Lists.newArrayList();
        permission.stream().forEach(p -> {
            list.addAll(p.getMenuList());
        });

        if(CollectionUtils.isEmpty(list)){
            return Lists.newArrayList();
        }

        List<Menu> menuList = list.stream().filter(menu -> null!=menu && menuType.equals(menu.getMenuType())).collect(Collectors.toList());
        if(CollectionUtils.isEmpty(menuList)){
            return Lists.newArrayList();
        }

        List<Map<String, Object>> menuTree = getMenuTree(menuList, code, null);
        return menuTree;
    }

    /**
     * ??????map??????
     * @param menu
     * @return
     */
    private Map<String, Object> buildMap(Menu menu, String disabled){
        Map<String, Object> map = Maps.newHashMap();
        map.put("id", menu.getId());
        map.put("menuName", menu.getMenuName());
        map.put("menuRoute", menu.getMenuRoute());
        map.put("parentCode", menu.getParentCode());
        map.put("menuType", menu.getMenuType());
        map.put("code", menu.getCode());
        map.put("icon", menu.getIcon());
        map.put("available", menu.getAvailable());
        map.put("sort", menu.getSort());
        map.put("createTime", menu.getCreateTime());
        // ???????????????????????????code?????????
        if(StringUtils.isNotEmpty(disabled) && menu.getCode().equals(disabled)){
            map.put("disabled", true);
        }
        return map;
    }
}
