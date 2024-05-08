package org.db.entity;

public class Address {
    private Long empid;
    private String houseName;
    private String area;
    private String city;
    private String state;
    private Long pincode;

    public Address() {
    }

    public Address(String houseName, String area, String city, String state, Long pincode) {
        this.houseName = houseName;
        this.area = area;
        this.city = city;
        this.state = state;
        this.pincode = pincode;
    }

    public Address(Long empid, String houseName, String area, String city, String state, Long pincode) {
        this.empid = empid;
        this.houseName = houseName;
        this.area = area;
        this.city = city;
        this.state = state;
        this.pincode = pincode;
    }

    public Long getEmpid() {
        return empid;
    }

    public void setEmpid(Long empid) {
        this.empid = empid;
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

    @Override
    public String toString() {
        return "Address{" +

                "houseName='" + houseName + '\'' +
                ", area='" + area + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", pincode=" + pincode +
                '}';
    }
}
