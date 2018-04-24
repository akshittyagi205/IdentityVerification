package com.example.android.identityverification.models;

/**
 * Created by SUBRAT KUMAR SAHOO on 16-04-2018.
 */

public class KYCModel {

    public String aadhaarNo,Name,Gender,yob,city;

    public KYCModel(String aadhaarNo, String name, String gender, String yob,String city) {
        this.aadhaarNo = aadhaarNo;
        Name = name;
        Gender = gender;
        this.yob = yob;
        this.city = city;
    }

    public String getYob() {
        return yob;
    }

    public void setYob(String yob) {
        this.yob = yob;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAadhaarNo() {
        return aadhaarNo;
    }

    public void setAadhaarNo(String aadhaarNo) {
        this.aadhaarNo = aadhaarNo;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }
}
