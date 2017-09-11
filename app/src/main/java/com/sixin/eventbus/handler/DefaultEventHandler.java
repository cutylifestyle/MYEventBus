package com.sixin.eventbus.handler;

import com.sixin.eventbus.Subscription;

import java.lang.reflect.Method;

/**
 * 在当前线程执行
 */

public class DefaultEventHandler implements EventHandler {

    @Override
    public void handleEvent(Subscription subscription, Object event) {
        Method method = subscription.getTargetMethod().getMethod();
        method.setAccessible(true);
        try {
            method.invoke(subscription.getSubscriber(), event);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
