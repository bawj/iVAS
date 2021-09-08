package com.yn.electricity.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @ClassName: CompanySaveRequest
 * @Author: zzs
 * @Date: 2021/3/11 16:03
 * @Description: 新增企业信息
 */
@Data
public class CompanySaveRequest implements Serializable {
    private static final long serialVersionUID = 3921596931780252267L;
    /**
     * 公司名称
     */
    @NotBlank(message = "公司名称不能为空")
    private String name;
    /**
     * 社会信用代码
     */
    @NotBlank(message = "社会信用代码不能为空")
    private String creditCode;
    /**
     * 营业执照号
     */
    @NotBlank(message = "营业执照号不能为空")
    private String licenseCode;
    /**
     * 法定代表人
     */
    @NotBlank(message = "法定代表人不能为空")
    private String legalRepresent;
    /**
     * 联系电话
     */
    @NotBlank(message = "联系电话不能为空")
    private String contactPhone;
    /**
     * 联系地址
     */
    @NotBlank(message = "联系地址不能为空")
    private String address;
    /**
     * 紧急联系人
     */
    @NotBlank(message = "紧急联系人不能为空")
    private String exigencyContact;
    /**
     * 紧急联系人电话
     */
    @NotBlank(message = "紧急联系人电话不能为空")
    private String exigencyContactPhone;
    /**
     * 状态:normal正常,abnormal异常
     */
    @NotBlank(message = "状态不能为空")
    private String status;
    /**
     * 父级code
     */
    @NotBlank(message = "父级code不能为空")
    private String parentCode;
    /**
     * 应用id
     */
    @NotNull(message = "应用id不能为空")
    private Integer appId;
    /**
     * 是否顶级企业，y是n否
     */
    @NotBlank(message = "是否顶级企业不能为空")
    private String isTop;
    /**
     * 扩展字段
     */
    private String ext;
    /**
     * 备注
     */
    private String remark;
}
