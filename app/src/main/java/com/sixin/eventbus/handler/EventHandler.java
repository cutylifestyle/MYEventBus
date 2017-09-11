package com.sixin.eventbus.handler;

import com.sixin.eventbus.Subscription;

/**
 *@author 周文涛
 */

public interface EventHandler {

    /**
     * 处理事件的方法
     * @param event 需要处理的时间
     * */
    void handleEvent(Subscription subscription , Object event);

}
