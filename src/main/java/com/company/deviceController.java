package com.company;

import java.sql.*;

public class deviceController {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://34.83.219.17:3306/275";

    //  Database credentials
    static final String USER = "275group17";
    static final String PASS = "275group17";

    //public class variables
    public Statement stmt;
    public Connection con;


    //add new device info
    //take * PARAMETERS, output a String result;
    //if modelID exist, result = modelIDExist;
    //if modelID NOT exist, then will add the new device and apply update checking;
    //if any update checking is false, result =  /  /  /  /  / ;
    //if all update checking is true, result = addSuccess;
    public String addNewDevice(int modelID, String deviceName, String description, boolean availability)
    {
        String finalCheck = "";
        boolean checkIDExist = false;
        stmt = null;
        con = null;
        try {
            connectDB();
            stmt = con.createStatement();
            String sql = "SELECT * FROM deviceInventory WHERE modelID ='" +modelID+ "'";
            ResultSet rsId = stmt.executeQuery(sql);
            if (rsId.next()) {
                int MdlID = rsId.getInt("modelID");
                if (modelID == MdlID) {
                    checkIDExist = true;
                    rsId.close();
                    stmt.close();
                    con.close();
                    return finalCheck = "modelIDExist";
                }
            } else {
                checkIDExist = false;
            }
            if (!checkIDExist) {
                rsId.close();
                //insert into new info
                String sql2 = "INSERT INTO userLogin VALUES('" + modelID + "','"+deviceName+"','" + description + "','"+availability+"')";
                stmt.executeUpdate(sql2);
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


        return finalCheck;
    }


    //delete a device
    //if the modelID NOT exist, result = modelIDNotExist;
    //if the modelID exist, it will apply the delete and apply a checking update;
    //if the checking is failed, result = deviceDeleteFailed;
    //if the checking is success, result = deviceDeleteSuccess;
    public String deleteDevice(int modelID)
    {
        String finalCheck = "";
        stmt = null;
        con = null;
        try{
            connectDB();
            stmt = con.createStatement();
            String sql = "SELECT * FROM deviceInventory WHERE modelID ='"+modelID+"'";
            ResultSet rsId = stmt.executeQuery(sql);
            if (rsId.next()) {
                int MdlID = rsId.getInt("modelID");
                if (modelID == MdlID) {
                    rsId.close();
                    String sql2 = "DELETE FROM deviceInventory WHERE modelID = '"+modelID+"'";
                    stmt.executeUpdate(sql2);
                    stmt.close();
                    con.close();
                }
            }else {
                rsId.close();
                stmt.close();
                con.close();
                return finalCheck = "modelIDNotExist";
            }
        }catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        }
        try
        {
            connectDB();
            stmt = con.createStatement();
            String sql2 = "SELECT * FROM deviceInventory WHERE modelID ='"+modelID+"'";
            ResultSet rsId = stmt.executeQuery(sql2);
            if (rsId.next()) {
                int MdlID = rsId.getInt("modelID");
                if (modelID == MdlID) {
                    rsId.close();
                    stmt.close();
                    con.close();
                    finalCheck = "deviceDeleteFailed";
                }
            }else {
                rsId.close();
                stmt.close();
                con.close();
                return finalCheck = "deviceDeleteSuccess";
            }
        }catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        }
        return finalCheck;
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
