package models;

import db.DB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import structures.Expense_type;

public class Expense_type_model
{
  public static void drop()
  {
    String query = "DROP TABLE if exists expense_type";
    DB.execute_update(query);
  }
  
  public static void create()
  {
    drop();
    String query = "CREATE  TABLE expense_type ('id' INTEGER UNIQUE , 'reason' STRING UNIQUE )";
    DB.execute_update(query);
  }
  
  public static void insert(Expense_type[] types)
  {
    create();
    Connection c = DB.getConnection();
    try
    {
      PreparedStatement ps = c.prepareStatement("");
      
      String insertTableSQL = "INSERT INTO expense_type(id , reason) VALUES(?,?)";
      
      PreparedStatement preparedStatement = DB.getConnection().prepareStatement(insertTableSQL);
      for (Expense_type type : types)
      {
        preparedStatement.setInt(1, type.getId());
        preparedStatement.setString(2, type.getReason());
        preparedStatement.addBatch();
      }
      preparedStatement.executeBatch(); return;
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
  
  public static ArrayList<Expense_type> get_expense_types()
  {
    ResultSet results = DB.execute_get("select * from expense_type");
    ArrayList<Expense_type> types = new ArrayList();
    try
    {
      while (results.next()) {
        types.add(new Expense_type(results.getInt("id"), results.getString("reason")));
      }
    }
    catch (SQLException ex)
    {
      Logger.getLogger(Expense_type_model.class.getName()).log(Level.SEVERE, null, ex);
    }
    return types;
  }
  
  public static int getId(String reason)
  {
    String query = "select id from expense_type where reason = '" + reason + "'";
    ResultSet resultset = DB.execute_get(query);
    int id = -1;
    try
    {
      while (resultset.next()) {
        id = resultset.getInt("id");
      }
    }
    catch (SQLException ex)
    {
      Logger.getLogger(Expense_type_model.class.getName()).log(Level.SEVERE, null, ex);
    }
    return id;
  }
}
