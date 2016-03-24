package com.amuro.myapp.login;

import com.amuro.lib.mvp.view.IMvpView;

/**
 * Created by Amuro on 2016/3/24.
 */
public interface ILoginView extends IMvpView
{
    void onLoginSucceed();
    void onLoginFailed();
}
