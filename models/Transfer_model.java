package models;

import db.DB;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import structures.ChangedItem;
import structures.Transfer;

public class Transfer_model
{
  public static ArrayList<Transfer> get()
  {
    ArrayList<Transfer> transfer_list = new ArrayList();
    String query = "SELECT * from transfer where from_showroom_id != showroom_id order by created_at desc";
    ResultSet resultset = DB.execute_get(query);
    try
    {
      while (resultset.next()) {
        transfer_list.add(new Transfer(resultset.getInt("id"), resultset.getInt("transfer_id"), resultset.getInt("showroom_id"), resultset.getString("created_at"), resultset.getInt("total_items"), resultset.getInt("from_showroom_id"), resultset.getInt("contextual_transfer_id")));
      }
    }
    catch (SQLException ex)
    {
      Logger.getLogger(Transfer_model.class.getName()).log(Level.SEVERE, null, ex);
    }
    return transfer_list;
  }
  
  public static int get_last_transfer_id()
  {
    String query = "select id,transfer_id from transfer where from_showroom_id = 1 order by transfer_id desc limit 1";
    int transfer_id = -1;
    ResultSet results = DB.execute_get(query);
    try
    {
      while (results.next()) {
        transfer_id = results.getInt("transfer_id");
      }
    }
    catch (SQLException ex)
    {
      Logger.getLogger(Transfer_model.class.getName()).log(Level.SEVERE, null, ex);
    }
    return transfer_id;
  }
  
  public static int get_last_CustomerChange_transfer_id()
  {
    String query = "select id,transfer_id from transfer where from_showroom_id = showroom_id order by transfer_id desc limit 1";
    int transfer_id = -1;
    ResultSet results = DB.execute_get(query);
    try
    {
      while (results.next()) {
        transfer_id = results.getInt("transfer_id");
      }
    }
    catch (SQLException ex)
    {
      Logger.getLogger(Transfer_model.class.getName()).log(Level.SEVERE, null, ex);
    }
    return transfer_id;
  }
  
  public static int get_last_ShowroomToShowroom_transfer_id()
  {
    String query = "select id,transfer_id from transfer where from_showroom_id != showroom_id and from_showroom_id != 1 order by transfer_id desc limit 1";
    int transfer_id = -1;
    ResultSet results = DB.execute_get(query);
    try
    {
      while (results.next()) {
        transfer_id = results.getInt("transfer_id");
      }
    }
    catch (SQLException ex)
    {
      Logger.getLogger(Transfer_model.class.getName()).log(Level.SEVERE, null, ex);
    }
    return transfer_id;
  }
  
  public static void insert(Transfer[] transfer_ids)
  {
    Connection c = DB.getConnection();
    try
    {
      c.setAutoCommit(false);
      PreparedStatement ps = c.prepareStatement("");
      
      String insertTableSQL = "INSERT INTO transfer(transfer_id , showroom_id, created_at, total_items, from_showroom_id, contextual_transfer_id) VALUES(?,?,?,?,?,?)";
      
      PreparedStatement preparedStatement = DB.getConnection().prepareStatement(insertTableSQL);
      for (Transfer transfer : transfer_ids)
      {
        preparedStatement.setInt(1, transfer.getTransfer_id());
        preparedStatement.setInt(2, transfer.getShowroom_id());
        preparedStatement.setString(3, transfer.getCreated_at());
        preparedStatement.setInt(4, transfer.getTotal_items());
        preparedStatement.setInt(5, transfer.getFrom_showroom_id());
        preparedStatement.setInt(6, transfer.getContextual_transfer_id());
        preparedStatement.addBatch();
      }
      preparedStatement.executeBatch();
      c.commit(); return;
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
  
  public static ArrayList<ChangedItem> get_changed_items()
  {
    String sql = "SELECT item.*, transfer.created_at as date, transfer.showroom_id,transfer.from_showroom_id FROM transfer join item on item.transfer_id = transfer.transfer_id\nwhere transfer.showroom_id == transfer.from_showroom_id order by item.transfer_id desc";
    
    ResultSet set = DB.execute_get(sql);
    ArrayList<ChangedItem> changed_items = new ArrayList();
    try
    {
      while (set.next()) {
        changed_items.add(new ChangedItem(set.getInt("item_id"), set.getString("type"), set.getString("size"), set.getFloat("sell_price"), set.getString("color_code"), set.getInt("transfer_id"), set.getString("date")));
      }
    }
    catch (SQLException ex)
    {
      Logger.getLogger(Expense_type_model.class.getName()).log(Level.SEVERE, null, ex);
    }
    return changed_items;
  }
  
  public static void main(String[] args)
  {
    ArrayList<ChangedItem> changedItems = get_changed_items();
    for (ChangedItem item : changedItems) {
      System.out.println(item);
    }
  }
}
