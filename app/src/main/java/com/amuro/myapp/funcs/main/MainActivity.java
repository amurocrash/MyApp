package com.amuro.myapp.funcs.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.amuro.myapp.funcs.MyAppBaseActivity;
import com.amuro.myapp.R;
import com.amuro.myapp.funcs.chat.ChatFragment;
import com.amuro.myapp.funcs.myself.MyselfFragment;
import com.amuro.myapp.utils.custom_view.BottomBar;
import com.amuro.myapp.utils.custom_view.CustomViewPager;

public class MainActivity extends MyAppBaseActivity
{
    private CustomViewPager viewPager;
    private MainPagerAdapter adapter;
    private BottomBar bottomBar;

    @Override
    protected void initData()
    {

    }

    @Override
    protected void initView(Bundle savedInstanceState)
    {
        setContentView(R.layout.activity_main_layout);
        viewPager = (CustomViewPager)findViewById(R.id.vp_container);
        bottomBar = (BottomBar)findViewById(R.id.bb);

        viewPager.setPagingEnabled(false);
        adapter = new MainPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        bottomBar.setOnBottomBarClickListener(new BottomBar.OnBottomBarClickListener()
        {
            @Override
            public void onItemClicked(int position)
            {
                viewPager.setCurrentItem(position);
            }
        });
        bottomBar.setTexts(bottomTitles);
    }

    final String[] bottomTitles = new String[]{"chat", "friend", "show", "myself"};

    public class MainPagerAdapter extends FragmentStatePagerAdapter
    {

        public MainPagerAdapter(FragmentManager fm)
        {
            super(fm);
        }

        @Override
        public Fragment getItem(int position)
        {
            switch (position)
            {
                case 0:
                    return new ChatFragment();
                case 1:
                    return new ChatFragment();
                case 2:
                    return new ChatFragment();
                default:
                    return new MyselfFragment();
            }
        }

        @Override
        public CharSequence getPageTitle(int position)
        {
            return bottomTitles[position];
        }

        @Override
        public int getCount()
        {
            return bottomTitles.length;
        }
    }

}
