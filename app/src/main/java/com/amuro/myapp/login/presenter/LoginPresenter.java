package com.amuro.myapp.login.presenter;

import android.os.Handler;

import com.amuro.lib.infrustructure.http.DefaultThreadPool;
import com.amuro.lib.mvp.presenter.AbsPresenter;
import com.amuro.myapp.login.ILoginView;
import com.amuro.myapp.login.model.User;

/**
 * Created by Amuro on 2016/3/24.
 */
public class LoginPresenter extends AbsPresenter<ILoginView>
{
    public static LoginPresenter getInstance()
    {
        return getInstance(LoginPresenter.class);
    }

    private User user;

    public LoginPresenter()
    {
        user = User.getInstance();
    }

    public static final int LOGIN_STATE_LOGOUT = -1;
    public static final int LOGIN_STATE_LOGIN = 0;
    public static final int LOGIN_STATE_LOGINING = 1;

    private static int loginState = LOGIN_STATE_LOGOUT;

    public static int getLoginState()
    {
        return loginState;
    }

    private Handler handler = new Handler();

    public void login(final String username, final String password)
    {
        notifyLoginStart();

        DefaultThreadPool.getInstance().execute(new Runnable()
        {
            @Override
            public void run()
            {
                user.login(username, password);
                notifyLoginResult();
            }
        });
    }

    private void notifyLoginStart()
    {
        loginState = LOGIN_STATE_LOGINING;
        if(view != null)
        {
            view.onLoadingStarted();
        }
    }

    private void notifyLoginResult()
    {
        loginState = LOGIN_STATE_LOGIN;
        if(view != null)
        {
            handler.post(new Runnable()
            {
                @Override
                public void run()
                {
                    view.onLoadingCompleted();
                    view.onLoginSucceed();
                }
            });
        }
    }
}
