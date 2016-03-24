package com.amuro.myapp.test;

import java.io.Serializable;

/**
 * Created by Amuro on 2016/3/9.
 */
public class TestBean implements Serializable
{
    private String username;
    private String password;

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("username=" + username + "\n");
        sb.append("password=" + password + "\n");

        return sb.toString();

    }
}
