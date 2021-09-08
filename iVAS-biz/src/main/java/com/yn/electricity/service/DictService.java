package com.yn.electricity.service;

import com.yn.electricity.qto.DictDTO;
import com.yn.electricity.request.DictAlterRequest;
import com.yn.electricity.request.DictSaveRequest;
import com.yn.electricity.result.DictResult;

import java.util.List;

/**
 * @ClassName: DictService
 * @Author: zzs
 * @Date: 2021/3/17 16:28
 * @Description: 字典类型管理
 */
public interface DictService {

    /**
     * 新增字段类型
     * @param entity
     * @return
     */
    String insert(DictSaveRequest entity);

    /**
     * 修改字段类型
     * @param entity
     * @return
     */
    String updateById(DictAlterRequest entity);

    /**
     * 列表查询
     * @param dictDTO
     * @return
     */
    List<DictResult> selectList(DictDTO dictDTO);

    /**
     * 详情查询
     * @param id
     * @return
     */
    DictResult selectOne(Integer id);
}
