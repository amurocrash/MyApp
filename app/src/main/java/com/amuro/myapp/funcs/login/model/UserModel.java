package com.amuro.myapp.funcs.login.model;

import com.amuro.lib.infrustructure.http_async.core.HttpError;
import com.amuro.lib.infrustructure.http_async.core.HttpHelper;
import com.amuro.lib.mvp.model.AbsModel;
import com.amuro.lib.mvp.model.Event;
import com.amuro.lib.utils.LogUtils;

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
    private HttpHelper<UserBean> httpHelperLogin;
    private HttpHelper httpHelperRegister;

    private UserModel()
    {
        userBean = new UserBean();
        httpHelperLogin = new HttpHelper();
        httpHelperRegister = new HttpHelper();
    }

    public UserBean getUserBean()
    {
        return userBean;
    }

    public void login(String username, String password)
    {
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("username", username);
        paramMap.put("password", password);

        httpHelperLogin.invoke("login", paramMap, new HttpHelper.OnResponseListener<UserBean>()
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

    public void register(
            String username, String password, String nickname, String sex,
            String age, String signature, String checkCode)
    {
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("username", username);
        paramMap.put("password", password);
        paramMap.put("nickname", nickname);
        paramMap.put("sex", sex);
        paramMap.put("age", age);
        paramMap.put("signature", signature);
        paramMap.put("checkCode", checkCode);

        httpHelperRegister.invoke("register", paramMap, new HttpHelper.OnResponseListener()
        {
            @Override
            public void onSuccess(Object o)
            {
                notifyEvent(Event.EventType.REGISTER_SUCCESS);
            }

            @Override
            public void onFailed(HttpError error)
            {
                notifyEvent(Event.EventType.REGISTER_FAILED, error);
            }
        });
    }
}






























