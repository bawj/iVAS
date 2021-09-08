package com.yn.electricity.controller.home;

import com.yn.electricity.qto.DictDTO;
import com.yn.electricity.request.DictAlterRequest;
import com.yn.electricity.request.DictSaveRequest;
import com.yn.electricity.result.DictResult;
import com.yn.electricity.service.DictService;
import com.yn.electricity.util.ValidationUtils;
import com.yn.electricity.util.log.annotation.SystemBeforeLog;
import com.yn.electricity.web.WebResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @ClassName: DictController
 * @Author: zzs
 * @Date: 2021/3/17 17:12
 * @Description: 字典控制层
 */
@RestController
@RequestMapping(value = "dict")
@Api(tags = "字典类型管理")
public class DictController {
    @Resource
    private DictService dictService;

    @SystemBeforeLog(menuName = "字典管理", description = "新增字典类型")
    @RequiresPermissions(value = "insert_dict_type.json")
    @RequestMapping(value = "insert_dict_type.json", method = RequestMethod.POST)
    @ApiOperation(value = "新增字典类型")
    public void insertDictType(@RequestBody DictSaveRequest entity, HttpServletResponse response) {
        ValidationUtils.checkParam(entity);

        String result = dictService.insert(entity);
        WebResult.success(result, response);
    }

    @SystemBeforeLog(menuName = "字典管理", description = "修改字典类型")
    @RequiresPermissions(value = "update_dict_type.json")
    @RequestMapping(value = "update_dict_type.json", method = RequestMethod.POST)
    @ApiOperation(value = "修改字典类型")
    public void updateDictType(@RequestBody DictAlterRequest entity, HttpServletResponse response) {
        ValidationUtils.checkParam(entity);

        String result = dictService.updateById(entity);
        WebResult.success(result, response);
    }

    @SystemBeforeLog(menuName = "字典管理", description = "字典类型列表查询")
    @RequestMapping(value = "query_dict_type_list_page.json", method = RequestMethod.POST)
    @ApiOperation(value = "字典类型列表查询")
    public void queryDictTypeListPage(DictDTO dictDTO, HttpServletResponse response) {
        List<DictResult> result = dictService.selectList(dictDTO);
        WebResult.success(result, response);
    }

    @SystemBeforeLog(menuName = "字典管理", description = "字典类型详情")
    @RequestMapping(value = "query_dict_type_id.json", method = RequestMethod.POST)
    @ApiOperation(value = "字典类型详情")
    public void queryDictTypeId(Integer id, HttpServletResponse response) {
        DictResult result = dictService.selectOne(id);
        WebResult.success(result, response);
    }

}
