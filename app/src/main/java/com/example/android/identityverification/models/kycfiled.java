package com.example.android.identityverification.models;

/**
 * Created by SUBRAT KUMAR SAHOO on 16-04-2018.
 */

public class kycfiled {

    public String aadhaarNo,Name,Gender,Pin;

    public kycfiled(String aadhaarNo, String name, String gender, String pin) {
        this.aadhaarNo = aadhaarNo;
        Name = name;
        Gender = gender;
        Pin = pin;
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

    public String getPin() {
        return Pin;
    }

    public void setPin(String pin) {
        Pin = pin;
    }
}
