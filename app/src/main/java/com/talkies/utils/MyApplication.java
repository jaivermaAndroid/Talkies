package com.talkies.utils;

import android.content.Context;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import io.paperdb.Paper;

public class MyApplication extends MultiDexApplication {
    private static RequestQueue mRequestQueue;
    private static MyApplication mAppInstance;
    private static Context mContext;

    public static synchronized MyApplication getInstance() {
        return mAppInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
        setValues(this);
        Paper.init(this);

    }


    private static void setValues(MyApplication myApplication) {
        mAppInstance = myApplication;
        mContext=myApplication;
    }





    public static RequestQueue getRequestQueue() {
        // lazy initialize the request queue, the queue instance will be
        // created when it is accessed for the first time
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(mContext);
        }

        return mRequestQueue;
    }

}