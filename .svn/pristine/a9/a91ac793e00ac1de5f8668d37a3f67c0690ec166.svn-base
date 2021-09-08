package com.yn.electricity.service;

import com.yn.electricity.qto.DictDetailDTO;
import com.yn.electricity.request.DictDetailAlterRequest;
import com.yn.electricity.request.DictDetailSaveRequest;
import com.yn.electricity.result.DictDetailResult;

import java.util.List;

/**
 * @ClassName: DictDetailService
 * @Author: zzs
 * @Date: 2021/3/17 16:27
 * @Description: 字典详情管理
 */
public interface DictDetailService {

    /**
     * 新增字段类型
     * @param entity
     * @return
     */
    String insert(DictDetailSaveRequest entity);

    /**
     * 修改字段类型
     * @param entity
     * @return
     */
    String updateById(DictDetailAlterRequest entity);

    /**
     * 列表查询
     * @param dictDTO
     * @return
     */
    List<DictDetailResult> selectList(DictDetailDTO dictDTO);

    /**
     * 详情查询
     * @param id
     * @return
     */
    DictDetailResult selectOne(Integer id);

    /**
     * 删除
     * @param idList
     * @return
     */
    String updateIsDelete(List<Integer> idList);


}
