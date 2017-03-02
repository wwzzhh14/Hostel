package com.springmvc.vo;

/**
 * Created by wzh on 20/01/2017.
 */
public class AppointmentInfoVO {
    private String appointmentId;
    private String startDate;
    private String endDate;
    private double deposit;
    private double total;
    private String checkInDate;
    private String checkOutDate;

    public AppointmentInfoVO() {
    }

    public AppointmentInfoVO(String appointmentId, String startDate, String endDate, double deposit, double total, String checkInDate, String checkOutDate) {
        this.appointmentId = appointmentId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.deposit = deposit;
        this.total = total;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
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

    public String getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
    }
}
