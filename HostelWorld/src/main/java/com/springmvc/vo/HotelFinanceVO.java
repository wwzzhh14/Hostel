package com.springmvc.vo;

/**
 * Created by wzh on 30/01/2017.
 */
public class HotelFinanceVO {

    private String hotelName ;
    private double balance;

    public HotelFinanceVO() {
    }

    public HotelFinanceVO(String hotelName, double balance) {
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
