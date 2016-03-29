package com.amuro.myapp.funcs.login.model;

import com.amuro.lib.mvp.model.AbsBean;

/**
 * Created by user on 2016/3/29.
 */
public class UserBean extends AbsBean
{
    private String username;
    private String nickname;
    private String sex;
    private String age;
    private String signature;

    public UserBean()
    {

    }

    public UserBean(String username, String nickname, String sex, String age, String signature)
    {
        this.username = username;
        this.nickname = nickname;
        this.sex = sex;
        this.age = age;
        this.signature = signature;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getNickname()
    {
        return nickname;
    }

    public void setNickname(String nickname)
    {
        this.nickname = nickname;
    }

    public String getSex()
    {
        return sex;
    }

    public void setSex(String sex)
    {
        this.sex = sex;
    }

    public String getAge()
    {
        return age;
    }

    public void setAge(String age)
    {
        this.age = age;
    }

    public String getSignature()
    {
        return signature;
    }

    public void setSignature(String signature)
    {
        this.signature = signature;
    }
}
