package com.amuro.lib.mvp.model;


/**
 * Created by Amuro on 2016/3/24.
 */
public class AbsModel
{
    public static <M extends AbsModel> M getInstance(Class<? extends AbsModel> clazz)
    {
        return ModelManager.getModel(clazz);
    }

    protected String errorMsg;

    public String getErrorMsg()
    {
        return errorMsg;
    }
}
