package com.amuro.lib.business;

import com.amuro.lib.http.core.HttpError;

import java.util.List;
import java.util.Objects;

/**
 * Created by Amuro on 2016/3/10.
 */
public abstract class BaseBusiness<T> implements IBusiness
{
    public interface OnBusinessCallback<T>
    {
        public void onSuccess(T obj);
        public void onFail(HttpError error);
    }

    private OnBusinessCallback<T> callback;

    public void setCallback(OnBusinessCallback<T> callback)
    {
        this.callback = callback;
    }

    public void removeCallback()
    {
        this.callback = null;
    }

    protected void notifyCallbackSucceed(T obj)
    {
        if(callback != null)
        {
            callback.onSuccess(obj);
        }
    }

    protected void notifyCallbackFailed(HttpError error)
    {
        if(callback != null)
        {
            callback.onFail(error);
        }
    }
}
