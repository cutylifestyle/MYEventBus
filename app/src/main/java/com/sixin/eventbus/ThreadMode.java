package com.sixin.eventbus;

/**
 * 线程模式，用于标识目标方法执行在哪个线程
 * @author 周文涛
 */

public enum ThreadMode {

    /**
     * 目标方法运行在主线程
     * */
    MAIN,

    /**
     * 目标方法执行在当前线程
     * */
    POST,

    /**
     * 目标方法执行在另外一个线程
     * */
    ASYN
}
