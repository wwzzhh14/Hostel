package com.springmvc.dao;

import com.springmvc.dataservice.UserDataService;
import com.springmvc.entities.UserEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wzh on 29/01/2017.
 */
@Service
public class UserDao implements UserDataService {
    @Resource
    BaseDao baseDao;
    @Override
    public boolean addUser(UserEntity entity) {
        return baseDao.save(entity);
    }

    @Override
    public UserEntity getUserById(String username) {
        return (UserEntity) baseDao.findById(UserEntity.class,username);
    }

    @Override
    public UserEntity getUserByHotelId(String hotelId) {
        List<UserEntity> entities = (List<UserEntity>) baseDao.findByProperty(UserEntity.class,"hotelId",hotelId);
        if (entities.size()>0)return entities.get(0);
        return null;
    }
}
