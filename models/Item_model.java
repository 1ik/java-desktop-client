package models;

import db.DB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import structures.Item;

public class Item_model
{
  public static ArrayList<Item> get(int contextual_transfer_id)
  {
    ArrayList<Item> item_lists = new ArrayList();
    String query = "select * from item where contextual_transfer_id = " + contextual_transfer_id;
    
    ResultSet results = DB.execute_get(query);
    try
    {
      while (results.next()) {
        item_lists.add(new Item(results.getInt("item_id"), results.getString("type"), results.getString("size"), results.getFloat("sell_price"), results.getString("color_code"), results.getInt("transfer_id"), results.getInt("contextual_transfer_id")));
      }
    }
    catch (SQLException ex)
    {
      Logger.getLogger(Item_model.class.getName()).log(Level.SEVERE, null, ex);
    }
    return item_lists;
  }
  
  public static void insert(Item[] items)
  {
    Connection c = DB.getConnection();
    try
    {
      c.setAutoCommit(false);
      String insertTableSQL = "INSERT INTO item(item_id , type, size, sell_price, color_code , transfer_id, contextual_transfer_id, designer_style) VALUES(?,?,?,?,?,?,?,?)";
      
      PreparedStatement preparedStatement = c.prepareStatement(insertTableSQL);
      for (Item item : items)
      {
        preparedStatement.setInt(1, item.getItem_id());
        preparedStatement.setString(2, item.getType());
        preparedStatement.setString(3, item.getSize());
        preparedStatement.setFloat(4, item.getSell_price());
        preparedStatement.setString(5, item.getColor_code());
        preparedStatement.setInt(6, item.getTransfer_id());
        preparedStatement.setInt(7, item.getContextual_transfer_id());
        preparedStatement.setString(8, item.designer_style);
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
  
  public static void delete(int[] ids)
  {
    Connection conn = DB.getConnection();
    
    String sql = "DELETE FROM item WHERE item_id IN (";
    for (int i = 0; i < ids.length; i++)
    {
      sql = sql + ids[i];
      if (i < ids.length - 1) {
        sql = sql + ",";
      }
    }
    sql = sql + ")";
    try
    {
      PreparedStatement preparedStatement = conn.prepareStatement(sql);
      preparedStatement.executeUpdate();
    }
    catch (SQLException ex)
    {
      Logger.getLogger(Expense_model.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
  public static Item get_single_item(int item_id)
  {
    String query = "select * from item where item_id = '" + item_id + "'";
    ResultSet result = DB.execute_get(query);
    Item item = null;
    try
    {
      while (result.next()) {
        item = new Item(result.getInt("item_id"), result.getString("type"), result.getString("size"), result.getFloat("sell_price"), result.getString("color_code"), result.getInt("transfer_id"));
      }
    }
    catch (SQLException ex)
    {
      Logger.getLogger(Item_model.class.getName()).log(Level.SEVERE, null, ex);
    }
    return item;
  }
  
  public static String getDesignerStyle(int item_id)
  {
    String sql = "select designer_style from item where item_id = " + item_id;
    ResultSet set = DB.execute_get(sql);
    String style = "";
    try
    {
      while (set.next()) {
        style = set.getString("designer_style");
      }
    }
    catch (SQLException ex)
    {
      Logger.getLogger(Item_model.class.getName()).log(Level.SEVERE, null, ex);
    }
    return style;
  }
  
  public static void main(String[] args) {}
}
