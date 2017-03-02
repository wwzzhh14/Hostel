package com.springmvc.dao;

import com.springmvc.dataservice.AppointmentDataService;
import com.springmvc.entities.AppointmentEntity;
import com.springmvc.entities.CheckInEntity;
import com.springmvc.entities.CheckOutEntity;
import com.springmvc.model.CheckInModel;
import com.springmvc.model.CheckOutModel;
import com.springmvc.model.SumModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by wzh on 21/01/2017.
 */
@Service
public class AppointmentDao implements AppointmentDataService {
    @Resource
    BaseDao baseDao;
    @Override
    public boolean addAppointment(AppointmentEntity entity) {
        return baseDao.save(entity);
    }

    @Override
    public boolean updateAppointmentById(String appointmentId, String[] keys, Object[] values) {
        String[] keys2 = {"appointmentId"};
        Object[] values2 = {appointmentId};
        return baseDao.updateByProperties(AppointmentEntity.class,keys2,values2,keys,values);
    }

    @Override
    public AppointmentEntity getAppointmentById(String appointmentId) {
        return (AppointmentEntity) baseDao.findById(AppointmentEntity.class,appointmentId);
    }

    @Override
    public List<AppointmentEntity> getAppointmentByMemberId(String memberId) {
        String key = "memberId";
        Object value = memberId;
        return (List<AppointmentEntity>) baseDao.findByProperty(AppointmentEntity.class,key,value);
    }

    @Override
    public boolean addCheckIn(CheckInEntity entity) {
        return baseDao.save(entity);
    }

    @Override
    public boolean addCheckOut(CheckOutEntity entity) {
        return baseDao.save(entity);
    }

    @Override
    public CheckInEntity getCheckInInfo(String appointmentId) {
        return (CheckInEntity) baseDao.findById(CheckInEntity.class,appointmentId);
    }

    @Override
    public CheckOutEntity getCheckOutInfo(String appointmentId) {
        return (CheckOutEntity) baseDao.findById(CheckOutEntity.class,appointmentId);
    }

    @Override
    public List<AppointmentEntity> getAppointmentByRoomId(String roomId) {
        String key = "roomId";
        return (List<AppointmentEntity>) baseDao.findByProperty(AppointmentEntity.class,key,roomId);
    }

    @Override
    public List<AppointmentEntity> getAppointmentByDate(Date date, String hotelId) {
        String keys[] = {"date","hotelId"};
        Object[] values = {date,hotelId};
        return (List<AppointmentEntity>) baseDao.findByPropertys(AppointmentEntity.class,keys,values);
    }

    @Override
    public List<CheckInModel> getCheckInByDate(Date date, String hotelId) {
        Object[] values = {date,hotelId};
        String sql = "select new com.springmvc.model.CheckInModel(c.date,r.roomId) from AppointmentEntity a,CheckInEntity c,RoomEntity r where c.date = ? and c.appointmentId = a.appointmentId and  r.roomId = a.roomId and r.hotelId = ?";
        return (List<CheckInModel>) baseDao.execQuery(sql,values);
    }

    @Override
    public List<CheckOutModel> getCheckOutByDate(Date date, String hotelId) {
        Object[] values = {date,hotelId};
        String sql = "select new com.springmvc.model.CheckOutModel(c.date,r.roomId,c.total) from AppointmentEntity a,CheckOutEntity c,RoomEntity r where c.date = ? and c.appointmentId = a.appointmentId and  r.roomId = a.roomId and r.hotelId = ?";
        return (List<CheckOutModel>) baseDao.execQuery(sql,values);
    }

    @Override
    public SumModel getSumByDate(Class c, String key, Date date, String hotelId) {
        Object[] values = {date,hotelId};
        StringBuffer sqlBuf = new StringBuffer();
//        sqlBuf.append("select new com.springmvc.model.SumModel(sum(").append(key).append(")) from").append(c.getSimpleName()).append(" where date = ? and hotelId")
        String sql = " AppointmentEntity a,CheckOutEntity c,RoomEntity r where c.date = ? and c.appointmentId = a.appointmentId and  r.roomId = a.roomId and r.hotelId = ?";
        return (SumModel) baseDao.execQuery(sql,values).get(0);
    }


}
