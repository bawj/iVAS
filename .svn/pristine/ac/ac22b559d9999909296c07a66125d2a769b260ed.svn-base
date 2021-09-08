package com.yn.electricity.util;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.yn.electricity.utils.BizParamCheckUtils;
import org.apache.shiro.util.CollectionUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName: ValidationUtils
 * @Author: zzs
 * @Date: 2021/2/23 14:37
 * @Description: 参数验证
 */
public class ValidationUtils {

    private static Validator validator;
    static {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    public static  <T> void checkParam(T entity){
        Map<String, String> map = Maps.newHashMap();

        Set<ConstraintViolation<T>> validate = validator.validate(entity);
        for (ConstraintViolation stv : validate) {
            map.put(stv.getPropertyPath().toString(), stv.getMessage());
        }

        if(!CollectionUtils.isEmpty(map)){
            BizParamCheckUtils.BizParamCheckException("检查必填参数 param:{}", JSON.toJSONString(map));
        }
    }

}
