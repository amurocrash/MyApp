package com.amuro.myapp;

import com.amuro.lib.mvp.view.BaseActivity;

/**
 * Created by Amuro on 2016/3/24.
 */
public abstract class MyAppBaseActivity extends BaseActivity
{
    protected MyApp getMyApp()
    {
        return (MyApp)getApplication();
    }
}
