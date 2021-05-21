package com.rookie.gktalk.utils.common;

import java.util.Calendar;
import java.util.Date;

public class TimeUtil {
    public static Calendar getCurrentTime(){
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return calendar;
    }

    public static Calendar setEffectiveTime(Calendar calendar,int fieldOfCalendar,int amount){
        calendar.add(fieldOfCalendar,amount);

        return calendar;
    }

    public static boolean ifValid(Calendar currentTime,Calendar deadTime){
        return currentTime.before(deadTime);
    }
}
