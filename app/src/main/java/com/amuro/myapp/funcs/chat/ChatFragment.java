package com.amuro.myapp.funcs.chat;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.amuro.lib.mvp.view.BaseFragment;

/**
 * Created by Amuro on 2016/3/25.
 */
public class ChatFragment extends BaseFragment
{
//    @Override
//    protected int getRootViewId()
//    {
//        return R.layout.activity_test_layout;
//    }


    @Override
    protected View getRootView()
    {
        LinearLayout linearLayout = new LinearLayout(getActivity());
        linearLayout.setLayoutParams(
                new LayoutParams(
                        LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        TextView textView = new TextView(getActivity());
        textView.setLayoutParams(
                new LayoutParams(
                        LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        textView.setText("chat");

        linearLayout.addView(textView);
        return linearLayout;
    }

    @Override
    protected void initData()
    {

    }

    @Override
    protected void initView(Bundle savedInstanceState)
    {

    }
}
