package com.amuro.myapp.funcs.login.presenter;

import android.os.Handler;
import android.text.TextUtils;

import com.amuro.lib.mvp.presenter.AbsPresenter;
import com.amuro.lib.utils.LogUtils;
import com.amuro.lib.utils.SharedPreferManager;
import com.amuro.myapp.funcs.login.ILoginView;
import com.amuro.myapp.funcs.login.model.UserModel;

/**
 * Created by Amuro on 2016/3/24.
 */
public class LoginPresenter extends AbsPresenter<ILoginView>
{
    public static final int LOGIN_NEVER = -2;
    public static final int LOGIN_STATE_LOGOUT = -1;
    public static final int LOGIN_STATE_LOGIN = 0;
    public static final int LOGIN_STATE_LOGINING = 1;

    public static LoginPresenter getInstance()
    {
        return getInstance(LoginPresenter.class);
    }

    private UserModel userModel;

    public LoginPresenter()
    {
        userModel = UserModel.getInstance();
    }

    private static int loginState = LOGIN_STATE_LOGOUT;

    public static int getLoginState()
    {
        return loginState;
    }

    private Handler handler = new Handler();

    public void login()
    {
        String username = SharedPreferManager.getInstance().getSpValue("username");
        String password = SharedPreferManager.getInstance().getSpValue("password");

        LogUtils.e("auto login: " + username + ", " + password);

        if(TextUtils.isEmpty(username) || TextUtils.isEmpty(password))
        {
            loginState = LOGIN_NEVER;
            return;
        }

        login(username, password);
    }

    public void login(final String username, final String password)
    {
        notifyLoginStart();

        runInBackground(new Runnable()
        {
            @Override
            public void run()
            {
                if(userModel.login(username, password))
                {
                    notifyLoginSucceed();
                }
                else
                {
                    notifyLoginFailed();
                }
            }
        });

    }

    private void notifyLoginStart()
    {
        loginState = LOGIN_STATE_LOGINING;
        if(view != null)
        {
            view.onLoginStarted();
        }
    }

    private void notifyLoginSucceed()
    {
        loginState = LOGIN_STATE_LOGIN;
        if(view != null)
        {
            handler.post(new Runnable()
            {
                @Override
                public void run()
                {
                    view.onLoginSucceed();
                }
            });
        }
    }

    private void notifyLoginFailed()
    {
        loginState = LOGIN_STATE_LOGOUT;
        if(view != null)
        {
            handler.post(new Runnable()
            {
                @Override
                public void run()
                {
                    view.onLoginFailed();
                }
            });
        }
    }
}
