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
    private String lastUpdated;
    private int confirmed;
    private int confirmedDiff;
    private int deaths;
    private int deathsDiff;
    private int recovered;
    private int recoveredDiff;
    private int active;
    private int activeDiff;
    private Double fatalityRate;
    private String country;

    public ByParams() {
    }

    public ByParams(String date, String lastUpdated, int confirmed, int confirmedDiff,
            int deaths, int deathsDiff, int recovered, int recoveredDiff, int active, int activeDiff,
            Double fatalityRate, String country) {
        this.date = date;
        this.lastUpdated = lastUpdated;
        this.confirmed = confirmed;
        this.confirmedDiff = confirmedDiff;
        this.deaths = deaths;
        this.deathsDiff = deathsDiff;
        this.recovered = recovered;
        this.recoveredDiff = recoveredDiff;
        this.active = active;
        this.activeDiff = activeDiff;
        this.fatalityRate = fatalityRate;
        this.country = country;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLastUpdated() {
        return this.lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public int getConfirmed() {
        return this.confirmed;
    }

    public void setConfirmed(int confirmed) {
        this.confirmed = confirmed;
    }

    public int getConfirmedDiff() {
        return this.confirmedDiff;
    }

    public void setConfirmedDiff(int confirmedDiff) {
        this.confirmedDiff = confirmedDiff;
    }

    public int getDeaths() {
        return this.deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getDeathsDiff() {
        return this.deathsDiff;
    }

    public void setDeathsDiff(int deathsDiff) {
        this.deathsDiff = deathsDiff;
    }

    public int getRecovered() {
        return this.recovered;
    }

    public void setRecovered(int recovered) {
        this.recovered = recovered;
    }

    public int getRecoveredDiff() {
        return this.recoveredDiff;
    }

    public void setRecoveredDiff(int recoveredDiff) {
        this.recoveredDiff = recoveredDiff;
    }

    public int getActive() {
        return this.active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public int getActiveDiff() {
        return this.activeDiff;
    }

    public void setActiveDiff(int activeDiff) {
        this.activeDiff = activeDiff;
    }

    public Double getFatalityRate() {
        return this.fatalityRate;
    }

    public void setFatalityRate(Double fatalityRate) {
        this.fatalityRate = fatalityRate;
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

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", datecreation='" + getDatecreation() + "'" +
                ", date='" + getDate() + "'" +
                ", lastUpdated='" + getLastUpdated() + "'" +
                ", confirmed='" + getConfirmed() + "'" +
                ", confirmedDiff='" + getConfirmedDiff() + "'" +
                ", deaths='" + getDeaths() + "'" +
                ", deathsDiff='" + getDeathsDiff() + "'" +
                ", recovered='" + getRecovered() + "'" +
                ", recoveredDiff='" + getRecoveredDiff() + "'" +
                ", active='" + getActive() + "'" +
                ", activeDiff='" + getActiveDiff() + "'" +
                ", fatalityRate='" + getFatalityRate() + "'" +
                ", country='" + getCountry() + "'" +
                "}";
    }

}