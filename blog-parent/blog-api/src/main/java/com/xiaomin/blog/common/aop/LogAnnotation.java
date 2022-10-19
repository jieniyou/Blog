package com.xiaomin.blog.common.aop;

import java.lang.annotation.*;

/**
 * @Author: XiaoMin
 * @PRODUCT_NAME: IntelliJ IDEA
 * @PROJECT_NAME: blog-parent
 * @Date_Time: 2022/6/23 19:54
 */
//Type 代表可以放在类上面 Method 代表可以放在方法上面
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogAnnotation {
    String module() default "";
    String operator() default "";
}
