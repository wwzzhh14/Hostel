package com.springmvc.vo;

import java.util.Date;

/**
 * Created by wzh on 17/01/2017.
 */
public class MemberInfoVO {
    private String memberId;
    private String name;
    private double balance;
    private String birth;
    private String state;
    private double points;
    private String account;
    private String IDnumber;

    public MemberInfoVO(String memberId, String name, double balance, String birth, String state, double points, String account, String IDnumber) {
        this.memberId = memberId;
        this.name = name;
        this.balance = balance;
        this.birth = birth;
        this.state = state;
        this.points = points;
        this.account = account;
        this.IDnumber = IDnumber;
    }

    public MemberInfoVO() {
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public double getPoints() {
        return points;
    }

    public void setPoints(double points) {
        this.points = points;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getIDnumber() {
        return IDnumber;
    }

    public void setIDnumber(String IDnumber) {
        this.IDnumber = IDnumber;
    }
}
