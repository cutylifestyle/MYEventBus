package com.sixin.eventbus;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;

/**
 * 该类的职责寻找订阅者类中被@Subcriber注解标记的所有方法
 * @author 周文涛
 */

public class SubscriberMethodHunter {

    private Map<EventType, ArrayList<Subscription>> targetMethodMap;

    public SubscriberMethodHunter(Map<EventType,ArrayList<Subscription>> targetMethodMap){
        this.targetMethodMap = targetMethodMap;
    }

    public void findMethod(Object subscriber){
        Class<?> clazz = subscriber.getClass();
        Method[] methods = clazz.getDeclaredMethods();
        if (methods != null) {
            for (int i = 0; i < methods.length; i++) {
                Method method = methods[i];
                Subscribe subscribe = method.getAnnotation(Subscribe.class);
                if (subscribe != null) {
                    // TODO: 2017/8/13 tag有没有必要存在
                    Class<?>[] paramsType = method.getParameterTypes();
                    if(paramsType != null && paramsType.length ==1){
                        String tag = subscribe.tag();
                        ThreadMode threadMode = subscribe.threadMode();
                        EventType eventType = new EventType(tag, paramsType[0]);
                        TargetMethod targetMethod = new TargetMethod(method,paramsType[0],threadMode);
                        checkMap(subscriber,eventType,targetMethod);
                    }
                }
            }
        }
    }


    private void checkMap(Object subscriber,EventType eventType,TargetMethod targetMethod){
        ArrayList<Subscription> subscriptionLists = targetMethodMap.get(eventType);
        if(subscriptionLists == null){
            subscriptionLists = new ArrayList<>();
        }
        Subscription subscription = new Subscription(subscriber, targetMethod);
        if(subscriptionLists.contains(subscription)){
            return;
        }
        subscriptionLists.add(subscription);
        targetMethodMap.put(eventType,subscriptionLists);
    }
}
