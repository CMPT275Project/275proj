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

    //showTable
    public String[][] showTable()
    {
        stmt = null;
        con = null;
        String[][] table = null;
        try {
            connectDB();
            stmt = con.createStatement();
            String sql = "SELECT * FROM deviceInventory";
            ResultSet rsId = stmt.executeQuery(sql);
            if(!rsId.next()) {
                rsId.close();
                stmt.close();
                con.close();
                return table;
            }
            else
            {
                int size = 0;
                while(rsId.next())
                {
                    table[size][0] = rsId.getString("modelID");
                    table[size][1] = rsId.getString("deviceType");
                    table[size][2] = rsId.getString("description");
                    table[size][3] = rsId.getString("availability");
                    table[size][4] = rsId.getString("reserve");
                    table[size][5] = rsId.getString("expireDate");
                    table[size][6] = rsId.getString("cond");
                    size++;
                }
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
        return table;
    }





    //add new device info
    //take * PARAMETERS, output a String result;
    //if modelID exist, result = 2;
    //if modelID NOT exist, then will add the new device and apply update checking;
    //if any update checking is false, result = -1;
    //if all update checking is true, result = 1;
    public int addNewDevice(String modelID, String deviceType, String description, String availability, String reserve, String expireDate, String cond)
    {
        int finalCheck = 0;
        boolean checkIDExist = false;
        stmt = null;
        con = null;
        boolean deviceTypeUpdate = false;
        boolean descriptionUpdate = false;
        boolean availabilityUpdate = false;
        boolean reserveUpdate = false;
        boolean expireDateUpdate = false;
        boolean condUpdate = false;
        try {
            connectDB();
            stmt = con.createStatement();
            String sql = "SELECT * FROM deviceInventory WHERE modelID ='"+modelID+"'";
            ResultSet rsId = stmt.executeQuery(sql);
            if (rsId.next()) {
                String ID = rsId.getString("modelID");
                if (ID.equals(modelID)) {
                    rsId.close();
                    stmt.close();
                    con.close();
                    return finalCheck = 2;
                }
            } else {
                checkIDExist = false;
            }
            if (!checkIDExist) {
                rsId.close();
                //insert into new info
                String sql2 = "INSERT INTO deviceInventory VALUES('"+modelID+"',,'"+deviceType+"','"+description+"', '"+availability+"', '"+reserve+"', '"+expireDate+"', '"+cond+"')";
                stmt.executeUpdate(sql2);
                stmt.close();
                con.close();

                //apply checking update
                if(checkOneItem(modelID, "deviceType", deviceType))
                    deviceTypeUpdate = true;
                if(checkOneItem(modelID, "description", description))
                    descriptionUpdate = true;
                if(checkOneItem(modelID, "availability", availability))
                    availabilityUpdate = true;
                if(checkOneItem(modelID, "reserve", reserve))
                    reserveUpdate = true;
                if(checkOneItem(modelID, "expireDate", expireDate))
                    expireDateUpdate = true;
                if(checkOneItem(modelID, "cond", cond))
                    condUpdate = true;
                if(deviceTypeUpdate && descriptionUpdate && availabilityUpdate && reserveUpdate && expireDateUpdate && condUpdate)
                    finalCheck = 1;
                else
                    finalCheck = -1;
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
    //it will apply the delete and apply a checking update;
    //if the checking is failed, result = -1;
    //if the checking is success, result = 1;
    public int deleteDevice(String modelID)
    {
        int finalCheck = 0;
        stmt = null;
        con = null;
        try
        {
            connectDB();
            stmt = con.createStatement();
            String sql = "DELETE FROM deviceInventory WHERE modelID ='"+modelID+"'";
            ResultSet rsId = stmt.executeQuery(sql);
            if (rsId.next()) {
                String ID = rsId.getString("modelID");
                if (ID.equals(modelID)) {
                    rsId.close();
                    stmt.close();
                    con.close();
                    finalCheck = -1;
                }
            }else {
                rsId.close();
                stmt.close();
                con.close();
                finalCheck = 1;
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

    //apply checking on ONE item
    //take 3 PARAMETERS, output a boolean result;
    //if modelID exist, then will apply checking on update;
    //if update checking is false, result = false;
    //if update checking is true, result = true;
    public boolean checkOneItem(String modelID, String checkType, String checkItem)
    {
        boolean checkExist = false;
        boolean finalCheck = false;
        stmt = null;
        con = null;
        try {
            connectDB();
            stmt = con.createStatement();
            String sql = "SELECT * FROM WHERE modelID = '"+modelID+"'";
            ResultSet rsId = stmt.executeQuery(sql);
            if(rsId.next())
            {
                String ID = rsId.getString("modelID");
                if(ID.equals(modelID))
                    checkExist = true;
            }
            else
            {
                checkExist = false;
            }
            if(checkExist)
            {
                String newSql = "SELECT "+checkType+" FROM deviceInventory U WHERE U.modelID = '"+modelID+"'";
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

    //search by name
    //if return table is empty, then no search result matches
    public String[][] searchType(String deviceType)
    {
        stmt = null;
        con = null;
        String[][] table = null;
        try {
            connectDB();
            stmt = con.createStatement();
            String sql = "SELECT modelID, deviceType, description, availability, reserve, expireDate, cond FROM deviceInventory" +
                    " WHERE deviceType LIKE '"+deviceType+"%'";
            ResultSet rsId = stmt.executeQuery(sql);
            if(!rsId.next()) {
                rsId.close();
                stmt.close();
                con.close();
                return table;
            }
            else
            {
                int size = 0;
                while(rsId.next())
                {
                    table[size][0] = rsId.getString("modelID");
                    table[size][1] = rsId.getString("deviceType");
                    table[size][2] = rsId.getString("description");
                    table[size][3] = rsId.getString("availability");
                    table[size][4] = rsId.getString("reserve");
                    table[size][5] = rsId.getString("expireDate");
                    table[size][6] = rsId.getString("cond");
                    size++;
                }
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
        return table;
    }

    //search by modelID
    //if return table is empty, then no search result matches
    public String[][] searchModelID(String modelID)
    {
        stmt = null;
        con = null;
        String[][] table = null;
        try {
            connectDB();
            stmt = con.createStatement();
            String sql = "SELECT modelID, deviceType, description, availability, reserve, expireDate, cond FROM deviceInventory" +
                    " WHERE modelID = '"+modelID+"'";
            ResultSet rsId = stmt.executeQuery(sql);
            if(!rsId.next()) {
                rsId.close();
                stmt.close();
                con.close();
                return table;
            }
            else
            {
                int size = 0;
                while(rsId.next())
                {
                    table[size][0] = rsId.getString("modelID");
                    table[size][1] = rsId.getString("deviceType");
                    table[size][2] = rsId.getString("description");
                    table[size][3] = rsId.getString("availability");
                    table[size][4] = rsId.getString("reserve");
                    table[size][5] = rsId.getString("expireDate");
                    table[size][6] = rsId.getString("cond");
                    size++;
                }
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
        return table;
    }

    //edit device
    public boolean edit(String modelID, String deviceType, String description, String availability, String reserve, String expireDate, String cond)
    {
        boolean finalCheck = false;
        boolean deviceTypeUpdate = false;
        boolean descriptionUpdate = false;
        boolean availabilityUpdate = false;
        boolean reserveUpdate = false;
        boolean expireDateUpdate = false;
        boolean condUpdate = false;
        stmt = null;
        con = null;
        try {
            connectDB();
            stmt = con.createStatement();
            String sql = "UPDATE deviceInventory SET modelID = '"+modelID+"' WHERE modelID ='"+modelID+"'";
            String sql2 = "UPDATE deviceInventory SET deviceType = '"+deviceType+"' WHERE modelID ='"+modelID+"'";
            String sql3 = "UPDATE deviceInventory SET description = '"+description+"' WHERE modelID ='"+modelID+"'";
            String sql4 = "UPDATE deviceInventory SET availability = '"+availability+"' WHERE modelID ='"+modelID+"'";
            String sql5 = "UPDATE deviceInventory SET reserve = '"+reserve+"' WHERE modelID ='"+modelID+"'";
            String sql6 = "UPDATE deviceInventory SET expireDate = '"+expireDate+"' WHERE modelID ='"+modelID+"'";
            String sql7 = "UPDATE deviceInventory SET cond = '"+cond+"' WHERE modelID ='"+modelID+"'";
            stmt.executeUpdate(sql);
            stmt.executeUpdate(sql2);
            stmt.executeUpdate(sql3);
            stmt.executeUpdate(sql4);
            stmt.executeUpdate(sql5);
            stmt.executeUpdate(sql6);
            stmt.executeUpdate(sql7);
            stmt.close();
            con.close();

            //check edit update
            if(checkOneItem(modelID, "deviceType", deviceType))
                deviceTypeUpdate = true;
            if(checkOneItem(modelID, "description", description))
                descriptionUpdate = true;
            if(checkOneItem(modelID, "availability", availability))
                availabilityUpdate = true;
            if(checkOneItem(modelID, "reserve", reserve))
                reserveUpdate = true;
            if(checkOneItem(modelID, "expireDate", expireDate))
                expireDateUpdate = true;
            if(checkOneItem(modelID, "cond", cond))
                condUpdate = true;
            if(deviceTypeUpdate && descriptionUpdate && availabilityUpdate && reserveUpdate && expireDateUpdate && condUpdate)
                finalCheck = true;
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
