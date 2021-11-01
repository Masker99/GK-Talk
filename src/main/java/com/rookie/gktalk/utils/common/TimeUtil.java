package com.rookie.gktalk.utils.common;

import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具类
 * @author Masker
 */
public class TimeUtil {
    /**
     * 获取当前时间
     * @return
     */
    public static Calendar getCurrentTime(){
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return calendar;
    }

    /**
     * 设置有效时间
     * @param calendar
     * @param fieldOfCalendar
     * @param amount
     * @return
     */
    public static Calendar setEffectiveTime(Calendar calendar,int fieldOfCalendar,int amount){
        calendar.add(fieldOfCalendar,amount);

        return calendar;
    }

    /**
     * 判断时间是否有效
     * @param currentTime 当前时间
     * @param deadTime 结束时间
     * @return
     */
    public static boolean ifValid(Calendar currentTime,Calendar deadTime){
        return currentTime.before(deadTime);
    }
}
