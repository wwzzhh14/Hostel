package com.springmvc.dao;

import com.springmvc.dataservice.GuestDataService;
import com.springmvc.entities.GuestEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by wzh on 21/01/2017.
 */
@Service
public class GuestDao implements GuestDataService {
    @Resource
    BaseDao baseDao;
    @Override
    public boolean addGuest(GuestEntity entity) {
        return baseDao.save(entity);
    }
}
