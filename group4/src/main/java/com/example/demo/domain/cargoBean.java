package com.example.demo.domain;

public class cargoBean {
    private int cargoId;
    private String cargoName;
    private int statu;

    public int getCargoId() {
        return cargoId;
    }

    public void setCargoId(int cargoId) {
        this.cargoId = cargoId;
    }

    public String getCargoName() {
        return cargoName;
    }

    public void setCargoName(String cargoName) {
        this.cargoName = cargoName;
    }

    public int getStatu() {
        return statu;
    }

    public void setStatu(int statu) {
        this.statu = statu;
    }

    public cargoBean(int cargoId, String cargoName, int statu) {
        this.cargoId = cargoId;
        this.cargoName = cargoName;
        this.statu = statu;
    }

    @Override
    public String toString() {
        return "cargoBean{" +
                "cargoId=" + cargoId +
                ", cargoName='" + cargoName + '\'' +
                ", statu=" + statu +
                '}';
    }
}
