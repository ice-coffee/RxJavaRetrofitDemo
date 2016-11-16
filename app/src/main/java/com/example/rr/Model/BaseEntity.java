package com.example.rr.Model;

/**
 * Created by mzp on 2016/11/16.
 */

public class BaseEntity
{
    private String code;

    private String msg;

    private Object data;

    public String getCode()
    {
        return code;
    }

    public BaseEntity setCode(String code)
    {
        this.code = code;
        return this;
    }

    public String getMsg()
    {
        return msg;
    }

    public BaseEntity setMsg(String msg)
    {
        this.msg = msg;
        return this;
    }

    public Object getData()
    {
        return data;
    }

    public BaseEntity setData(Object data)
    {
        this.data = data;
        return this;
    }
}
