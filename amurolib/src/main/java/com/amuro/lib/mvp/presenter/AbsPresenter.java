package com.amuro.lib.mvp.presenter;

import com.amuro.lib.infrustructure.http_async.DefaultThreadPool;
import com.amuro.lib.mvp.view.IMvpView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Amuro on 2016/3/24.
 */
public abstract class AbsPresenter<V extends IMvpView>
{
    public static <P extends AbsPresenter> P getInstance(Class<? extends AbsPresenter> clazz)
    {
        return PresenterManager.getPresenter(clazz);
    }

    protected AbsPresenter()
    {
        viewList = new ArrayList<>();
    }

    protected List<V> viewList;

    public void addView(V view)
    {
        viewList.add(view);
    }

    public void removeView(V view)
    {
        viewList.remove(view);
    }

    protected void runInBackground(Runnable r)
    {
        DefaultThreadPool.getInstance().execute(r);
    }
}
