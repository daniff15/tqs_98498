package com.example.demo.entities;


public class Province {

    private String iso;
    private String name;
    private String province;
    private Double latitude;
    private Double longitude;

    public Province() {
    }

    public Province(String iso, String name, String province, Double latitude, Double longitude) {
        this.iso = iso;
        this.name = name;
        this.province = province;
        this.latitude = latitude;
        this.longitude = longitude;
    }


    public String getIso() {
        return this.iso;
    }

    public void setIso(String iso) {
        this.iso = iso;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvince() {
        return this.province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public Double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "{" +
            " iso='" + getIso() + "'" +
            ", name='" + getName() + "'" +
            ", province='" + getProvince() + "'" +
            ", latitude='" + getLatitude() + "'" +
            ", longitude='" + getLongitude() + "'" +
            "}";
    }

}