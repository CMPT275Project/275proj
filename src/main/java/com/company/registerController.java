package com.company;

import java.sql.*;

public class registerController {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://34.83.219.17:3306/connect";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "root";
    public Statement  stmt;
    public Connection con;

    //add new user registration info
    public boolean addUserEmail(int id, String firstName, String lastName, String roleType, String email, String password) {
        stmt = null;
        con = null;
        boolean check = false;
        try {
            Class.forName(JDBC_DRIVER);
            con = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = con.createStatement();
            String sql = "INSERT INTO userLogin VALUES("+id+",'"+firstName+"','"+lastName+"', '"+roleType+"', '"+email+"', '"+password+"')";
            stmt.executeUpdate(sql);
            stmt.close();
            con.close();
            check = true;
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        }
        return check;
    }

    //update user password
    public boolean changePassword(int id, String email, String password) {
        stmt = null;
        con = null;
        boolean check = false;
        try {
            Class.forName(JDBC_DRIVER);
            con = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = con.createStatement();
            String sql = "UPDATE userLogin SET password = '"+password+"' WHERE id = "+id+"";
            stmt.executeUpdate(sql);
            stmt.close();
            con.close();
            check = true;
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        }
        return check;
    }
}
