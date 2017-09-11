package com.sixin.eventbus;

import android.support.annotation.NonNull;

import com.sixin.eventbus.handler.AsynEventHandler;
import com.sixin.eventbus.handler.DefaultEventHandler;
import com.sixin.eventbus.handler.EventHandler;
import com.sixin.eventbus.handler.UIThreadEventHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 事件总线类，以单例的形式存在
 *
 * @author 周文涛
 */

public final class EventBus {

    private static EventBus mEventBus;

    private Map<EventType, ArrayList<Subscription>> targetMethodMap;

    private SubscriberMethodHunter methodHunter;

    private EventDispacher mDispacher;

    private EventBus() {
        targetMethodMap = new HashMap<>();
        methodHunter = new SubscriberMethodHunter(targetMethodMap);
        mDispacher = new EventDispacher();
    }

    public static EventBus getInstance() {
        if (mEventBus == null) {
            synchronized (EventBus.class) {
                if (mEventBus == null) {
                    mEventBus = new EventBus();
                }
            }
        }
        return mEventBus;
    }

    /**
     * 注册订阅者
     *
     * @param subscriber 订阅者
     */
    public void register(@NonNull Object subscriber) {
        methodHunter.findMethod(subscriber);
    }

    /**
     * 注销订阅对象
     *
     * @param subscriber 订阅者
     */
    public void unregister(Object subscriber) {

    }

    /**
     * 发布事件，分发给订阅者
     */
    public void post(String tag,Object event) {
        mDispacher.dispatchEvents(tag,event);
    }

    /**
     * 事件分发器类
     * */
    private class EventDispacher{

        private EventHandler mUIThreadEventHandler = new UIThreadEventHandler();

        private EventHandler mDefaultEventHandler = new DefaultEventHandler();

        private EventHandler mAsynEventHandler = new AsynEventHandler();

        /**
         * 分发事件
         * */
        public void dispatchEvents(String tag,Object event){
            EventType eventType = new EventType(tag, event.getClass());
            if(targetMethodMap.containsKey(eventType)){
                ArrayList<Subscription> lists = targetMethodMap.get(eventType);
                for(Subscription subscription : lists){
                        ThreadMode threadMode = subscription.getTargetMethod().getThreadMode();
                        EventHandler eventHandler = getEventHandler(threadMode);
                        eventHandler.handleEvent(subscription,event);
                }
            }
        }

        private EventHandler getEventHandler(ThreadMode threadMode){
            EventHandler eventHandler = null;
            switch (threadMode){
                case MAIN:
                    eventHandler = mUIThreadEventHandler;
                    break;
                case POST:
                    eventHandler =  mDefaultEventHandler;
                    break;
                case ASYN:
                    eventHandler = mAsynEventHandler;
                    break;
            }
            return eventHandler;
        }
    }
}
