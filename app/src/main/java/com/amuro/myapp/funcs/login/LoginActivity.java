package com.amuro.myapp.funcs.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.amuro.lib.infrustructure.http_async.core.HttpError;
import com.amuro.lib.utils.DialogUtils;
import com.amuro.lib.utils.ToastUtils;
import com.amuro.myapp.R;
import com.amuro.myapp.funcs.MyAppBaseActivity;
import com.amuro.myapp.funcs.login.model.UserBean;
import com.amuro.myapp.funcs.login.presenter.LoginPresenter;
import com.amuro.myapp.funcs.main.MainActivity;
import com.amuro.myapp.funcs.register.RegisterActivity;

/**
 * Created by user on 2016/3/28.
 */
public class LoginActivity extends MyAppBaseActivity implements ILoginView
{
    private LoginPresenter loginPresenter;

    @Override
    protected void initData()
    {
        loginPresenter = LoginPresenter.getInstance();
        loginPresenter.addView(this);
    }

    private AutoCompleteTextView acTextViewUsername;
    private EditText editTextPassword;
    private Button buttonLogin;
    private TextView textViewForgetPassword;
    private TextView textViewRegister;

    @Override
    protected void initView(Bundle savedInstanceState)
    {
        setContentView(R.layout.activity_login_layout);

        acTextViewUsername = (AutoCompleteTextView)findViewById(R.id.et_username);
        editTextPassword = (EditText)findViewById(R.id.et_password);
        buttonLogin = (Button)findViewById(R.id.bt_login);
        textViewForgetPassword = (TextView)findViewById(R.id.tv_forget_password);
        textViewRegister = (TextView)findViewById(R.id.tv_register);

        buttonLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                toLogin();
            }
        });

        textViewForgetPassword.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

            }
        });

        textViewRegister.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                toRegister();
            }
        });
    }

    private void toLogin()
    {
        String username = acTextViewUsername.getText().toString();
        String password = editTextPassword.getText().toString();

        if(TextUtils.isEmpty(username) || TextUtils.isEmpty(password))
        {
            ToastUtils.show(this, "username or password can not be empty~");
            return;
        }

        loginPresenter.login(username, password);
    }

    private void toRegister()
    {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }


    @Override
    public void onLoginStarted()
    {
        getProgressDialog().show();
    }

    @Override
    public void onLoginSucceed(UserBean userBean)
    {
        getProgressDialog().dismiss();
        ToastUtils.show(this, "sign in success");
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onLoginFailed(HttpError error)
    {
        getProgressDialog().dismiss();
        ToastUtils.show(this, "sign in failed: " + error);
    }

    @Override
    public void onLogout()
    {

    }

    @Override
    protected void onDestroy()
    {
        loginPresenter.removeView(this);
        super.onDestroy();
    }
}
