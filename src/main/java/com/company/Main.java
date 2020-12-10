package com.company;

import java.sql.*;

public class Main {
    public static void main(String[] args){
        final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
        final String DB_URL = "jdbc:mysql://34.83.219.17:3306/275";

        //  Database credentials
        final String USER = "275group17";
        final String PASS = "275group17";

        //public class variables
        Statement stmt;
        Connection con;

        loginGUI login_win = new loginGUI();
        login_win.loginWindow();


        try{
            Class.forName(JDBC_DRIVER);
            con = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = con.createStatement();
            //String sql = "SELECT expireDate FROM deviceInventory WHERE modelID = 'AMP003'";
            String sql = "UPDATE deviceInventory SET expireDate = '01/01/2020' WHERE modelID = 'AMP003'";
            stmt.executeUpdate(sql);
        }
        catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        }

    }

}