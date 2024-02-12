package com.nada.SpringBootMiniProject.bo.user;

public class Contact {

    private String name;
    private String email;
    private int phone;

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    private double balance = 0.0;

    public String getEmail() {
        return email;
    }

    public int getPhone() {
        return phone;
    }

    public String getName() {
        return name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public void setName(String name) {
        this.name = name;
    }
}


