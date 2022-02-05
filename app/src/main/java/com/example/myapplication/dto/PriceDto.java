package com.example.myapplication.dto;

import java.io.Serializable;

public class PriceDto implements Serializable {
    private String price;
    private String point;
    private String qr;

    public PriceDto() {
    }

    public PriceDto(String price, String point, String qr) {
        this.price = price;
        this.point = point;
        this.qr = qr;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getQr() {
        return qr;
    }

    public void setQr(String qr) {
        this.qr = qr;
    }
}