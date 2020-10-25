package com.kapul.hobbyclub;

public class Member {
    private String name;
    private String dept;
    private String roll;
    private String phone;
    private String email;

    public Member() {

    }

    public Member(String name, String dept, String roll, String phone, String email) {
        this.name = name;
        this.dept = dept;
        this.roll = roll;
        this.phone = phone;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}