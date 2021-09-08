package com.yn.electricity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yn.electricity.dao.Department;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName: DepartmentMapper
 * @Author: zzs
 * @Date: 2021/3/11 15:58
 * @Description: 部门
 */
public interface DepartmentMapper extends BaseMapper<Department> {

    /**
     * 根据id删除部门
     * @param list
     * @return
     */
    int deleteById(List<Integer> list);

    /**
     * 根据数据权限查询部门
     * @param list
     * @return
     */
    List<Department> selectByCode(@Param("list") List<String> list, @Param("available") String available);

    /**
     * 上往下找
     * @param list
     * @return
     */
    List<Department> selectByParentCode(@Param("list") List<String> list, @Param("available") String available);
}
