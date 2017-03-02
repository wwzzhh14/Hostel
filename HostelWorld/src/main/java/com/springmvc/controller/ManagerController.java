package com.springmvc.controller;

import com.springmvc.blservice.ManagerBLService;
import com.springmvc.config.Msg;
import com.springmvc.vo.HotelFinanceVO;
import com.springmvc.vo.HotelInfoVO;
import com.springmvc.vo.PlanVO;
import com.springmvc.vo.ResultMessageVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by wzh on 01/02/2017r.
 */
@Controller
public class ManagerController
    @Resource
    ManagerBLService managerBLService;

    @RequestMapping(value = "/manager/login",method = RequestMethod.GET)
    @ResponseBody
    public ResultMessageVO login(HttpSession session,String hotelId, String password){
        ResultMessageVO resultMessageVO= managerBLService.login(hotelId,password);
        if (resultMessageVO.getStatus()== Msg.SUCCESS){
            session.setAttribute("Id",hotelId);
        }
        return resultMessageVO;
    }

    @RequestMapping(value = "/manager/getAllPlans",method = RequestMethod.GET)
    @ResponseBody
    public List<PlanVO> getAllPlans(){
        return managerBLService.getAllPlans();
    }

    @RequestMapping(value = "/manager/getHotelApplication",method = RequestMethod.GET)
    @ResponseBody
    public List<HotelInfoVO> getHotelApplication(){
        return managerBLService.getHotelApplication();
    }

    @RequestMapping(value = "/manager/getHotelModifiedApplication",method = RequestMethod.GET)
    @ResponseBody
    public List<HotelInfoVO> getHotelModifiedApplication(){
        return managerBLService.getHotelModifiedApplication();
    }

    @RequestMapping(value = "/manager/agreeApplication",method = RequestMethod.GET)
    @ResponseBody
    public ResultMessageVO agreeApplication(String hotelId){
        return managerBLService.agreeApplication(hotelId);
    }

    @RequestMapping(value = "/manager/refuseApplication",method = RequestMethod.GET)
    @ResponseBody
    public ResultMessageVO refuseApplication(String hotelId){
        return managerBLService.refuseApplication(hotelId);
    }

    @RequestMapping(value = "/manager/settleAccounts",method = RequestMethod.GET)
    @ResponseBody
    public ResultMessageVO settleAccounts(String hotelId,double amount){
        return settleAccounts(hotelId,amount);
    }

    @RequestMapping(value = "/manager/getAllHotelFinanceInfo",method = RequestMethod.GET)
    @ResponseBody
    public List<HotelFinanceVO> getAllHotelFinanceInfo(){
        return managerBLService.getAllHotelFinanceInfo();
    }
}
