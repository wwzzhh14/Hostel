package com.springmvc.dataservice;

import com.springmvc.entities.AppointmentEntity;
import com.springmvc.entities.CheckInEntity;
import com.springmvc.entities.CheckOutEntity;
import com.springmvc.model.CheckInModel;
import com.springmvc.model.CheckOutModel;
import com.springmvc.model.SumModel;

import java.util.Date;
import java.util.List;

/**
 * Created by wzh on 20/01/2017.
 */
public interface AppointmentDataService {
    public boolean addAppointment(AppointmentEntity entity);
    public boolean updateAppointmentById(String appointmentId, String[] keys, Object[] values);
    public AppointmentEntity getAppointmentById(String appointmentId);
    public List<AppointmentEntity> getAppointmentByMemberId(String memberId);
    public boolean addCheckIn(CheckInEntity entity);
    public boolean addCheckOut(CheckOutEntity entity);
    public CheckInEntity getCheckInInfo(String appointmentId);
    public CheckOutEntity getCheckOutInfo(String appointmentId);
    public List<AppointmentEntity> getAppointmentByRoomId(String roomId);
    public List<AppointmentEntity> getAppointmentByDate(Date date,String hotelId);
    public List<CheckInModel> getCheckInByDate(Date date, String hotelId);
    public List<CheckOutModel> getCheckOutByDate(Date date, String hotelId);
    public SumModel getSumByDate(Class c,String key,Date date,String hotelId);

}
