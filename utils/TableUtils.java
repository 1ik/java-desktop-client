package utils;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import structures.Item;

public class TableUtils
{
  public static boolean item_exists_in_table(JTable return_item_table, Item item)
  {
    DefaultTableModel model = (DefaultTableModel)return_item_table.getModel();
    for (int i = 0; i < model.getRowCount(); i++)
    {
      int itemId = ((Integer)model.getValueAt(i, 0)).intValue();
      if (itemId == item.getItem_id()) {
        return true;
      }
    }
    return false;
  }
  
  public static boolean item_exists_in_table(JTable return_item_table, int item_id)
  {
    DefaultTableModel model = (DefaultTableModel)return_item_table.getModel();
    for (int i = 0; i < model.getRowCount(); i++)
    {
      int itemId = ((Integer)model.getValueAt(i, 0)).intValue();
      if (itemId == item_id) {
        return true;
      }
    }
    return false;
  }
}
