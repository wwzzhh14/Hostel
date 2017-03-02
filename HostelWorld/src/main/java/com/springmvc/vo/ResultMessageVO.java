package com.springmvc.vo;

import com.springmvc.config.Msg;

/**
 * Created by guhan on 16/8/29.
 */
public class ResultMessageVO {
    private Msg status;

    private String reason;

    public ResultMessageVO(Msg msg, String reason){
        this.status = msg;
        this.reason = reason;
    }

    public Msg getStatus(){return this.status;}

    public void setStatus(Msg msg){this.status = msg;}

    public String getReason(){return this.reason;}

    public void setReason(String reason){this.reason = reason;}

}
