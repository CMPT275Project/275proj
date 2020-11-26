package com.company;

import org.apache.commons.validator.routines.EmailValidator;

import java.sql.*;
//import org.apache.commons.validator.routines.EmailValidator;


public class registerController {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://34.83.219.17:3306/connect";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "root";
    public Statement  stmt;
    public Connection con;
    public boolean checkIdUpdate = false;
    public boolean checkFNUpdate = false;
    public boolean checkLNUpdate = false;
    public boolean checkTypeUpdate = false;
    public boolean checkEmailUpdate = false;
    public boolean checkPwdUpdate = false;

    //add new user registration info
    public boolean addUserInfo(int id, String firstName, String lastName, String roleType, String email, String password) {
        stmt = null;
        con = null;
        boolean check = false;
        try {
            Class.forName(JDBC_DRIVER);
            con = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = con.createStatement();
            //insert into new info
            String sql = "INSERT INTO userLogin VALUES("+id+",'"+firstName+"','"+lastName+"', '"+roleType+"', '"+email+"', '"+password+"')";
            stmt.executeUpdate(sql);
            stmt.close();
            con.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        }
        //apply checking update
        if(checkOneItem(id, "firstName", firstName))
            checkFNUpdate = true;
        if(checkOneItem(id, "lastName", lastName))
            checkLNUpdate = true;
        if(checkOneItem(id, "roleType", roleType))
            checkTypeUpdate = true;
        if(checkOneItem(id, "email", email))
            checkEmailUpdate = true;
        if(checkOneItem(id, "password", password))
            checkPwdUpdate = true;
        if(checkFNUpdate && checkLNUpdate && checkTypeUpdate && checkEmailUpdate && checkPwdUpdate)
            check = true;
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

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        }
        //apply checking update
        if(checkOneItem(id, "password", password))
            check = true;
        return check;
    }

    //apply checking on ONE item
    public boolean checkOneItem(int id, String checkType, String checkItem)
    {
        boolean checkExist = false;
        boolean finalCheck = false;
        stmt = null;
        con = null;
        try {
            Class.forName(JDBC_DRIVER);
            con = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = con.createStatement();
            String sql = "SELECT * FROM userLogin WHERE id = "+ id +"";
            ResultSet rsId = stmt.executeQuery(sql);
            if(rsId.next())
            {
                int ID = rsId.getInt(1);
                if(id == ID)
                    checkExist = true;
            }
            else
            {
                this.checkIdUpdate = false;
            }
            if(checkExist)
            {
                String newSql = "SELECT "+checkType+" FROM userLogin U WHERE U.id = "+id+"";
                ResultSet rs = stmt.executeQuery(newSql);
                while(rs.next())
                {
                    String type = rs.getString(1);
                    finalCheck = checkItem.equals(type);
                }
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
        return finalCheck;
    }

    //check inputs validation
    //check if email is Valid
    public boolean emailValid(String email)
    {
        boolean checkValid = false;
        checkValid = EmailValidator.getInstance().isValid(email);
        return checkValid;
    }
}
