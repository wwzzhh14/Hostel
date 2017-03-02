package com.springmvc.vo;

/**
 * Created by wzh on 24/01/2017.
 */
public class AppointmentVO {
    private String appointmentId;
    private String startDate;
    private String endDate;
    private double deposit;
    private String roomId;

    public AppointmentVO() {
    }

    public AppointmentVO(String appointmentId, String startDate, String endDate, double deposit, String roomId) {
        this.appointmentId = appointmentId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.deposit = deposit;
        this.roomId = roomId;
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

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
}
