package com.jay.router_annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author jaydroid
 * @version 1.0
 * @date 5/24/21
 */
//标记在类上面
@Target(ElementType.TYPE)
//被保留在编译阶段的字节码阶段
@Retention(RetentionPolicy.CLASS)
public @interface Destination {

    //页面路由地址，不能为空
    String url();

    //页面描述
    String description();
}
