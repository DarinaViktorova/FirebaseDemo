package com.example.firebasedemo;

public class Information {
    private String email;
    private String name;

    public Information() {
    }

    public Information(String email, String name){
        this.email = email;
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }
}
