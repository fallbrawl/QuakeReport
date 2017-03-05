package com.example.android.quakereport;

import java.util.Date;

/**
 * Created by nexus on 28.02.2017.
 */
public class Earthquake {

    Earthquake(double power, String city, long date, String siteName) {
        this.power = power;
        this.city = city;
        this.date = date;
        this.siteName = siteName;
    }

    public double getPower() {
        return power;
    }

    public String getCity() {
        return city;
    }

    public long getDate() {
        return date;
    }

    public String getSiteName() {
        return siteName;
    }

    private double power = 0;
    private String city = "";
    private long date = -1;
    private String siteName = "";

}
