package com.springmvc.controller;

import com.springmvc.blservice.MemberBLService;
import com.springmvc.config.Msg;
import com.springmvc.vo.AppointmentInfoVO;
import com.springmvc.vo.MemberInfoVO;
import com.springmvc.vo.ResultMessageVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by wzh on 17/01/2017.
 */

@Controller
public class MemberController {
    @Resource
    MemberBLService memberBLService;

    @RequestMapping(value = "/member/register",method = RequestMethod.GET)
    @ResponseBody
    public ResultMessageVO register(String name,String IDnumber,String account,String password){
        return memberBLService.register(name,IDnumber,account,password);
    }

    @RequestMapping(value = "/member/login",method = RequestMethod.GET)
    @ResponseBody
    public ResultMessageVO login(HttpSession session,String memberId, String password) {
        ResultMessageVO resultMessageVO = memberBLService.login(memberId,password);
        if (resultMessageVO.getStatus() == Msg.SUCCESS){
            session.setAttribute("Id",memberId);
            return resultMessageVO;
        }
        return resultMessageVO;
    }

    @RequestMapping(value = "/member/recharge",method = RequestMethod.GET)
    @ResponseBody
    public ResultMessageVO recharge(HttpSession session,double amount) {
        Object memberId = session.getAttribute("memberId");
        return memberBLService.recharge(amount, (String) memberId);
    }

    @RequestMapping(value = "/member/pay",method = RequestMethod.GET)
    @ResponseBody
    public ResultMessageVO pay(HttpSession session,double amount) {
        Object memberId = session.getAttribute("memberId");
        return memberBLService.pay(amount, (String) memberId);
    }

    @RequestMapping(value = "/member/stop",method = RequestMethod.GET)
    @ResponseBody
    public ResultMessageVO stop(HttpSession session,String password) {
        Object memberId = session.getAttribute("memberId");
        return memberBLService.stop(password, (String) memberId);
    }

    @RequestMapping(value = "/member/restart",method = RequestMethod.GET)
    @ResponseBody
    public ResultMessageVO restart(HttpSession session,String password) {
        Object memberId = session.getAttribute("memberId");
        return memberBLService.restart(password, (String) memberId);
    }

    @RequestMapping(value = "/member/getMemberInfo",method = RequestMethod.GET)
    @ResponseBody
    public MemberInfoVO getMemberInfo(HttpSession session) {
        Object memberId = session.getAttribute("memberId");
        return memberBLService.getMemberInfo((String) memberId);
    }

    @RequestMapping(value = "/member/exchangePoints",method = RequestMethod.GET)
    @ResponseBody
    public ResultMessageVO exchangePoints(HttpSession session,String password) {
        Object memberId = session.getAttribute("memberId");
        return memberBLService.exchangePoints(password, (String) memberId);
    }

    @RequestMapping(value = "/member/updateMemberInfo",method = RequestMethod.GET)
    @ResponseBody
    public ResultMessageVO updateMemberInfo(HttpSession session, @ModelAttribute MemberInfoVO memberInfoVO){
        Object memberId = session.getAttribute("memberId");
        return memberBLService.updateMemberInfo((String) memberId,memberInfoVO);
    }

    @RequestMapping(value = "/member/createAppointment",method = RequestMethod.GET)
    @ResponseBody
    public ResultMessageVO createAppointment(HttpSession session,String roomId,String startDate,String endDate,double deposit){
        Object memberId = session.getAttribute("memberId");
        return memberBLService.createAppointment((String) memberId,roomId,startDate,endDate,deposit);
    }

    @RequestMapping(value = "/member/cancelAppointment",method = RequestMethod.GET)
    @ResponseBody
    public ResultMessageVO cancelAppointment(String appointmentId){
        return memberBLService.cancelAppointment(appointmentId);
    }

    @RequestMapping(value = "/member/checkIn",method = RequestMethod.GET)
    @ResponseBody
    public ResultMessageVO checkIn(String appointmentId){
        return memberBLService.checkIn(appointmentId);
    }

    @RequestMapping(value = "/member/checkOut",method = RequestMethod.GET)
    @ResponseBody
    public ResultMessageVO checkOut(String appointmentId,double total){
        return memberBLService.checkOut(appointmentId,total);
    }

    @RequestMapping(value = "/member/getAllAppointment",method = RequestMethod.GET)
    @ResponseBody
    public List<AppointmentInfoVO> getAllAppointment(HttpSession session){
        Object memberId = session.getAttribute("memberId");
        return memberBLService.getAllAppointment((String) memberId);
    }

}
