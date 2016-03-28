package com.amuro.myapp.login.business;

import com.amuro.lib.business.BaseBusiness;
import com.amuro.lib.business.IBusiness;
import com.amuro.lib.http.core.HttpError;
import com.amuro.lib.http.core.HttpHelper;
import com.amuro.myapp.login.dao.UserBean;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Amuro on 2016/3/9.
 */
public class LoginBusiness extends BaseBusiness<UserBean>
{
    private HttpHelper<UserBean> loginHttpHelper;
    private Map<String, String> paramMap;

    public LoginBusiness()
    {
        loginHttpHelper = new HttpHelper<>();
        paramMap = new HashMap<String, String>();
    }

    public void login(String username, String password)
    {
        paramMap.put("username", username);
        paramMap.put("password", password);

        loginHttpHelper.invoke("login", paramMap, new HttpHelper.OnResponseListener<UserBean>()
        {
            @Override
            public void onSuccess(UserBean obj)
            {
                notifyCallbackSucceed(obj);
            }

            @Override
            public void onFailed(HttpError error)
            {
                notifyCallbackFailed(error);
            }
        });
    }
}
