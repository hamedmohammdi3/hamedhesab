package com.gmail.hamed.logic;

import com.gmail.hamed.baseClass.kala;
import com.gmail.hamed.dbCon.DAOanbar;
import com.gmail.hamed.dbCon.DAOfrosh;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class LogicFrosh {
    private static List <kala> ListAnbar;

    static {
        try {
            ListAnbar = DAOanbar.selectAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
   static kala kala1;

    private static int searchKala(kala kala) throws SQLException {

        if(kala.getNoe().equals("روسری")||kala.getNoe().equals("سایر")){
            for (kala i:
                 ListAnbar) {
                if((i.getCode()==kala.getCode())&&i.getColor().equals(kala.getColor())){
                    kala1=i;
                    return i.getMid();
                }
            }
        }
      else {  for (kala i:
             ListAnbar) {
            if ((i.getCode()==kala.getCode()||i.getName().equals(kala.getName())) &&
                    i.getColor().equals(kala.getColor())&&i.getSize()==kala.getSize()){
                kala1=i;
                return i.getMid();
            }
        }}
        return -1;
    }
    public static boolean logicf(kala kala) throws SQLException {
      int mid=searchKala(kala);
      if(mid!=-1){
          DAOanbar.delete(mid);
          DAOfrosh.insert(kala);
          return true;
      }
return false;
    }
    public static kala searcher(kala kala) throws SQLException {
        ListAnbar=DAOanbar.selectAll();
        if(kala.getNoe().equals("روسری")||kala.getNoe().equals("سایر")){
        for (kala i:
             ListAnbar) {
            if (i.getCode()==kala.getCode())
                return i;
        }}else {
            for (kala i:
                    ListAnbar) {
                if (i.getCode()==kala.getCode()&&i.getColor().equals(kala.getColor())&&i.getSize()==kala.getSize())
                    return i;
        }}
        return null;
    }
}
