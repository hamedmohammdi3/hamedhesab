package com.gmail.hamed.logic;
import com.gmail.hamed.baseClass.kala;
import com.gmail.hamed.dbCon.DAOanbar;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LogicFactor {
  private static List<kala> Lkala=new ArrayList<kala>();
  public static kala search(kala kala1) throws SQLException {
    Lkala=DAOanbar.selectAll();
    for (kala i:
         Lkala) {
      if (i.getCode()==kala1.getCode()){
        return i;
      }
    }
    return null;
  }
  public static void fLogic(kala kala, ArrayList<String> size) throws SQLException {
    if(size.isEmpty()){
      DAOanbar.insert(kala);
    }else{
    for (String i:
         size) {
      kala.setSize(Integer.parseInt(i));
      DAOanbar.insert(kala);
    }
  }}
}
