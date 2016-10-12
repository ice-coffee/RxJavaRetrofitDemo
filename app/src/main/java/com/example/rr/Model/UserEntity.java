package com.example.rr.Model;

/**
 * 相同格式的请求返回数据处理
 */
public class UserEntity
{
    private String code;

    private String msg;

    private UserInfos data;

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }

    public UserInfos getData()
    {
        return data;
    }

    public void setData(UserInfos data)
    {
        this.data = data;
    }
}
