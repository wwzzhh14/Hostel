package com.springmvc.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by wzh on 20/01/2017.
 */

@Entity
@Table(name = "tb_checkout")
public class CheckOutEntity {
    private String appointmentId;
    private Date date;
    private double total;

    public CheckOutEntity() {
    }

    public CheckOutEntity(String appointmentId, Date date, double total) {
        this.appointmentId = appointmentId;
        this.date = date;
        this.total = total;
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

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
