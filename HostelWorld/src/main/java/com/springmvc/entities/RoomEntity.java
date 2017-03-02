package com.springmvc.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by wzh on 24/01/2017.
 */
@Entity
@Table(name = "tb_room")
public class RoomEntity {
    private String roomId;
    private String type;
    private double price;
    private String state;
    private String name;
    private String hotelId;

    public RoomEntity() {
    }

    public RoomEntity(String roomId, String type, double price, String state, String name, String hotelId) {
        this.roomId = roomId;
        this.type = type;
        this.price = price;
        this.state = state;
        this.name = name;
        this.hotelId = hotelId;
    }

    @Id
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

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }
}
