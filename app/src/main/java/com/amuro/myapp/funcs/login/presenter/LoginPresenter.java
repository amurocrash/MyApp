package com.amuro.myapp.funcs.login.presenter;

import android.os.Handler;
import android.text.TextUtils;

import com.amuro.lib.infrustructure.http_async.core.HttpError;
import com.amuro.lib.mvp.model.Event;
import com.amuro.lib.mvp.presenter.AbsPresenter;
import com.amuro.lib.utils.LogUtils;
import com.amuro.lib.utils.SharedPreferManager;
import com.amuro.myapp.funcs.login.ILoginView;
import com.amuro.myapp.funcs.login.model.UserBean;
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

    private UserModel  userModel;

    public LoginPresenter()
    {
        userModel = UserModel.getInstance();
        userModel.registerEventSubscriber(this);
    }

    private static int loginState = LOGIN_STATE_LOGOUT;

    public static int getLoginState()
    {
        return loginState;
    }

    private Handler handler = new Handler();
    private String username;
    private String password;

    public void login()
    {
        username = SharedPreferManager.getInstance().getSpValue("username");
        password = SharedPreferManager.getInstance().getSpValue("password");

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
        this.username = username;
        this.password = password;
        notifyLoginStart();
        userModel.login(username, password);
    }

    private void notifyLoginStart()
    {
        loginState = LOGIN_STATE_LOGINING;
        if(view != null)
        {
            view.onLoginStarted();
        }
    }

    @Event(Event.EventType.LOGIN_SUCCEED)
    private void notifyLoginSucceed(UserBean bean)
    {
        SharedPreferManager.getInstance().saveToSP("username", username);
        SharedPreferManager.getInstance().saveToSP("password", password);

        loginState = LOGIN_STATE_LOGIN;
        if(view != null)
        {
            view.onLoginSucceed(bean);
        }
    }

    @Event(Event.EventType.LOGIN_FAILED)
    private void notifyLoginFailed(HttpError error)
    {
        loginState = LOGIN_STATE_LOGOUT;
        if(view != null)
        {
            view.onLoginFailed(error);
        }
    }

    public UserBean getUserBean()
    {
        return userModel.getUserBean();
    }
}
