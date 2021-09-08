package com.yn.electricity.controller.home;

import com.yn.electricity.controller.YniVASApplicationTests;
import com.yn.electricity.request.DepartmentAlterRequest;
import com.yn.electricity.request.DepartmentSaveRequest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: DepartmentTestController
 * @Author: zzs
 * @Date: 2021/3/12 10:43
 * @Description:
 */
public class DepartmentTestController extends YniVASApplicationTests {

    @Autowired
    private DepartmentController departmentController;
    @Autowired(required = false)
    private HttpServletResponse response;

    /**
     * 新增
     */
    @Test
    public void insert() {
        DepartmentSaveRequest request = new DepartmentSaveRequest();
        request.setName("研发部");
        request.setAvailable("y");
        request.setParentCode("54654");
        request.setSort("1");

    }

    /**
     * 修改
     */
    @Test
    public void updateById() {
        DepartmentAlterRequest request = new DepartmentAlterRequest();
        request.setId(1);
        request.setParentCode("888888");
        departmentController.updateById(request, response);
    }

}
