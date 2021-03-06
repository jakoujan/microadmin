/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.data.dto;

/**
 *
 * @author Edgar
 */
public class Store {

    private String name;
    private String bussinesName;
    private String taxId;
    private String street;
    private String external;
    private String internal;
    private String colony;
    private String city;
    private String county;
    private int state;
    private int country;
    private String postalCode;
    private String phoneNumber;
    private String email;
    private String webpage;
    private String taxRegime;

    public Store() {
    }

    public Store(String name, String bussinesName, String taxId, String street, String external, String internal, String colony, String city, String county, int state, int country, String postalCode, String phoneNumber, String email, String webpage, String taxRegime) {
        this.name = name;
        this.bussinesName = bussinesName;
        this.taxId = taxId;
        this.street = street;
        this.external = external;
        this.internal = internal;
        this.colony = colony;
        this.city = city;
        this.county = county;
        this.state = state;
        this.country = country;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.webpage = webpage;
        this.taxRegime = taxRegime;
    }

    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBussinesName() {
        return bussinesName;
    }

    public void setBussinesName(String bussinesName) {
        this.bussinesName = bussinesName;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getExternal() {
        return external;
    }

    public void setExternal(String external) {
        this.external = external;
    }

    public String getInternal() {
        return internal;
    }

    public void setInternal(String internal) {
        this.internal = internal;
    }

    public String getColony() {
        return colony;
    }

    public void setColony(String colony) {
        this.colony = colony;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getCountry() {
        return country;
    }

    public void setCountry(int country) {
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebpage() {
        return webpage;
    }

    public void setWebpage(String webpage) {
        this.webpage = webpage;
    }

    public String getTaxRegime() {
        return taxRegime;
    }

    public void setTaxRegime(String taxRegime) {
        this.taxRegime = taxRegime;
    }

    public String getAddress() {
        StringBuilder builder = new StringBuilder();
        builder.append(this.street).append(" ")
                .append(this.external).append(" ").append(this.internal).append("\n")
                .append(this.colony).append(" ").append(this.city).append("\n")
                .append(this.county).append(", ").append(this.state).append("\n")
                .append(this.county).append("\n")
                .append("C.P. ").append(this.postalCode);
        return builder.toString();
    }

}
