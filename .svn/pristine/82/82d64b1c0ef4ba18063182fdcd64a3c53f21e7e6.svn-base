package com.yn.electricity.controller.home;

import cn.gjing.tools.excel.ExcelFactory;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.yn.electricity.dao.LogInfo;
import com.yn.electricity.qto.LogInfoDTO;
import com.yn.electricity.result.execl.LogInfoExcel;
import com.yn.electricity.service.LogInfoService;
import com.yn.electricity.util.log.annotation.SystemBeforeLog;
import com.yn.electricity.web.WebResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @ClassName: LogInfoController
 * @Author: zzs
 * @Date: 2021/3/18 10:43
 * @Description: 日志控制层
 */
@RestController
@RequestMapping(value = "log_info")
@Api(tags = "日志管理")
public class LogInfoController {
    @Resource
    private LogInfoService logInfoService;

    @SystemBeforeLog(menuName = "日志管理", description = "日志列表")
    @ApiOperation(value = "日志列表")
    @RequestMapping(value = "query_list_page.json", method = RequestMethod.POST)
    public void queryListPage(LogInfoDTO infoDTO, HttpServletResponse response) {
        PageInfo<LogInfo> pageInfo = logInfoService.selectList(infoDTO);
        WebResult.success(pageInfo, response);
    }


    @ApiOperation(value = "日志下载")
    @RequestMapping(value = "log_info_download.json", method = {RequestMethod.GET, RequestMethod.POST})
    public void logInfoDownload(LogInfoDTO infoDTO, HttpServletResponse response){
        infoDTO.setPageSize(10000);
        PageInfo<LogInfo> pageInfo = logInfoService.selectList(infoDTO);
        if(CollectionUtils.isEmpty(pageInfo.getList())){
            return;
        }

        List<LogInfoExcel> resultList = Lists.newArrayList();
        for (LogInfo r: pageInfo.getList()) {
            LogInfoExcel result = new LogInfoExcel();
            BeanUtils.copyProperties(r, result);

            resultList.add(result);
        }

        ExcelFactory.createWriter(LogInfoExcel.class, response).write(resultList).flush();


    }

}
