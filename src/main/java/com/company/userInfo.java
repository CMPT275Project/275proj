package com.company;

public class userInfo{
    public String username;
    public String email;
    public String type;
    public String fn;
    public String ln;
    public String userID;


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
