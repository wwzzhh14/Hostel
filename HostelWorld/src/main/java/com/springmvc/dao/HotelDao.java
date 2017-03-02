package com.springmvc.dao;

import com.springmvc.dataservice.HotelDataService;
import com.springmvc.entities.HotelEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wzh on 29/01/2017.
 */
@Service
public class HotelDao implements HotelDataService {
    @Resource
    BaseDao baseDao;
    @Override
    public boolean addHotel(HotelEntity entity) {
        return baseDao.save(entity);
    }

    @Override
    public boolean updateHotel(HotelEntity entity) {
        return baseDao.update(entity);
    }

    @Override
    public boolean updateHotelById(String hotelId, String[] keys, Object[] values) {
        String[] keys2 = {"hotelId"};
        Object[] values2 = {hotelId};
        return baseDao.updateByProperties(HotelEntity.class,keys2,values2,keys,values);
    }

    @Override
    public List<HotelEntity> getHotelByCondition(String[] keys, Object[] values) {
        return (List<HotelEntity>) baseDao.findByPropertys(HotelEntity.class,keys,values);
    }
}
