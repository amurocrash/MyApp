package com.amuro.myapp.welcome;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;

import com.amuro.lib.mvp.view.BaseActivity;
import com.amuro.myapp.MyAppBaseActivity;
import com.amuro.myapp.R;
import com.amuro.myapp.login.ILoginView;
import com.amuro.myapp.login.presenter.LoginPresenter;
import com.amuro.myapp.main.MainActivity;

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
