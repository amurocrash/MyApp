package com.amuro.myapp.login;

import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.amuro.lib.activity.BaseActivity;
import com.amuro.lib.business.BaseBusiness;
import com.amuro.lib.http.core.HttpError;
import com.amuro.lib.utils.ToastUtils;
import com.amuro.myapp.R;
import com.amuro.myapp.login.business.LoginBusiness;
import com.amuro.myapp.login.dao.UserBean;

/**
 * Created by Amuro on 2016/3/9.
 */
public class LoginActivity extends BaseActivity
{
    private AutoCompleteTextView editTextUsername;
    private EditText editTextPassword;

    private LoginBusiness loginBusiness;

    @Override
    protected void initData()
    {
        loginBusiness = new LoginBusiness();
    }

    @Override
    protected void initView(Bundle savedInstanceState)
    {
        setContentView(R.layout.activity_login_layout);

        editTextUsername = (AutoCompleteTextView)findViewById(R.id.et_username);
        editTextPassword = (EditText)findViewById(R.id.et_password);

        findViewById(R.id.bt_login).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                login();
            }
        });
    }

    private void login()
    {

        loginBusiness.setCallback(new BaseBusiness.OnBusinessCallback<UserBean>()
        {
            @Override
            public void onSuccess(UserBean obj)
            {
                ToastUtils.show(context, obj.toString());
            }

            @Override
            public void onFail(HttpError error)
            {
                ToastUtils.show(context, error.getErrorMessage());
            }
        });
        loginBusiness.login(
                editTextUsername.getText().toString(), editTextPassword.getText().toString());
    }
}
