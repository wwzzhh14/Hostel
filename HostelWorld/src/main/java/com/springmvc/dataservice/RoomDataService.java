package com.springmvc.dataservice;

import com.springmvc.entities.RoomEntity;

import java.util.List;

/**
 * Created by wzh on 24/01/2017.
 */
public interface RoomDataService {

    public List<RoomEntity> getRoomByHotelId(String hotelId);
    public RoomEntity getRoomById(String roomId);
    public boolean updateRoom(RoomEntity entity);
}
