package com.springmvc.vo;

/**
 * Created by wzh on 24/01/2017.
 */
public class RoomInfoVO {
    private String roomId;
    private String type;
    private double price;
    private String state;
    private String name;

    public RoomInfoVO() {
    }

    public RoomInfoVO(String roomId, String type, double price, String state, String name) {
        this.roomId = roomId;
        this.type = type;
        this.price = price;
        this.state = state;
        this.name = name;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
