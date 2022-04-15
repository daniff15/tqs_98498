package com.example.demo;

import java.util.Objects;

public class CarDTO {

    private Long carID;
    private String maker;
    private String model;


    public CarDTO() {
    }


    public CarDTO(Long carID, String maker, String model) {
        this.carID = carID;
        this.maker = maker;
        this.model = model;
    }
    

    public Long getCarID() {
        return this.carID;
    }

    public void setCarID(Long carID) {
        this.carID = carID;
    }

    public String getMaker() {
        return this.maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof CarDTO)) {
            return false;
        }
        CarDTO carDTO = (CarDTO) o;
        return Objects.equals(carID, carDTO.carID) && Objects.equals(maker, carDTO.maker) && Objects.equals(model, carDTO.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carID, maker, model);
    }

}