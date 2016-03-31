package com.amuro.myapp.funcs.login;

import com.amuro.lib.infrustructure.http_async.core.HttpError;
import com.amuro.lib.mvp.view.IMvpView;
import com.amuro.myapp.funcs.login.model.UserBean;

/**
 * Created by Amuro on 2016/3/24.
 */
public interface ILoginView extends IMvpView
{
    void onLoginStarted();
    void onLoginSucceed(UserBean userBean);
    void onLoginFailed(HttpError error);
}
