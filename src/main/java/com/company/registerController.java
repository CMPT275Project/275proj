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

    //public class variables
    public Statement stmt;
    public Connection con;
    public boolean checkIDUpdate = false;
    public boolean checkFNUpdate = false;
    public boolean checkLNUpdate = false;
    public boolean checkTypeUpdate = false;
    public boolean checkEmailUpdate = false;
    public boolean checkPwdUpdate = false;


    //add new user registration info
    //take 6 PARAMETERS, output a String result;
    //if username exist, result = UNExist;
    //if username NOT exist, then will add the new user and apply update checking;
    //if any update checking is false, result = IDUpdWrong / FNUpdWrong / lNUpdWrong / roleTypeUpdWrong / emailUpdWrong / pwdUpdWrong;
    //if all update checking is true, result = addSuccess;
    public String addUserInfo(int id, String username, String firstName, String lastName, String roleType, String email, String password)
    {
        stmt = null;
        con = null;
        String check = "";
        boolean checkUNExist = false;
        try {
            connectDB();
            stmt = con.createStatement();
            String sql = "SELECT * FROM userLogin WHERE username ='"+username+"'";
            ResultSet rsId = stmt.executeQuery(sql);
            if (rsId.next()) {
                String USERNAME = rsId.getString("username");
                if (username.equals(USERNAME)) {
                    checkUNExist = true;
                    rsId.close();
                    stmt.close();
                    con.close();
                    return check = "UNExist";
                }
            } else {
                checkUNExist = false;
            }
            if (!checkUNExist) {
                rsId.close();
                //insert into new info
                String sql2 = "INSERT INTO userLogin VALUES('" + id + "','"+username+"','" + firstName + "','" + lastName + "', '" + roleType + "', '" + email + "', '" + password + "')";
                stmt.executeUpdate(sql2);
                stmt.close();
                con.close();

                //apply checking update
                if(checkID(username, "id", id))
                    checkIDUpdate = true;
                else
                    return check = "IDUpdWrong";
                if(checkOneItem(username, "firstName", firstName))
                    checkFNUpdate = true;
                else
                    return check = "FNUpdWrong";
                if(checkOneItem(username, "lastName", lastName))
                    checkLNUpdate = true;
                else
                    return check = "lNUpdWrong";
                if(checkOneItem(username, "roleType", roleType))
                    checkTypeUpdate = true;
                else
                    return check = "roleTypeUpdWrong";
                if(checkOneItem(username, "email", email))
                    checkEmailUpdate = true;
                else
                    return check = "emailUpdWrong";
                if(checkOneItem(username, "password", password))
                    checkPwdUpdate = true;
                else
                    return check = "pwdUpdWrong";
                if(checkIDUpdate && checkFNUpdate && checkLNUpdate && checkTypeUpdate && checkEmailUpdate && checkPwdUpdate)
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
    //take 2 PARAMETERS, output a String result;
    //if userID NOT exist, result = UNNotExist;
    //if userID exist, then will change the new user pwd and apply update checking;
    //if update checking is false, result = pwdUpdWrong;
    //if update checking is true, result = pwdChangeSuccess
    public String changePassword(String username, String password)
    {
        stmt = null;
        con = null;
        String check = "";
        boolean checkUNExist = false;
        try {
            connectDB();
            stmt = con.createStatement();
            String sql = "SELECT * FROM userLogin WHERE username = '"+username+"'";
            ResultSet rsId = stmt.executeQuery(sql);
            if (rsId.next()) {
                String USERNAME = rsId.getString("username");
                if (username.equals(USERNAME)) {
                    checkUNExist = true;
                }
            } else {
                rsId.close();
                stmt.close();
                con.close();
                return check = "UNNotExist";
            }if (checkUNExist) {
                rsId.close();
                //insert into new info
                String sql2 = "UPDATE userLogin SET password = '"+password+"' WHERE username = '"+username+"'";
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
        if(checkOneItem(username, "password", password))
            check = "pwdChangeSuccess";
        else
            check = "pwdUpdWrong";
        return check;
    }

    //apply checking on ONE item
    //take 3 PARAMETERS, output a boolean result;
    //if userID exist, then will apply checking on update;
    //if update checking is false, result = false;
    //if update checking is true, result = true;
    public boolean checkOneItem(String username, String checkType, String checkItem)
    {
        boolean checkExist = false;
        boolean finalCheck = false;
        stmt = null;
        con = null;
        try {
            connectDB();
            stmt = con.createStatement();
            String sql = "SELECT * FROM userLogin WHERE username = '"+ username +"'";
            ResultSet rsId = stmt.executeQuery(sql);
            if(rsId.next())
            {
                String USERNAME = rsId.getString("username");
                if(username.equals(USERNAME))
                    checkExist = true;
            }
            else
            {
                checkExist = false;
            }
            if(checkExist)
            {
                String newSql = "SELECT "+checkType+" FROM userLogin U WHERE U.username = '"+username+"'";
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

    //apply checking on ID item
    //take 3 PARAMETERS, output a boolean result;
    //if userID exist, then will apply checking on update;
    //if update checking is false, result = false;
    //if update checking is true, result = true;
    public boolean checkID(String username, String checkID, int checkItem)
    {
        boolean checkExist = false;
        boolean finalCheck = false;
        stmt = null;
        con = null;
        try {
            connectDB();
            stmt = con.createStatement();
            String sql = "SELECT * FROM userLogin WHERE username = '"+username+"'";
            ResultSet rsId = stmt.executeQuery(sql);
            if(rsId.next())
            {
                String USERNAME = rsId.getString("username");
                if(username.equals(USERNAME))
                    checkExist = true;
            }
            else
            {
                checkExist = false;
            }
            if(checkExist)
            {
                String newSql = "SELECT "+checkID+" FROM userLogin U WHERE U.username = '"+username+"'";
                ResultSet rs = stmt.executeQuery(newSql);
                while(rs.next())
                {
                    int value = rs.getInt("id");
                    if(value == checkItem)
                        finalCheck = true;
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
    //check if id format is valid, can only be number, returns a boolean result
    public boolean IDValidator(String id)
    {
        boolean checkResult = true;
        try
        {
            Integer.parseInt(id);
        }
        catch (NumberFormatException e)
        {
            checkResult = false;
        }
        return checkResult;
    }

    //check if user name is valid, returns a String result
    //if contains SPACE, result = spaceWrong;
    //if contains invalid character, result = characterWrong;
    //if a valid name, result = UNGood;
    //should only contain character from A - Z;
    //NO SPACE included;
    public boolean UNValidator(String username)
    {
        boolean checkResult = true;
        boolean check = true;
        // to check space
        if (username.contains(" ") && check)
        {
            checkResult = false;
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
            checkResult = false;
        }
        else
            checkResult = true;
        return checkResult;
    }

    //check if email format is valid, returns a boolean result
    //The local part can contain:
    //-alphanumeric characters – A to Z (both upper and lower case) and 0 to 9
    //-printable characters – !#$%&'*+-/=?^_`{|}~
    //-a dot . (the local part cannot start and end with a dot, and can’t use the dot consecutively like example..first@mailtrap.io).
    public boolean emailValidator(String email)
    {
        boolean checkValid = false;
        checkValid = EmailValidator.getInstance().isValid(email);
        return checkValid;
    }

    //check if first name is valid, returns a String result
    //if contains SPACE, result = spaceWrong;
    //if contains invalid character, result = characterWrong;
    //if a valid name, result = FNGood;
    //should only contain character from A - Z;
    //NO SPACE included;
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

    //check if last name is valid, returns a String result
    //if contains SPACE, result = spaceWrong;
    //if contains invalid character, result = characterWrong;
    //if a valid name, result = LNGood;
    //should only contain character from A - Z;
    //NO SPACE included;
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

