package views;

import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import models.Expense_type_model;
import structures.Expense_type;

public class Expense_type_view
{
  public static void reloadView(JComboBox b)
  {
    ArrayList<Expense_type> types = Expense_type_model.get_expense_types();
    DefaultComboBoxModel model = (DefaultComboBoxModel)b.getModel();
    model.removeAllElements();
    for (Expense_type type : types) {
      model.addElement(type.getReason());
    }
  }
}
