package com.hammad.jfx1;

public class Users {

    String fname;
    String lname;
    String email;
    String phone;
    String license;

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }




    public Users(String fname, String lname, String email, String phone, String license) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.phone = phone;
        this.license = license;
    }





}
