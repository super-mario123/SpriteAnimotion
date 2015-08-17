package com.sprite.spriteanimotion.app;

import android.app.Application;

import com.activeandroid.ActiveAndroid;
import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

/**
 * Created by dings on 2015/8/5.
 */
public class SApplication extends Application{

    private static Application mApplication;
    private OkHttpClient mOkHttpClient;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        mOkHttpClient = new OkHttpClient();
        mOkHttpClient.setConnectTimeout(10, TimeUnit.SECONDS);
        mOkHttpClient.setReadTimeout(30, TimeUnit.SECONDS);
        ActiveAndroid.initialize(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        ActiveAndroid.dispose();
    }

    public static Application getApplication(){
        return mApplication;
    }

    public OkHttpClient getOkHttpClient(){
        return mOkHttpClient;
    }
}
