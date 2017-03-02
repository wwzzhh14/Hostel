package com.springmvc.controller;

import com.springmvc.blservice.GuestBLService;
import com.springmvc.vo.ResultMessageVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by wzh on 22/01/2017.
 */
@Controller
public class GuestController {
    @Resource
    GuestBLService guestBLService;

    @RequestMapping(value = "/guest/createAppointment",method = RequestMethod.GET)
    @ResponseBody
    public ResultMessageVO createAppointment(String name,String IDnumber,String roomId, String startDate, String endDate,double deposit){
        return guestBLService.createAppointment(name,IDnumber,roomId,startDate,endDate,deposit);
    }
}
