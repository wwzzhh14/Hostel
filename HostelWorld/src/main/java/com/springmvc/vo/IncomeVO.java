package com.springmvc.vo;

/**
 * Created by wzh on 24/01/2017.
 */
public class IncomeVO {
    private double deposit;
    private double total;

    public IncomeVO(double deposit, double total) {
        this.deposit = deposit;
        this.total = total;
    }

    public IncomeVO() {
    }

    public double getDeposit() {
        return deposit;
    }

    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
