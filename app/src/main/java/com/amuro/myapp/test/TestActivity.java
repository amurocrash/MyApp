package com.amuro.myapp.test;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.amuro.lib.activity.BaseActivity;
import com.amuro.lib.http.core.HttpError;
import com.amuro.lib.http.core.HttpHelper;
import com.amuro.lib.utils.DialogUtils;
import com.amuro.lib.utils.ToastUtils;
import com.amuro.myapp.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Amuro on 2016/3/9.
 */
public class TestActivity extends BaseActivity
{
    private ProgressDialog progressDialog;
    private Context context;
    private HttpHelper<TestBean> userHttpHelper;

    @Override
    protected void initData()
    {
        context = this;
        userHttpHelper = new HttpHelper<TestBean>();
    }

    @Override
    protected void initView(Bundle savedInstanceState)
    {
        setContentView(R.layout.activity_test_layout);

        progressDialog = DialogUtils.getProgressDialog(context);


        findViewById(R.id.bt1).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                testGet();
            }
        });

        findViewById(R.id.bt2).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                testPost();
            }
        });
    }

    private void testGet()
    {
        progressDialog.show();

        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("username", "amuro");
        paramMap.put("password", "1234");


        userHttpHelper.invoke("testGet", paramMap, new HttpHelper.OnResponseListener<TestBean>()
        {

            @Override
            public void onSuccess(TestBean user)
            {
                progressDialog.dismiss();
                ToastUtils.show(context, "succeed\n" + user.toString());
            }

            @Override
            public void onFailed(HttpError error)
            {
                progressDialog.dismiss();
                ToastUtils.show(
                        context,
                        "failed\n" + error.getErrorCode() + " : " + error.getErrorMessage());
            }
        });
    }

    private void testPost()
    {
        progressDialog.show();

        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("username", "amuro");
        paramMap.put("password", "1234");

        userHttpHelper.invoke("testPost", paramMap, new HttpHelper.OnResponseListener<TestBean>()
        {

            @Override
            public void onSuccess(TestBean user)
            {
                progressDialog.dismiss();
                ToastUtils.show(context, "succeed\n" + user.toString());
            }

            @Override
            public void onFailed(HttpError error)
            {
                progressDialog.dismiss();
                ToastUtils.show(
                        context,
                        "failed\n" + error.getErrorCode() + " : " + error.getErrorMessage());
            }
        });
    }

    @Override
    protected void onDestroy()
    {
        userHttpHelper.cancel();
        super.onDestroy();
    }
}
