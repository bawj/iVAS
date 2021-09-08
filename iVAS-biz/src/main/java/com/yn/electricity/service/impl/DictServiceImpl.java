package com.yn.electricity.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yn.electricity.dao.Dict;
import com.yn.electricity.enums.ExecutionResultEnum;
import com.yn.electricity.mapper.DictMapper;
import com.yn.electricity.qto.DictDTO;
import com.yn.electricity.request.DictAlterRequest;
import com.yn.electricity.request.DictSaveRequest;
import com.yn.electricity.result.DictResult;
import com.yn.electricity.service.DictService;
import com.yn.electricity.util.ValidationUtils;
import com.yn.electricity.utils.BizBusinessUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: DictServiceImpl
 * @Author: zzs
 * @Date: 2021/3/17 16:40
 * @Description:
 */
@Service
public class DictServiceImpl implements DictService {
    @Resource
    private DictMapper dictMapper;

    @Override
    public String insert(DictSaveRequest entity) {
        ValidationUtils.checkParam(entity);

        checkName(entity.getName(), null);
        checkDictType(entity.getDictType(), null);

        Dict dictDao = new Dict();
        BeanUtils.copyProperties(entity, dictDao);

        dictDao.setCreateTime(new Date());
        dictDao.setModifyTime(new Date());
        int i = dictMapper.insert(dictDao);
        if(i < 1){
            BizBusinessUtils.bizBusinessException(ExecutionResultEnum.FAIL.getChineseName());
        }
        return ExecutionResultEnum.SUCCESS.getEnglishName();
    }

    /**
     * 查询字典类型
     * @param dict
     * @return
     */
    private Dict getDict(Dict dict){
        Wrapper<Dict> queryWrapper = new QueryWrapper<>(dict);
        return dictMapper.selectOne(queryWrapper);
    }

    /**
     * 检查字典类型
     * @param dictTypes
     */
    private void checkDictType(String dictTypes, Integer id){
        Dict dict = new Dict();
        dict.setDictType(dictTypes);
        Dict dictType = getDict(dict);
        if(null != dictType && !dictType.getId().equals(id)){
            BizBusinessUtils.bizBusinessException("字典类型不可重复添加");
        }
    }

    /**
     * 检查名称
     * @param names
     */
    private void checkName(String names, Integer id){
        Dict dict = new Dict();
        dict.setName(names);
        Dict name = getDict(dict);
        if(null != name && !name.getId().equals(id)){
            BizBusinessUtils.bizBusinessException("字典名称不可重复添加");
        }
    }

    @Override
    public String updateById(DictAlterRequest entity) {
        ValidationUtils.checkParam(entity);

        Dict dict = dictMapper.selectById(entity.getId());
        if(null == dict){
            BizBusinessUtils.bizBusinessException("数据不存在");
        }

        if(!dict.getName().equals(entity.getName()) || !dict.getDictType().equals(entity.getDictType())){
            checkName(entity.getName(), entity.getId());
            checkDictType(entity.getDictType(), entity.getId());
        }

        Dict dictDao = new Dict();
        BeanUtils.copyProperties(entity, dictDao);

        dictDao.setModifyTime(new Date());
        int i = dictMapper.updateById(dictDao);
        if(i < 1){
            BizBusinessUtils.bizBusinessException(ExecutionResultEnum.FAIL.getChineseName());
        }
        return ExecutionResultEnum.SUCCESS.getEnglishName();
    }

    @Override
    public List<DictResult> selectList(DictDTO dictDTO) {
        Dict dict = new Dict();
        Wrapper<Dict> queryWrapper = new QueryWrapper<>(dict);

        Page page = new Page(dictDTO.getPageNum(), dictDTO.getPageSize());
        Page list = dictMapper.selectPage(page, queryWrapper);
        List records = list.getRecords();

        return records;
    }

    @Override
    public DictResult selectOne(Integer id) {
        if(null == id){
            BizBusinessUtils.bizBusinessException("id is not null");
        }

        Dict dict = dictMapper.selectById(id);
        DictResult result = new DictResult();
        BeanUtils.copyProperties(dict, result);
        return result;
    }
}
