package com.springmvc.dataservice;

import com.springmvc.entities.HotelEntity;

import java.util.List;

/**
 * Created by wzh on 24/01/2017.
 */
public interface HotelDataService {
    public boolean addHotel(HotelEntity entity);
    public boolean updateHotel(HotelEntity entity);
    public boolean updateHotelById(String hotelId, String[] keys, Object[] values);
    public List<HotelEntity> getHotelByCondition(String[] keys, Object[] values);
}
