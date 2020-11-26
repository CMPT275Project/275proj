package com.company;

import java.sql.*;

public class loginController {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://34.83.219.17:3306/connect";

    // Database credentials
    static final String USER = "root";
    static final String PASS = "root";
    public Statement stmt;
    public Connection con;

    // check login info， if password matches, a "true" will be returned, and vise versa
    public boolean checkLogin(int id, String password)
    {
        Boolean check = false;
        stmt = null;
        con = null;
        try {
            Class.forName(JDBC_DRIVER);
            con = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = con.createStatement();
            String sql = "SELECT password FROM userLogin U WHERE U.id = "+id+"";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next())
            {
                String pass = rs.getString("password");
                check = password.equals(pass);
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
        return check;
    }
}
