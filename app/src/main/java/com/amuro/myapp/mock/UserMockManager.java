package com.amuro.myapp.mock;

import com.amuro.lib.http.mock.IMockManager;
import com.amuro.myapp.login.dao.UserBean;

/**
 * Created by Amuro on 2016/3/9.
 */
public class UserMockManager implements IMockManager<UserBean>
{

    @Override
    public UserBean getResponseObject()
    {
        UserBean user = new UserBean();
        user.setUsername("test");
        user.setPassword("test");

        return user;
    }

    @Override
    public int getResponseTime()
    {
        return 3;
    }
}
