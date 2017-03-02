package com.springmvc.entities;

import com.sun.org.apache.xml.internal.dtm.DTMAxisIterator;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by wzh on 24/01/2017.
 */
@Entity
@Table(name = "tb_plan")
public class PlanEntity {
    private int id;
    private String hotelId;
    private Date startdate;
    private Date enddate;

    public PlanEntity() {
    }

    public PlanEntity( String hotelId, Date startdate, Date enddate) {
        this.hotelId = hotelId;
        this.startdate = startdate;
        this.enddate = enddate;
    }

    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }
}
