package com.yn.electricity.service;

import com.github.pagehelper.PageInfo;
import com.yn.electricity.dao.LogInfo;
import com.yn.electricity.qto.LogInfoDTO;
import com.yn.electricity.request.LogInfoSaveRequest;

/**
 * @ClassName: LogInfoService
 * @Author: zzs
 * @Date: 2021/3/18 9:44
 * @Description: 日志管理
 */
public interface LogInfoService {

    /**
     * 新增日志
     * @param entity
     * @return
     */
    String insert(LogInfoSaveRequest entity);

    /**
     * 列表查询
     * @param infoDTO
     * @return
     */
    PageInfo<LogInfo> selectList(LogInfoDTO infoDTO);

    /**
     * 删除大于30的日志
     */
    void deleteTime();
}
