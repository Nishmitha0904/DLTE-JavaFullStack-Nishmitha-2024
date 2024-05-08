package org.file;

import java.io.Serializable;

public class Address implements Serializable {
    private String homeAddress;
    private String area;
    private String city;
    private String state;
    private Long pincode;

    public String toString() {
        return homeAddress +
                ", " + area +
                ", " + city +
                ", " + state +
                "-" + pincode ;
    }

    public Address() {
    }

    public Address(String homeAddress, String area, String city, String state, Long pincode) {
        this.homeAddress = homeAddress;
        this.area = area;
        this.city = city;
        this.state = state;
        this.pincode = pincode;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Long getPincode() {
        return pincode;
    }

    public void setPincode(Long pincode) {
        this.pincode = pincode;
    }
}
