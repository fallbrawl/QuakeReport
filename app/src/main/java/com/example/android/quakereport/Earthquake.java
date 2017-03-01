package com.example.android.quakereport;

import java.util.Date;

/**
 * Created by nexus on 28.02.2017.
 */
public class Earthquake {

    Earthquake(double power, String city, Date date){
        this.power = power;
        this.city = city;
        this.date = date;
    }

    public double getPower() {
        return power;
    }

    public String getCity() {
        return city;
    }

    public Date getDate() {
        return date;
    }

    private double power = 0;
    private String city = "";
    private Date date = null;


}
