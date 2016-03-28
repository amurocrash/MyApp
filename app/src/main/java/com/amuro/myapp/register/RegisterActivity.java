package com.amuro.myapp.register;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.amuro.lib.activity.BaseActivity;
import com.amuro.lib.business.BaseBusiness;
import com.amuro.lib.http.core.HttpError;
import com.amuro.lib.utils.ToastUtils;
import com.amuro.myapp.R;
import com.amuro.myapp.login.dao.User;
import com.amuro.myapp.register.business.RegisterBusiness;
import com.amuro.myapp.register.dao.RegisterBean;

/**
 * Created by Amuro on 2016/3/11.
 */
public class RegisterActivity extends BaseActivity
{
    private EditText editTextUsername;

    private RegisterBusiness registerBusiness;

    @Override
    protected void initData()
    {
        registerBusiness = new RegisterBusiness();
    }

    @Override
    protected void initView(Bundle savedInstanceState)
    {
        setContentView(R.layout.activity_register_layout);

        findViewById(R.id.bt_register).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                register();
            }
        });

    }

    private void register()
    {
        registerBusiness.setCallback(new BaseBusiness.OnBusinessCallback<User>()
        {
            @Override
            public void onSuccess(User obj)
            {
                ToastUtils.show(context, obj.toString());
            }

            @Override
            public void onFail(HttpError error)
            {
                ToastUtils.show(context, error.getErrorMessage());
            }
        });

        registerBusiness.register(getRegisterBean());
    }

    private RegisterBean getRegisterBean()
    {
        RegisterBean rb = new RegisterBean();
        rb.setUsername("");
        rb.setPassword("");
        rb.setPasswordConfirm("");
        rb.setEmail("");
        rb.setBirthday("");
        rb.setNickname("");
        rb.setCheckCode("");

        return rb;
    }
}
