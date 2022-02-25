package com.example.myapplication.models.apis;

import com.example.myapplication.dto.Weather;

import java.util.List;

public class WeatherNews {
    private Coord coord;
    private List<Weather> weather;
    private String base;
    private Main main;
    private Wind wind;
    private Clouds clouds;
    private long dt;
    private Sys sys;
    private long timezone;
    private long id;
    private String name;
    private long cod;

    public Coord getCoord() { return coord; }
    public void setCoord(Coord value) { this.coord = value; }

    public List<Weather> getWeather() { return weather; }
    public void setWeather(List<Weather> value) { this.weather = value; }

    public String getBase() { return base; }
    public void setBase(String value) { this.base = value; }

    public Main getMain() { return main; }
    public void setMain(Main value) { this.main = value; }

    public Wind getWind() { return wind; }
    public void setWind(Wind value) { this.wind = value; }

    public Clouds getClouds() { return clouds; }
    public void setClouds(Clouds value) { this.clouds = value; }

    public long getDt() { return dt; }
    public void setDt(long value) { this.dt = value; }

    public Sys getSys() { return sys; }
    public void setSys(Sys value) { this.sys = value; }

    public long getTimezone() { return timezone; }
    public void setTimezone(long value) { this.timezone = value; }

    public long getID() { return id; }
    public void setID(long value) { this.id = value; }

    public String getName() { return name; }
    public void setName(String value) { this.name = value; }

    public long getCod() { return cod; }
    public void setCod(long value) { this.cod = value; }
}
