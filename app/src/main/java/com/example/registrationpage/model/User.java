package com.example.registrationpage.model;

public class User {
    private String name;
    private String surname;
    private String password;
    private String parentName;

    public User(String name, String surname, String password, String parentName) {
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.parentName = parentName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSurname() {
        return surname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getParentName() {
        return parentName;
    }
}
