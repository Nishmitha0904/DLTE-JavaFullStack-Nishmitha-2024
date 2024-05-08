package org.employee;

public class Address {
    private Long employee_val;
    private String houseName;
    private String area;
    private String city;
    private String state;
    private Long pincode;

    public String toString() {
        return houseName +
                ", " + area +
                ", " + city +
                ", " + state +
                "-" + pincode ;
    }

    public Address() {
    }

    public Address(Long employee_val, String houseName, String area, String city, String state, Long pincode) {
        this.employee_val = employee_val;
        this.houseName = houseName;
        this.area = area;
        this.city = city;
        this.state = state;
        this.pincode = pincode;
    }

    public Address(String houseName, String area, String city, String state, Long pincode) {
        this.houseName = houseName;
        this.area = area;
        this.city = city;
        this.state = state;
        this.pincode = pincode;
    }

    public Long getEmployee_val() {
        return employee_val;
    }

    public void setEmployee_val(Long employee_val) {
        this.employee_val = employee_val;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
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
