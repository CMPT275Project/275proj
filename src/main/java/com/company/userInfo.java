package com.company;

import java.sql.*;

public class userInfo{
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://34.83.219.17:3306/275";

    // Database credentials
    static final String USER = "275group17";
    static final String PASS = "275group17";

    public String username;
    public String email;
    public String type;
    public String fn;
    public String ln;
    public String userID;
    public Statement stmt;
    public Connection con;

    public void setInfo(String username)
    {
        stmt = null;
        con = null;
        try {
            Class.forName(JDBC_DRIVER);
            con = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = con.createStatement();
            String sql = "SELECT * FROM userLogin WHERE username = '"+username+"'";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next())
            {
                String ID = rs.getString(1);
                String UN = rs.getString(2);
                String FN = rs.getString(3);
                String LN = rs.getString(4);
                String TYPE = rs.getString(5);
                String EMAIL = rs.getString(6);

                //setter
                setEmail(EMAIL);
                setUserName(UN);
                setFN(FN);
                setLN(LN);
                setID(ID);
                setType(TYPE);
            }
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        }
    }



    public void getInfo(String uname) {
        /*setUserName();
        setEmail();
        setType();
        setFN();
        setLN();
        setID();*/
    }

    //getter
    public String getEmail() {
        return this.email;
    }

    public String getUserName() {
        return this.username;
    }

    public String getType() {
        return this.type;
    }

    public String getFN() {
        return this.fn;
    }

    public String getLN() {
        return this.ln;
    }

    public String getID() {
        return this.userID;
    }

    //setter
    public void setEmail(String email) {
        this.email = email;
    }

    public void setUserName(String username) {
        this.username = username;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setFN(String fn) {
        this.fn = fn;
    }

    public void setLN(String ln) {
        this.ln = ln;
    }

    public void setID(String ID) {
        this.userID = ID;
    }
}