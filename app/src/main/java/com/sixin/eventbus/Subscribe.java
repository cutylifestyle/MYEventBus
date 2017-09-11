package com.sixin.eventbus;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 该注解用于标注订阅者类中需要被执行的方法
 * @author 周文涛
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Subscribe {

    /**
     * 该元素作为订阅者中需要被执行的方法的唯一标识
     * */
    String tag();

    /**
     * 线程模式
     * */
    ThreadMode threadMode();

}
