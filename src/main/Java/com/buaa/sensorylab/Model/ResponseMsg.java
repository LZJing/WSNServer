package com.buaa.sensorylab.Model;

/**
 * Created by LZJing on 2015/11/10.
 */
public class ResponseMsg<T> {
    private boolean result;
    private String msg;
    private T data;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
