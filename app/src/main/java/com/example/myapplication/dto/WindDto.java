package com.example.myapplication.dto;

import java.io.Serializable;

public class WindDto implements Serializable {
    private Float speed;
    private String deg;
    private Float gust;

    public WindDto() {
    }

    public WindDto(Float speed, String deg, Float gust) {
        this.speed = speed;
        this.deg = deg;
        this.gust = gust;
    }

    public Float getSpeed() {
        return speed;
    }

    public void setSpeed(Float id) {
        this.speed = speed;
    }

    public String getDeg() {
        return deg;
    }

    public void getDeg(String deg) {
        this.deg = deg;
    }

    public Float getGust() {
        return gust;
    }

    public void setGust(Float gust) {
        this.gust = gust;
    }
}