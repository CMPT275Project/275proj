package com.company;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) throws Exception {
        // write your code here
        System.out.println("Start");
        loginController testReg = new loginController();
        //boolean result = testReg.addUserInfo(222,"asdasd","aa","student", "fk@a.ca","qwe");

/*
        //Boolean result = testReg.checkLogin("222", "password");
        //boolean result = testReg.changePassword(345, "","newp");
        //System.out.println(result);
        //boolean testEmail = testReg.emailValid("&'^@sfuc.ca");
        //System.out.println("check email valid: " + testEmail);
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection(
                    "jdbc:mysql://34.83.219.17:3306/275","275group17","275group17");
            //here sonoo is database name, root is username and password
            Statement stmt = con.createStatement();
            //String sql = "DROP TABLE deviceInventory";
            //String sql = "CREATE TABLE deviceInventory(modelID VARCHAR(255), deviceType VARCHAR(255), description VARCHAR(255), availability VARCHAR(255), reserve VARCHAR(255), expireDate VARCHAR(255), cond VARCHAR(255), PRIMARY KEY(modelID))";
            String sql = "INSERT INTO deviceInventory VALUES('666', 'pn junction', 'A p–n junction is a boundary or interface between two types of semiconductor materials, p-type and n-type, inside a single crystal of semiconductor.'" +
                    ", 'yes', 'NA', '01/01/2021', 'good')";
            stmt.executeUpdate(sql);
            //stmt.executeUpdate(sql2);
            ResultSet rs = stmt.executeQuery("select * from deviceInventory");
            while(rs.next()) {
                System.out.println("test ROW:" + rs.getRow() );
                System.out.println(rs.getInt(1) + "  " + rs.getString(2) + " " + rs.getString(3) + " "
                        + rs.getString(4));
            }
            con.close();
        }catch(Exception e){
            System.out.println(e);
        }



 */
        /*deviceGUI g = new deviceGUI();
        g.deviceWindow("aaa","Student");
        deviceController k = new deviceController();
        String date = "1/01/2021";
        int re = k.dateValidator(date);
        System.out.println(re);*/

        loginGUI login_win = new loginGUI();
        login_win.loginWindow();


    }



}