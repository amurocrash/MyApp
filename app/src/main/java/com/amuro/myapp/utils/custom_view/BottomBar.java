package com.amuro.myapp.utils.custom_view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amuro.lib.utils.DisplayUtils;
import com.amuro.myapp.R;


/**
 * Created by Amuro on 2016/3/25.
 */
public class BottomBar extends LinearLayout
{
    public interface OnBottomBarClickListener
    {
        void onItemClicked(int position);
    }

    private OnBottomBarClickListener onBottomBarClickListener;

    public void setOnBottomBarClickListener(OnBottomBarClickListener onBottomBarClickListener)
    {
        this.onBottomBarClickListener = onBottomBarClickListener;
    }

    private View rootView;

    private TextView textView0;
    private TextView textView1;
    private TextView textView2;
    private TextView textView3;

    public BottomBar(Context context)
    {
        this(context, null, 0);
    }

    public BottomBar(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public BottomBar(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs)
    {
        rootView =
                LayoutInflater.from(getContext()).inflate(R.layout.bottom_bar_layout, null);
        rootView.setLayoutParams(new LayoutParams(
                DisplayUtils.getScreenWidth(getContext()), LayoutParams.WRAP_CONTENT));

        textView0 = (TextView)rootView.findViewById(R.id.tv_0);
        textView1 = (TextView)rootView.findViewById(R.id.tv_1);
        textView2 = (TextView)rootView.findViewById(R.id.tv_2);
        textView3 = (TextView)rootView.findViewById(R.id.tv_3);

        initView();

        addView(rootView);
    }

    private void initView()
    {
        textView0.setOnClickListener(getOnClickListener(0));
        textView1.setOnClickListener(getOnClickListener(1));
        textView2.setOnClickListener(getOnClickListener(2));
        textView3.setOnClickListener(getOnClickListener(3));

    }

    private OnClickListener getOnClickListener(final int position)
    {
        return new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (onBottomBarClickListener != null)
                {
                    onBottomBarClickListener.onItemClicked(position);
                }
            }
        };
    }

    public void setTexts(String[] titles)
    {
        textView0.setText(titles[0]);
        textView1.setText(titles[1]);
        textView2.setText(titles[2]);
        textView3.setText(titles[3]);
    }

}






























