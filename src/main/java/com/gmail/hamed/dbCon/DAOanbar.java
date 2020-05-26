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
public class DAOanbar {
    public static List<kala> selectAll() throws SQLException {
        List<kala> listM=new ArrayList<>();

        Statement st=DbSql.getConnection().createStatement();
        ResultSet rs=st.executeQuery("select * from anbar;");
        while(rs.next()){
            kala kala =new kala();
            kala.setNoe(rs.getString("noe"));
            kala.setMid(rs.getInt("mid"));
            kala.setCode(rs.getInt("code"));
            kala.setName(rs.getString("name"));
            kala.setColor(rs.getString("color"));
            kala.setSize(rs.getInt("size"));
            kala.setDateIn(rs.getString("pdate"));
            kala.setPriceIn(rs.getInt("price"));
            kala.setTolydi(rs.getString("tolydiname"));
            listM.add(kala);
        }
        return listM;
    }

    public static void insert(kala name) throws SQLException {
        PreparedStatement rs=DbSql.getConnection().prepareStatement("insert into anbar (name,color,size,pdate,price,tolydiname,noe,code) values (?,?,?,?,?,?,?,?)");
         rs.setString(1,name.getName());
         rs.setString(2,name.getColor());
         rs.setInt(3,name.getSize());
         rs.setString(4,name.getDateIn());
         rs.setInt(5,name.getPriceIn());
         rs.setString(6,name.getTolydi());
         rs.setString(7,name.getNoe());
         rs.setInt(8,name.getCode());
         rs.executeUpdate();
    }
    public static void delete(int mid) throws SQLException {
        PreparedStatement rs=DbSql.getConnection().prepareStatement("DELETE FROM anbar WHERE mid=(?);");
       rs.setInt(1,mid);
       rs.executeUpdate();
    }
}
