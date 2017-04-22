package models;

import config.Config;
import db.DB;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Config_model
{
  public static void initialize()
  {
    String query = "select * from config";
    ResultSet rset = DB.execute_get(query);
    try
    {
      while (rset.next())
      {
        Config.SHOWROOM_NAME = rset.getString("showroom_name");
        Config.SHOWROOM_ID = rset.getInt("showroom_id");
        Config.USERNAME = rset.getString("username");
        Config.PASSWORD = rset.getString("password");
      }
    }
    catch (SQLException ex)
    {
      Logger.getLogger(Config_model.class.getName()).log(Level.SEVERE, null, ex);
    }
    Config.SHOWROOM_LOCATION = showroom_model.getShowroomLocation();
    System.out.println(Config.SHOWROOM_LOCATION);
  }
  
  public static int getShowroomID()
  {
    int showroom_id = -1;
    String sql = "select showroom_id from config";
    ResultSet rset = DB.execute_get(sql);
    try
    {
      while (rset.next()) {
        showroom_id = rset.getInt("showroom_id");
      }
    }
    catch (SQLException ex)
    {
      Logger.getLogger(Config_model.class.getName()).log(Level.SEVERE, null, ex);
    }
    return showroom_id;
  }
  
  public static void insert(String showroomname, int showroom_id, String username, String password)
  {
    String query = "insert into config(showroom_id, showroom_name, username, password)VALUES(?,?,?,?)";
    
    Connection con = DB.getConnection();
    try
    {
      con.setAutoCommit(false);
      PreparedStatement ps = con.prepareStatement(query);
      ps.setInt(1, showroom_id);
      ps.setString(2, showroomname);
      ps.setString(3, username);
      ps.setString(4, password);
      ps.execute();
      con.commit(); return;
    }
    catch (SQLException ex)
    {
      Logger.getLogger(Config_model.class.getName()).log(Level.SEVERE, null, ex);
      try
      {
        con.rollback();
      }
      catch (SQLException ex1)
      {
        Logger.getLogger(Config_model.class.getName()).log(Level.SEVERE, null, ex1);
      }
    }
    finally
    {
      try
      {
        con.close();
      }
      catch (SQLException ex)
      {
        Logger.getLogger(Config_model.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }
  
  public static void updateShowroomInfo(String vat, String contact)
  {
    String sql = "UPDATE config SET vat_reg = '" + vat + "', mobile = '" + contact + "' WHERE rowid = 1";
    DB.execute_update(sql);
  }
  
  public static ArrayList<String> getShowroomInfo()
  {
    ArrayList<String> list = new ArrayList();
    String sql = "select vat_reg , mobile from config";
    ResultSet set = DB.execute_get(sql);
    try
    {
      while (set.next())
      {
        list.add(set.getString("vat_reg"));
        list.add(set.getString("mobile"));
      }
    }
    catch (SQLException ex)
    {
      Logger.getLogger(Config_model.class.getName()).log(Level.SEVERE, null, ex);
    }
    return list;
  }
  
  public static void main(String[] args)
  {
    ArrayList l = getShowroomInfo();
    for (Object d : l) {
      System.out.println(d);
    }
  }
}
