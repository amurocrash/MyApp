package com.amuro.myapp.funcs.myself;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.amuro.lib.infrustructure.http_async.core.HttpError;
import com.amuro.lib.mvp.view.BaseFragment;
import com.amuro.lib.utils.LogUtils;
import com.amuro.lib.utils.ToastUtils;
import com.amuro.myapp.R;
import com.amuro.myapp.funcs.login.ILoginView;
import com.amuro.myapp.funcs.login.LoginActivity;
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
        loginPresenter.addView(this);
    }

    private TextView textView;
    private Button buttonLogout;

    @Override
    protected void initView(Bundle savedInstanceState)
    {
        textView = (TextView)findViewById(R.id.tv);
        buttonLogout = (Button)findViewById(R.id.bt_logout);

        buttonLogout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                loginPresenter.logout();
            }
        });

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

    @Override
    public void onLogout()
    {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    @Override
    public void onDestroy()
    {
        loginPresenter.removeView(this);
        super.onDestroy();
    }
}
