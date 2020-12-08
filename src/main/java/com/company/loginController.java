
package com.company;

import java.sql.*;

public class loginController
{
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://34.83.219.17:3306/275";

    // Database credentials
    static final String USER = "275group17";
    static final String PASS = "275group17";

    //public class variables
    public Statement stmt;
    public Connection con;
    public String type = "";

    // check login info，to see if password matches;
    //if username NOT exist, result = false;
    //if username exist, then will check the user's pwd and apply update checking;
    //if pwd checking is false, result = false;
    //if pwd checking is true, result = true;
    public boolean checkLogin(String username, String password)
    {
        boolean check = false;
        boolean checkIDExist = false;
        stmt = null;
        con = null;
        try {
            connectDB();
            stmt = con.createStatement();
            String sql = "SELECT * FROM userLogin WHERE username = '"+username+"'";
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                String USERNAME = rs.getString("username");
                if (username.equals(USERNAME))
                    checkIDExist = true;
            } else {
                rs.close();
                stmt.close();
                con.close();
                return check;
            }
            if (checkIDExist) {
                rs.close();
                String sql2 = "SELECT password, roleType FROM userLogin U WHERE U.username = '"+username+"'";
                ResultSet rs2 = stmt.executeQuery(sql2);
                while(rs2.next()) {
                    String pass = rs2.getString(1);
                    String TYPE = rs2.getString(2);
                    if(password.equals(pass)) {
                        this.type = TYPE;
                        check = true;
                    }
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

    //check if user name is valid, returns a String result
    //if contains SPACE, result = spaceWrong;
    //if contains invalid character, result = characterWrong;
    //if a valid name, result = UNGood;
    //should only contain character from A - Z;
    //NO SPACE included;
    public String UNValidator(String username)
    {
        String checkResult = "";
        boolean check = true;
        // to check space
        if (username.contains(" ") && check)
        {
            checkResult = "spaceWrong";
            check = false;
        }
        // for special characters
        else if ((username.contains("@") || username.contains("#")
                || username.contains("!") || username.contains("~")
                || username.contains("$") || username.contains("%")
                || username.contains("^") || username.contains("&")
                || username.contains("*") || username.contains("(")
                || username.contains(")") || username.contains("-")
                || username.contains("+") || username.contains("/")
                || username.contains(":") || username.contains(".")
                || username.contains(",") || username.contains("<")
                || username.contains(">") || username.contains("?")
                || username.contains("|") || username.contains("'")) && check)
        {
            checkResult = "characterWrong";
        }
        else
            checkResult = "UNGood";
        return checkResult;
    }

    //check if password is Valid, returns a String result
    //if pwd length is NOT in range, result = lengthWrong;
    //if contains SPACE, result = spaceWrong;
    //if contains invalid character, result = characterWrong;
    //if a valid pwd, result = passwordGood;
    //should NOT contain special characters !#$%&'*+-/=?^_`{|}~;
    //NO SPACE included;
    public String passwordValidator(String password)
    {
        String checkResult = "";
        boolean check = true;
        // for checking if password length is between 8 and 15
        if (!((password.length() >= 6) && (password.length() <= 15)) && check)
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

    //public class for connecting DB
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

}