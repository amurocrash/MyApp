package com.amuro.lib.http.mock;

/**
 * Created by Amuro on 2016/3/9.
 */
public interface IMockManager<T>
{
    T getResponseObject();
    int getResponseTime();
}
