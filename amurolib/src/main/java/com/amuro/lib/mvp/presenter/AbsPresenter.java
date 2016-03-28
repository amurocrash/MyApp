package com.amuro.lib.mvp.presenter;

import com.amuro.lib.infrustructure.http.DefaultThreadPool;
import com.amuro.lib.mvp.view.IMvpView;

/**
 * Created by Amuro on 2016/3/24.
 */
public abstract class AbsPresenter<V extends IMvpView>
{
    public static <P extends AbsPresenter> P getInstance(Class<? extends AbsPresenter> clazz)
    {
        return PresenterManager.getPresenter(clazz);
    }

    protected V view;

    public void setView(V view)
    {
        this.view = view;
    }

    public V getView()
    {
        return view;
    }

    public void removeView()
    {
        view = null;
    }

    protected void runInBackground(Runnable r)
    {
        DefaultThreadPool.getInstance().execute(r);
    }
}
