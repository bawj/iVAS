package com.yn.electricity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yn.electricity.dao.Company;

import java.util.List;

/**
 * @ClassName: CompanyMapper
 * @Author: zzs
 * @Date: 2021/3/11 15:53
 * @Description:
 */
public interface CompanyMapper extends BaseMapper<Company> {
    /**
     * 查询企业
     * @param code
     * @return
     */
    Company selectByCode(String code);

    /**
     * 查询企业子集
     * @param parentCode
     * @return
     */
    List<Company> selectByParentCode(String parentCode);
}
