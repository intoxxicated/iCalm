package com.example.icalm.DataClass;

public class User {
    String uuid;
    public String name;
    public String email;
    public String phone;
    public String password;
    public String dob;
    public String address;
    public String dp;

    public User() {
    }

    public User(String uuid, String name, String phone, String email, String password, String address, String dob, String dp){
        this.email = email;
        this.name=name;
        this.phone=phone;
        this.password = password;
        this.uuid=uuid;
        this.dob = dob;
        this.address = address;
        this.dp = dp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDp() {
        return dp;
    }

    public void setDp(String dp) {
        this.dp = dp;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
