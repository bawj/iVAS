package com.yn.electricity.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @ClassName: CompanySaveRequest
 * @Author: zzs
 * @Date: 2021/3/11 16:03
 * @Description: 新增企业信息
 */
@Data
public class CompanyAlterRequest implements Serializable {
    private static final long serialVersionUID = 3921596931780252267L;
    @NotNull(message = "id不能为空")
    private Integer id;
    /**
     * 公司名称
     */
    private String name;
    /**
     * 社会信用代码
     */
    private String creditCode;
    /**
     * 营业执照号
     */
    private String licenseCode;
    /**
     * 法定代表人
     */
    private String legalRepresent;
    /**
     * 联系电话
     */
    private String contactPhone;
    /**
     * 联系地址
     */
    private String address;
    /**
     * 紧急联系人
     */
    private String exigencyContact;
    /**
     * 紧急联系人电话
     */
    private String exigencyContactPhone;
    /**
     * 状态:normal正常,abnormal异常
     */
    private String status;
    /**
     * 父级code
     */
    private String parentCode;
    /**
     * 应用id
     */
    private Integer appId;
    /**
     * 是否顶级企业，y是n否
     */
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
