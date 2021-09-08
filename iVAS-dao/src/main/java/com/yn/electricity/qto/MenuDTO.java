package com.yn.electricity.qto;

import com.yn.electricity.enums.YesOrNotEnum;
import lombok.Data;

/**
 * @ClassName: MenuDTO
 * @Author: zzs
 * @Date: 2021/2/24 9:52
 * @Description:
 */
@Data
public class MenuDTO extends BaseQuery{
    private static final long serialVersionUID = 2530626641984907914L;
    /**
     * 应用id
     */
    private Integer appId;
    /**
     * 菜单code
     */
    private String disabled;
    /**
     * type = menu 返回列表不带按钮
     */
    private String type;
    /**
     * 是否启用  提供枚举： {@link YesOrNotEnum}
     */
    private String available;
}
