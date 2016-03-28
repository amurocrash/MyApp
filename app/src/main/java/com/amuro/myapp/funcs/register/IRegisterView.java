package com.amuro.myapp.funcs.register;

import com.amuro.lib.mvp.view.IMvpView;

/**
 * Created by Amuro on 2016/3/24.
 */
public interface IRegisterView extends IMvpView
{
    void onRegisterSucceed();
    void onRegisterFailed();
}
