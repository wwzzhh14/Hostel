package com.springmvc.vo;

/**
 * Created by wzh on 30/01/2017.
 */
public class PlanVO {
    private String hotelName;
    private String hotelId;
    private String startDate;
    private String endDate;

    public PlanVO() {
    }

    public PlanVO(String hotelName, String hotelId, String startDate, String endDate) {
        this.hotelName = hotelName;
        this.hotelId = hotelId;
        startDate = startDate;
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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
