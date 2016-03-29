package com.amuro.myapp.funcs.welcome;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.amuro.myapp.funcs.MyAppBaseActivity;
import com.amuro.myapp.R;
import com.amuro.myapp.funcs.login.ILoginView;
import com.amuro.myapp.funcs.login.LoginActivity;
import com.amuro.myapp.funcs.login.presenter.LoginPresenter;
import com.amuro.myapp.funcs.main.MainActivity;

/**
 * Created by Amuro on 2016/3/24.
 */
public class WelcomeActvity extends MyAppBaseActivity implements ILoginView
{
    private LoginPresenter loginPresenter;

    @Override
    protected void initData()
    {
        loginPresenter = LoginPresenter.getInstance();
    }

    @Override
    protected void initView(Bundle savedInstanceState)
    {
        setContentView(R.layout.activity_init_layout);

        loginPresenter.login();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                jumpTo();
            }
        }, 2000);

    }

    private void jumpTo()
    {
        Intent intent;

        if(LoginPresenter.getLoginState() == LoginPresenter.LOGIN_NEVER)
        {
            intent = new Intent(this, LoginActivity.class);
        }
        else
        {
            intent = new Intent(this, MainActivity.class);
        }

        startActivity(intent);
        finish();
    }

    @Override
    public void onLoginStarted()
    {

    }

    @Override
    public void onLoginSucceed()
    {

    }

    @Override
    public void onLoginFailed()
    {

    }

}
