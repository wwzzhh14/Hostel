package com.springmvc.blservice;

import com.springmvc.vo.*;

import java.util.List;

/**
 * Created by wzh on 24/01/2017.
 */
public interface HotelBLService {
    public ResultMessageVO register(String name,String owner,String address,String note,String username,String password);
    public ResultMessageVO login(String hotelId,String password);
    public ResultMessageVO apply(String hotelId,String password);
    public ResultMessageVO updateHotelInfo(HotelInfoVO hotelInfoVO,String password);
    public List<RoomInfoVO> getAllRoomsInfo(String hotelId);
    public RoomInfoVO getRoomInfo(String roomId);
    public ResultMessageVO updateRoomInfo(RoomInfoVO vo);
    public List<String> getOccupiedDate(String roomId);
    public ResultMessageVO releasePlan(String hotelId,String startDate,String endDate);
    public List<AppointmentVO> getAppointmentByDate(String hotelId,String date);
    public List<CheckInVO> getCheckInByDate(String hotelId, String date);
    public List<CheckOutVO> getCheckOutByDate(String hotelId,String date);
    public IncomeVO getTotalIncomeByDate(String hotelId,String date);

}
