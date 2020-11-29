package com.company;

import javax.mail.MessagingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) throws MessagingException {
        // write your code here
        System.out.println("testtestt02222000est");
        deviceController testReg = new deviceController();
        //boolean result = testReg.addUserInfo(222,"asdasd","aa","student", "fk@a.ca","qwe");
        //String result = testReg.addUserInfo(333, "ccc","c","bb","student","cc@aa.ca","ccc");
        //String result = testReg.changePassword("GROUP17","275group17");
        String result = testReg.deleteDevice(7354);
        System.out.println(result);
        //boolean testEmail = testReg.emailValid("&'^@sfuc.ca");
        //System.out.println("check email valid: " + testEmail);
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection(
                    "jdbc:mysql://34.83.219.17:3306/275","275group17","275group17");
            //here sonoo is database name, root is username and password
            Statement stmt = con.createStatement();

            //String sql = "DROP TABLE userLogin";
            //String sql = "CREATE TABLE deviceInventory(modelID INTEGER, deviceName VARCHAR(255)," +
                   // "description VARCHAR(255), availability BOOLEAN, PRIMARY KEY(modelID))";
            //String sql = "INSERT INTO deviceInventory VALUES (111,'resistor','NA',2)";
            //stmt.executeUpdate(sql);
            //stmt.executeUpdate(sql2);s

            ResultSet rs = stmt.executeQuery("select * from deviceInventory");
            while(rs.next())
                System.out.println(rs.getInt(1)+"  "+rs.getString(2));
            con.close();
        }catch(Exception e){
            System.out.println(e);
        }

        System.out.println("now test the GUI");
        new loginGUI();
        loginGUI.loginWindow();

        //*/
    }



}
