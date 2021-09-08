package com.yn.electricity.util;

import com.yn.electricity.dao.BaseDAO;
import com.yn.electricity.result.UserResult;

/**
 * @author admin
 * Date 2021/3/17 13:56
 * Description 相同属性set
 **/
public class EntityUtil {


    public static <T extends BaseDAO> T setCpyCodeDepCodeIpIdUserId(T t, UserResult userResult) {
        t.setCompanyCode(userResult.getCompanyCode());
        t.setDepartmentCode(userResult.getDepCode());
        t.setUserIpId(userResult.getIpId());
        t.setUpdateUserId(userResult.getId());
        return t;
    }

}
