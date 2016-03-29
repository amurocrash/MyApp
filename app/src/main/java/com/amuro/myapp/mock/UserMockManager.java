package com.amuro.myapp.mock;

import com.alibaba.fastjson.JSON;
import com.amuro.lib.infrustructure.http.IMockManager;
import com.amuro.lib.infrustructure.http_async.response_parser.BaseEntity;
import com.amuro.myapp.funcs.login.model.UserBean;

import java.util.Map;

/**
 * Created by user on 2016/3/29.
 */
public class UserMockManager implements IMockManager
{
    private Map<String, String> paramMap;

    @Override
    public void setParams(Map<String, String> paramMap)
    {
        this.paramMap = paramMap;
    }

    @Override
    public BaseEntity getResponse()
    {
        BaseEntity baseEntity = null;

        String username = paramMap.get("username");
        String password = paramMap.get("password");

        if("1".equals(username) && "1".equals(password))
        {
            UserBean ub = new UserBean();
            ub.setUsername("1");
            ub.setSex("0");
            ub.setAge("18");
            ub.setNickname("Amuro");
            ub.setSignature("signature");

            String json = JSON.toJSONString(ub);

            baseEntity = new BaseEntity(true, 200, "success", json);
        }
        else
        {
            baseEntity = new BaseEntity(false, 2000001, "failed", "");
        }

        return baseEntity;
    }

    @Override
    public int getResponseTime()
    {
        return 3;
    }
}
