package com.yn.electricity.result;

import com.yn.electricity.enums.MenuTypeEnum;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: MenuResult
 * @Author: zzs
 * @Date: 2021/2/25 9:58
 * @Description:
 */
@Data
public class MenuResult implements Serializable {
    private static final long serialVersionUID = 6652856711609348369L;
    /**
     * 自增
     */
    private Integer id;
    /**
     * 权限名称
     */
    private String menuName;
    /**
     * 权限路由
     */
    private String menuRoute;
    /**
     * 父Id
     */
    private Integer parentId;
    /**
     * 节点位置 提供枚举
     * {@link MenuTypeEnum}
     */
    private String type;
    /**
     * 应用id
     */
    private Integer appId;
    /**
     * 创建时间
     */
    private Date createTime;
}
