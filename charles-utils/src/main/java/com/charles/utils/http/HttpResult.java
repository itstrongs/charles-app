package com.charles.utils.http;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by 刘奉强 on 2018/12/16 17:55
 * <p>
 * Describe：
 */
public class HttpResult {

    private int code;

    private String msg;

    private Object data;

    public String getStringData() {
        if (this.data != null) {
            return JSONObject.toJSONString(this.data);
        }
        return null;
    }

    public JSONObject getJSONData() {
        if (this.data != null) {
            return JSONObject.parseObject(JSONObject.toJSONString(this.data));
        }
        return null;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "HttpResult{" +
                "msg='" + msg + '\'' +
                ", code='" + code + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
