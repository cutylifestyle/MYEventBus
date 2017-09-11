package com.sixin.eventbus.handler;

import android.os.Handler;
import android.os.HandlerThread;

import com.sixin.eventbus.Subscription;

/**
 * 在另外一个线程执行
 * @author 周文涛
 */

public class AsynEventHandler implements EventHandler {

    private DispatcherThread mThread;

    private DefaultEventHandler mDefaultEventHandler = new DefaultEventHandler();

    public AsynEventHandler(){
        mThread = new DispatcherThread(AsynEventHandler.class.getName());
        mThread.start();
    }

    @Override
    public void handleEvent(final Subscription subscription, final Object event) {
        mThread.post(new Runnable() {
            @Override
            public void run() {
                mDefaultEventHandler.handleEvent(subscription,event);
            }
        });
    }

    private class DispatcherThread extends HandlerThread{

        private Handler handler;

        public DispatcherThread(String name) {
            super(name);
        }

        public void post(Runnable runnable){
            handler.post(runnable);
        }

        @Override
        public synchronized void start() {
            super.start();
            handler = new Handler(getLooper());
        }
    }
}
