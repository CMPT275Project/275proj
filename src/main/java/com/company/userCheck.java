package com.company;

import java.sql.*;

public class userCheck {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://34.83.219.17:3306/275";

    //  Database credentials
    static final String USER = "275group17";
    static final String PASS = "275group17";
    public Statement stmt;
    public Connection con;

    //apply checking
    public boolean checking(int id, String checkType, String checkItem)
    {
        boolean check = false;
        stmt = null;
        con = null;
        try {
            Class.forName(JDBC_DRIVER);
            con = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = con.createStatement();
            String sql = "SELECT "+checkType+" FROM userLogin U WHERE U.id = "+id+"";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next())
            {
                String pass = rs.getString(1);
                check = checkItem.equals(pass);
            }
            stmt.close();
            con.close();
        }catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        }
        System.out.println("in function checking:" + check);
        return check;
    }

}
