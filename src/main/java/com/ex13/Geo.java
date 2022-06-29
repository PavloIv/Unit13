package com.ex13;

public class Geo {
    private float lat;
    private float lng;

    public Geo(float lat, float lng) {
        this.lat = lat;
        this.lng = lng;
    }
    @Override
    public String toString() {
        return "geo:{" +
                ", lat:" + lat +
                ", lng:" + lng +
                '}';
    }
}
