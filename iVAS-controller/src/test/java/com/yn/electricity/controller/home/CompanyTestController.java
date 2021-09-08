package com.yn.electricity.controller.home;

import com.yn.electricity.controller.YniVASApplicationTests;
import com.yn.electricity.request.CompanyAlterRequest;
import com.yn.electricity.request.CompanySaveRequest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: CompanyTestController
 * @Author: zzs
 * @Date: 2021/3/12 9:19
 * @Description:
 */
public class CompanyTestController extends YniVASApplicationTests {

    @Autowired
    private CompanyController companyController;
    @Autowired(required = false)
    private HttpServletResponse response;

    /**
     * 新增企业
     */
    @Test
    public void insert(){
        CompanySaveRequest request = new CompanySaveRequest();
        request.setAppId(0);
        request.setAddress("浙江省杭州市西湖区");
        request.setContactPhone("13232323212");
        request.setCreditCode("645564d8748XD");
        request.setExigencyContact("张三");
        request.setExigencyContactPhone("13636363636");
        request.setExt("扩展字段");
        request.setIsTop("y");
        request.setLegalRepresent("法定代表人");
        request.setName("中国一能电力科学研究院");
        request.setParentCode("0");
        request.setRemark("备注");
        request.setStatus("y");
        request.setLicenseCode("564584132154DS");

        companyController.insert(request, response);
    }

    @Test
    public void updateById(){
        CompanyAlterRequest request = new CompanyAlterRequest();
        request.setId(1);
        request.setAddress("余杭");
        companyController.updateById(request ,response);
    }

}
