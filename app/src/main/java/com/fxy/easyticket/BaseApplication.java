package com.fxy.easyticket;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

/**
 * create by:Fxymine4ever
 * time: 2019/4/8
 */
public class BaseApplication extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
