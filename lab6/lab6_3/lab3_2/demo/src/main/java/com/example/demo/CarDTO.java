package com.example.demo;

public class CarDTO {

    private Long carID;
    private String maker;
    private String model;


    public CarDTO() {
    }


    public CarDTO(String maker, String model) {
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

}