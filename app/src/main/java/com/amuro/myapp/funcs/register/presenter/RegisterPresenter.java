package com.amuro.myapp.funcs.register.presenter;

import com.amuro.lib.mvp.presenter.AbsPresenter;
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

    public RegisterPresenter()
    {
        userModel = UserModel.getInstance();
    }

    public void register()
    {
       runInBackground(new Runnable()
       {
           @Override
           public void run()
           {
               userModel.register();
           }
       });

    }
}
