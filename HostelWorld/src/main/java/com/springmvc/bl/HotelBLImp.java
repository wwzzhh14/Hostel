package com.springmvc.bl;

import com.springmvc.blservice.HotelBLService;
import com.springmvc.config.HotelState;
import com.springmvc.config.Msg;
import com.springmvc.dataservice.*;
import com.springmvc.entities.*;
import com.springmvc.model.CheckInModel;
import com.springmvc.model.CheckOutModel;
import com.springmvc.model.SumModel;
import com.springmvc.util.RandomStringUtil;
import com.springmvc.util.TimeUtil;
import com.springmvc.vo.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wzh on 26/01/2017.
 */
@Service
public class HotelBLImp implements HotelBLService {

    @Resource
    HotelDataService hotelDataService;

    @Resource
    UserDataService userDataService;

    @Resource
    RoomDataService roomDataService;

    @Resource
    AppointmentDataService appointmentDataService;

    @Resource
    PlanDataService planDataService;

    @Override
    public ResultMessageVO register(String name, String owner, String address, String note, String username, String password) {
        if(username.equals("")||password.equals(""))return new ResultMessageVO(Msg.FAIL,"用户名和密码不能为空");
        String hotelId = RandomStringUtil.getRandomNumberString(7);
        HotelEntity hotelEntity = new HotelEntity(hotelId,name,owner,address, HotelState.REGISTER,note);
        UserEntity userEntity = new UserEntity(username,password,hotelId);
        if(hotelDataService.addHotel(hotelEntity)&&userDataService.addUser(userEntity)){
            return new ResultMessageVO(Msg.SUCCESS,hotelId);
        }
        return new ResultMessageVO(Msg.FAIL,"注册失败,请稍后再试");
    }

    @Override
    public ResultMessageVO login(String hotelId, String password) {
        if(checkPassword(password,hotelId)){
            return new ResultMessageVO(Msg.SUCCESS,"");
        }
        return new ResultMessageVO(Msg.FAIL,"用户名或密码错误");
    }

    @Override
    public ResultMessageVO apply(String hotelId, String password) {
        if (checkPassword(password,hotelId)){
            String[] keys = {"state"};
            String[] values = {HotelState.APPLY};
            if(hotelDataService.updateHotelById(hotelId,keys,values)){
                return new ResultMessageVO(Msg.SUCCESS,"申请已经发送");
            }
            return new ResultMessageVO(Msg.FAIL,"申请失败,请稍后再试");
        }
        return new ResultMessageVO(Msg.FAIL,"密码错误");
    }

    @Override
    public ResultMessageVO updateHotelInfo(HotelInfoVO hotelInfoVO, String password) {
        if(checkPassword(password, hotelInfoVO.getHotelId())){
            HotelEntity hotelEntity = new HotelEntity(hotelInfoVO.getHotelId(),hotelInfoVO.getName(),hotelInfoVO.getOwner(),hotelInfoVO.getAddress(),HotelState.CHANGED,hotelInfoVO.getNote());
            if(hotelDataService.updateHotel(hotelEntity)){
                return new ResultMessageVO(Msg.SUCCESS,"");
            }
        }
        return new ResultMessageVO(Msg.FAIL,"更新失败,请稍后再试");
    }

    @Override
    public List<RoomInfoVO> getAllRoomsInfo(String hotelId) {
        List<RoomEntity> roomEntityList = roomDataService.getRoomByHotelId(hotelId);
        List<RoomInfoVO> voList = new ArrayList<>();
        for (RoomEntity entity:roomEntityList){
            voList.add(new RoomInfoVO(entity.getRoomId(),entity.getType(),entity.getPrice(),entity.getState(),entity.getName()));
        }
        return voList;
    }

    @Override
    public RoomInfoVO getRoomInfo(String roomId) {
        RoomEntity entity= roomDataService.getRoomById(roomId);
        RoomInfoVO vo = new RoomInfoVO(entity.getRoomId(),entity.getType(),entity.getPrice(),entity.getState(),entity.getName());
        return vo;
    }

    @Override
    public ResultMessageVO updateRoomInfo(RoomInfoVO vo) {
        RoomEntity entity = roomDataService.getRoomById(vo.getRoomId());
        RoomEntity newEntity = new RoomEntity(vo.getRoomId(),vo.getType(),vo.getPrice(),vo.getState(),vo.getName(),entity.getHotelId());
        if(roomDataService.updateRoom(newEntity)){
            return new ResultMessageVO(Msg.SUCCESS,"");
        }
        return new ResultMessageVO(Msg.FAIL,"更新失败,请稍后再试");
    }

    @Override
    public List<String> getOccupiedDate(String roomId) {
       List<AppointmentEntity> entities = appointmentDataService.getAppointmentByRoomId(roomId);
        List<String> resultList = new ArrayList<>();
        for (AppointmentEntity entity:entities){
            List<String> dates = TimeUtil.getDateByStartAndEnd(entity.getStartdate(),entity.getEnddate());
            for (String s:dates){
                resultList.add(s);
            }
        }
        return resultList;
    }

    @Override
    public ResultMessageVO releasePlan(String hotelId, String startDate, String endDate) {
        PlanEntity entity = new PlanEntity(hotelId,TimeUtil.getDate(startDate),TimeUtil.getDate(endDate));
        if(planDataService.addPlan(entity)){
            return new ResultMessageVO(Msg.SUCCESS,"");
        }
        return new ResultMessageVO(Msg.FAIL,"发布失败,请稍后再试");
    }

    @Override
    public List<AppointmentVO> getAppointmentByDate(String hotelId, String date) {
        List<AppointmentEntity> entities = appointmentDataService.getAppointmentByDate(TimeUtil.getDate(date),hotelId);
        List<AppointmentVO> vos = new ArrayList<>();
        for (AppointmentEntity entity:entities){
            vos.add(new AppointmentVO(entity.getAppointmentId(),TimeUtil.date2String(entity.getStartdate()),TimeUtil.date2String(entity.getEnddate()),entity.getDeposit(),entity.getRoomId()));
        }
        return vos;
    }

    @Override
    public List<CheckInVO> getCheckInByDate(String hotelId, String date) {
        List<CheckInModel> models = appointmentDataService.getCheckInByDate(TimeUtil.getDate(date),hotelId);
        List<CheckInVO> vos = new ArrayList<>();
        for (CheckInModel model:models){
            vos.add(new CheckInVO(TimeUtil.date2String(model.getDate()),model.getRoomId()));
        }
        return vos;
    }

    @Override
    public List<CheckOutVO> getCheckOutByDate(String hotelId, String date) {
        List<CheckOutModel> models = appointmentDataService.getCheckOutByDate(TimeUtil.getDate(date),hotelId);
        List<CheckOutVO> vos = new ArrayList<>();
        for (CheckOutModel model:models){
            vos.add(new CheckOutVO(TimeUtil.date2String(model.getDate()),model.getRoomId(),model.getTotal()));
        }
        return vos;
    }

    @Override
    public IncomeVO getTotalIncomeByDate(String hotelId, String date) {
        SumModel depositModel = appointmentDataService.getSumByDate(AppointmentEntity.class,"deposit",TimeUtil.getDate(date),hotelId);
        SumModel totalModel = appointmentDataService.getSumByDate(CheckOutEntity.class,"total",TimeUtil.getDate(date),hotelId);
        IncomeVO vo = new IncomeVO(depositModel.getSum(),totalModel.getSum());
        return vo;
    }
    private boolean checkPassword(String password,String hotelId){
        if (hotelId==null||password.equals("")) return false;
        UserEntity entity = userDataService.getUserByHotelId(hotelId);
        if (entity==null) return false;
        if(entity.getPassword().equals(password)) return true;
        return false;
    }
}
