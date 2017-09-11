package com.sixin.eventbus.handler;

import android.os.Handler;
import android.os.Looper;

import com.sixin.eventbus.Subscription;

/**
 *在主线程执行
 */

public class UIThreadEventHandler implements EventHandler {

    private Handler mUIHandler = new Handler(Looper.getMainLooper());

    private DefaultEventHandler mDefaultEventHandler = new DefaultEventHandler();

    @Override
    public void handleEvent(final Subscription subscription, final Object event) {
            mUIHandler.post(new Runnable() {
                @Override
                public void run() {
                    mDefaultEventHandler.handleEvent(subscription,event);
                }
            });
    }
}
