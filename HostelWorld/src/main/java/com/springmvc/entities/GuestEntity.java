package com.springmvc.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by wzh on 20/01/2017.
 */
@Entity
@Table(name = "tb_guest")
public class GuestEntity {
    private String guestId;
    private String name;
    private String IDnumber;

    public GuestEntity() {
    }

    public GuestEntity(String guestId, String name, String IDnumber) {
        this.guestId = guestId;
        this.name = name;
        this.IDnumber = IDnumber;
    }

    @Id
    public String getGuestId() {
        return guestId;
    }

    public void setGuestId(String guestId) {
        this.guestId = guestId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIDnumber() {
        return IDnumber;
    }

    public void setIDnumber(String IDnumber) {
        this.IDnumber = IDnumber;
    }
}
