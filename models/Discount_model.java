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
import structures.Discount;
import structures.Item;

public class Discount_model
{
  public static ArrayList<Discount> getDiscounts()
  {
    ArrayList<Discount> discountList = new ArrayList();
    ResultSet results = DB.execute_get("select * from discounts");
    try
    {
      while (results.next()) {
        discountList.add(new Discount(results.getInt("percent"), results.getString("designer_style")));
      }
    }
    catch (SQLException ex)
    {
      Logger.getLogger(Expense_model.class.getName()).log(Level.SEVERE, null, ex);
    }
    return discountList;
  }
  
  public static void add_discounts(Discount[] discounts)
  {
    DB.execute_update("DELETE FROM discounts");
    
    Connection c = DB.getConnection();
    try
    {
      c.setAutoCommit(false);
      PreparedStatement ps = c.prepareStatement("");
      
      String insertTableSQL = "INSERT INTO discounts(designer_style , percent) VALUES(?,?)";
      
      PreparedStatement preparedStatement = DB.getConnection().prepareStatement(insertTableSQL);
      for (Discount d : discounts)
      {
        preparedStatement.setString(1, d.designer_style);
        preparedStatement.setInt(2, d.percent);
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
  
  public static Discount getDiscountByDesignerStyle(String designer_style)
  {
    String sql = "select * from discounts where designer_style = '" + designer_style + "'";
    ResultSet set = DB.execute_get(sql);
    Discount discount = null;
    try
    {
      while (set.next()) {
        discount = new Discount(set.getInt("percent"), set.getString("designer_style"));
      }
    }
    catch (SQLException ex)
    {
      Logger.getLogger(Item_model.class.getName()).log(Level.SEVERE, null, ex);
    }
    return discount;
  }
  
  public static double getDiscountAmount(int itemid)
  {
    String designer_style = Item_model.getDesignerStyle(itemid);
    
    Discount discount = getDiscountByDesignerStyle(designer_style);
    System.out.println(discount);
    if (discount == null) {
      return 0.0D;
    }
    Item item = Item_model.get_single_item(itemid);
    
    double percent = discount.percent;
    double price = item.getSell_price();
    
    return price * (percent / 100.0D);
  }
  
  public static void main(String[] args)
  {
    System.out.println(getDiscountAmount(36678));
  }
}
