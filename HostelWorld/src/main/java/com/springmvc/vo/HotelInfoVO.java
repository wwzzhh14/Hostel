package com.springmvc.vo;

/**
 * Created by wzh on 24/01/2017.
 */
public class HotelInfoVO {
    private String hotelId;
    private String name;
    private String owner;
    private String address;
    private String note;

    public HotelInfoVO() {
    }

    public HotelInfoVO(String hotelId, String name, String owner, String address, String note) {
        this.hotelId = hotelId;
        this.name = name;
        this.owner = owner;
        this.address = address;
        this.note = note;
    }

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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
