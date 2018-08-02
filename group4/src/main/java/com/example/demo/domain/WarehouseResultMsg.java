package com.example.demo.domain;

import java.util.List;
public class WarehouseResultMsg {
    private int code;
    private String msg;
    private int count;
    private List<warehouseBean> data;

    public WarehouseResultMsg(int code, String msg, int count, List<warehouseBean> data) {
        this.code = code;
        this.msg = msg;
        this.count = count;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<warehouseBean> getData() {
        return data;
    }

    public void setData(List<warehouseBean> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultMsg{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", count=" + count +
                ", data=" + data +
                '}';
    }
}
