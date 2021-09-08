package com.yn.electricity.util.log.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author admin
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SystemAfterLog {
    /**
     * 值
     * @return
     */
    String value() default "";

    /**
     * 接口中文名
     * @return
     */
    String menuName() default "";

    /**
     * 描述
     * @return
     */
    String description() default "";

    /**
     * 项目来源
     * @return
     */
    String source() default "视频联网系统";

}
