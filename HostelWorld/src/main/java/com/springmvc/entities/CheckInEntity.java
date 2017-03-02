package com.springmvc.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by wzh on 20/01/2017.
 */
@Entity
@Table(name = "tb_checkin")
public class CheckInEntity {
    private String appointmentId;
    private Date date;

    public CheckInEntity() {
    }

    public CheckInEntity(String appointmentId, Date date) {
        this.appointmentId = appointmentId;
        this.date = date;
    }

    @Id
    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
