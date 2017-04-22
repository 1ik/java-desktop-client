package test;

import db.DB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Test
{
  public static void main(String[] args)
    throws Exception
  {
    DB.execute_update("DELETE FROM item");
    DB.execute_update("DELETE FROM config");
    DB.execute_update("DELETE FROM transfer");
    DB.execute_update("DELETE FROM expense");
    DB.execute_update("DELETE FROM expense_type");
    DB.execute_update("DELETE FROM memo");
    DB.execute_update("DELETE FROM returns");
    DB.execute_update("DELETE FROM showrooms");
  }
  
  public static void mysqlinserttest()
    throws SQLException
  {
    Connection c = DB.getConnection();
    c.setAutoCommit(false);
    String insertTableSQL = "INSERT INTO item(item_id , type, size, sell_price, barcode, transfer_id) VALUES(?,?,?,?,?,?)";
    
    PreparedStatement preparedStatement = c.prepareStatement(insertTableSQL);
    for (int i = 0; i < 100000; i++)
    {
      preparedStatement.setInt(1, i);
      preparedStatement.setString(2, "type1");
      preparedStatement.setString(3, "sized");
      preparedStatement.setFloat(4, 332.0F);
      preparedStatement.setString(5, "201310" + i);
      preparedStatement.setInt(6, 66);
      preparedStatement.addBatch();
    }
    preparedStatement.executeBatch();
    c.commit();
    c.close();
  }
}
