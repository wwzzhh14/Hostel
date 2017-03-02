package com.springmvc.blservice;

import com.springmvc.vo.ResultMessageVO;

/**
 * Created by wzh on 20/01/2017.
 */
public interface GuestBLService {
    public ResultMessageVO createAppointment(String name,String IDnumber,String roomId, String startDate, String endDate,double deposit);

}
