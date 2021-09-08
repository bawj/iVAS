package com.yn.electricity.exception;

import com.google.common.collect.Lists;
import com.yn.electricity.ResultUtilVO;
import com.yn.electricity.ResultVO;
import com.yn.electricity.enums.ErrorCommonEnum;
import com.yn.electricity.enums.ResultEnum;
import com.yn.electricity.logutil.YNLogUtils;
import com.yn.electricity.web.Result;
import com.yn.electricity.web.WebResult;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.Set;


/**
 * @ClassName: GlobalHandlerException
 * @Author: zzs
 * @Date: 2021/1/19 14:01
 * @Description: 全局异常
 */
@RestControllerAdvice
public class GlobalHandlerException {
    private final static Logger LOGGER = LoggerFactory.getLogger(GlobalHandlerException.class);

    @ExceptionHandler(Exception.class)
    public void resolveException(HttpServletRequest request, HttpServletResponse response, Exception ex){
        YNLogUtils.error(LOGGER, ex, null);

        Result<String> result = new Result<>();
        result.setSuccess(false);
        result.setTraceId(MDC.get("X-B3-TraceId"));
        result.setTabType("2");
        result.setErrorCode(ErrorCommonEnum.DEFAULT_ERROR.getCode());
        result.setErrorMsg(ErrorCommonEnum.DEFAULT_ERROR.getMsg());
        if(ex instanceof UnauthorizedException){
            result.setTabType("2");
            result.setErrorCode(ErrorCommonEnum.NOT_PERMISSION_ERROR.getCode());
            result.setErrorMsg(ErrorCommonEnum.NOT_PERMISSION_ERROR.getMsg());
        }else if(ex instanceof ParamCheckException){
            result.setTabType("1");
            result.setErrorCode(ErrorCommonEnum.PARAM_ERROR.getCode());
            result.setErrorMsg(ex.getMessage());
        }else if(ex instanceof BusinessException){
            result.setTabType("2");
            result.setErrorCode(ErrorCommonEnum.DEFAULT_ERROR.getCode());
            result.setErrorMsg(ex.getMessage());
        }else if(ex instanceof AuthenticationException){
            result.setTabType("2");
            result.setErrorCode(ErrorCommonEnum.DEFAULT_ERROR.getCode());
            result.setErrorMsg(ex.getMessage());
        }
        WebResult.response(result, response);
    }

    @ExceptionHandler(ValidationException.class)
    public ResultVO<String> violationException(ValidationException e) {
        StringBuilder sb = new StringBuilder();
        if (e instanceof ConstraintViolationException) {
            ConstraintViolationException exs = (ConstraintViolationException) e;
            Set<ConstraintViolation<?>> violations = exs.getConstraintViolations();
            for (ConstraintViolation<?> item : violations) {
                sb.append(item.getMessage());
            }
        }
        if (sb.length() > 0) {
            return ResultUtilVO.onErrorResult(ResultEnum.FAIL.getCode(), sb.toString());
        }
        return ResultUtilVO.onErrorResult(ResultEnum.FAIL.getCode(), e.toString());
    }
}
