package com.yn.electricity.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName: Company
 * @Author: zzs
 * @Date: 2021/3/11 15:45
 * @Description: 企业信息实体类
 */
@Data
@TableName(value = "company")
public class Company {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 编码
     */
    private String code;
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
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date modifyTime;

}
