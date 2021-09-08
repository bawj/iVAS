package com.yn.electricity.controller.home;

import com.yn.electricity.qto.DictDetailDTO;
import com.yn.electricity.request.DictDetailAlterRequest;
import com.yn.electricity.request.DictDetailSaveRequest;
import com.yn.electricity.result.DictDetailResult;
import com.yn.electricity.service.DictDetailService;
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
 * @ClassName: DictDetailController
 * @Author: zzs
 * @Date: 2021/3/17 18:42
 * @Description: 字典详情管理
 */
@RestController
@RequestMapping(value = "dict_detail")
@Api(tags = "字典详情管理")
public class DictDetailController {
    @Resource
    private DictDetailService dictDetailService;

    @SystemBeforeLog(menuName = "字典管理", description = "新增字典详情信息")
    @RequiresPermissions(value = "insert_dict_detail.json")
    @RequestMapping(value = "insert_dict_detail.json", method = RequestMethod.POST)
    @ApiOperation(value = "新增字典详情信息")
    public void insert(@RequestBody DictDetailSaveRequest entity, HttpServletResponse response) {
        ValidationUtils.checkParam(entity);

        String result = dictDetailService.insert(entity);
        WebResult.success(result, response);
    }

    @SystemBeforeLog(menuName = "字典管理", description = "修改字典详情信息")
    @RequiresPermissions(value = "update_dict_detail.json")
    @RequestMapping(value = "update_dict_detail.json", method = RequestMethod.POST)
    @ApiOperation(value = "修改字典详情信息")
    public void updateById(@RequestBody DictDetailAlterRequest entity, HttpServletResponse response) {
        ValidationUtils.checkParam(entity);

        String result = dictDetailService.updateById(entity);
        WebResult.success(result, response);
    }

    @SystemBeforeLog(menuName = "字典管理", description = "列表查询字典详情信息")
    @RequestMapping(value = "query_dict_detail_list_page.json", method = RequestMethod.POST)
    @ApiOperation(value = "列表查询字典详情信息")
    public void queryDictDetailListPage(@RequestBody DictDetailDTO dictDTO, HttpServletResponse response) {
        List<DictDetailResult> resultList = dictDetailService.selectList(dictDTO);
        WebResult.success(resultList, response);
    }

    @SystemBeforeLog(menuName = "字典管理", description = "查询字典详情信息")
    @RequestMapping(value = "query_dict_detail_id.json", method = RequestMethod.POST)
    @ApiOperation(value = "查询字典详情信息")
    public void queryDictDetailId(Integer id, HttpServletResponse response) {
        DictDetailResult result = dictDetailService.selectOne(id);
        WebResult.success(result, response);
    }

    @SystemBeforeLog(menuName = "字典管理", description = "删除字典详情信息")
    @RequiresPermissions(value = "update_is_delete.json")
    @RequestMapping(value = "update_is_delete.json", method = RequestMethod.POST)
    @ApiOperation(value = "删除字典详情信息")
    public void updateIsDelete(@RequestBody List<Integer> idList, HttpServletResponse response) {
        String result = dictDetailService.updateIsDelete(idList);
        WebResult.success(result, response);
    }

}
