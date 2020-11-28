package com.company;

import java.sql.*;

public class loginController {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://34.83.219.17:3306/275";

    // Database credentials
    static final String USER = "275group17";
    static final String PASS = "275group17";
    public Statement stmt;
    public Connection con;

    // check login info， if password matches, a "true" will be returned, and vise versa
    public String checkLogin(int id, String password)
    {
        String check = "";
        boolean checkIDExist = false;
        stmt = null;
        con = null;
        try {
            connectDB();
            stmt = con.createStatement();
            String sql = "SELECT * FROM userLogin WHERE id = " + id + "";
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                int ID = rs.getInt(1);
                if (id == ID) {
                    checkIDExist = true;
                }
            } else {
                rs.close();
                stmt.close();
                con.close();
                return check = "idNotExist";
            }
            if (checkIDExist) {
                rs.close();
                String sql2 = "SELECT password FROM userLogin U WHERE U.id = "+id+"";
                ResultSet rs2 = stmt.executeQuery(sql2);
                while(rs2.next()) {
                    String pass = rs2.getString(1);
                    if(password.equals(pass)) { check = "loginSuccess"; }
                    else { check = "passwordNotMatch";}
                }
                rs2.close();
                stmt.close();
                con.close();
            }
        }catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        }
        return check;
    }


    //--------------------check inputs FORMAT validation---------------------------
    //check if id is valid, can only be number
    public String IDValidator(String id)
    {
        String checkResult = "";
        try
        {
            Integer.parseInt(id);
            checkResult = "goodID";
        }
        catch (NumberFormatException e)
        {
            checkResult = "badID";
        }
        return checkResult;
    }

    //check if password is Valid
    public String passwordValidator(String password)
    {
        String checkResult = "";
        boolean check = true;
        // for checking if password length is between 8 and 15
        if (!((password.length() >= 8) && (password.length() <= 15)) && check)
        {
            checkResult = "lengthWrong";
            check = false;
        }
        // to check space
        else if (password.contains(" ") && check)
        {
            checkResult = "spaceWrong";
            check = false;
        }
        // for special characters
        else if ((password.contains("@") || password.contains("#")
                || password.contains("!") || password.contains("~")
                || password.contains("$") || password.contains("%")
                || password.contains("^") || password.contains("&")
                || password.contains("*") || password.contains("(")
                || password.contains(")") || password.contains("-")
                || password.contains("+") || password.contains("/")
                || password.contains(":") || password.contains(".")
                || password.contains(", ") || password.contains("<")
                || password.contains(">") || password.contains("?")
                || password.contains("|") || password.contains("'")) && check)
        {
            checkResult = "characterWrong";
        }
        else
            checkResult = "passwordGood";
        return checkResult;
    }


    public void connectDB()
    {
        try {
            Class.forName(JDBC_DRIVER);
            con = DriverManager.getConnection(DB_URL, USER, PASS);
        }catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        }
    }

    //check input validation
}
