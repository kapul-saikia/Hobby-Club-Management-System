package com.kapul.hobbyclub;


public class User {
    public String name;
    public String dept;
    public String roll;
    public String phone;
    public String email;
    public String club_name;
    public String type;

    public User() {

    }

    public User(String name, String dept, String roll, String phone, String email, String club_name, String type) {
        this.name = name;
        this.dept = dept;
        this.roll = roll;
        this.phone = phone;
        this.email = email;
        this.club_name = club_name;
        this.type = type;
    }
}
