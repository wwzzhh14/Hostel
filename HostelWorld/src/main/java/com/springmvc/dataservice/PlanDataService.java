package com.springmvc.dataservice;

import com.springmvc.entities.PlanEntity;
import com.springmvc.model.PlanModel;

import java.util.List;

/**
 * Created by wzh on 24/01/2017.
 */
public interface PlanDataService {
    public boolean addPlan(PlanEntity entity);
    public List<PlanModel> getAllPlanModel();
}
