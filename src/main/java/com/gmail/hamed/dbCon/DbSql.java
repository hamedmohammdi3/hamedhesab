package com.gmail.hamed.dbCon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.sql.*;
import java.util.Properties;
@Service
public class DbSql {

    private  static Connection connection=null;
    @Autowired
    public static Connection getConnection() {
        if (connection != null){
            return connection;
        }
        else {
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

                connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1434;databaseName=lonidor",
                        "sa", "@Hamed1223");
                System.out.println("salam");
            } catch (Exception e) {
                System.out.println("Err...");
                e.printStackTrace();
            }
            return connection;
        }}
}
