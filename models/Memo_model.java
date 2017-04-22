package models;

import config.Config;
import db.DB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import structures.Memo;

public class Memo_model
{
  public static void add_memo(String items, String changed_items, float discount, String token)
  {
    String query = "insert into memo (added_on , showroom_id, items, returned_items, discount, token, salesman) VALUES(?,?,?,?,?,?,?)";
    Connection connection = DB.getConnection();
    try
    {
      connection.setAutoCommit(false);
      PreparedStatement stmt = connection.prepareStatement(query);
      
      SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      stmt.setString(1, format.format(new Date()));
      
      stmt.setInt(2, Config.SHOWROOM_ID);
      stmt.setString(3, items);
      stmt.setString(4, changed_items);
      stmt.setFloat(5, discount);
      stmt.setString(6, token);
      
      String username = get_username();
      stmt.setString(7, username);
      stmt.executeUpdate();
      connection.commit();
      connection.close();
    }
    catch (SQLException ex)
    {
      Logger.getLogger(Memo_model.class.getName()).log(Level.SEVERE, null, ex);
      try
      {
        connection.rollback();
        connection.close();
      }
      catch (SQLException ex1)
      {
        Logger.getLogger(Memo_model.class.getName()).log(Level.SEVERE, null, ex1);
      }
    }
  }
  
  public static ArrayList<Memo> get()
  {
    ArrayList<Memo> memos = new ArrayList();
    
    ResultSet results = DB.execute_get("select * from memo order by id desc");
    try
    {
      while (results.next())
      {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        
        memos.add(new Memo(results.getInt("id"), results.getString("added_on"), results.getString("items"), results.getString("returned_items"), results.getFloat("discount"), results.getString("token"), results.getString("salesman"), results.getInt("updated"), results.getString("memo_id")));
      }
    }
    catch (SQLException ex)
    {
      Logger.getLogger(Memo_model.class.getName()).log(Level.SEVERE, null, ex);
    }
    return memos;
  }
  
  public static ArrayList<Memo> getUnupdatedMemos()
  {
    ArrayList<Memo> memos = new ArrayList();
    
    ResultSet results = DB.execute_get("select * from memo where updated = 0 or memo_id = null");
    try
    {
      while (results.next())
      {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        memos.add(new Memo(results.getInt("id"), results.getString("added_on"), results.getString("items"), results.getString("returned_items"), results.getFloat("discount"), results.getString("token"), results.getString("salesman"), results.getInt("updated"), results.getString("memo_id")));
      }
    }
    catch (SQLException ex)
    {
      Logger.getLogger(Memo_model.class.getName()).log(Level.SEVERE, null, ex);
    }
    return memos;
  }
  
  public static void updateMemos(MemoResponseObject[] responseObjects)
  {
    Connection c = DB.getConnection();
    try
    {
      c.setAutoCommit(false);
      PreparedStatement ps = c.prepareStatement("");
      
      String updateTableSql = "UPDATE memo SET updated=1, memo_id=? WHERE id=?";
      
      PreparedStatement preparedStatement = DB.getConnection().prepareStatement(updateTableSql);
      for (MemoResponseObject responseObject : responseObjects)
      {
        preparedStatement.setInt(1, responseObject.getMemo_id());
        preparedStatement.setInt(2, responseObject.getId());
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
  
  public static void remove(int[] ids)
  {
    Connection conn = DB.getConnection();
    
    String sql = "DELETE FROM memo WHERE id IN (";
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
  
  public static String get_username()
  {
    String username = "";
    String query = "select username from config";
    ResultSet rset = DB.execute_get(query);
    try
    {
      while (rset.next()) {
        username = rset.getString("username");
      }
    }
    catch (SQLException ex)
    {
      Logger.getLogger(Config_model.class.getName()).log(Level.SEVERE, null, ex);
    }
    return username;
  }
  
  public static void main(String[] args) {}
}
