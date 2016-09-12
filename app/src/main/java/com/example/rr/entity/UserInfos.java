package com.example.rr.entity;

/**
 * Created by mzp on 2016/8/22.
 */
public class UserInfos
{
    private String user_token;
    private String uid;
    private String username; //用户名(登录)
    private String gender; //性别
    private String mobile; //手机号
    private String email; //邮箱
    private String zodiac; //生肖
    private String totalFobi; //总佛币
    private int is_perfect; //是否完善信息

    public int getIs_perfect()
    {
        return is_perfect;
    }

    public void setIs_perfect(int is_perfect)
    {
        this.is_perfect = is_perfect;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getGender()
    {
        return gender;
    }

    public void setGender(String gender)
    {
        this.gender = gender;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getTotalFobi()
    {
        return totalFobi;
    }

    public void setTotalFobi(String totalFobi)
    {
        this.totalFobi = totalFobi;
    }

    public String getUser_token()
    {
        return user_token;
    }

    public void setUser_token(String user_token)
    {
        this.user_token = user_token;
    }

    public String getUid()
    {
        return uid;
    }

    public void setUid(String uid)
    {
        this.uid = uid;
    }

    public String getMobile()
    {
        return mobile;
    }

    public void setMobile(String mobile)
    {
        this.mobile = mobile;
    }

    public String getZodiac()
    {
        return zodiac;
    }

    public void setZodiac(String zodiac)
    {
        this.zodiac = zodiac;
    }
}

