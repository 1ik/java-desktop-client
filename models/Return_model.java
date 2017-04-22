package models;

import db.DB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import structures.Return;

public class Return_model
{
  public static void main(String[] args)
  {
    add_return(new Return(3, "2,3,4,5,6"));
    add_return(new Return(5, "7,8,9,10,11"));
    add_return(new Return(6, "12,13,14,15,16"));
  }
  
  public static void add_return(Return ret)
  {
    DB.execute_update("insert into returns (from_showroom_id, to_showroom_id, items) values( " + ret.getFrom_showroom_id() + "," + ret.getTo_showroom_id() + ", '" + ret.getItems() + "' )");
  }
  
  public static ArrayList<Return> getReturns()
  {
    ArrayList<Return> returns = new ArrayList();
    String query = "select * from returns";
    ResultSet set = DB.execute_get(query);
    try
    {
      while (set.next()) {
        returns.add(new Return(set.getInt("to_showroom_id"), set.getString("items"), set.getInt("id")));
      }
    }
    catch (SQLException ex)
    {
      Logger.getLogger(Return_model.class.getName()).log(Level.SEVERE, null, ex);
    }
    return returns;
  }
  
  public static void removeReturns(int[] return_ids)
  {
    Connection conn = DB.getConnection();
    
    String sql = "DELETE FROM returns WHERE id IN (";
    for (int i = 0; i < return_ids.length; i++)
    {
      sql = sql + return_ids[i];
      if (i < return_ids.length - 1) {
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
}
