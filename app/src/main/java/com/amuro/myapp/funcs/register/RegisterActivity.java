package com.amuro.myapp.funcs.register;

import android.os.Bundle;

import com.amuro.myapp.funcs.MyAppBaseActivity;
import com.amuro.myapp.R;

/**
 * Created by Amuro on 2016/3/24.
 */
public class RegisterActivity extends MyAppBaseActivity implements IRegisterView
{
    @Override
    protected void initData()
    {

    }

    @Override
    protected void initView(Bundle savedInstanceState)
    {
        setContentView(R.layout.activity_register_layout);
    }

    @Override
    public void onRegisterSucceed()
    {

    }

    @Override
    public void onRegisterFailed()
    {

    }

    @Override
    public void onLoadingStarted()
    {

    }

    @Override
    public void onLoadingCompleted()
    {

    }
}
