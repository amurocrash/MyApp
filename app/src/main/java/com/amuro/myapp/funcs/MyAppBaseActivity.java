package com.amuro.myapp.funcs;

import android.app.ProgressDialog;

import com.amuro.lib.mvp.view.BaseActivity;
import com.amuro.lib.utils.DialogUtils;
import com.amuro.myapp.funcs.MyApp;

/**
 * Created by Amuro on 2016/3/24.
 */
public abstract class MyAppBaseActivity extends BaseActivity
{
    protected MyApp getMyApp()
    {
        return (MyApp)getApplication();
    }

    private ProgressDialog progressDialog;

    protected ProgressDialog getProgressDialog()
    {
        if(progressDialog == null)
        {
            progressDialog = DialogUtils.getProgressDialog(this);
        }

        return progressDialog;
    }
}
