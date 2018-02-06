package com.poe.trajetfacile.form;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class OfferARideForm {

    private Long userId;
    private String fromCity;
    private String toCity;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date fullDate;

    private short startHours;
    private short startMinutes;
    private Double cost;
    private short seats;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFromCity() {
        return fromCity;
    }

    public void setFromCity(String fromCity) {
        this.fromCity = fromCity;
    }

    public String getToCity() {
        return toCity;
    }

    public void setToCity(String toCity) {
        this.toCity = toCity;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public short getStartHours() {
        return startHours;
    }

    public void setStartHours(short startHours) {
        this.startHours = startHours;
    }

    public short getStartMinutes() {
        return startMinutes;
    }

    public void setStartMinutes(short startMinutes) {
        this.startMinutes = startMinutes;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public short getSeats() {
        return seats;
    }

    public void setSeats(short seats) {
        this.seats = seats;
    }

    public Date getFullDate() {
        return fullDate;
    }

    public void setFullDate(Date fullDate) {
        this.fullDate = fullDate;
    }
}
