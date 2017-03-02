package com.springmvc.bl;

import com.springmvc.blservice.MemberBLService;
import com.springmvc.config.*;
import com.springmvc.dataservice.AccountDataService;
import com.springmvc.dataservice.AppointmentDataService;
import com.springmvc.dataservice.MemberDataService;
import com.springmvc.entities.*;
import com.springmvc.util.RandomStringUtil;
import com.springmvc.util.TimeUtil;
import com.springmvc.vo.AppointmentInfoVO;
import com.springmvc.vo.MemberInfoVO;
import com.springmvc.vo.ResultMessageVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wzh on 17/01/2017.
 */
@Service
public class MemberBLImp implements MemberBLService {

    @Resource
    MemberDataService memberDataService;
    @Resource
    AppointmentDataService appointmentDataService;
    @Resource
    AccountDataService accountDataService;

    public ResultMessageVO register(String name, String IDnumber, String account, String password) {
        if (name.equals("")||IDnumber.equals("")||account.equals("")||password.equals(""))
            return new ResultMessageVO(Msg.FAIL,"请检查信息输入是否完整");
        String memberId = RandomStringUtil.getRandomNumberString(7);
        MemberEntity entity = new MemberEntity(memberId,password,name,0,new Date(), MemberState.NOT_ACTIVE,
                0,account,IDnumber);
        if(memberDataService.addMember(entity)){
            return new ResultMessageVO(Msg.SUCCESS,memberId);
        }
        return new ResultMessageVO(Msg.FAIL,"注册失败,请稍后重试!");
    }

    public ResultMessageVO login(String memberId, String password) {
        if (memberId.equals("")||password.equals(""))
            return new ResultMessageVO(Msg.FAIL,"账号或密码为空");
        MemberEntity entity = memberDataService.getMemberById(memberId);
        if (entity==null)
            return new ResultMessageVO(Msg.FAIL,"没有这个账号");
        if(entity.getPassword().equals(password)){
            return new ResultMessageVO(Msg.SUCCESS,"");
        }
        return new ResultMessageVO(Msg.SUCCESS,"密码错误");
    }

    public ResultMessageVO recharge(double amount,String memberId) {
        if(amount<=0){
            return  new ResultMessageVO(Msg.FAIL,"不合法的金额!");
        }
        synchronized(this.getClass()) {
            MemberEntity entity = memberDataService.getMemberById(memberId);
            if (entity == null) {
                return new ResultMessageVO(Msg.FAIL, "请稍后重试!");
            }
            double balance = entity.getBalance() + amount;
            String keys[] = {"balance"};
            Object values[] = {balance};
            boolean success = memberDataService.updateMemberById(memberId, keys, values);
            if (success) {
                if (entity.getState().equals(MemberState.NOT_ACTIVE) && amount > 1000) {
                    synchronized (this) {
                        String keys2[] = {"state"};
                        Object values2[] = {MemberState.ACTIVE};
                        boolean update = memberDataService.updateMemberById(memberId, keys2, values2);
                        if(update)
                            return new ResultMessageVO(Msg.SUCCESS,"会员卡已成功激活");
                        else
                            return  new ResultMessageVO(Msg.SUCCESS, "会员卡激活失败,请联系系统管理员");
                    }
                }
                return new ResultMessageVO(Msg.SUCCESS, "");
            }
        }
        return new ResultMessageVO(Msg.FAIL,"请稍后重试!");
    }

    public ResultMessageVO pay(double amount,String memberId) {
        if(amount<=0){
            return  new ResultMessageVO(Msg.FAIL,"不合法的金额!");
        }
        synchronized(this.getClass()) {
            MemberEntity entity = memberDataService.getMemberById(memberId);
            if (entity == null) {
                return new ResultMessageVO(Msg.FAIL, "请稍后重试!");
            }
            String state = entity.getState();
            if(state.equals(MemberState.NOT_ACTIVE)||state.equals(MemberState.STOPPED)||state.equals(MemberState.OVERDUE)){
                return new ResultMessageVO(Msg.FAIL,"不是有效的会员卡");
            }
            double balance = entity.getBalance();
            if(balance<amount){
                return new ResultMessageVO(Msg.FAIL,"余额不足");
            }
            double points = entity.getPoints();
            String keys[] = {"balance","points"};
            Object values[] = {balance-amount,points+amount};
            if(memberDataService.updateMemberById(memberId,keys,values)){
                AccountEntity accountEntity = accountDataService.getAccountById(AccountConfig.HEADQUARTERS_ACCOUNT);
                String[] keys2 = {"balance"};
                Object[] values2 = {accountEntity.getBalance()+amount};
                if(accountDataService.updateAccountById(AccountConfig.HEADQUARTERS_ACCOUNT,keys2,values2)){
                    return new ResultMessageVO(Msg.SUCCESS,"");
                }
                return new ResultMessageVO(Msg.FAIL,"支付失败,请稍后再试");
            }else {
                return new ResultMessageVO(Msg.FAIL,"支付失败,请稍后再试");
            }
        }
    }

    public ResultMessageVO stop(String password,String memberId) {
        if(checkPassword(password,memberId)){
           String[] keys = {"state"};
           Object[] values = {MemberState.STOPPED};
           if(memberDataService.updateMemberById(memberId,keys,values)){
               return new ResultMessageVO(Msg.SUCCESS,"会员卡已成功冻结");
           }else {
               return new ResultMessageVO(Msg.FAIL,"请稍后再试");
           }
        }else {
            return new ResultMessageVO(Msg.FAIL,"密码错误");
        }
    }

    public ResultMessageVO restart(String password,String memberId) {
        if(checkPassword(password,memberId)){
            String[] keys = {"state"};
            Object[] values = {MemberState.ACTIVE};
            if(memberDataService.updateMemberById(memberId,keys,values)){
                return new ResultMessageVO(Msg.SUCCESS,"会员卡已成功恢复");
            }else {
                return new ResultMessageVO(Msg.FAIL,"请稍后再试");
            }
        }else {
            return new ResultMessageVO(Msg.FAIL,"密码错误");
        }
    }

    public MemberInfoVO getMemberInfo(String memberId) {
        MemberEntity entity = memberDataService.getMemberById(memberId);
        if (entity==null)
            return null;
        MemberInfoVO memberInfoVO = new MemberInfoVO(entity.getMemberId(),entity.getName(),entity.getBalance(), TimeUtil.date2String(entity.getBirth()),entity.getState(),
                entity.getPoints(),entity.getAccount(),entity.getIDnumber());
        return memberInfoVO;
    }

    public ResultMessageVO exchangePoints(String password,String memberId) {
        MemberEntity entity = memberDataService.getMemberById(memberId);
        if(entity==null){
            return new ResultMessageVO(Msg.FAIL,"稍后再试");
        }
        if(password.equals(entity.getPassword())){
            synchronized (this.getClass()){
                double balance = entity.getBalance();
                double points = entity.getPoints();
                String keys[] = {"balance","points"};
                Object values[] = {balance+points/100,0};
                if(memberDataService.updateMemberById(memberId,keys,values)){
                    return new ResultMessageVO(Msg.SUCCESS,"");
                }
                return new ResultMessageVO(Msg.FAIL,"兑换失败");
            }
        }else {
            return new ResultMessageVO(Msg.FAIL,"密码错误");
        }
    }

    @Override
    public ResultMessageVO updateMemberInfo(String memberId,MemberInfoVO memberInfoVO) {
        MemberEntity entity = memberDataService.getMemberById(memberId);
        if(entity!=null){
            MemberEntity newEntity = new MemberEntity(memberId,entity.getPassword(),memberInfoVO.getName(),entity.getBalance(),entity.getBirth(),entity.getState(),entity.getPoints(),memberInfoVO.getAccount(),entity.getIDnumber());
            if(memberDataService.updateMemberEntity(newEntity)){
                return new ResultMessageVO(Msg.SUCCESS,"");
            }else {
                return new ResultMessageVO(Msg.FAIL,"修改失败");
            }
        }
        return new ResultMessageVO(Msg.FAIL,"修改失败");
    }

    @Override
    public ResultMessageVO createAppointment(String memberId, String roomId, String startDate, String endDate, double deposit) {
        String appointmentId = RandomStringUtil.getRandomNumberString(10);
        String type = AppointmentType.MEMBER;
        String state = AppointmentState.NOT_CHECK_IN;
        AppointmentEntity entity = new AppointmentEntity(appointmentId,memberId,roomId,TimeUtil.getDate(startDate),TimeUtil.getDate(endDate),deposit,type,state,new Date());
        if(appointmentDataService.addAppointment(entity)){
            return new ResultMessageVO(Msg.SUCCESS,appointmentId);
        }
        return new ResultMessageVO(Msg.FAIL,"预定失败,请稍后再试");
    }

    @Override
    public ResultMessageVO cancelAppointment(String appointmentId) {
        String keys[] = {"state"};
        Object values[] = {AppointmentState.CANCEL};
        if(appointmentDataService.updateAppointmentById(appointmentId,keys,values)){
            return new ResultMessageVO(Msg.SUCCESS,"");
        }
        return new ResultMessageVO(Msg.FAIL,"取消失败,请稍后再试");
    }

    @Override
    public ResultMessageVO checkIn(String appointmentId) {
        AppointmentEntity entity = appointmentDataService.getAppointmentById(appointmentId);
        if(entity.getState().equals(AppointmentState.NOT_CHECK_IN)){
            boolean add = appointmentDataService.addCheckIn(new CheckInEntity(appointmentId,new Date()));
            String keys[] = {"state"};
            Object values[] = {AppointmentState.CHECK_IN};
            boolean update=appointmentDataService.updateAppointmentById(appointmentId,keys,values);
            if(add&&update){
                return new ResultMessageVO(Msg.SUCCESS,"");
            }
            return new ResultMessageVO(Msg.FAIL,"入住失败,请稍后再试");
        }else {
            return new ResultMessageVO(Msg.FAIL,"订单已失效");
        }

    }

    @Override
    public ResultMessageVO checkOut(String appointmentId, double total) {
        AppointmentEntity entity = appointmentDataService.getAppointmentById(appointmentId);
        if(entity.getState().equals(AppointmentState.CHECK_IN)){
            boolean add = appointmentDataService.addCheckOut(new CheckOutEntity(appointmentId,new Date(),total));
            String keys[] = {"state"};
            Object values[] = {AppointmentState.CHECK_OUT};
            boolean update=appointmentDataService.updateAppointmentById(appointmentId,keys,values);
            if(add&&update){
                return new ResultMessageVO(Msg.SUCCESS,total-entity.getDeposit()+"");
            }
            return new ResultMessageVO(Msg.FAIL,"结算失败,请稍后再试");
        }else {
            return new ResultMessageVO(Msg.FAIL,"订单已失效");
        }
    }


    @Override
    public List<AppointmentInfoVO> getAllAppointment(String memberId) {
        List<AppointmentEntity> appointmentEntities = appointmentDataService.getAppointmentByMemberId(memberId);
        List<AppointmentInfoVO> resultList = new ArrayList<>();
        for (AppointmentEntity entity:appointmentEntities){
            CheckInEntity checkInEntity = appointmentDataService.getCheckInInfo(entity.getAppointmentId());
            CheckOutEntity checkOutEntity = appointmentDataService.getCheckOutInfo(entity.getAppointmentId());
            String checkInDate = null;
            String checkOutDate = null;
            double total = entity.getDeposit();
            if(checkInEntity!=null)
                checkInDate = TimeUtil.date2String(checkInEntity.getDate());
            if(checkOutEntity!=null){
                checkOutDate = TimeUtil.date2String(checkOutEntity.getDate());
                total = checkOutEntity.getTotal();
            }
            resultList.add(new AppointmentInfoVO(entity.getAppointmentId(),TimeUtil.date2String(entity.getStartdate()),TimeUtil.date2String(entity.getEnddate()),entity.getDeposit(),total,checkInDate,checkOutDate));
        }
        return resultList;
    }

    private boolean checkPassword(String password,String memberId){
        if (memberId==null||password.equals("")) return false;
        MemberEntity entity = memberDataService.getMemberById(memberId);
        if (entity==null) return false;
        if(entity.getPassword().equals(password)) return true;
        return false;
    }
}
