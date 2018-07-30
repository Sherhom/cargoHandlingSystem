package com.example.demo.domain;

public class skuBean {
    private int skuId;
    private String skuColor;
    private int count;
    private String skuType;
    private int cargoId;
    private int whId;

    public int getSkuId() {
        return skuId;
    }

    public void setSkuId(int skuId) {
        this.skuId = skuId;
    }

    public String getSkuColor() {
        return skuColor;
    }

    public void setSkuColor(String skuColor) {
        this.skuColor = skuColor;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getSkuType() {
        return skuType;
    }

    public void setSkuType(String skuType) {
        this.skuType = skuType;
    }

    public int getCargoId() {
        return cargoId;
    }

    public void setCargoId(int cargoId) {
        this.cargoId = cargoId;
    }

    public int getWhId() {
        return whId;
    }

    public void setWhId(int whId) {
        this.whId = whId;
    }

    public skuBean(int skuId, String skuColor, String skuType, int cargoId, int whId,int count) {
        this.skuId = skuId;
        this.skuColor = skuColor;
        this.count = count;
        this.skuType = skuType;
        this.cargoId = cargoId;
        this.whId = whId;
    }

    @Override
    public String toString() {
        return "skuBean{" +
                "skuId=" + skuId +
                ", skuColor='" + skuColor + '\'' +
                ", count=" + count +
                ", skuType='" + skuType + '\'' +
                ", cargoId=" + cargoId +
                ", whId=" + whId +
                '}';
    }
}
