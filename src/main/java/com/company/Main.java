package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) {
        // write your code here
        System.out.println("testtestt02222000est");
        registerController testReg = new registerController();
        //boolean result = testReg.addUserInfo(567, "word","newnewnew", "adm", "asd@sfu.ca", "new");
        boolean result = testReg.changePassword(345, "","newp");
        System.out.println(result);
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection(
                    "jdbc:mysql://34.83.219.17:3306/connect","root","root");
            //here sonoo is database name, root is username and password
            Statement stmt = con.createStatement();
            String sql = "DELETE FROM  userLogin WHERE id = 123";
            //String sql2 = "INSERT INTO userLogin VALUES ('qweqwe', 'qweqwe');";
            stmt.executeUpdate(sql);
            //stmt.executeUpdate(sql2);

            ResultSet rs = stmt.executeQuery("select * from userLogin");
            while(rs.next())
                System.out.println(rs.getInt(1)+"  "+rs.getString(2));
            con.close();
        }catch(Exception e){
            System.out.println(e);
        }

        //*/
    }



}
