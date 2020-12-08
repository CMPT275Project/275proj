package com.company;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class deviceController
{
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://34.83.219.17:3306/275";

    //  Database credentials
    static final String USER = "275group17";
    static final String PASS = "275group17";

    //public class variables
    public Statement stmt;
    public Connection con;
    public int rowNum;

    //set the row number of table
    public void setRowNum(){
        stmt = null;
        con = null;
        try {
            connectDB();
            stmt = con.createStatement();
            String sql = "SELECT COUNT(*) FROM deviceInventory";
            ResultSet count = stmt.executeQuery(sql);
            count.next();
            this.rowNum = count.getInt(1);
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

    //get the row number of table
    public int getRowNum()
    {
        return this.rowNum;
    }


    //showTable
    public String[][] showTable()
    {
        stmt = null;
        con = null;
        String[][] table = new String[0][];
        try {
            connectDB();
            stmt = con.createStatement();
            String sql = "SELECT COUNT(*) FROM deviceInventory";
            ResultSet count = stmt.executeQuery(sql);
            count.next();
            int size = count.getInt(1);
            table = new String[size][7];
            ResultSet rs = stmt.executeQuery("SELECT modelID, deviceType, description, availability, reserve, expireDate, cond FROM deviceInventory");
            int i = 0;
            while(rs.next())
            {
                String id = rs.getString(1);
                String Type = rs.getString(2);
                String des = rs.getString(3);
                String avail = rs.getString(4);
                String rsv = rs.getString(5);
                String expire = rs.getString(6);
                String con = rs.getString(7);
                String[] row = {id, Type, des, avail, rsv, expire, con};
                table[i] = row;
                i++;
            }
            rs.close();
            stmt.close();
            con.close();
            return table;
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
                String sql2 = "INSERT INTO deviceInventory VALUES('"+modelID+"','"+deviceType+"','"+description+"', '"+availability+"', 'NA', 'NA', '"+cond+"')";
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
            stmt.executeUpdate(sql);
            String sql2 = "SELECT modelID FROM deviceInventory WHERE modelID ='"+modelID+"'";
            ResultSet rs = stmt.executeQuery(sql2);
            if (rs.next()) {
                String ID = rs.getString("modelID");
                if (ID.equals(modelID)) {
                    rs.close();
                    stmt.close();
                    con.close();
                    finalCheck = -1;
                }
            }else {
                rs.close();
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
            String sql = "SELECT * FROM deviceInventory WHERE modelID = '"+modelID+"'";
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
    public String[][] searchType(String deviceType) {
        stmt = null;
        con = null;
        String[][] table = null;
        try {
            connectDB();
            stmt = con.createStatement();
            String sql = "SELECT COUNT(*) FROM deviceInventory WHERE deviceType LIKE '"+deviceType+"%'";
            ResultSet count = stmt.executeQuery(sql);
            count.next();
            int size = count.getInt(1);
            table = new String[size][7];

            String sql2 = "SELECT modelID, deviceType, description, availability, reserve, expireDate, cond FROM deviceInventory" +
                    " WHERE deviceType LIKE '"+deviceType+"%'";
            ResultSet rs = stmt.executeQuery(sql2);
            if(size == 0) {
                rs.close();
                stmt.close();
                con.close();
                return table = null;
            }
            else
            {
                int tempSize = 0;
                while(rs.next())
                {
                    String id = rs.getString(1);
                    String Type = rs.getString(2);
                    String des = rs.getString(3);
                    String avail = rs.getString(4);
                    String rsv = rs.getString(5);
                    String expire = rs.getString(6);
                    String con = rs.getString(7);
                    String[] row = {id, Type, des, avail, rsv, expire, con};
                    table[tempSize] = row;
                    tempSize++;
                }
            }
            rs.close();
            stmt.close();
            con.close();
            return table;
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
    public String[][] searchModelID(String modelID) {
        stmt = null;
        con = null;
        String[][] table = null;
        try {
            connectDB();
            stmt = con.createStatement();
            String sql = "SELECT COUNT(*) FROM deviceInventory WHERE modelID = '"+modelID+"'";
            ResultSet count = stmt.executeQuery(sql);
            count.next();
            int size = count.getInt(1);
            table = new String[size][7];

            String sql2 = "SELECT modelID, deviceType, description, availability, reserve, expireDate, cond FROM deviceInventory WHERE modelID = '"+modelID+"'";
            ResultSet rs = stmt.executeQuery(sql2);
            if(size == 0) {
                rs.close();
                stmt.close();
                con.close();
                return table = null;
            }
            else
            {
                int tempSize = 0;
                while(rs.next())
                {
                    String id = rs.getString(1);
                    String Type = rs.getString(2);
                    String des = rs.getString(3);
                    String avail = rs.getString(4);
                    String rsv = rs.getString(5);
                    String expire = rs.getString(6);
                    String con = rs.getString(7);
                    String[] row = {id, Type, des, avail, rsv, expire, con};
                    table[tempSize] = row;
                    tempSize++;
                }
            }
            rs.close();
            stmt.close();
            con.close();
            return table;
        }catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        }
        return table;
    }

    //search by username
    //if return table is empty, then no search result matches
    public String[][] searchUsername(String Reserve)
    {
        stmt = null;
        con = null;
        String[][] table = null;
        try {
            connectDB();
            stmt = con.createStatement();
            String sql = "SELECT COUNT(*) FROM deviceInventory WHERE Reserve = '"+Reserve+"'";
            ResultSet count = stmt.executeQuery(sql);
            count.next();
            int size = count.getInt(1);
            table = new String[size][7];

            String sql2 = "SELECT modelID, deviceType, description, availability, reserve, expireDate, cond FROM deviceInventory WHERE Reserve = '"+Reserve+"'";
            ResultSet rs = stmt.executeQuery(sql2);
            if(size == 0) {
                rs.close();
                stmt.close();
                con.close();
                return table = null;
            }
            else
            {
                int tempSize = 0;
                while(rs.next())
                {
                    String id = rs.getString(1);
                    String Type = rs.getString(2);
                    String des = rs.getString(3);
                    String avail = rs.getString(4);
                    String rsv = rs.getString(5);
                    String expire = rs.getString(6);
                    String con = rs.getString(7);
                    String[] row = {id, Type, des, avail, rsv, expire, con};
                    table[tempSize] = row;
                    tempSize++;
                }
            }
            rs.close();
            stmt.close();
            con.close();
            return table;
        }catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        }
        return table;
    }

    //borrow device
    //output boolean result
    public boolean borrowDevice(String modelID, String username, String expireDate)
    {
        boolean finalCheck = false;
        stmt = null;
        con = null;
        try {
            connectDB();
            stmt = con.createStatement();
            String sql = "UPDATE deviceInventory SET reserve = '"+username+"' WHERE modelID = '"+modelID+"'";
            String sql2 = "UPDATE deviceInventory SET expireDate = '"+expireDate+"' WHERE modelID = '"+modelID+"'";
            stmt.executeUpdate(sql);
            stmt.executeUpdate(sql2);
            stmt.close();
            con.close();
            if(checkOneItem(modelID, "reserve", username) && checkOneItem(modelID, "expireDate", expireDate))
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

    //check if device can be borrowed
    public boolean checkCanBorrow(String modelID)
    {
        boolean finalCheck = false;
        stmt = null;
        con = null;
        try {
            connectDB();
            stmt = con.createStatement();
            String sql = "SELECT reserve, expireDate FROM deviceInventory WHERE modelID = '"+modelID+"'";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next())
            {
                String RSV = rs.getString(1);
                String ED = rs.getString(2);
                if(!RSV.equals("NA") || !ED.equals("NA"))
                {
                    finalCheck = true;
                }
            }
            rs.close();
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

    //check valid username in table
    public boolean checkUN(String username)
    {
        boolean finalCheck = false;
        stmt = null;
        con = null;
        try {
            connectDB();
            stmt = con.createStatement();
            String sql = "SELECT username FROM userLogin WHERE username = '"+username+"'";
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                String USERNAME = rs.getString(1);
                if (username.equals(USERNAME))
                    finalCheck = true;
            }
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

    //edit device
    public boolean edit(String modelID, String deviceType, String description, String availability, String cond)
    {
        boolean finalCheck = false;
        boolean descriptionUpdate = false;
        boolean availabilityUpdate = false;
        boolean condUpdate = false;
        stmt = null;
        con = null;
        try {
            connectDB();
            stmt = con.createStatement();
            String sql3 = "UPDATE deviceInventory SET description = '"+description+"' WHERE modelID ='"+modelID+"'";
            String sql4 = "UPDATE deviceInventory SET availability = '"+availability+"' WHERE modelID ='"+modelID+"'";
            String sql7 = "UPDATE deviceInventory SET cond = '"+cond+"' WHERE modelID ='"+modelID+"'";
            stmt.executeUpdate(sql3);
            stmt.executeUpdate(sql4);
            stmt.executeUpdate(sql7);
            stmt.close();
            con.close();

            //check edit update
            if(checkOneItem(modelID, "description", description))
                descriptionUpdate = true;
            if(checkOneItem(modelID, "availability", availability))
                availabilityUpdate = true;
            if(checkOneItem(modelID, "cond", cond))
                condUpdate = true;
            if(descriptionUpdate && availabilityUpdate && condUpdate)
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

    public boolean inputValidator(String password)
    {
        boolean checkResult = false;
        boolean check = true;

        // for special characters
        if ((password.contains("@") || password.contains("#")
                || password.contains("~")
                || password.contains("$") || password.contains("%")
                || password.contains("^") || password.contains("&")
                || password.contains("*") || password.contains("-")
                || password.contains("+") || password.contains("/")
                || password.contains(":") || password.contains("<")
                || password.contains(">")
                || password.contains("|") || password.contains("'")) && check)
        {
            checkResult = false;
        }
        else
            checkResult = true;
        return checkResult;
    }

    //check the input date is valid
    //return -2 if date format is wrong
    //return -1 if the date is not late than current date
    //return 1 if the date is good
    public int dateValidator(String tempDay)throws Exception {
        int result = 0;
        if(tempDay.equals(""))
        {return -2;}
        else
        {
            //set current date
            SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yyyy");
            Date day = new Date();
            Date TpDay = new SimpleDateFormat("dd/MM/yyyy").parse(tempDay);

            System.out.println("current date:" + ft.format(day));
            System.out.println("input date:" + ft.format(TpDay));
            ft.setLenient(false);
            try {
                ft.parse(tempDay.trim());
                //compare date
                if(TpDay.before(day))
                    result = -1;
                else
                    result = 1;
            } catch (ParseException pe) {
                return -2;
            }
        }
        return result;
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