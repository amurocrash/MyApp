package com.amuro.myapp.funcs.myself;

import com.amuro.lib.mvp.view.BaseFragment;
import com.amuro.lib.utils.LogUtils;
import com.amuro.lib.utils.ToastUtils;
import com.amuro.myapp.R;
import com.amuro.myapp.funcs.login.ILoginView;
import com.amuro.myapp.funcs.login.presenter.LoginPresenter;

/**
 * Created by Amuro on 2016/3/25.
 */
public class MyselfFragment extends BaseFragment implements ILoginView
{
    private LoginPresenter loginPresenter;

    @Override
    protected int getRootViewId()
    {
        return R.layout.fragment_myself_layout;
    }

    @Override
    protected void initData()
    {
        loginPresenter = LoginPresenter.getInstance();
        loginPresenter.setView(this);
    }

    @Override
    protected void initView()
    {
        if(LoginPresenter.getLoginState() == LoginPresenter.LOGIN_STATE_LOGIN)
        {
            ToastUtils.show(getActivity(), "login succeed");
        }
        else
        {
            ToastUtils.show(getActivity(), "login failed");
        }

    }

    @Override
    public void onLoginStarted()
    {

    }

    @Override
    public void onLoginSucceed()
    {
        ToastUtils.show(getActivity(), "login succeed");
    }

    @Override
    public void onLoginFailed()
    {

    }


}
