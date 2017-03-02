package com.springmvc.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by wzh on 20/01/2017.
 */
@Entity
@Table(name = "tb_appointment")
public class AppointmentEntity {
    private String appointmentId;
    private String guestId;
    private String roomId;
    private Date startdate;
    private Date enddate;
    private double deposit;
    private String type;
    private String state;
    private Date date;

    public AppointmentEntity() {
    }

    public AppointmentEntity(String appointmentId, String guestId, String roomId, Date startdate, Date enddate, double deposit, String type, String state,Date date) {
        this.appointmentId = appointmentId;
        this.guestId = guestId;
        this.roomId = roomId;
        this.startdate = startdate;
        this.enddate = enddate;
        this.deposit = deposit;
        this.type = type;
        this.state = state;
        this.date = date;
    }

    @Id
    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getGuestId() {
        return guestId;
    }

    public void setGuestId(String guestId) {
        this.guestId = guestId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public double getDeposit() {
        return deposit;
    }

    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
