package com.example.demo.domain;

public class warehouseBean {
    private int whId;
    private String whName;
    private String whAddress;
    private int status;

    public int getWhId() {
        return whId;
    }

    public void setWhId(int whId) {
        this.whId = whId;
    }

    public String getWhName() {
        return whName;
    }

    public void setWhName(String whName) {
        this.whName = whName;
    }

    public String getWhAddress() {
        return whAddress;
    }

    public void setWhAddress(String whAddress) {
        this.whAddress = whAddress;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public warehouseBean(int whId, String whName, String whAddress, int status) {
        this.whId = whId;
        this.whName = whName;
        this.whAddress = whAddress;
        this.status = status;
    }

    @Override
    public String toString() {
        return "warehouseBean{" +
                "whId=" + whId +
                ", whName='" + whName + '\'' +
                ", whAddress='" + whAddress + '\'' +
                ", status=" + status +
                '}';
    }
}
