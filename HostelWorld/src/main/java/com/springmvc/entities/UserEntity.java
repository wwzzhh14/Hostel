package com.springmvc.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by wzh on 24/01/2017.
 */
@Entity
@Table(name = "tb_user")
public class UserEntity {
    private String username;
    private String password;
    private String hotelId;

    public UserEntity() {
    }

    public UserEntity(String username, String password, String hotelId) {
        this.username = username;
        this.password = password;
        this.hotelId = hotelId;
    }

    @Id
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }
}
