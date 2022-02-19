package com.example.myapplication.dto;

import java.io.Serializable;

public class WeatherDto implements Serializable {
    private Long id;
    private String main;
    private String description;
    private String icon;

    public WeatherDto() {
    }

    public WeatherDto(Long id, String main, String description, String icon) {
        this.id = id;
        this.main = main;
        this.description = description;
        this.icon = icon;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return description;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}