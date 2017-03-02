package com.springmvc.model;

/**
 * Created by wzh on 30/01/2017.
 */
public class AccountModel {
    private String hotelName ;
    private double balance;

    public AccountModel() {
    }

    public AccountModel(String hotelName, double balance) {
        this.hotelName = hotelName;
        this.balance = balance;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
