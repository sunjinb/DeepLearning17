package com.example.call_application;

public class RegisterHelper {
    String email, password, name, year, month, day, gender, phoneNumber;

    public RegisterHelper() {
    }

    public RegisterHelper(String email, String password, String name, String year, String month, String day, String gender, String phoneNumber) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.year = year;
        this.month = month;
        this.day = day;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
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

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String gender) {
        this.phoneNumber = phoneNumber;
    }
}
