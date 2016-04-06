package com.amuro.myapp.funcs.register;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.amuro.lib.infrustructure.http_async.core.HttpError;
import com.amuro.lib.utils.ToastUtils;
import com.amuro.myapp.funcs.MyAppBaseActivity;
import com.amuro.myapp.R;
import com.amuro.myapp.funcs.login.ILoginView;
import com.amuro.myapp.funcs.login.model.UserBean;
import com.amuro.myapp.funcs.login.presenter.LoginPresenter;
import com.amuro.myapp.funcs.main.MainActivity;
import com.amuro.myapp.funcs.register.presenter.RegisterPresenter;

/**
 * Created by Amuro on 2016/3/24.
 */
public class RegisterActivity extends MyAppBaseActivity implements IRegisterView, ILoginView
{
    private RegisterPresenter registerPresenter;
    private LoginPresenter loginPresenter;

    @Override
    protected void initData()
    {
        registerPresenter = RegisterPresenter.getInstance();
        registerPresenter.addView(this);
        loginPresenter = LoginPresenter.getInstance();
        loginPresenter.addView(this);
    }

    private EditText editTextUsername;
    private EditText editTextPassword;
    private EditText editTextPasswordConfirm;
    private EditText editTextNickname;
    private EditText editTextSex;
    private EditText editTextAge;
    private EditText editTextSignature;
    private EditText editTextCheckCode;

    private Button buttonRegister;

    @Override
    protected void initView(Bundle savedInstanceState)
    {
        setContentView(R.layout.activity_register_layout);

        editTextUsername = (EditText)findViewById(R.id.et_username);
        editTextPassword = (EditText)findViewById(R.id.et_password);
        editTextPasswordConfirm = (EditText)findViewById(R.id.et_password_confirm);
        editTextNickname = (EditText)findViewById(R.id.et_nickname);
        editTextSex = (EditText)findViewById(R.id.et_sex);
        editTextAge = (EditText)findViewById(R.id.et_age);
        editTextSignature = (EditText)findViewById(R.id.et_signature);
        editTextCheckCode = (EditText)findViewById(R.id.et_check_code);

        buttonRegister = (Button)findViewById(R.id.bt_register);
        buttonRegister.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                toRegister();
            }
        });

    }

    private void toRegister()
    {
        String username = editTextUsername.getText().toString();
        String password = editTextPassword.getText().toString();
        String passwordConfirm = editTextPasswordConfirm.getText().toString();
        String nickname = editTextNickname.getText().toString();
        String sex = editTextSex.getText().toString();
        String age = editTextAge.getText().toString();
        String signature = editTextSignature.getText().toString();
        String checkCode = editTextCheckCode.getText().toString();

        if(TextUtils.isEmpty(username) || TextUtils.isEmpty(password) ||
                TextUtils.isEmpty(passwordConfirm) || TextUtils.isEmpty(sex) ||
                TextUtils.isEmpty(age) || TextUtils.isEmpty(nickname) ||
                TextUtils.isEmpty(checkCode))
        {
            ToastUtils.show(RegisterActivity.this, "None of them could not be empty!");
            return;
        }

        if(!password.equals(passwordConfirm))
        {
            ToastUtils.show(this, "twice password doesn't match");
            return;
        }

        registerPresenter.register(username, password, nickname, sex, age, signature, checkCode);
    }


    @Override
    public void onRegisterStarted()
    {
        getProgressDialog().show();
    }

    @Override
    public void onRegisterSucceed()
    {
        getProgressDialog().dismiss();
        ToastUtils.show(this, "register succeed, now login for you~");
        loginPresenter.login(
                editTextUsername.getText().toString(),
                editTextPassword.getText().toString());
    }

    @Override
    public void onRegisterFailed(HttpError error)
    {
        getProgressDialog().dismiss();
        ToastUtils.show(this, "register failed " + error.toString());
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
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onLoginFailed(HttpError error)
    {
        getProgressDialog().dismiss();
        ToastUtils.show(this, "login failed " + error.toString());
    }

    @Override
    public void onLogout()
    {

    }

    @Override
    protected void onDestroy()
    {
        registerPresenter.removeView(this);
        loginPresenter.removeView(this);
        super.onDestroy();
    }
}
