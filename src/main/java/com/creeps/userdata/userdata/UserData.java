package com.creeps.userdata.userdata;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class UserData {

    private final static String PATH = RemoteCaller.FILE_SERVER_URL+"distributed/v1/merge/";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private String firstName;
    private String lastName;
    private String address1;
    private String address2;
    private String phone;

    private String gender;
    @JsonIgnore
    private Long aadharFileId;
    @JsonIgnore
    private Long licenseFileId;
    @JsonIgnore
    private Long panFileId;

    @Column(columnDefinition = "default false")
    private Boolean isDataVerified;

    private String aadharURL;
    private String licenseURL;
    private String panURL;

    private String username;
    @Column(nullable = false)
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Long getAadharFileId() {
        return aadharFileId;
    }

    public void setAadharFileId(Long aadharFileId) {
        this.aadharFileId = aadharFileId;
    }

    public Long getLicenseFileId() {
        return licenseFileId;
    }

    public void setLicenseFileId(Long licenseFileId) {
        this.licenseFileId = licenseFileId;
    }

    public Long getPanFileId() {
        return panFileId;
    }

    public void setPanFileId(Long panFileId) {
        this.panFileId = panFileId;
    }



    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Boolean getDataVerified() {
        return isDataVerified;
    }

    public void setDataVerified(Boolean dataVerified) {
        isDataVerified = dataVerified;
    }


    public String getAadharURL() {
        return aadharURL;
    }

    public void setAadharURL() {
        this.aadharURL = PATH + this.aadharFileId;
    }

    public String getLicenseURL() {
        return licenseURL;
    }

    public void setLicenseURL() {
        this.licenseURL = PATH + this.licenseFileId;
    }

    public String getPanURL() {
        return panURL;
    }

    public void setPanURL() {
        this.panURL = PATH+this.panFileId;
    }
}
