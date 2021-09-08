package com.yn.electricity.controller.home;

import cn.gjing.tools.excel.ExcelFactory;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.yn.electricity.enums.UserLockStatusEnum;
import com.yn.electricity.enums.YesOrNotEnum;
import com.yn.electricity.enums.dict.SexEnum;
import com.yn.electricity.qto.UserDTO;
import com.yn.electricity.request.UserAlterRequest;
import com.yn.electricity.request.UserSaveRequest;
import com.yn.electricity.result.UserResult;
import com.yn.electricity.result.execl.UserDowExcel;
import com.yn.electricity.service.UserService;
import com.yn.electricity.util.ValidationUtils;
import com.yn.electricity.util.log.annotation.SystemBeforeLog;
import com.yn.electricity.web.WebResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * @ClassName: UserController
 * @Author: zzs
 * @Date: 2021/1/18 14:52
 * @Description:
 */
@Api(tags = "用户管理")
@RestController
@RequestMapping("user")
public class UserController {
    @Resource
    private UserService userService;

    @SystemBeforeLog(menuName = "用户管理", description = "用户列表查询")
    @ApiOperation(value = "用户列表查询")
    @RequestMapping(value = "query_list_page.json", method = {RequestMethod.GET, RequestMethod.POST})
    public void queryListPage(UserDTO userDTO, HttpServletResponse response){
        PageInfo pageInfo = userService.selectListPage(userDTO);
        WebResult.success(pageInfo, response);
    }

    /**
     * 注册用户
     * @param entity
     * @param response
     */
    @SystemBeforeLog(menuName = "用户管理", description = "用户注册")
    @ApiOperation(value = "用户注册")
    @RequiresPermissions(value = "register.json")
    @RequestMapping(value = "register.json", method = RequestMethod.POST)
    public void register(@RequestBody UserSaveRequest entity, HttpServletResponse response){
        ValidationUtils.checkParam(entity);

        String result = userService.insert(entity);
        WebResult.success(result, response);
    }

    /**
     * 修改用户信息
     * @param entity
     * @param response
     * @return
     */
    @SystemBeforeLog(menuName = "用户管理", description = "修改用户信息")
    @ApiOperation(value = "修改用户信息")
    @RequiresPermissions(value = "update_by_id.json")
    @RequestMapping(value = "update_by_id.json", method = RequestMethod.POST)
    public void updateById(@RequestBody UserAlterRequest entity, HttpServletResponse response) {
        ValidationUtils.checkParam(entity);
        if("".equals(entity.getPassword())){
            entity.setPassword(null);
        }
        if("".equals(entity.getEmail())){
            entity.setEmail(null);
        }
        if("".equals(entity.getPhone())){
            entity.setPhone(null);
        }

        String result = userService.updateById(entity);
        WebResult.success(result, response);
    }

    /**
     * 逻辑删除用户
     * @param idList
     * @param response
     */
    @SystemBeforeLog(menuName = "用户管理", description = "删除用户")
    @ApiOperation(value = "删除用户")
    @ApiImplicitParam(name = "idList", value = "主键id", dataType = "String", required = true)
    @RequiresPermissions(value = "update_batch_id.json")
    @RequestMapping(value = "update_batch_id.json", method = RequestMethod.POST)
    public void updateBatchId(@RequestBody List<Integer> idList, HttpServletResponse response) {
        String result = userService.updateBatchIds(idList);
        WebResult.success(result, response);
    }

    /**
     * 用户下载
     */
    @ApiOperation(value = "用户下载")
    @RequestMapping(value = "download_list.json", method = {RequestMethod.GET, RequestMethod.POST})
    public void downloadList(UserDTO userDTO, HttpServletResponse response){
        userDTO.setPageSize(10000);

        PageInfo pageInfo = userService.selectListPage(userDTO);
        List<UserResult> list = pageInfo.getList();
        if(CollectionUtils.isEmpty(list)){
            return;
        }

        List<UserDowExcel> resultList = Lists.newArrayList();
        for (UserResult r: list) {
            UserDowExcel result = new UserDowExcel();
            BeanUtils.copyProperties(r, result);

            result.setLockStatus(UserLockStatusEnum.getByCode(r.getLockStatus()));
            result.setAvailable(YesOrNotEnum.getByCode(r.getAvailable()));
            result.setSex(SexEnum.getByCode(Integer.parseInt(r.getSex())));
            resultList.add(result);
        }

        ExcelFactory.createWriter(UserDowExcel.class, response).write(resultList).flush();
    }

}
