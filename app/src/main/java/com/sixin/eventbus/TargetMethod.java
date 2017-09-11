package com.sixin.eventbus;

import java.lang.reflect.Method;

/**
 * 其中封装了目标方法所在类的对象，
 * 目标方法本身，目标方法的参数类型
 * @author 周文涛
 */

public class TargetMethod {

    /**
     * 订阅者中的目标方法
     * */
    private Method method;

    /**
     * 目标方法中的参数类型
     * */
    private Class<?> paramType;

    /**
     * 目标方法所处的线程
     * */
    private ThreadMode threadMode;

    public TargetMethod(Method method, Class<?> paramType,ThreadMode threadMode) {
        this.method = method;
        this.paramType = paramType;
        this.threadMode = threadMode;
    }

    public Method getMethod() {
        return method;
    }

    public Class<?> getParamType() {
        return paramType;
    }

    public ThreadMode getThreadMode() {
        return threadMode;
    }
}
