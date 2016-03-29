package com.amuro.myapp.funcs.login;

import com.amuro.lib.mvp.view.IMvpView;

/**
 * Created by Amuro on 2016/3/24.
 */
public interface ILoginView extends IMvpView
{
    void onLoginStarted();
    void onLoginSucceed();
    void onLoginFailed();
}
