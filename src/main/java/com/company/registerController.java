package com.company;

import java.sql.*;
import org.apache.commons.validator.routines.EmailValidator;


public class registerController {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://34.83.219.17:3306/275";

    //  Database credentials
    static final String USER = "275group17";
    static final String PASS = "275group17";
    public Statement  stmt;
    public Connection con;
    public boolean checkFNUpdate = false;
    public boolean checkLNUpdate = false;
    public boolean checkTypeUpdate = false;
    public boolean checkEmailUpdate = false;
    public boolean checkPwdUpdate = false;

    //add new user registration info
    public String addUserInfo(int id, String firstName, String lastName, String roleType, String email, String password)
    {
        stmt = null;
        con = null;
        String check = "";
        boolean checkIDExist = false;
        try {
            connectDB();
            stmt = con.createStatement();
            String sql = "SELECT * FROM userLogin WHERE id = " + id + "";
            ResultSet rsId = stmt.executeQuery(sql);
            if (rsId.next()) {
                int ID = rsId.getInt(1);
                if (id == ID) {
                    checkIDExist = true;
                    rsId.close();
                    stmt.close();
                    con.close();
                    return check = "idExist";
                }
            } else {
                checkIDExist = false;
            }
            if (!checkIDExist) {
                rsId.close();
                //insert into new info
                String sql2 = "INSERT INTO userLogin VALUES(" + id + ",'" + firstName + "','" + lastName + "', '" + roleType + "', '" + email + "', '" + password + "')";
                stmt.executeUpdate(sql2);
                stmt.close();
                con.close();

                //apply checking update
                if(checkOneItem(id, "firstName", firstName))
                    checkFNUpdate = true;
                else
                    return check = "firstNameWrong";
                if(checkOneItem(id, "lastName", lastName))
                    checkLNUpdate = true;
                else
                    return check = "lastNameWrong";
                if(checkOneItem(id, "roleType", roleType))
                    checkTypeUpdate = true;
                else
                    return check = "roleTypeWrong";
                if(checkOneItem(id, "email", email))
                    checkEmailUpdate = true;
                else
                    return check = "emailWrong";
                if(checkOneItem(id, "password", password))
                    checkPwdUpdate = true;
                else
                    return check = "passwordWrong";
                if(checkFNUpdate && checkLNUpdate && checkTypeUpdate && checkEmailUpdate && checkPwdUpdate)
                    return check = "addSuccess";
            }
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
    public String changePassword(int id, String password)
    {
        stmt = null;
        con = null;
        String check = "";
        boolean checkIDExist = false;
        try {
            connectDB();
            stmt = con.createStatement();
            String sql = "SELECT * FROM userLogin WHERE id = " + id + "";
            ResultSet rsId = stmt.executeQuery(sql);
            if (rsId.next()) {
                int ID = rsId.getInt(1);
                if (id == ID) {
                    checkIDExist = true;
                }
            } else {
                rsId.close();
                stmt.close();
                con.close();
                return check = "idNotExist";
            }if (checkIDExist) {
                rsId.close();
                //insert into new info
                String sql2 = "UPDATE userLogin SET password = '"+password+"' WHERE id = "+id+"";
                stmt.executeUpdate(sql2);
                stmt.close();
                con.close();
            }
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        }
        //apply checking update
        if(checkOneItem(id, "password", password))
            check = "pwdChangeSuccess";
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
            connectDB();
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
                checkExist = false;
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
                rs.close();
            }
            rsId.close();
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

    //check if email is valid
    public boolean emailValidator(String email)
    {
        boolean checkValid = false;
        checkValid = EmailValidator.getInstance().isValid(email);
        return checkValid;
    }

    //check if first name is valid
    public String FNValidator(String firstName)
    {
        String checkResult = "";
        boolean check = true;
        // to check space
        if (firstName.contains(" ") && check)
        {
            checkResult = "spaceWrong";
            check = false;
        }
        // for special characters
        else if ((firstName.contains("@") || firstName.contains("#")
                || firstName.contains("!") || firstName.contains("~")
                || firstName.contains("$") || firstName.contains("%")
                || firstName.contains("^") || firstName.contains("&")
                || firstName.contains("*") || firstName.contains("(")
                || firstName.contains(")") || firstName.contains("-")
                || firstName.contains("+") || firstName.contains("/")
                || firstName.contains(":") || firstName.contains(".")
                || firstName.contains(",") || firstName.contains("<")
                || firstName.contains(">") || firstName.contains("?")
                || firstName.contains("|") || firstName.contains("'")) && check)
        {
            checkResult = "characterWrong";
        }
        else
            checkResult = "FNGood";
        return checkResult;
    }

    //check if first name is valid
    public String LNValidator(String lastName)
    {
        String checkResult = "";
        boolean check = true;
        // to check space
        if (lastName.contains(" ") && check)
        {
            checkResult = "spaceWrong";
            check = false;
        }
        // for special characters
        else if ((lastName.contains("@") || lastName.contains("#")
                || lastName.contains("!") || lastName.contains("~")
                || lastName.contains("$") || lastName.contains("%")
                || lastName.contains("^") || lastName.contains("&")
                || lastName.contains("*") || lastName.contains("(")
                || lastName.contains(")") || lastName.contains("-")
                || lastName.contains("+") || lastName.contains("/")
                || lastName.contains(":") || lastName.contains(".")
                || lastName.contains(",") || lastName.contains("<")
                || lastName.contains(">") || lastName.contains("?")
                || lastName.contains("|") || lastName.contains("'")) && check)
        {
            checkResult = "characterWrong";
        }
        else
            checkResult = "LNGood";
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
}
