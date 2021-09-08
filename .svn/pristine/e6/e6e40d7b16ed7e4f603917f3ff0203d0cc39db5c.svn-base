package com.yn.electricity.service.impl;

import com.google.common.collect.Maps;
import com.yn.electricity.dao.AppWeb;
import com.yn.electricity.enums.ExecutionResultEnum;
import com.yn.electricity.enums.YesOrNotEnum;
import com.yn.electricity.mapper.AppWebMapper;
import com.yn.electricity.service.AppWebService;
import com.yn.electricity.utils.BizBusinessUtils;
import com.yn.electricity.utils.BizOpinionUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: AppWebServiceImpl
 * @Author: zzs
 * @Date: 2021/2/22 15:54
 * @Description:
 */
@Service
public class AppWebServiceImpl implements AppWebService {

    @Resource
    private AppWebMapper appWebMapper;

    @Override
    public List<AppWeb> queryList() {
        return appWebMapper.selectList(null);
    }

    @Override
    public Integer selectByMap(String appName) {
        Map<String, Object> columnMap = Maps.newHashMap();
        columnMap.put("app", appName);

        List<AppWeb> appWeb = appWebMapper.selectByMap(columnMap);
        if(CollectionUtils.isEmpty(appWeb) || appWeb.size() > 1){
            BizBusinessUtils.bizBusinessException("com.yn.electricity.service.impl.AppWebServiceImpl#selectByMap size:{}", "" + appWeb.size());
        }

        return appWeb.get(0).getId();
    }

    @Override
    public String insert(AppWeb entity) {
        BizOpinionUtils.isAllNotEmpty("is not empty app:{}, appName:{}", entity.getApp(), entity.getAppName());

        entity.setAvailable(YesOrNotEnum.Y.getCode());
        entity.setCreateTime(new Date());
        entity.setModifyTime(new Date());

        int insert = appWebMapper.insert(entity);
        if(insert > 0){
            return ExecutionResultEnum.SUCCESS.getEnglishName();
        }
        return ExecutionResultEnum.FAIL.getEnglishName();
    }

    @Override
    public String updateById(AppWeb entity) {
        BizOpinionUtils.isAllNotEmpty("is not empty id:{}, app:{}, appName:{}",
                String.valueOf(entity.getId()), entity.getApp(), entity.getAppName());

        entity.setModifyTime(new Date());

        int insert = appWebMapper.updateById(entity);
        if(insert > 0){
            return ExecutionResultEnum.SUCCESS.getEnglishName();
        }
        return ExecutionResultEnum.FAIL.getEnglishName();
    }
}
