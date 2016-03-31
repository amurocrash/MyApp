package com.amuro.myapp.funcs.login.model;

import com.amuro.lib.infrustructure.http_async.core.HttpError;
import com.amuro.lib.infrustructure.http_async.core.HttpHelper;
import com.amuro.lib.mvp.model.AbsModel;
import com.amuro.lib.mvp.model.Event;
import com.amuro.lib.utils.LogUtils;
import com.amuro.lib.utils.ToastUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Amuro on 2016/3/24.
 */
public class UserModel extends AbsModel
{
    public static UserModel getInstance()
    {
        return getInstance(UserModel.class);
    }

    private UserBean userBean;
    private HttpHelper<UserBean> httpHelper;

    private UserModel()
    {
        userBean = new UserBean();
        httpHelper = new HttpHelper();
    }

    public UserBean getUserBean()
    {
        return userBean;
    }

    public interface LoginListener
    {
        void onSucceed();
        void onFailed();
    }

    public void login(String username, String password)
    {
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("username", username);
        paramMap.put("password", password);

        httpHelper.invoke("login", paramMap, new HttpHelper.OnResponseListener<UserBean>()
        {

            @Override
            public void onSuccess(UserBean obj)
            {
                userBean = obj;
                LogUtils.e(obj.toString());
                notifyEvent(Event.EventType.LOGIN_SUCCEED, obj);
            }

            @Override
            public void onFailed(HttpError error)
            {
                LogUtils.e(error.toString());
                notifyEvent(Event.EventType.LOGIN_FAILED, error);
            }
        });
    }

    public void register()
    {

    }
}
