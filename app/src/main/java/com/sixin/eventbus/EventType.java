package com.sixin.eventbus;

/**
 * 事件类型
 *@author 周文涛
 */

public class EventType {

    /**
     * Subscribe注解中的tag
     * */
    private String tag;

    /**
     * 目标方法参数类型
     * */
    private Class<?> paramType;

    public EventType(String tag, Class<?> paramType){
        this.tag = tag;
        this.paramType = paramType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EventType eventType = (EventType) o;

        return tag.equals(eventType.tag)&&paramType.equals(eventType.paramType);

    }

    @Override
    public int hashCode() {
        int result = tag.hashCode();
        result = 31 * result + paramType.hashCode();
        return result;
    }
}
