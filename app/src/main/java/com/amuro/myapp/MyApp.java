package com.amuro.myapp;

import android.app.Application;

import com.amuro.lib.LibCore;
import com.amuro.myapp.login.presenter.LoginPresenter;

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
