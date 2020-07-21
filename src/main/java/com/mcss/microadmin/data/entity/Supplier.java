/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.data.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author edgar
 */
@Entity
@Table(name = "SUPPLIER", schema = "PUBLIC", catalog = "DB")
public class Supplier implements Serializable {

    private Integer id;
    private String business_name;
    private String city;
    private String contact;
    private Country country;
    private String external_number;
    private String county;
    private String internal_number;
    private String postal_code;
    private String settlement;
    private State state;
    private String street;
    private String tax_id;
    private String telephone;
    private String email;
    private Boolean active;

    public Supplier() {
    }

    public Supplier(String business_name, String city, String contact, Country country, String external_number, String county, String internal_number, String postal_code, String settlement, State state, String street, String tax_id, String telephone, String email, Boolean active) {
        this.business_name = business_name;
        this.city = city;
        this.contact = contact;
        this.country = country;
        this.external_number = external_number;
        this.county = county;
        this.internal_number = internal_number;
        this.postal_code = postal_code;
        this.settlement = settlement;
        this.state = state;
        this.street = street;
        this.tax_id = tax_id;
        this.telephone = telephone;
        this.email = email;
        this.active = active;
    }

    

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "BUSINESS_NAME", length = 60)
    public String getBusiness_name() {
        return business_name;
    }

    public void setBusiness_name(String business_name) {
        this.business_name = business_name;
    }

    @Column(name = "CITY", length = 60)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column(name = "CONTACT", length = 60)
    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @JoinColumn(name = "COUNTRY", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Column(name = "EXTERNAL_NUMBER", length = 3)
    public String getExternal_number() {
        return external_number;
    }

    public void setExternal_number(String external_number) {
        this.external_number = external_number;
    }

    @Column(name = "COUNTY", length = 60)
    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    @Column(name = "INTERNAL_NUMBER", length = 3)
    public String getInternal_number() {
        return internal_number;
    }

    public void setInternal_number(String internal_number) {
        this.internal_number = internal_number;
    }

    @Column(name = "POSTAL_CODE", length = 6)
    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    @Column(name = "SETTLEMENT", length = 60)
    public String getSettlement() {
        return settlement;
    }

    public void setSettlement(String settlement) {
        this.settlement = settlement;
    }

    @JoinColumn(name = "STATE", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Column(name = "STREET", length = 60)
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Column(name = "TAX_ID", length = 15)
    public String getTax_id() {
        return tax_id;
    }

    public void setTax_id(String tax_id) {
        this.tax_id = tax_id;
    }

    @Column(name = "TELEPHONE", length = 15)
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Column(name = "EMAIL", length = 60)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "ACTIVE", length = 1)
    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

}
