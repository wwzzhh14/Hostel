package com.springmvc.blservice;

import com.springmvc.vo.AppointmentInfoVO;
import com.springmvc.vo.MemberInfoVO;
import com.springmvc.vo.ResultMessageVO;

import java.util.List;

/**
 * Created by wzh on 17/01/2017.
 */
public interface MemberBLService {
    //注册
    public ResultMessageVO register(String name, String IDnumber, String account, String password);
    //登录
    public ResultMessageVO login(String memberId, String password);
    //充值
    public ResultMessageVO recharge(double amount, String memberId);
    //付款
    public ResultMessageVO pay(double amount, String memberId);
    //停止会员资格
    public ResultMessageVO stop(String password, String memberId);
    //恢复会员资格
    public ResultMessageVO restart(String password, String memberId);
    //获取会员信息
    public MemberInfoVO getMemberInfo(String memberId);
    //兑换积分
    public ResultMessageVO exchangePoints(String password, String memberId);

    public ResultMessageVO updateMemberInfo(String memberId,MemberInfoVO memberInfoVO);

    public ResultMessageVO createAppointment(String memberId,String roomId,String startDate,String endDate,double deposit);

    public ResultMessageVO cancelAppointment(String appointmentId);

    public ResultMessageVO checkIn(String appointmentId);

    public ResultMessageVO checkOut(String appointmentId,double total);

    public List<AppointmentInfoVO> getAllAppointment(String memberId);
}
