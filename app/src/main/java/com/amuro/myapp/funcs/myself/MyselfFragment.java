package com.amuro.myapp.funcs.myself;

import android.widget.TextView;

import com.amuro.lib.infrustructure.http_async.core.HttpError;
import com.amuro.lib.mvp.view.BaseFragment;
import com.amuro.lib.utils.LogUtils;
import com.amuro.lib.utils.ToastUtils;
import com.amuro.myapp.R;
import com.amuro.myapp.funcs.login.ILoginView;
import com.amuro.myapp.funcs.login.model.UserBean;
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

    private TextView textView;

    @Override
    protected void initView()
    {
        textView = (TextView)findViewById(R.id.tv);

        if(LoginPresenter.getLoginState() == LoginPresenter.LOGIN_STATE_LOGIN)
        {
            disposeLogin(loginPresenter.getUserBean());
        }
        else
        {
            ToastUtils.show(getActivity(), "login failed");
        }

    }

    private void disposeLogin(UserBean userBean)
    {
        textView.setText(userBean.toString());
    }

    @Override
    public void onLoginStarted()
    {

    }

    @Override
    public void onLoginSucceed(UserBean userBean)
    {
        disposeLogin(userBean);
    }

    @Override
    public void onLoginFailed(HttpError error)
    {

    }


}
