package com.hz.spring_ioc_xml.entity;

/**
 * @Auther zehua
 * @Date 2020/11/6 5:28
 */
public class Address {
    private String province;
    private String city;

    public Address() {
        System.out.println("address created....");
    }

    public Address(String province, String city) {
        this.province = province;
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Address{" +
                "province='" + province + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
