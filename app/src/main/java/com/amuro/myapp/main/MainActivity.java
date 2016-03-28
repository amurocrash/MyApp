package com.amuro.myapp.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.amuro.lib.activity.BaseActivity;
import com.amuro.myapp.R;
import com.amuro.myapp.login.LoginActivity;
import com.amuro.myapp.register.RegisterActivity;
import com.amuro.myapp.test.TestActivity;

public class MainActivity extends BaseActivity
{

    @Override
    protected void initData()
    {

    }

    @Override
    protected void initView(Bundle savedInstanceState)
    {
        setContentView(R.layout.activity_main_layout);

        findViewById(R.id.bt_to_login).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(context, LoginActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.bt_to_register).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(context, RegisterActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.bt_to_test).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(context, TestActivity.class);
                startActivity(intent);
            }
        });
    }


}
