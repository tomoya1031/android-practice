package com.example.myapplication.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class PriceDto implements Serializable {
    private BigDecimal price;
    private BigDecimal point;
    private String qr;

    public PriceDto() {
    }

    public PriceDto(BigDecimal price, BigDecimal point, String qr) {
        this.price = price;
        this.point = point;
        this.qr = qr;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPoint() {
        return point;
    }

    public void setPoint(BigDecimal point) {
        this.point = point;
    }

    public String getQr() {
        return qr;
    }

    public void setQr(String qr) {
        this.qr = qr;
    }
}