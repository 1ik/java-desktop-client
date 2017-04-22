package views;

import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import models.showroom_model;
import structures.Transfer;

public class Transfer_tab_view
{
  public static boolean locked = false;
  
  public static synchronized void load_table(JTable table, ArrayList<Transfer> transfers)
  {
    DefaultTableModel tablemodel = (DefaultTableModel)table.getModel();
    tablemodel.setRowCount(0);
    for (Transfer t : transfers)
    {
      String showroom_name = showroom_model.getShowroomName(t.getFrom_showroom_id());
      tablemodel.addRow(new Object[] { Integer.valueOf(t.getContextual_transfer_id()), Integer.valueOf(t.getTotal_items()), t.created_at, showroom_name });
    }
  }
}
