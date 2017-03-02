package com.springmvc.model;

import java.util.Date;

/**
 * Created by wzh on 26/01/2017.
 */
public class CheckOutModel {
    private Date date;
    private String roomId;
    private double total;

    public CheckOutModel(Date date, String roomId, double total) {
        this.date = date;
        this.roomId = roomId;
        this.total = total;
    }

    public CheckOutModel() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

}
