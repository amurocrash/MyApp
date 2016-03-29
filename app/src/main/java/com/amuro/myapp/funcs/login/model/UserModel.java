package com.amuro.myapp.funcs.login.model;

import com.amuro.lib.infrustructure.http.HttpHelper;
import com.amuro.lib.infrustructure.http_async.response_parser.BaseEntity;
import com.amuro.lib.infrustructure.http_async.response_parser.IResponseParser;
import com.amuro.lib.infrustructure.http_async.response_parser.ResponseParserFactory;
import com.amuro.lib.infrustructure.http_async.urlParser.URLData;
import com.amuro.lib.infrustructure.http_async.urlParser.URLDataInitiator;
import com.amuro.lib.mvp.model.AbsModel;

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
    private HttpHelper httpHelper;

    private UserModel()
    {
        userBean = new UserBean();
        httpHelper = new HttpHelper();
    }

    public boolean login(String username, String password)
    {
        URLData urlData = URLDataInitiator.findURL("login");

        if(urlData == null)
        {
            this.errorMsg = "url data not exist";
            return false;
        }

        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("username", username);
        paramMap.put("password", password);
        BaseEntity be = httpHelper.invoke(urlData, paramMap);

        if(be.isSuccess())
        {
            IResponseParser<UserBean> ip =
                    ResponseParserFactory.getResponseParser(urlData.getResponseType());
            ip.parseResponse(be.getResult());

            this.userBean = ip.parseResponse(be.getResult());
            return true;
        }

        this.errorMsg = be.getErrorMessage();
        return false;
    }

    public void register()
    {

    }
}
