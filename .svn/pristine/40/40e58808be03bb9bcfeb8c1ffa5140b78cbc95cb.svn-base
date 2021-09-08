package com.yn.electricity.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yn.electricity.dao.Company;
import com.yn.electricity.enums.ExecutionResultEnum;
import com.yn.electricity.mapper.CompanyMapper;
import com.yn.electricity.request.CompanyAlterRequest;
import com.yn.electricity.request.CompanySaveRequest;
import com.yn.electricity.service.CompanyService;
import com.yn.electricity.util.SequenceGenerator;
import com.yn.electricity.util.ValidationUtils;
import com.yn.electricity.utils.BizBusinessUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @ClassName: CompanyServiceImpl
 * @Author: zzs
 * @Date: 2021/3/11 16:14
 * @Description:
 */
@Service
public class CompanyServiceImpl implements CompanyService {

    @Resource
    private CompanyMapper companyMapper;

    @Override
    public String insert(CompanySaveRequest request) {
        ValidationUtils.checkParam(request);

        Company companyDTO = new Company();
        companyDTO.setName(request.getName());
        Wrapper<Company> wrapper = new QueryWrapper<>(companyDTO);
        Company entity = companyMapper.selectOne(wrapper);
        if(null != entity){
            BizBusinessUtils.bizBusinessException("企业信息已存在 name:{}", companyDTO.getName());
        }

        Company company = new Company();
        BeanUtils.copyProperties(request, company);
        company.setCode(SequenceGenerator.sequence());
        company.setCreateTime(new Date());
        company.setModifyTime(new Date());

        int i = companyMapper.insert(company);
        if(i < 1){
            BizBusinessUtils.bizBusinessException(ExecutionResultEnum.FAIL.getChineseName());
        }
        return ExecutionResultEnum.SUCCESS.getEnglishName();
    }


    @Override
    public String updateById(CompanyAlterRequest request) {
        ValidationUtils.checkParam(request);

        Company company = companyMapper.selectById(request.getId());
        if(null == company){
            BizBusinessUtils.bizBusinessException("企业信息不存在 id:{}", "" + request.getId());
        }

        BeanUtils.copyProperties(request, company);
        int i = companyMapper.updateById(company);
        if(i < 1){
            BizBusinessUtils.bizBusinessException(ExecutionResultEnum.FAIL.getChineseName());
        }
        return ExecutionResultEnum.SUCCESS.getEnglishName();
    }
}
