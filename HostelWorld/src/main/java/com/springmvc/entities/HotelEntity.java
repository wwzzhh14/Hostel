package com.springmvc.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by wzh on 24/01/2017.
 */
@Entity
@Table(name = "tb_hotel")
public class HotelEntity {
    private String hotelId;
    private String name;
    private String owner;
    private String address;
    private String state;
    private String note;

    public HotelEntity(String hotelId, String name, String owner, String address, String state, String note) {
        this.hotelId = hotelId;
        this.name = name;
        this.owner = owner;
        this.address = address;
        this.state = state;
        this.note = note;
    }

    public HotelEntity() {
    }
    @Id
    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
