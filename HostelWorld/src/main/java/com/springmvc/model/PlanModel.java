package com.springmvc.model;

import java.util.Date;

/**
 * Created by wzh on 30/01/2017.
 */
public class PlanModel {
    private String hotelName;
    private String hotelId;
    private Date startDate;
    private Date endDate;

    public PlanModel() {
    }

    public PlanModel(String hotelName, String hotelId, Date startDate, Date endDate) {
        this.hotelName = hotelName;
        this.hotelId = hotelId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
