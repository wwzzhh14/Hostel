package com.springmvc.model;

import java.util.Date;

/**
 * Created by wzh on 26/01/2017.
 */
public class CheckInModel {
    private Date date;
    private String roomId;


    public CheckInModel(Date date, String roomId) {
        this.date = date;
        this.roomId = roomId;
    }

    public CheckInModel() {
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

}
