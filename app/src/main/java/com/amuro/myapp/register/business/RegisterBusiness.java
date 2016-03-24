package com.amuro.myapp.register.business;

import com.amuro.lib.business.BaseBusiness;
import com.amuro.lib.business.IBusiness;
import com.amuro.lib.http.core.HttpError;
import com.amuro.lib.http.core.HttpHelper;
import com.amuro.myapp.login.dao.User;
import com.amuro.myapp.register.dao.RegisterBean;

/**
 * Created by Amuro on 2016/3/11.
 */
public class RegisterBusiness extends BaseBusiness<User>
{
    private HttpHelper<User> httpHelper;

    public RegisterBusiness()
    {
        httpHelper = new HttpHelper<>();
    }

    public void register(RegisterBean rb)
    {
        httpHelper.invoke("register", rb, new HttpHelper.OnResponseListener<User>()
        {
            @Override
            public void onSuccess(User obj)
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
