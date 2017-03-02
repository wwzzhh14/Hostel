package com.springmvc.dao;

import com.springmvc.dataservice.RoomDataService;
import com.springmvc.entities.RoomEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wzh on 29/01/2017.
 */
@Service
public class RoomDao implements RoomDataService {
    @Resource
    BaseDao baseDao;
    @Override
    public List<RoomEntity> getRoomByHotelId(String hotelId) {
        return (List<RoomEntity>) baseDao.findByProperty(RoomEntity.class,"hotelId",hotelId);
    }

    @Override
    public RoomEntity getRoomById(String roomId) {
        return (RoomEntity) baseDao.findById(RoomEntity.class,roomId);
    }

    @Override
    public boolean updateRoom(RoomEntity entity) {
        return baseDao.update(entity);
    }
}
