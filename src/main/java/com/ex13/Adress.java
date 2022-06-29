package com.ex13;

public class Adress {
    private String street;
    private String suite;
    private String city;
    private Integer zipcode;
    private Geo geo;

    public Adress(String street, String suite, String city, Integer zipcode, Geo geo) {
        this.street = street;
        this.suite = suite;
        this.city = city;
        this.zipcode = zipcode;
        this.geo = geo;
    }

    @Override
    public String toString() {
        return "adress:{" +
                "street:'" + street + '\'' +
                ", suite:'" + suite + '\'' +
                ", city:'" + city + '\'' +
                ", zipcode:'" + zipcode + '\'' +
                ", geo:" + geo +
                '}';
    }
}
