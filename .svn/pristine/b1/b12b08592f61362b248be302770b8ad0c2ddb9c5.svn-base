package com.yn.electricity.qto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: BaseQuery
 * @Author: zzs
 * @Date: 2021/2/23 14:09
 * @Description:
 */
@ApiModel
public class BaseQuery implements Serializable {
    private static final long serialVersionUID = 218330390321486360L;

    /**
     * 页码
     */
    @ApiModelProperty(value = "页码")
    private Integer pageNum=0;
    /**
     * 页面大小
     */
    @ApiModelProperty(value = "页码")
    private Integer pageSize=1000;

    /**
     * 部门code 用于数据权限
     */
    private List<String> depCodeList = null;
    /**
     * 企业code 用于数据权限
     */
    private List<String> companyCodeList = null;


    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<String> getDepCodeList() {
        return depCodeList;
    }

    public void setDepCodeList(List<String> depCodeList) {
        this.depCodeList = depCodeList;
    }

    public List<String> getCompanyCodeList() {
        return companyCodeList;
    }

    public void setCompanyCodeList(List<String> companyCodeList) {
        this.companyCodeList = companyCodeList;
    }
}
