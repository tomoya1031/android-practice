package com.example.myapplication.models.apis;

public class Sys {
    private long type;
    private long id;
    private double message;
    private String country;
    private long sunrise;
    private long sunset;

    public long getType() { return type; }
    public void setType(long value) { this.type = value; }

    public long getID() { return id; }
    public void setID(long value) { this.id = value; }

    public double getMessage() { return message; }
    public void setMessage(double value) { this.message = value; }

    public String getCountry() { return country; }
    public void setCountry(String value) { this.country = value; }

    public long getSunrise() { return sunrise; }
    public void setSunrise(long value) { this.sunrise = value; }

    public long getSunset() { return sunset; }
    public void setSunset(long value) { this.sunset = value; }
}
