package com.gmail.hamed.dbCon;

import com.ibm.icu.util.*;

import java.util.Date;
public class PDate  {
    public static String pDate() {
        PersianCalendar persianCalendar=new PersianCalendar(new Date());
        int year= persianCalendar.get(Calendar.YEAR);
        int month = persianCalendar.get(Calendar.MONTH) + 1;
        int day = persianCalendar.get(Calendar.DAY_OF_MONTH);
        String days;
        String months;

        if(day<10)
            days="0"+day;
        else
            days=""+day;
        if(month<10)
            months="0"+month;
        else
            months=""+month;

        return ""+year+"/"+months+"/"+days;
    }
    public static String pTime() {
        PersianCalendar persianCalendar=new PersianCalendar(new Date());
        int hour= persianCalendar.get(Calendar.HOUR);
        int minute = persianCalendar.get(Calendar.MINUTE) + 1;
        int second = persianCalendar.get(Calendar.SECOND);
        return ""+hour+":"+minute+":"+second;
    }
}