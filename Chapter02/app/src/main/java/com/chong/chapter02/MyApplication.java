package com.chong.chapter02;

import com.chong.chapter02.binderpool.BinderPool;
import com.chong.chapter02.utils.MyUtils;

import android.app.Application;
import android.os.Process;
import android.util.Log;

public class MyApplication extends Application {

    private static final String TAG = "MyApplication";

    @Override
    public void onCreate() {
        super.onCreate();
        String processName = MyUtils.getProcessName(getApplicationContext(),
                Process.myPid());
        Log.d(TAG, "application start, process name:" + processName);
        new Thread(new Runnable() {

            @Override
            public void run() {
                doWorkInBackground();
            }
        }).start();
    }

    private void doWorkInBackground() {
        // init binder pool
        BinderPool.getInsance(getApplicationContext());
    }
}
