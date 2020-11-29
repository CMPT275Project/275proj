package com.company;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.Session;
import javax.mail.Transport;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class loginController {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://34.83.219.17:3306/275";

    // Database credentials
    static final String USER = "275group17";
    static final String PASS = "275group17";

    //public class variables
    public Statement stmt;
    public Connection con;

    // check login info，to see if password matches;
    //take 2 PARAMETERS, output a String result;
    //if username NOT exist, result = UNNotExist;
    //if username exist, then will check the user's pwd and apply update checking;
    //if pwd checking is false, result = passwordNotMatch;
    //if pwd checking is true, result = loginSuccess;
    public String checkLogin(String username, String password)
    {
        String check = "";
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
                return check = "UNNotExist";
            }
            if (checkIDExist) {
                rs.close();
                String sql2 = "SELECT password FROM userLogin U WHERE U.username = '"+username+"'";
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

    //update user password
    //take 2 PARAMETERS, output a String result;
    //if username NOT exist, result = UNNotExist;
    //if username exist, then will change the new user pwd and apply update checking;
    //if update checking is false, result = pwdUpdWrong;
    //if update checking is true, result = pwdChangeSuccess
    public String changePassword(String username, String password)
    {
        stmt = null;
        con = null;
        String check = "";
        boolean checkIDExist = false;
        try {
            connectDB();
            stmt = con.createStatement();
            String sql = "SELECT * FROM userLogin WHERE username = '"+username+"'";
            ResultSet rsId = stmt.executeQuery(sql);
            if (rsId.next()) {
                String USERNAME = rsId.getString("username");
                if (username.equals(USERNAME))
                    checkIDExist = true;
            } else {
                rsId.close();
                stmt.close();
                con.close();
                return check = "UNNotExist";
            }if (checkIDExist) {
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


    //send pwd to user by email if user forgot the pwd
    //if username NOT exist, result = UNNotExist;
    //if username exist, then will get the pwd and email for that username;
    //if email sending is failed, result = emailSendFailed;
    //if email sending is success, result = emailSendSuccess;
    public static String sendPwdInEmail(String username) {
        String finalResult = "";
        boolean checkUNExist = false;
        String password = "";
        String email = "";
        Statement stmt;
        Connection con;
        try {
            Class.forName(JDBC_DRIVER);
            con = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = con.createStatement();
            String sql = "SELECT * FROM userLogin WHERE username = '" + username + "'";
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                String USERNAME = rs.getString("username");
                if (username.equals(USERNAME))
                    checkUNExist = true;
            } else {
                rs.close();
                stmt.close();
                con.close();
                return finalResult = "UNNotExist";
            }
            if (checkUNExist) {
                rs.close();
                String sql2 = "SELECT password, email FROM userLogin U WHERE U.username = '"+username+"'";
                ResultSet rs2 = stmt.executeQuery(sql2);
                while(rs2.next()) {
                    String pass = rs2.getString(1);
                    String EMAIL = rs2.getString(2);
                    password = pass;
                    email = EMAIL;
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

        String recipient = email;
        String sender = "cmpt275proj@gmail.com";
        String emailPwd = "275group17";

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sender, emailPwd);
            }
        });
        try{
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(sender));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject("[Device Inventory System] - Password Reminder");
            message.setText("Hello " + username + ", \n\n" + "Your password for log-in is: " + password +".\n" + "Please keep it safe.\n\n\n" +
                    "Regards,\n\n" + "Device Inventory System Team");
            Transport.send(message);
            finalResult = "emailSendSuccess";
        }
        catch (MessagingException e) {
            finalResult = "emailSendFailed";
            e.printStackTrace();
        }
        return finalResult;
    }


    //apply checking on ONE item
    //take 3 PARAMETERS, output a boolean result;
    //if username exist, then will apply checking on update;
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


    //--------------------check inputs FORMAT validation---------------------------
    //check if id format is valid, can only be number, returns a boolean result
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
