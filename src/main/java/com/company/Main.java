package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        // write your code here
        System.out.println("testtestt02222000est");
        loginController testReg = new loginController();
        //boolean result = testReg.addUserInfo(222,"asdasd","aa","student", "fk@a.ca","qwe");
        Boolean result = testReg.checkLogin("222", "password");
        //boolean result = testReg.changePassword(345, "","newp");
        System.out.println(result);
        //boolean testEmail = testReg.emailValid("&'^@sfuc.ca");
        //System.out.println("check email valid: " + testEmail);
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection(
                    "jdbc:mysql://34.83.219.17:3306/275","275group17","275group17");
            //here sonoo is database name, root is username and password
            Statement stmt = con.createStatement();
            //String sql = "DELETE FROM  userLogin WHERE id = 123";
            //String sql = "CREATE TABLE userLogin(id INTEGER, firstName VARCHAR(255), lastName VARCHAR(255)," +
                   // "roleType VARCHAR(255), email VARCHAR(255), password VARCHAR(255), PRIMARY KEY(id))";
            //String sql = "INSERT INTO userLogin VALUES (111,'qweqwe', 'qweqwe','adm','asd@sfu.ca', 'asdasd');";
            //stmt.executeUpdate(sql);
            //stmt.executeUpdate(sql2);

            ResultSet rs = stmt.executeQuery("select * from userLogin");
            while(rs.next())
                System.out.println(rs.getInt(1)+"  "+rs.getString(2) + " " + rs.getString("password"));
            con.close();
        }catch(Exception e){
            System.out.println(e);
        }

        loginGUI login_win = new loginGUI();
        login_win.loginWindow();

    }



}
