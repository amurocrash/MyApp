package com.amuro.myapp.main;

import android.os.Bundle;

import com.amuro.lib.utils.ToastUtils;
import com.amuro.myapp.MyAppBaseActivity;
import com.amuro.myapp.R;
import com.amuro.myapp.login.ILoginView;
import com.amuro.myapp.login.presenter.LoginPresenter;

public class MainActivity extends MyAppBaseActivity implements ILoginView
{
    private LoginPresenter loginPresenter;

    @Override
    protected void initData()
    {
        loginPresenter = LoginPresenter.getInstance();
        loginPresenter.setView(this);
    }

    @Override
    protected void initView(Bundle savedInstanceState)
    {
        setContentView(R.layout.activity_main_layout);

        if(LoginPresenter.getLoginState() == LoginPresenter.LOGIN_STATE_LOGIN)
        {
            doWhenLogin();
        }
    }

    private void doWhenLogin()
    {
        ToastUtils.show(this, "Login succeed");
    }


    @Override
    public void onLoginSucceed()
    {
        doWhenLogin();
    }

    @Override
    public void onLoginFailed()
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

    @Override
    protected void onDestroy()
    {
        loginPresenter.removeView();
        super.onDestroy();
    }
}
