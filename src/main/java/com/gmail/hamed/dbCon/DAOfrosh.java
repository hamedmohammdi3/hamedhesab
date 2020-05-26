package com.gmail.hamed.dbCon;

import com.gmail.hamed.baseClass.kala;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
@Service
public class DAOfrosh {

        public static List<kala> selectAll() throws SQLException {
            List<kala> listM=new ArrayList<>();

            Statement st=DbSql.getConnection().createStatement();
            ResultSet rs=st.executeQuery("select * from Frosh1;");
            while(rs.next()){
                kala kala =new kala();
                kala.setNoe(rs.getString("noe"));
                kala.setCode(rs.getInt("fcode"));
                kala.setName(rs.getString("fname"));
                kala.setColor(rs.getString("rang"));
                kala.setSize(rs.getInt("size"));
                kala.setDateOut(rs.getString("fdateOut"));
                kala.setDateIn(rs.getString("fdateIn"));
                kala.setPriceIn(rs.getInt("price"));
                kala.setPriceOut(rs.getInt("priceOut"));
                kala.setTime(rs.getString("ftime"));
                listM.add(kala);
            }
            return listM;
        }

        public static boolean insert(kala name) throws SQLException {
            System.out.println("frosh Insert");
            PreparedStatement rs=DbSql.getConnection().prepareStatement("insert into Frosh1 (fname,rang,size,fdateOut,price,ftime,noe,fcode,fdateIn,priceOut) values (?,?,?,?,?,?,?,?,?,?)");
            rs.setString(1,name.getName());
            rs.setString(2,name.getColor());
            rs.setInt(3,name.getSize());
            rs.setString(4,name.getDateOut());
            rs.setInt(5,name.getPriceIn());
            rs.setString(6,name.getTime());
            rs.setString(7,name.getNoe());
            rs.setInt(8,name.getCode());
            rs.setString(9,name.getDateIn());
            rs.setInt(10,name.getPriceOut());
            rs.executeUpdate();
      return true;
        }

    }


