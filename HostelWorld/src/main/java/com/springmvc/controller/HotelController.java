package com.springmvc.controller;

import com.springmvc.blservice.HotelBLService;
import com.springmvc.config.Msg;
import com.springmvc.vo.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by wzh on 30/01/2017.
 */
@Controller
public class HotelController {
    @Resource
    HotelBLService hotelBLService;

    @RequestMapping(value = "/hotel/register",method = RequestMethod.GET)
    @ResponseBody
    public ResultMessageVO register(String name, String owner, String address, String note, String username, String password){
        return hotelBLService.register(name,owner,address,note,username,password);
    }
    @RequestMapping(value = "/hotel/login",method = RequestMethod.GET)
    @ResponseBody
    public ResultMessageVO login(HttpSession session,String hotelId, String password){
        ResultMessageVO resultMessageVO= hotelBLService.login(hotelId,password);
        if (resultMessageVO.getStatus()== Msg.SUCCESS){
            session.setAttribute("Id",hotelId);
        }
        return resultMessageVO;
    }

    @RequestMapping(value = "/hotel/apply",method = RequestMethod.GET)
    @ResponseBody
    public ResultMessageVO apply(HttpSession session,String password){
        String hotelId = (String) session.getAttribute("Id");
        return hotelBLService.apply(hotelId,password);
    }

    @RequestMapping(value = "/hotel/updateHotelInfo",method = RequestMethod.GET)
    @ResponseBody
    public ResultMessageVO updateHotelInfo(HotelInfoVO hotelInfoVO, String password){
        return hotelBLService.updateHotelInfo(hotelInfoVO,password);
    }

    @RequestMapping(value = "/hotel/getAllRoomsInfo",method = RequestMethod.GET)
    @ResponseBody
    public List<RoomInfoVO> getAllRoomsInfo(HttpSession session){
        String hotelId = (String) session.getAttribute("Id");
        return hotelBLService.getAllRoomsInfo(hotelId);
    }

    @RequestMapping(value = "/hotel/getRoomInfo",method = RequestMethod.GET)
    @ResponseBody
    public RoomInfoVO getRoomInfo(String roomId){
        return hotelBLService.getRoomInfo(roomId);
    }

    @RequestMapping(value = "/hotel/updateRoomInfo",method = RequestMethod.GET)
    @ResponseBody
    public ResultMessageVO updateRoomInfo(RoomInfoVO vo){
        return hotelBLService.updateRoomInfo(vo);
    }

    @RequestMapping(value = "/hotel/getOccupiedDate",method = RequestMethod.GET)
    @ResponseBody
    public List<String> getOccupiedDate(String roomId){
        return hotelBLService.getOccupiedDate(roomId);
    }

    @RequestMapping(value = "/hotel/releasePlan",method = RequestMethod.GET)
    @ResponseBody
    public ResultMessageVO releasePlan(HttpSession session,String startDate,String endDate){
        String hotelId = (String) session.getAttribute("Id");
        return hotelBLService.releasePlan(hotelId,startDate,endDate);
    }

    @RequestMapping(value = "/hotel/getAppointmentByDate",method = RequestMethod.GET)
    @ResponseBody
    public List<AppointmentVO> getAppointmentByDate(HttpSession session, String date){
        String hotelId = (String) session.getAttribute("Id");
        return hotelBLService.getAppointmentByDate(hotelId,date);
    }

    @RequestMapping(value = "/hotel/getCheckInByDate",method = RequestMethod.GET)
    @ResponseBody
    public List<CheckInVO> getCheckInByDate(HttpSession session,String date){
        String hotelId = (String) session.getAttribute("Id");
        return hotelBLService.getCheckInByDate(hotelId,date);
    }

    @RequestMapping(value = "/hotel/getCheckOutByDate",method = RequestMethod.GET)
    @ResponseBody
    public List<CheckOutVO> getCheckOutByDate(HttpSession session,String date){
        String hotelId = (String) session.getAttribute("Id");
        return hotelBLService.getCheckOutByDate(hotelId,date);
    }

    @RequestMapping(value = "/hotel/getTotalIncomeByDate",method = RequestMethod.GET)
    @ResponseBody
    public IncomeVO getTotalIncomeByDate(HttpSession session ,String date){
        String hotelId = (String) session.getAttribute("Id");
        return hotelBLService.getTotalIncomeByDate(hotelId,date);
    }
}
