package com.gmail.hamed.logic;

import com.gmail.hamed.baseClass.kala;

import java.util.ArrayList;
import java.util.List;

public class DateFilter {
    public static List<kala> dateFilterAnbar(List<kala> kala1,String last,String to){
        Integer last1=Integer.parseInt(last);
        Integer to1=Integer.parseInt(to);
      List<kala> kala2=new ArrayList<>();
        for (kala i:
             kala1) {
         String m= i.getDateIn().replace("/","");
         int date=Integer.parseInt(m);
         if(date>=last1&&date<to1)
             kala2.add(i);
        }
     return kala2;
    }
}
