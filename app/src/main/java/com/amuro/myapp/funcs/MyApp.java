package com.amuro.myapp.funcs;

import android.app.Application;

import com.amuro.lib.LibCore;

/**
 * Created by Amuro on 2016/3/8.
 */
public class MyApp extends Application
{

    @Override
    public void onCreate()
    {
        super.onCreate();

        LibCore.getInstance().init(this);
    }

}
