package com.yn.electricity.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yn.electricity.dao.LogInfo;
import com.yn.electricity.enums.ExecutionResultEnum;
import com.yn.electricity.mapper.LogInfoMapper;
import com.yn.electricity.qto.LogInfoDTO;
import com.yn.electricity.request.LogInfoSaveRequest;
import com.yn.electricity.service.LogInfoService;
import com.yn.electricity.util.ValidationUtils;
import com.yn.electricity.utils.BizBusinessUtils;
import com.yn.electricity.utils.cron.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: LogInfoServiceImpl
 * @Author: zzs
 * @Date: 2021/3/18 9:46
 * @Description:
 */
@Slf4j
@Service
public class LogInfoServiceImpl implements LogInfoService {
    @Resource
    private LogInfoMapper logInfoMapper;

    @Override
    public String insert(LogInfoSaveRequest entity) {
        ValidationUtils.checkParam(entity);

        LogInfo logInfo = new LogInfo();
        BeanUtils.copyProperties(entity, logInfo);
        logInfo.setCreateTime(new Date());

        int i = logInfoMapper.insert(logInfo);
        if(i < 1){
            BizBusinessUtils.bizBusinessException(ExecutionResultEnum.FAIL.getChineseName());
        }
        return ExecutionResultEnum.SUCCESS.getEnglishName();
    }

    @Override
    public PageInfo<LogInfo> selectList(LogInfoDTO infoDTO) {

        List<String> idList = null;
        if(StringUtils.isNotEmpty(infoDTO.getIdList())){
            String[] split = infoDTO.getIdList().split(",");
            idList = Arrays.asList(split);
        }

        PageHelper.startPage(infoDTO.getPageNum(), infoDTO.getPageSize());
        List<LogInfo> logInfoList = logInfoMapper.queryListPage(infoDTO, idList);
        return new PageInfo<>(logInfoList);
    }

    @Override
    public void deleteTime() {
        logInfoMapper.deleteTime();
        log.info("删除日志时间：" + new Date() + "");
    }
}
