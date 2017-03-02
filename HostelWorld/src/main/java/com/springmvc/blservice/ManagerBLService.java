package com.springmvc.blservice;

import com.springmvc.vo.*;

import java.util.List;

/**
 * Created by wzh on 30/01/2017.
 */
public interface ManagerBLService  {
    public ResultMessageVO login(String hotelId,String password);
    public List<PlanVO> getAllPlans();
    public List<HotelInfoVO> getHotelApplication();
    public List<HotelInfoVO> getHotelModifiedApplication();
    public ResultMessageVO agreeApplication(String hotelId);
    public ResultMessageVO refuseApplication(String hotelId);
    public ResultMessageVO settleAccounts(String hotelId,double amount);
    public List<HotelFinanceVO> getAllHotelFinanceInfo();

}
