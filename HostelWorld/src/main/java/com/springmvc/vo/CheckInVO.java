package com.springmvc.vo;

/**
 * Created by wzh on 24/01/2017.
 */
public class CheckInVO {

    private String date;
    private String roomId;


    public CheckInVO(String date, String roomId) {
        this.date = date;
        this.roomId = roomId;
    }

    public CheckInVO() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
}
