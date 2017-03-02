package com.springmvc.vo;

/**
 * Created by wzh on 24/01/2017.
 */
public class CheckOutVO {
    private String date;
    private String roomId;
    private double total;

    public CheckOutVO(String date, String roomId, double total) {
        this.date = date;
        this.roomId = roomId;
        this.total = total;
    }

    public CheckOutVO() {
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

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
