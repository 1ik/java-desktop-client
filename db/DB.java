package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DB
{
  public static void execute_update(String query)
  {
    Statement statement = getStatement();
    try
    {
      statement.executeUpdate(query);
      statement.getConnection().close();
    }
    catch (SQLException ex)
    {
      Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
  public static ResultSet execute_get(String query)
  {
    Statement statement = getStatement();
    try
    {
      return statement.executeQuery(query);
    }
    catch (SQLException ex)
    {
      Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
  }
  
  public static Statement getStatement()
  {
    try
    {
      return getConnection().createStatement();
    }
    catch (SQLException ex)
    {
      Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
    }
    catch (Exception e) {}
    return null;
  }
  
  public static Connection getConnection()
  {
    try
    {
      Class.forName("org.sqlite.JDBC");
      Connection connection = null;
      
      return DriverManager.getConnection("jdbc:sqlite:jfdb.sqlite");
    }
    catch (Exception ex)
    {
      Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
  }
}
