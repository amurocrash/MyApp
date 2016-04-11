package com.amuro.myapp.funcs.register.presenter;

import com.amuro.lib.infrustructure.http_async.core.HttpError;
import com.amuro.lib.mvp.model.Event;
import com.amuro.lib.mvp.presenter.AbsPresenter;
import com.amuro.myapp.funcs.login.model.UserBean;
import com.amuro.myapp.funcs.login.model.UserModel;
import com.amuro.myapp.funcs.register.IRegisterView;

/**
 * Created by Amuro on 2016/3/24.
 */
public class RegisterPresenter extends AbsPresenter<IRegisterView>
{
    public static RegisterPresenter getInstance()
    {
        return getInstance(RegisterPresenter.class);
    }

    private UserModel userModel;

    protected RegisterPresenter()
    {
        super();
        userModel = UserModel.getInstance();
        userModel.registerEventSubscriber(this);

    }

    public void register(String username, String password, String nickname, String sex,
                         String age, String signature, String checkCode)
    {
        userModel.register(username, password, nickname, sex, age, signature, checkCode);

        for(IRegisterView view : viewList)
        {
            if (view != null)
            {
                view.onRegisterStarted();
            }
        }
    }

    @Event(Event.EventType.REGISTER_SUCCESS)
    private void onRegisterSuccerss()
    {
        for(IRegisterView view : viewList)
        {
            if (view != null)
            {
                view.onRegisterSucceed();
            }
        }
    }

    @Event(Event.EventType.REGISTER_FAILED)
    private void onRegisterFailed(HttpError error)
    {
        for(IRegisterView view : viewList)
        {
            if (view != null)
            {
                view.onRegisterFailed(error);
            }
        }
    }
}
