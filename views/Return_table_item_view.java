package views;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import structures.Item;

public class Return_table_item_view
{
  public static void add_item(JTable return_item_table, Item item)
  {
    DefaultTableModel model = (DefaultTableModel)return_item_table.getModel();
    model.addRow(new Object[] { Integer.valueOf(item.getItem_id()), item.getType(), item.getSize(), Float.valueOf(item.getSell_price()), Integer.valueOf(item.getTransfer_id()), item.getColor_code() });
  }
  
  public static int[] get_items_from_table(JTable return_item_table)
  {
    int[] items = null;
    items = new int[return_item_table.getRowCount()];
    DefaultTableModel model = (DefaultTableModel)return_item_table.getModel();
    for (int i = 0; i < items.length; i++) {
      items[i] = ((Integer)model.getValueAt(i, 0)).intValue();
    }
    return items;
  }
  
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
}
