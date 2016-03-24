package com.amuro.lib.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentActivity;

/**
 * Created by Amuro on 2016/3/9.
 */
public abstract class BaseActivity extends FragmentActivity
{
    protected Context context;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        context = this;
        initData();
        initView(savedInstanceState);
    }

    protected abstract void initData();
    protected abstract void initView(Bundle savedInstanceState);

}
