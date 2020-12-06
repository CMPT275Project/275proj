package com.company;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.sql.*;
import java.util.Properties;

public class forgotPassController {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://34.83.219.17:3306/275";

    // Database credentials
    static final String USER = "275group17";
    static final String PASS = "275group17";
    public Statement stmt;
    public Connection con;


    //send pwd to user by email if user forgot the pwd
    //if email sending is failed, result = emailSendFailed;
    //if email sending is success, result = emailSendSuccess;
    public static String sendPwdInEmail(String username)
    {
        String finalResult = "";
        String password = "";
        String email = "";
        Statement stmt;
        Connection con;
        try {
            Class.forName(JDBC_DRIVER);
            con = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = con.createStatement();

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
        }catch (SQLException se) {
            //Error handling for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Error handling for Class.forName
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
            message.setText("Hello " + username + ", \n\n" + "Your password for log-in is: " + password +"\n" + "Please keep it safe.\n\n\n" +
                    "Regards,\n\n" + "Device Inventory System Team");
            Transport.send(message);
            finalResult = "Email sent";
        }
        catch (MessagingException e) {
            finalResult = "Unable to send the email";
            String a = e.getStackTrace().toString();
        }
        return finalResult;
    }


    // Check if the username is exist
    // return true if the username is exist
    public boolean checkUname(String uname)
    {
        stmt = null;
        con = null;
        boolean checkUNExist = false;
        try {
            connectDB();
            stmt = con.createStatement();
            String sql = "SELECT * FROM userLogin WHERE username ='"+uname+"'";
            ResultSet rsId = stmt.executeQuery(sql);
            if (rsId.next()) {
                String USERNAME = rsId.getString("username");
                if (uname.equals(USERNAME)) {
                    checkUNExist = true;
                    rsId.close();
                    stmt.close();
                    con.close();
                }
            }
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        }
        return checkUNExist;
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

