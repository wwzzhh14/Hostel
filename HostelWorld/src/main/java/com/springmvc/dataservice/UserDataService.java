package com.springmvc.dataservice;

import com.springmvc.entities.UserEntity;

/**
 * Created by wzh on 24/01/2017.
 */
public interface UserDataService {
    public boolean addUser(UserEntity entity);
    public UserEntity getUserById(String username);
    public UserEntity getUserByHotelId(String hotelId);
}
