package com.springmvc.dao;

import com.springmvc.dataservice.PlanDataService;
import com.springmvc.entities.PlanEntity;
import com.springmvc.model.PlanModel;
import org.omg.CORBA.Object;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wzh on 29/01/2017.
 */
@Service
public class PlanDao implements PlanDataService {
    @Resource
    BaseDao baseDao;
    @Override
    public boolean addPlan(PlanEntity entity) {
        return baseDao.save(entity);
    }

    @Override
    public List<PlanModel> getAllPlanModel() {
        String sql = "select new com.springmvc.model.PlanModel(h.name,h.hotelId,p.startdate,p.enddate) from PlanEntity p,HotelEntity h where p.hotelId=h.hotelId";
        Object[] objects = {};
        return (List<PlanModel>) baseDao.execQuery(sql,objects);
    }
}
