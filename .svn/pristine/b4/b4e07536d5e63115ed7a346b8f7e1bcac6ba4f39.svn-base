package com.yn.electricity.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yn.electricity.dao.DictDetail;
import com.yn.electricity.enums.ExecutionResultEnum;
import com.yn.electricity.enums.YesOrNotEnum;
import com.yn.electricity.mapper.DictDetailMapper;
import com.yn.electricity.qto.DictDetailDTO;
import com.yn.electricity.request.DictDetailAlterRequest;
import com.yn.electricity.request.DictDetailSaveRequest;
import com.yn.electricity.result.DictDetailResult;
import com.yn.electricity.service.DictDetailService;
import com.yn.electricity.utils.BizBusinessUtils;
import com.yn.electricity.utils.BizParamCheckUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName: DictDetailServiceImpl
 * @Author: zzs
 * @Date: 2021/3/17 16:40
 * @Description:
 */
@Service
public class DictDetailServiceImpl implements DictDetailService {

    @Resource
    private DictDetailMapper dictDetailMapper;

    @Override
    public String insert(DictDetailSaveRequest entity) {
        DictDetail dictDetail = checkNameRepeat(entity.getDictType(), entity.getName());
        if(null != dictDetail){
            BizBusinessUtils.bizBusinessException("该类型下已有此名称 name:{}", entity.getName());
        }

        DictDetail detail = new DictDetail();
        BeanUtils.copyProperties(entity, detail);

        detail.setAvailable(YesOrNotEnum.Y.getCode());
        detail.setCreateTime(new Date());
        detail.setModifyTime(new Date());
        int i = dictDetailMapper.insert(detail);
        if(i < 1){
            BizBusinessUtils.bizBusinessException(ExecutionResultEnum.FAIL.getChineseName());
        }
        return ExecutionResultEnum.SUCCESS.getEnglishName();
    }

    /**
     * 检查名称是否已存在
     * @param dictType
     * @param name
     */
    private DictDetail checkNameRepeat(String dictType, String name){
        DictDetail dict = new DictDetail();
        dict.setDictType(dictType);
        dict.setName(name);
        Wrapper<DictDetail> queryWrapper = new QueryWrapper<>(dict);
        return dictDetailMapper.selectOne(queryWrapper);
    }

    @Override
    public String updateById(DictDetailAlterRequest entity) {
        DictDetail dictDetail = dictDetailMapper.selectById(entity.getId());
        if(null == dictDetail){
            BizBusinessUtils.bizBusinessException("数据不存在");
        }

        DictDetail dict = checkNameRepeat(entity.getDictType(), entity.getName());
        if(null != dict && !dict.getId().equals(entity.getId())){
            BizBusinessUtils.bizBusinessException("该类型下已有此名称 name:{}", entity.getName());
        }

        DictDetail details = new DictDetail();
        BeanUtils.copyProperties(entity, details);

        details.setModifyTime(new Date());
        int i = dictDetailMapper.updateById(details);
        if(i < 1){
            BizBusinessUtils.bizBusinessException(ExecutionResultEnum.FAIL.getChineseName());
        }
        return ExecutionResultEnum.SUCCESS.getEnglishName();
    }

    @Override
    public List<DictDetailResult> selectList(DictDetailDTO dictDTO) {
        DictDetail detail = new DictDetail();
        detail.setDictType(dictDTO.getDictType());
        detail.setIsDelete(YesOrNotEnum.N.getCode());
        Wrapper<DictDetail> queryWrapper = new QueryWrapper<>(detail);
        List<DictDetail> dictDetails = dictDetailMapper.selectList(queryWrapper);

        return dictDetails.stream().map(d -> {
            DictDetailResult result = new DictDetailResult();
            BeanUtils.copyProperties(d, result);
            return result;
        }).collect(Collectors.toList());
    }

    @Override
    public DictDetailResult selectOne(Integer id) {
        if(null == id){
            BizParamCheckUtils.BizParamCheckException("id is not null");
        }
        DictDetail dictDetail = dictDetailMapper.selectById(id);

        DictDetailResult result = new DictDetailResult();
        if(null == dictDetail){
            BizBusinessUtils.bizBusinessException("入参条件不正确");
        }
        BeanUtils.copyProperties(dictDetail, result);
        return result;
    }

    @Override
    public String updateIsDelete(List<Integer> idList) {
        if(CollectionUtils.isEmpty(idList)){
            BizBusinessUtils.bizBusinessException("idList is not null");
        }

        idList.stream().forEach(id->{
            DictDetail dictDetail = new DictDetail();
            dictDetail.setId(id);
            dictDetail.setIsDelete(YesOrNotEnum.Y.getCode());
            dictDetailMapper.updateById(dictDetail);
        });

        return ExecutionResultEnum.SUCCESS.getEnglishName();
    }
}
