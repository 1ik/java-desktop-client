package models;

import config.Config;
import db.DB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import structures.Showroom;

public class showroom_model
{
  public static HashMap<Integer, String> showroom_id_map;
  
  public static String getShowroomName(int showroom_id)
  {
    if (showroom_id_map == null)
    {
      showroom_id_map = new HashMap();
      
      String sql = "select * from showrooms";
      ResultSet set = DB.execute_get(sql);
      try
      {
        while (set.next()) {
          showroom_id_map.put(Integer.valueOf(set.getInt("showroom_id")), set.getString("name"));
        }
      }
      catch (SQLException ex)
      {
        Logger.getLogger(showroom_model.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    return (String)showroom_id_map.get(Integer.valueOf(showroom_id));
  }
  
  public static void insert(Showroom[] showrooms)
  {
    DB.execute_update("DELETE FROM showrooms");
    
    Connection c = DB.getConnection();
    try
    {
      c.setAutoCommit(false);
      String insertTableSQL = "INSERT INTO showrooms(name , showroom_id, address) VALUES(?,?,?)";
      
      PreparedStatement preparedStatement = c.prepareStatement(insertTableSQL);
      for (Showroom showroom : showrooms)
      {
        preparedStatement.setString(1, showroom.getName());
        preparedStatement.setInt(2, showroom.getShowroom_id());
        preparedStatement.setString(3, showroom.location);
        preparedStatement.addBatch();
      }
      preparedStatement.executeBatch();
      c.commit();
      c.close(); return;
    }
    catch (SQLException ex)
    {
      Logger.getLogger(Expense_type_model.class.getName()).log(Level.SEVERE, null, ex);
    }
    finally
    {
      try
      {
        c.close();
      }
      catch (SQLException ex)
      {
        Logger.getLogger(Expense_type_model.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }
  
  public static String getShowroomLocation()
  {
    String sql = "select address from showrooms where showroom_id = '" + Config.SHOWROOM_ID + "'";
    ResultSet resSet = DB.execute_get(sql);
    String shoroom_location = "";
    try
    {
      while (resSet.next()) {
        shoroom_location = resSet.getString("address");
      }
    }
    catch (SQLException ex)
    {
      Logger.getLogger(showroom_model.class.getName()).log(Level.SEVERE, null, ex);
    }
    return shoroom_location;
  }
  
  public static ArrayList<Showroom> get()
  {
    ArrayList<Showroom> showroom_list = new ArrayList();
    String query = "select * from showrooms";
    ResultSet results = DB.execute_get(query);
    try
    {
      while (results.next()) {
        showroom_list.add(new Showroom(results.getInt("showroom_id"), results.getString("name"), results.getString("address")));
      }
      results.getStatement().getConnection().close();
    }
    catch (SQLException ex)
    {
      Logger.getLogger(showroom_model.class.getName()).log(Level.SEVERE, null, ex);
    }
    return showroom_list;
  }
  
  public static int getId(String showroom_name)
  {
    String sql = "select showroom_id from showrooms where name = '" + showroom_name + "'";
    ResultSet resSet = DB.execute_get(sql);
    int showroom_id = -1;
    try
    {
      while (resSet.next()) {
        showroom_id = resSet.getInt("showroom_id");
      }
    }
    catch (SQLException ex)
    {
      Logger.getLogger(showroom_model.class.getName()).log(Level.SEVERE, null, ex);
    }
    return showroom_id;
  }
  
  public static String getVatRegiNo()
  {
    String sql = "select vat_reg from config";
    ResultSet resSet = DB.execute_get(sql);
    String vat_reg = "";
    try
    {
      while (resSet.next()) {
        vat_reg = resSet.getString("vat_reg");
      }
    }
    catch (SQLException ex)
    {
      Logger.getLogger(showroom_model.class.getName()).log(Level.SEVERE, null, ex);
    }
    return vat_reg;
  }
  
  public static String getPhoneNumber()
  {
    String sql = "select mobile from config";
    ResultSet resSet = DB.execute_get(sql);
    String mobile = "";
    try
    {
      while (resSet.next()) {
        mobile = resSet.getString("mobile");
      }
    }
    catch (SQLException ex)
    {
      Logger.getLogger(showroom_model.class.getName()).log(Level.SEVERE, null, ex);
    }
    return mobile;
  }
}
