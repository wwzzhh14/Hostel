package com.springmvc.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by wzh on 21/01/2017.
 */
@Entity
@Table(name = "tb_account")
public class AccountEntity {
    private String accountId;
    private String hotelId;
    private double balance;

    public AccountEntity() {
    }

    public AccountEntity(String accountId, String hotelId, double balance) {
        this.accountId = accountId;
        this.hotelId = hotelId;
        this.balance = balance;
    }

    @Id
    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
