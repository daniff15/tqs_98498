package com.example.demo.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "by_params")
public class ByParams implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private Date datecreation;

    private String date;
    private String last_updated;
    private int confirmed;
    private int confirmed_diff;
    private int deaths;
    private int deaths_diff;
    private int recovered;
    private int recovered_diff;
    private int active;
    private int active_diff;
    private Double fatality_rate;
    private String country;
    private String province;

    public ByParams() {
    }

    public ByParams(String date, String last_updated, int confirmed, int confirmed_diff,
            int deaths, int deaths_diff, int recovered, int recovered_diff, int active, int active_diff,
            Double fatality_rate, String country, String province) {
        this.date = date;
        this.last_updated = last_updated;
        this.confirmed = confirmed;
        this.confirmed_diff = confirmed_diff;
        this.deaths = deaths;
        this.deaths_diff = deaths_diff;
        this.recovered = recovered;
        this.recovered_diff = recovered_diff;
        this.active = active;
        this.active_diff = active_diff;
        this.fatality_rate = fatality_rate;
        this.country = country;
        this.province = province;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLast_updated() {
        return this.last_updated;
    }

    public void setLast_updated(String last_updated) {
        this.last_updated = last_updated;
    }

    public int getConfirmed() {
        return this.confirmed;
    }

    public void setConfirmed(int confirmed) {
        this.confirmed = confirmed;
    }

    public int getConfirmed_diff() {
        return this.confirmed_diff;
    }

    public void setConfirmed_diff(int confirmed_diff) {
        this.confirmed_diff = confirmed_diff;
    }

    public int getDeaths() {
        return this.deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getDeaths_diff() {
        return this.deaths_diff;
    }

    public void setDeaths_diff(int deaths_diff) {
        this.deaths_diff = deaths_diff;
    }

    public int getRecovered() {
        return this.recovered;
    }

    public void setRecovered(int recovered) {
        this.recovered = recovered;
    }

    public int getRecovered_diff() {
        return this.recovered_diff;
    }

    public void setRecovered_diff(int recovered_diff) {
        this.recovered_diff = recovered_diff;
    }

    public int getActive() {
        return this.active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public int getActive_diff() {
        return this.active_diff;
    }

    public void setActive_diff(int active_diff) {
        this.active_diff = active_diff;
    }

    public Double getFatality_rate() {
        return this.fatality_rate;
    }

    public void setFatality_rate(Double fatality_rate) {
        this.fatality_rate = fatality_rate;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDatecreation() {
        return this.datecreation;
    }

    public void setDatecreation(Date datecreation) {
        this.datecreation = datecreation;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return this.province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", datecreation='" + getDatecreation() + "'" +
                ", date='" + getDate() + "'" +
                ", last_updated='" + getLast_updated() + "'" +
                ", confirmed='" + getConfirmed() + "'" +
                ", confirmed_diff='" + getConfirmed_diff() + "'" +
                ", deaths='" + getDeaths() + "'" +
                ", deaths_diff='" + getDeaths_diff() + "'" +
                ", recovered='" + getRecovered() + "'" +
                ", recovered_diff='" + getRecovered_diff() + "'" +
                ", active='" + getActive() + "'" +
                ", active_diff='" + getActive_diff() + "'" +
                ", fatality_rate='" + getFatality_rate() + "'" +
                ", country='" + getCountry() + "'" +
                ", province='" + getProvince() + "'" +
                "}";
    }

}