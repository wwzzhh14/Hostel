package com.springmvc.bl;

import com.springmvc.blservice.GuestBLService;
import com.springmvc.config.AppointmentState;
import com.springmvc.config.AppointmentType;
import com.springmvc.config.Msg;
import com.springmvc.dataservice.AppointmentDataService;
import com.springmvc.dataservice.GuestDataService;
import com.springmvc.entities.AppointmentEntity;
import com.springmvc.entities.GuestEntity;
import com.springmvc.util.RandomStringUtil;
import com.springmvc.util.TimeUtil;
import com.springmvc.vo.ResultMessageVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by wzh on 21/01/2017.
 */

@Service
public class GuestBLImp implements GuestBLService {
    @Resource
    AppointmentDataService appointmentDataService;
    @Resource
    GuestDataService guestDataService;
    @Override
    public ResultMessageVO createAppointment(String name, String IDnumber, String roomId, String startDate, String endDate,double deposit) {
        String appointmentId = RandomStringUtil.getRandomNumberString(10);
        String type = AppointmentType.GUEST;
        String state = AppointmentState.NOT_CHECK_IN;
        String guestId = RandomStringUtil.getRandomNumberString(7);
        GuestEntity guestEntity = new GuestEntity(guestId,name,IDnumber);
        if(guestDataService.addGuest(guestEntity)){
            AppointmentEntity entity = new AppointmentEntity(appointmentId,guestId,roomId, TimeUtil.getDate(startDate),TimeUtil.getDate(endDate),deposit,type,state,new Date());
            if(appointmentDataService.addAppointment(entity)){
                return new ResultMessageVO(Msg.SUCCESS,appointmentId);
            }
        }
        return new ResultMessageVO(Msg.FAIL,"预定失败,请稍后再试");
    }

}
