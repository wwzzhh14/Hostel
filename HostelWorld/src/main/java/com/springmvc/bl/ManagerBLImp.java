package com.springmvc.bl;

import com.springmvc.blservice.ManagerBLService;
import com.springmvc.config.AccountConfig;
import com.springmvc.config.HotelState;
import com.springmvc.config.Msg;
import com.springmvc.config.UserConfig;
import com.springmvc.dataservice.*;
import com.springmvc.entities.AccountEntity;
import com.springmvc.entities.HotelEntity;
import com.springmvc.entities.UserEntity;
import com.springmvc.model.AccountModel;
import com.springmvc.model.PlanModel;
import com.springmvc.util.TimeUtil;
import com.springmvc.vo.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wzh on 31/01/2017.
 */
public class ManagerBLImp implements ManagerBLService {

    @Resource
    PlanDataService planDataService;
    @Resource
    HotelDataService hotelDataService;
    @Resource
    AccountDataService accountDataService;
    @Resource
    AppointmentDataService appointmentDataService;
    @Resource
    UserDataService userDataService;

    @Override
    public ResultMessageVO login(String hotelId, String password) {
        if(!hotelId.equals(UserConfig.HEADQUARTERS_ACCOUNT)){
            return new ResultMessageVO(Msg.FAIL,"您没有这个权限");
        }
        if(checkPassword(password,hotelId)){
            return new ResultMessageVO(Msg.SUCCESS,"");
        }
        return new ResultMessageVO(Msg.FAIL,"用户名或密码错误");
    }

    private boolean checkPassword(String password,String hotelId){
        if (hotelId==null||password.equals("")) return false;
        UserEntity entity = userDataService.getUserByHotelId(hotelId);
        if (entity==null) return false;
        if(entity.getPassword().equals(password)) return true;
        return false;
    }

    @Override
    public List<PlanVO> getAllPlans() {
        List<PlanModel> models = planDataService.getAllPlanModel();
        List<PlanVO> vos = new ArrayList<>();
        for (PlanModel model:models){
            vos.add(new PlanVO(model.getHotelName(),model.getHotelId(), TimeUtil.date2String(model.getStartDate()),TimeUtil.date2String(model.getEndDate())));
        }
        return vos;
    }

    @Override
    public List<HotelInfoVO> getHotelApplication() {
        String keys[] = {"state"};
        Object values[] = {HotelState.APPLY};
        List<HotelEntity> entities = hotelDataService.getHotelByCondition(keys,values);
        List<HotelInfoVO> vos = new ArrayList<>();
        for (HotelEntity entity:entities){
            vos.add(new HotelInfoVO(entity.getHotelId(),entity.getName(),entity.getOwner(),entity.getAddress(),entity.getNote()));
        }
        return vos;
    }

    @Override
    public List<HotelInfoVO> getHotelModifiedApplication() {
        String keys[] = {"state"};
        Object values[] = {HotelState.CHANGED};
        List<HotelEntity> entities = hotelDataService.getHotelByCondition(keys,values);
        List<HotelInfoVO> vos = new ArrayList<>();
        for (HotelEntity entity:entities){
            vos.add(new HotelInfoVO(entity.getHotelId(),entity.getName(),entity.getOwner(),entity.getAddress(),entity.getNote()));
        }
        return vos;
    }

    @Override
    public ResultMessageVO agreeApplication(String hotelId) {
        String keys[] = {"state"};
        Object values[] = {HotelState.ADOPTED};
        if(hotelDataService.updateHotelById(hotelId,keys,values)){
            return new ResultMessageVO(Msg.SUCCESS,"");
        }
        return new ResultMessageVO(Msg.FAIL,"操作失败,请稍后再试");
    }

    @Override
    public ResultMessageVO refuseApplication(String hotelId) {
        String keys[] = {"state"};
        Object values[] = {HotelState.REFUSED};
        if(hotelDataService.updateHotelById(hotelId,keys,values)){
            return new ResultMessageVO(Msg.SUCCESS,"");
        }
        return new ResultMessageVO(Msg.FAIL,"操作失败,请稍后再试");
    }


    @Override
    public ResultMessageVO settleAccounts(String hotelId, double amount) {
        synchronized (this.getClass()){
            AccountEntity accountEntity = accountDataService.getAccountById(AccountConfig.HEADQUARTERS_ACCOUNT);
            AccountEntity accountEntity1 = accountDataService.getAccountById(hotelId);
            double balance = accountEntity.getBalance()-amount;
            if(balance<0){
                return new ResultMessageVO(Msg.FAIL,"余额不足,结算失败");
            }
            String keys[] = {"balance"};
            Object[] values1 = {balance};
            Object[] values2 = {accountEntity1.getBalance()+amount};
            if(accountDataService.updateAccountById(AccountConfig.HEADQUARTERS_ACCOUNT,keys,values1)&&accountDataService.updateAccountById(hotelId,keys,values2)){
                return new ResultMessageVO(Msg.SUCCESS,"");
            }
        }
        return new ResultMessageVO(Msg.FAIL,"操作失败,请稍后再试");
    }

    @Override
    public List<HotelFinanceVO> getAllHotelFinanceInfo() {
        List<AccountModel> models = accountDataService.getAllAccountInfo();
        List<HotelFinanceVO> vos = new ArrayList<>();
        for (AccountModel model:models){
            vos.add(new HotelFinanceVO(model.getHotelName(),model.getBalance()));
        }
        return vos;
    }
}
