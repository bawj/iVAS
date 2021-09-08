package com.yn.electricity.service;


import com.yn.electricity.dao.AppWeb;

import java.util.List;

/**
 * @ClassName: AppWebService
 * @Author: zzs
 * @Date: 2021/2/22 15:52
 * @Description: 应用管理
 */
public interface AppWebService {

    List<AppWeb> queryList();

    /**
     * 根据应用英文名称查询
     * @param appName
     */
    Integer selectByMap(String appName);

    /**
     * 新增应用
     * @param entity
     * @return
     */
    String insert(AppWeb entity);

    /**
     * 修改应用
     * @param entity
     * @return
     */
    String updateById(AppWeb entity);

}
