package com.example.demo.domain;
/**
 * 货物相关实体类
 *
 * @author 杨添
 *
 */
public class cargo {
    private String cargoName;

    public String getCargoName() {
        return cargoName;
    }

    public void setCargoName(String cargoName) {
        this.cargoName = cargoName;
    }

    public cargo(String cargoName) {
        this.cargoName = cargoName;
    }

    @Override
    public String toString() {
        return "cargo{" +
                "cargoName='" + cargoName + '\'' +
                '}';
    }
}
