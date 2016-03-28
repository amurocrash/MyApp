package com.amuro.myapp.funcs.login.model;

import com.amuro.lib.mvp.model.AbsModel;

/**
 * Created by Amuro on 2016/3/24.
 */
public class User extends AbsModel
{
    public static User getInstance()
    {
        return getInstance(User.class);
    }

    private String username;
    private String nickname;
    private String sex;
    private String age;
    private String signature;

    public User()
    {

    }

    public User(String username, String nickname, String sex, String age, String signature)
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

    public void login(String username, String password)
    {
        try
        {
            Thread.sleep(3000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    public void register()
    {

    }
}
