package com.sixin.eventbus;

/**
 * 订阅者与具体订阅方法的类
 * @author 周文涛
 */

public class Subscription {

    /**
     * 订阅者
     * */
    private Object subscriber;

    /**
     * 订阅者中的具体订阅方法
     * */
    private TargetMethod targetMethod;

    public Subscription(Object subscriber , TargetMethod targetMethod){
        this.subscriber = subscriber;
        this.targetMethod = targetMethod;
    }

    public Object getSubscriber() {
        return subscriber;
    }

    public TargetMethod getTargetMethod() {
        return targetMethod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Subscription that = (Subscription) o;

        return subscriber.equals(that.subscriber) &&targetMethod.equals(that.targetMethod);

    }

    @Override
    public int hashCode() {
        int result = subscriber.hashCode();
        result = 31 * result + targetMethod.hashCode();
        return result;
    }
}
