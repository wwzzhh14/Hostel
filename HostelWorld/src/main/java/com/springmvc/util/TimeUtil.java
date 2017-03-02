package com.springmvc.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by HP on 2016/3/28.
 */
public class TimeUtil {
    private static long getTimeLong(Date date){
        Calendar c=Calendar.getInstance();
        c.setTime(date);
        long result=c.getTimeInMillis();
        return result;
    }

//    public static String getNowDate(){
//        Date date=new Date();
//        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
//        String result=sdf.format(date);
//        return result;
//    }
//    public static String getPassedDate(double days,String nowDate){
//        Calendar c=Calendar.getInstance();
//        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
//        Date date= null;
//        try {
//            date = sdf.parse(nowDate);
//            c.setTime(date);
//            long offset=(long)days*24*60*60*1000;
//            long time=c.getTimeInMillis()-offset;
//            c.setTimeInMillis(time);
//            Date resultDate=c.getTime();
//            String result=sdf.format(resultDate);
//            return result;
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }
    private static String long2String(long time){
        Calendar c=Calendar.getInstance();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        c.setTimeInMillis(time);
        Date resultDate=c.getTime();
        String result=sdf.format(resultDate);
        return result;
    }
    public static List<String> getDateByStartAndEnd(Date start,Date end){
        Long startLong = getTimeLong(start);
        Long endLong = getTimeLong(end);
        List<String> resultList = new ArrayList<>();
        for (long i = startLong;i<=endLong;i+=24*60*60*1000){
            resultList.add(long2String(i));
        }
        return resultList;
    }


    public static Date  getDate(String dateString){
        SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd" );
        try {
            Date date = sdf.parse(dateString);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String date2String(Date date){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String str=sdf.format(date);
        return str;
    }


}
