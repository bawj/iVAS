package com.yn.electricity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yn.electricity.dao.LogInfo;
import com.yn.electricity.qto.BaseQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName: LogInfoMapper
 * @Author: zzs
 * @Date: 2021/3/18 9:29
 * @Description: 日志
 */
public interface LogInfoMapper extends BaseMapper<LogInfo> {

    /**
     * 列表查询
     * @param query
     * @return
     */
    List<LogInfo> queryListPage(@Param("query") BaseQuery query, @Param("idList") List<String> idList);

    /**
     * 删除大于30的日志
     */
    void deleteTime();
}
