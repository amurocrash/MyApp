package com.amuro.myapp.funcs.welcome;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.amuro.myapp.funcs.MyAppBaseActivity;
import com.amuro.myapp.R;
import com.amuro.myapp.funcs.login.ILoginView;
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

        loginPresenter.login("1", "1");

        Handler handler = new Handler();
        handler.postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                toMainActivity();
            }
        }, 2000);

    }

    private void toMainActivity()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onLoginSucceed()
    {

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
}
