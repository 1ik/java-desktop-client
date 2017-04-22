package main;

import com.google.gson.Gson;
import config.Config;
import httpClient.HttpRequest;
import httpClient.HttpResponse;
import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import models.Config_model;
import models.Discount_model;
import models.Expense_model;
import models.Expense_type_model;
import models.Item_model;
import models.Memo_model;
import models.Return_model;
import models.Transfer_model;
import models.showroom_model;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import print.PrintObject;
import print.Printer;
import structures.ChangedItem;
import structures.Discount;
import structures.Expense;
import structures.Expense_type;
import structures.Item;
import structures.Memo;
import structures.Return;
import structures.Showroom;
import structures.Transfer;
import structures.changed_item;
import sync.Discount_sync;
import sync.Expense_sync;
import sync.Expense_type_sync;
import sync.Memo_sync;
import sync.Returns_sync;
import sync.Transfer_sync;
import views.Expense_type_view;
import views.Item_table_view;
import views.Return_table_item_view;
import views.Transfer_tab_view;
import views.initializing_form;

public class Launcher
  extends JFrame
{
  private static final int SELL_TAB_INDEX = 0;
  private static final int EXPENSE_TAB_INDEX = 1;
  private static final int ITEMS_TAB_INDEX = 2;
  private static final int RETURN_ITEMS_TAB_INDEX = 3;
  private static final int CHANGE_ITEMS_TAB_INDEX = 4;
  private static final String UPDATES_DOWNLOAD_JOB_GRUP = "updates_download";
  private initializing_form init_form;
  private JButton btn_showroom_info_update;
  private JButton button_discount_check;
  private JTextField change_barcode_field;
  private JButton change_item_remove;
  private JTable change_item_table;
  private JButton change_table_clear;
  private JLabel change_table_status;
  private JTable customer_return_item_table;
  private JTable discount_table;
  private JTextField expense_amount_field;
  private JButton expense_confirm;
  private JTextArea expense_explanation_field;
  private JTable expense_list_table;
  private JButton expense_remove_button;
  private JLabel expense_total_label;
  private JComboBox expense_type;
  private JButton expense_type_reload;
  private JScrollPane item_summery_jscroll;
  private JTable item_summery_table;
  private JTable items_table;
  private JButton jButton1;
  private JLabel jLabel1;
  private JLabel jLabel10;
  private JLabel jLabel11;
  private JLabel jLabel12;
  private JLabel jLabel13;
  private JLabel jLabel14;
  private JLabel jLabel15;
  private JLabel jLabel16;
  private JLabel jLabel17;
  private JLabel jLabel18;
  private JLabel jLabel19;
  private JLabel jLabel2;
  private JLabel jLabel3;
  private JLabel jLabel4;
  private JLabel jLabel5;
  private JLabel jLabel6;
  private JLabel jLabel7;
  private JLabel jLabel8;
  private JLabel jLabel9;
  private JPanel jPanel1;
  private JPanel jPanel10;
  private JPanel jPanel11;
  private JPanel jPanel12;
  private JPanel jPanel13;
  private JPanel jPanel14;
  private JPanel jPanel2;
  private JPanel jPanel3;
  private JPanel jPanel4;
  private JPanel jPanel5;
  private JPanel jPanel6;
  private JPanel jPanel7;
  private JPanel jPanel8;
  private JPanel jPanel9;
  private JScrollPane jScrollPane1;
  private JScrollPane jScrollPane10;
  private JScrollPane jScrollPane2;
  private JScrollPane jScrollPane3;
  private JScrollPane jScrollPane4;
  private JScrollPane jScrollPane5;
  private JScrollPane jScrollPane6;
  private JScrollPane jScrollPane7;
  private JScrollPane jScrollPane8;
  private JScrollPane jScrollPane9;
  private JTable memoTable;
  private JButton memo_row_delete;
  private JTextField return_item_barcode_field;
  private JButton return_item_table_delete_entry_button;
  private JLabel return_items_added_label;
  private JButton return_items_confirm;
  private JTable return_items_table;
  private JTextField sell_barcode_field;
  private JTextField sell_discount_field;
  private JButton sell_item_paid_button;
  private JTable sell_item_table;
  private JButton sell_item_table_clear_button;
  private JTextField sell_paid_field;
  private JLabel sell_return_amount_field;
  private JLabel sell_total_items_added;
  private JLabel sell_total_price;
  private JTextField showroom_contact;
  private JLabel showroom_label;
  private JLabel showroom_name;
  private JButton submit_expense;
  private JTabbedPane tabs;
  private JComboBox transfer_showroom_list;
  private JTable transfers_table;
  private JTextField vat_regi;
  
  public Launcher()
  {
    initComponents();
    Expense_type_view.reloadView(this.expense_type);
    Expense_sync.startsynching(15000);
    init_transfer_table_onItemSelectedHandler();
    
    init_new_return_check_job();
    init_new_memo_sync_job();
    init_transfer_showroom_list();
    
    this.showroom_label.setText(Config.SHOWROOM_NAME);
    
    DefaultTableModel model = (DefaultTableModel)this.sell_item_table.getModel();
    model.setRowCount(0);
    model = (DefaultTableModel)this.return_items_table.getModel();
    model.setRowCount(0);
    model = (DefaultTableModel)this.change_item_table.getModel();
    model.setRowCount(0);
  }
  
  private void init_transfer_table_onItemSelectedHandler()
  {
    this.transfers_table.getSelectionModel().addListSelectionListener(new ListSelectionListener()
    {
      public void valueChanged(ListSelectionEvent lse)
      {
        if (!lse.getValueIsAdjusting())
        {
          int selectedRow = Launcher.this.transfers_table.getSelectedRow();
          if (selectedRow >= 0)
          {
            int transfer_id = ((Integer)Launcher.this.transfers_table.getValueAt(selectedRow, 0)).intValue();
            Item_table_view.load_table(Launcher.this.items_table, Launcher.this.item_summery_table, Item_model.get(transfer_id));
          }
        }
      }
    });
  }
  
  private void init_new_return_check_job()
  {
    try
    {
      Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
      scheduler.start();
      
      JobDetail job = JobBuilder.newJob(Returns_sync.class).withIdentity("ReturnSync", "updates_download").build();
      
      Trigger trigger = TriggerBuilder.newTrigger().withIdentity("returnTrigger", "updates_download").startNow().withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(75).repeatForever()).build();
      
      scheduler.scheduleJob(job, trigger);
    }
    catch (SchedulerException e)
    {
      e.printStackTrace();
    }
  }
  
  private void init_new_memo_sync_job()
  {
    try
    {
      Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
      scheduler.start();
      
      JobDetail job = JobBuilder.newJob(Memo_sync.class).withIdentity("Memo_sync", "updates_download").build();
      
      Trigger trigger = TriggerBuilder.newTrigger().withIdentity("memoTrigger", "updates_download").startNow().withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(45).repeatForever()).build();
      
      scheduler.scheduleJob(job, trigger);
    }
    catch (SchedulerException e)
    {
      System.err.println("exception occured in Launcher : init_new_memo_sync_job() ");
      e.printStackTrace();
    }
  }
  
  private void init_transfer_showroom_list()
  {
    this.transfer_showroom_list.removeAllItems();
    ArrayList<Showroom> showrooms = showroom_model.get();
    for (Showroom room : showrooms) {
      if (room.getShowroom_id() != Config.SHOWROOM_ID) {
        this.transfer_showroom_list.addItem(room.getName());
      }
    }
  }
  
  private void initComponents()
  {
    this.jPanel1 = new JPanel();
    this.jLabel1 = new JLabel();
    this.showroom_label = new JLabel();
    this.tabs = new JTabbedPane();
    this.jPanel2 = new JPanel();
    this.jPanel6 = new JPanel();
    this.jPanel7 = new JPanel();
    this.jPanel8 = new JPanel();
    this.sell_total_items_added = new JLabel();
    this.jLabel6 = new JLabel();
    this.sell_total_price = new JLabel();
    this.sell_paid_field = new JTextField();
    this.sell_return_amount_field = new JLabel();
    this.jLabel13 = new JLabel();
    this.sell_item_paid_button = new JButton();
    this.jLabel14 = new JLabel();
    this.jLabel18 = new JLabel();
    this.sell_discount_field = new JTextField();
    this.jScrollPane1 = new JScrollPane();
    this.sell_item_table = new JTable();
    this.jScrollPane5 = new JScrollPane();
    this.change_item_table = new JTable();
    this.change_barcode_field = new JTextField();
    this.jLabel3 = new JLabel();
    this.jLabel16 = new JLabel();
    this.sell_item_table_clear_button = new JButton();
    this.memo_row_delete = new JButton();
    this.change_table_clear = new JButton();
    this.change_item_remove = new JButton();
    this.sell_barcode_field = new JTextField();
    this.change_table_status = new JLabel();
    this.jPanel3 = new JPanel();
    this.expense_type = new JComboBox();
    this.jLabel5 = new JLabel();
    this.expense_type_reload = new JButton();
    this.jLabel7 = new JLabel();
    this.expense_amount_field = new JTextField();
    this.jLabel8 = new JLabel();
    this.jScrollPane2 = new JScrollPane();
    this.expense_explanation_field = new JTextArea();
    this.submit_expense = new JButton();
    this.jScrollPane7 = new JScrollPane();
    this.expense_list_table = new JTable();
    this.jLabel12 = new JLabel();
    this.expense_total_label = new JLabel();
    this.expense_remove_button = new JButton();
    this.expense_confirm = new JButton();
    this.jPanel4 = new JPanel();
    this.jPanel9 = new JPanel();
    this.jScrollPane3 = new JScrollPane();
    this.transfers_table = new JTable();
    this.jScrollPane4 = new JScrollPane();
    this.items_table = new JTable();
    this.item_summery_jscroll = new JScrollPane();
    this.item_summery_table = new JTable();
    this.jButton1 = new JButton();
    this.jPanel5 = new JPanel();
    this.return_item_barcode_field = new JTextField();
    this.jLabel9 = new JLabel();
    this.jLabel10 = new JLabel();
    this.return_items_confirm = new JButton();
    this.return_items_added_label = new JLabel();
    this.jLabel11 = new JLabel();
    this.return_item_table_delete_entry_button = new JButton();
    this.jScrollPane6 = new JScrollPane();
    this.return_items_table = new JTable();
    this.transfer_showroom_list = new JComboBox();
    this.jLabel15 = new JLabel();
    this.jPanel10 = new JPanel();
    this.jPanel11 = new JPanel();
    this.jLabel2 = new JLabel();
    this.jScrollPane8 = new JScrollPane();
    this.customer_return_item_table = new JTable();
    this.jPanel12 = new JPanel();
    this.jLabel4 = new JLabel();
    this.showroom_name = new JLabel();
    this.jLabel17 = new JLabel();
    this.jLabel19 = new JLabel();
    this.btn_showroom_info_update = new JButton();
    this.vat_regi = new JTextField();
    this.showroom_contact = new JTextField();
    this.jPanel13 = new JPanel();
    this.jScrollPane9 = new JScrollPane();
    this.memoTable = new JTable();
    this.jPanel14 = new JPanel();
    this.jScrollPane10 = new JScrollPane();
    this.discount_table = new JTable();
    this.button_discount_check = new JButton();
    
    setDefaultCloseOperation(3);
    setBackground(new Color(255, 255, 255));
    
    this.jPanel1.setBackground(new Color(255, 255, 255));
    
    this.jLabel1.setFont(new Font("Tahoma", 1, 18));
    this.jLabel1.setText("IMAGINARY FASHION");
    
    this.showroom_label.setFont(new Font("Tahoma", 0, 12));
    this.showroom_label.setText("Orkid Plaza Branch");
    
    GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
    this.jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup().addContainerGap(-1, 32767).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jLabel1, GroupLayout.Alignment.TRAILING, -2, 235, -2).addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup().addComponent(this.showroom_label, -2, 134, -2).addGap(58, 58, 58))).addGap(486, 486, 486)));
    
    jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addComponent(this.jLabel1).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.showroom_label).addContainerGap(59, 32767)));
    
    this.tabs.setBackground(new Color(255, 255, 255));
    this.tabs.addChangeListener(new ChangeListener()
    {
      public void stateChanged(ChangeEvent evt)
      {
        Launcher.this.tab_state_changed(evt);
      }
    });
    this.jPanel2.setBackground(new Color(255, 255, 255));
    
    GroupLayout jPanel6Layout = new GroupLayout(this.jPanel6);
    this.jPanel6.setLayout(jPanel6Layout);
    jPanel6Layout.setHorizontalGroup(jPanel6Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 0, 32767));
    
    jPanel6Layout.setVerticalGroup(jPanel6Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 56, 32767));
    
    this.jPanel7.setBackground(new Color(255, 255, 255));
    
    this.jPanel8.setBackground(new Color(255, 255, 255));
    
    this.sell_total_items_added.setFont(new Font("Tahoma", 0, 18));
    this.sell_total_items_added.setText("0");
    
    this.jLabel6.setFont(new Font("Tahoma", 0, 18));
    this.jLabel6.setText("Total Items Added :");
    
    this.sell_total_price.setFont(new Font("Consolas", 0, 120));
    this.sell_total_price.setForeground(new Color(255, 51, 51));
    this.sell_total_price.setText("000");
    this.sell_total_price.setHorizontalTextPosition(4);
    
    this.sell_paid_field.setFont(new Font("Tahoma", 0, 36));
    this.sell_paid_field.setText("0");
    this.sell_paid_field.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent evt)
      {
        Launcher.this.sell_paid_fieldKeyPressed(evt);
      }
    });
    this.sell_return_amount_field.setFont(new Font("Tahoma", 0, 18));
    this.sell_return_amount_field.setText("000");
    
    this.jLabel13.setFont(new Font("Tahoma", 0, 18));
    this.jLabel13.setText("Paid         :");
    
    this.sell_item_paid_button.setText("Paid");
    this.sell_item_paid_button.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent evt)
      {
        Launcher.this.sell_item_paid_buttonActionPerformed(evt);
      }
    });
    this.jLabel14.setFont(new Font("Tahoma", 0, 18));
    this.jLabel14.setText("Return      :");
    
    this.jLabel18.setFont(new Font("Tahoma", 0, 18));
    this.jLabel18.setText("Discount   : ");
    
    this.sell_discount_field.setFont(new Font("Tahoma", 0, 36));
    this.sell_discount_field.setText("0");
    this.sell_discount_field.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent evt)
      {
        Launcher.this.sell_discount_fieldActionPerformed(evt);
      }
    });
    this.sell_discount_field.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent evt)
      {
        Launcher.this.sell_discount_fieldKeyPressed(evt);
      }
    });
    GroupLayout jPanel8Layout = new GroupLayout(this.jPanel8);
    this.jPanel8.setLayout(jPanel8Layout);
    jPanel8Layout.setHorizontalGroup(jPanel8Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel8Layout.createSequentialGroup().addGroup(jPanel8Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.sell_total_price, GroupLayout.Alignment.TRAILING, -1, -1, 32767).addGroup(jPanel8Layout.createSequentialGroup().addGroup(jPanel8Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel8Layout.createSequentialGroup().addGap(6, 6, 6).addComponent(this.jLabel6).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.sell_total_items_added, -2, 172, -2)).addGroup(jPanel8Layout.createSequentialGroup().addContainerGap().addGroup(jPanel8Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.sell_item_paid_button, -2, 161, -2).addGroup(jPanel8Layout.createSequentialGroup().addComponent(this.jLabel18).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.sell_discount_field, -2, 243, -2)))).addGroup(jPanel8Layout.createSequentialGroup().addContainerGap().addComponent(this.jLabel13).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.sell_paid_field, -2, 243, -2))).addGap(0, 214, 32767))).addContainerGap()).addGroup(jPanel8Layout.createSequentialGroup().addContainerGap().addComponent(this.jLabel14).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.sell_return_amount_field).addContainerGap(-1, 32767)));
    
    jPanel8Layout.setVerticalGroup(jPanel8Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel8Layout.createSequentialGroup().addGap(27, 27, 27).addGroup(jPanel8Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel6, -2, 43, -2).addComponent(this.sell_total_items_added, -2, 43, -2)).addGap(18, 18, 18).addComponent(this.sell_total_price).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel8Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.sell_paid_field, -2, 40, -2).addComponent(this.jLabel13, -2, 34, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel8Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.sell_discount_field, -2, 40, -2).addComponent(this.jLabel18, -2, 34, -2)).addGap(18, 18, 18).addGroup(jPanel8Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel14).addComponent(this.sell_return_amount_field)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 35, 32767).addComponent(this.sell_item_paid_button, -2, 32, -2).addContainerGap()));
    
    this.sell_item_table.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null, null, null }, { null, null, null, null, null, null }, { null, null, null, null, null, null }, { null, null, null, null, null, null } }, new String[] { "item_id", "item_type", "size", "item_price", "transfer_id", "color_code" })
    {
      Class[] types = { String.class, String.class, String.class, Float.class, Integer.class, String.class };
      
      public Class getColumnClass(int columnIndex)
      {
        return this.types[columnIndex];
      }
    });
    this.jScrollPane1.setViewportView(this.sell_item_table);
    
    this.change_item_table.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null, null, null, null }, { null, null, null, null, null, null, null }, { null, null, null, null, null, null, null }, { null, null, null, null, null, null, null } }, new String[] { "item_id", "item_type", "item_size", "price", "color", "sell showroom", "sell date" }));
    
    this.jScrollPane5.setViewportView(this.change_item_table);
    
    this.change_barcode_field.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent evt)
      {
        Launcher.this.change_barcode_fieldActionPerformed(evt);
      }
    });
    this.change_barcode_field.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent evt)
      {
        Launcher.this.change_barcode_button_keyPressed(evt);
      }
    });
    this.jLabel3.setText("SELL");
    
    this.jLabel16.setText("CHANGE");
    
    this.sell_item_table_clear_button.setText("clear table");
    this.sell_item_table_clear_button.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent evt)
      {
        Launcher.this.sell_item_table_clear_buttonActionPerformed(evt);
      }
    });
    this.memo_row_delete.setText("Remove");
    this.memo_row_delete.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent evt)
      {
        Launcher.this.memo_row_deleteActionPerformed(evt);
      }
    });
    this.change_table_clear.setText("clear table");
    this.change_table_clear.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent evt)
      {
        Launcher.this.change_table_clearActionPerformed(evt);
      }
    });
    this.change_item_remove.setText("Remove");
    this.change_item_remove.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent evt)
      {
        Launcher.this.change_item_removeActionPerformed(evt);
      }
    });
    this.sell_barcode_field.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent evt)
      {
        Launcher.this.sell_barcode_fieldKeyPressed(evt);
      }
    });
    GroupLayout jPanel7Layout = new GroupLayout(this.jPanel7);
    this.jPanel7.setLayout(jPanel7Layout);
    jPanel7Layout.setHorizontalGroup(jPanel7Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel7Layout.createSequentialGroup().addGap(49, 49, 49).addGroup(jPanel7Layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addGroup(jPanel7Layout.createSequentialGroup().addComponent(this.memo_row_delete, -2, 90, -2).addGap(18, 18, 18).addComponent(this.sell_item_table_clear_button)).addGroup(jPanel7Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addGroup(GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup().addComponent(this.change_table_status, -2, 273, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 102, 32767).addComponent(this.change_item_remove, -2, 90, -2).addGap(18, 18, 18).addComponent(this.change_table_clear)).addComponent(this.jScrollPane1, GroupLayout.Alignment.TRAILING).addGroup(jPanel7Layout.createSequentialGroup().addComponent(this.jLabel16, -2, 53, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767).addComponent(this.change_barcode_field, -2, 328, -2)).addComponent(this.jScrollPane5, GroupLayout.Alignment.TRAILING).addGroup(GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup().addGap(10, 10, 10).addComponent(this.jLabel3).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767).addComponent(this.sell_barcode_field, -2, 328, -2)))).addGap(87, 87, 87).addComponent(this.jPanel8, -2, -1, -2).addContainerGap(30, 32767)));
    
    jPanel7Layout.setVerticalGroup(jPanel7Layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addGroup(jPanel7Layout.createSequentialGroup().addGap(29, 29, 29).addGroup(jPanel7Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel7Layout.createSequentialGroup().addGroup(jPanel7Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.sell_barcode_field, -2, 20, -2).addComponent(this.jLabel3)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jScrollPane1, -2, 98, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel7Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.sell_item_table_clear_button).addComponent(this.memo_row_delete)).addGap(52, 52, 52).addGroup(jPanel7Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel16).addComponent(this.change_barcode_field, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jScrollPane5, -2, 105, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel7Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.change_table_clear).addComponent(this.change_item_remove).addComponent(this.change_table_status))).addComponent(this.jPanel8, -2, -1, -2))));
    
    GroupLayout jPanel2Layout = new GroupLayout(this.jPanel2);
    this.jPanel2.setLayout(jPanel2Layout);
    jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jPanel6, -1, -1, 32767).addGroup(jPanel2Layout.createSequentialGroup().addComponent(this.jPanel7, -2, -1, -2).addGap(0, 369, 32767)));
    
    jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel2Layout.createSequentialGroup().addComponent(this.jPanel6, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jPanel7, -2, -1, -2).addContainerGap(88, 32767)));
    
    this.tabs.addTab("Sell", this.jPanel2);
    
    this.jPanel3.setBackground(new Color(255, 255, 255));
    
    this.expense_type.setModel(new DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
    
    this.jLabel5.setFont(new Font("Tahoma", 0, 18));
    this.jLabel5.setText("Expense Name : ");
    
    this.expense_type_reload.setText("Refresh");
    this.expense_type_reload.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent evt)
      {
        Launcher.this.expense_type_reloadActionPerformed(evt);
      }
    });
    this.jLabel7.setFont(new Font("Tahoma", 0, 18));
    this.jLabel7.setText("Amount         :");
    
    this.jLabel8.setFont(new Font("Tahoma", 0, 18));
    this.jLabel8.setText("Explanation    :");
    
    this.expense_explanation_field.setColumns(20);
    this.expense_explanation_field.setRows(5);
    this.jScrollPane2.setViewportView(this.expense_explanation_field);
    
    this.submit_expense.setText("Submit");
    this.submit_expense.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent evt)
      {
        Launcher.this.submit_expenseActionPerformed(evt);
      }
    });
    this.expense_list_table.setModel(new DefaultTableModel(new Object[0][], new String[] { "reason", "amount", "explanation", "time" })
    {
      Class[] types = { String.class, String.class, String.class, String.class };
      
      public Class getColumnClass(int columnIndex)
      {
        return this.types[columnIndex];
      }
    });
    this.jScrollPane7.setViewportView(this.expense_list_table);
    
    this.jLabel12.setText("Total Amount : ");
    
    this.expense_total_label.setFont(new Font("Tahoma", 1, 14));
    this.expense_total_label.setText("000");
    
    this.expense_remove_button.setText("Remove");
    this.expense_remove_button.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent evt)
      {
        Launcher.this.expense_remove_buttonActionPerformed(evt);
      }
    });
    this.expense_confirm.setText("Confirm");
    this.expense_confirm.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent evt)
      {
        Launcher.this.expense_confirmActionPerformed(evt);
      }
    });
    GroupLayout jPanel3Layout = new GroupLayout(this.jPanel3);
    this.jPanel3.setLayout(jPanel3Layout);
    jPanel3Layout.setHorizontalGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel3Layout.createSequentialGroup().addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel3Layout.createSequentialGroup().addComponent(this.expense_type, -2, 156, -2).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.expense_type_reload)).addComponent(this.jLabel5).addComponent(this.jLabel7).addComponent(this.expense_amount_field, -2, 156, -2).addComponent(this.jLabel8).addComponent(this.jScrollPane2, -2, 473, -2)).addGap(28, 28, 28).addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel3Layout.createSequentialGroup().addComponent(this.expense_remove_button).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767).addComponent(this.jLabel12).addGap(26, 26, 26).addComponent(this.expense_total_label, -2, 162, -2).addGap(39, 39, 39)).addGroup(jPanel3Layout.createSequentialGroup().addComponent(this.jScrollPane7, -1, 1170, 32767).addContainerGap()).addGroup(jPanel3Layout.createSequentialGroup().addComponent(this.expense_confirm, -2, 86, -2).addContainerGap(-1, 32767)))).addGroup(jPanel3Layout.createSequentialGroup().addComponent(this.submit_expense).addGap(0, 0, 32767)));
    
    jPanel3Layout.setVerticalGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel3Layout.createSequentialGroup().addContainerGap().addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addGroup(jPanel3Layout.createSequentialGroup().addComponent(this.jLabel5).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.expense_type_reload).addComponent(this.expense_type, -2, 32, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel7).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.expense_amount_field, -2, 30, -2).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.jLabel8)).addComponent(this.jScrollPane7, -2, 186, -2)).addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel3Layout.createSequentialGroup().addGap(18, 18, 18).addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel12).addComponent(this.expense_total_label).addComponent(this.expense_remove_button)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.expense_confirm)).addGroup(jPanel3Layout.createSequentialGroup().addGap(8, 8, 8).addComponent(this.jScrollPane2, -2, 134, -2))).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.submit_expense).addContainerGap(246, 32767)));
    
    this.tabs.addTab("Expense", this.jPanel3);
    
    this.jPanel4.setBackground(new Color(255, 255, 255));
    
    this.transfers_table.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null }, { null, null, null, null }, { null, null, null, null }, { null, null, null, null } }, new String[] { "Transfer id", "items", "Created at", "From" }));
    
    this.jScrollPane3.setViewportView(this.transfers_table);
    
    this.items_table.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null, null, null }, { null, null, null, null, null, null }, { null, null, null, null, null, null }, { null, null, null, null, null, null }, { null, null, null, null, null, null }, { null, null, null, null, null, null }, { null, null, null, null, null, null }, { null, null, null, null, null, null }, { null, null, null, null, null, null }, { null, null, null, null, null, null }, { null, null, null, null, null, null }, { null, null, null, null, null, null }, { null, null, null, null, null, null }, { null, null, null, null, null, null }, { null, null, null, null, null, null }, { null, null, null, null, null, null }, { null, null, null, null, null, null }, { null, null, null, null, null, null }, { null, null, null, null, null, null }, { null, null, null, null, null, null }, { null, null, null, null, null, null }, { null, null, null, null, null, null }, { null, null, null, null, null, null }, { null, null, null, null, null, null }, { null, null, null, null, null, null }, { null, null, null, null, null, null }, { null, null, null, null, null, null }, { null, null, null, null, null, null }, { null, null, null, null, null, null }, { null, null, null, null, null, null }, { null, null, null, null, null, null }, { null, null, null, null, null, null }, { null, null, null, null, null, null }, { null, null, null, null, null, null }, { null, null, null, null, null, null }, { null, null, null, null, null, null }, { null, null, null, null, null, null }, { null, null, null, null, null, null } }, new String[] { "item_id", "type", "size", "transfer_id", "price", "color code" })
    {
      Class[] types = { Integer.class, String.class, String.class, Integer.class, Float.class, String.class };
      
      public Class getColumnClass(int columnIndex)
      {
        return this.types[columnIndex];
      }
    });
    this.jScrollPane4.setViewportView(this.items_table);
    
    this.item_summery_table.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null, null, null } }, new String[] { "Total Items", "", "", "", "Total Price", "" })
    {
      Class[] types = { Integer.class, Object.class, Object.class, Object.class, Float.class, Object.class };
      
      public Class getColumnClass(int columnIndex)
      {
        return this.types[columnIndex];
      }
    });
    this.item_summery_table.setFocusable(false);
    this.item_summery_jscroll.setViewportView(this.item_summery_table);
    
    GroupLayout jPanel9Layout = new GroupLayout(this.jPanel9);
    this.jPanel9.setLayout(jPanel9Layout);
    jPanel9Layout.setHorizontalGroup(jPanel9Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel9Layout.createSequentialGroup().addComponent(this.jScrollPane3, -1, 758, 32767).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel9Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.item_summery_jscroll, -2, 897, -2).addComponent(this.jScrollPane4, -2, 907, -2)).addContainerGap()));
    
    jPanel9Layout.setVerticalGroup(jPanel9Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel9Layout.createSequentialGroup().addGap(19, 19, 19).addComponent(this.jScrollPane4, -2, 371, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.item_summery_jscroll, -2, 42, -2).addContainerGap(-1, 32767)).addGroup(GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup().addContainerGap(-1, 32767).addComponent(this.jScrollPane3, -2, -1, -2).addContainerGap(-1, 32767)));
    
    this.jButton1.setText("Check");
    this.jButton1.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent evt)
      {
        Launcher.this.jButton1ActionPerformed(evt);
      }
    });
    GroupLayout jPanel4Layout = new GroupLayout(this.jPanel4);
    this.jPanel4.setLayout(jPanel4Layout);
    jPanel4Layout.setHorizontalGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jPanel9, -1, -1, 32767).addGroup(jPanel4Layout.createSequentialGroup().addContainerGap().addComponent(this.jButton1).addContainerGap(-1, 32767)));
    
    jPanel4Layout.setVerticalGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup().addContainerGap(120, 32767).addComponent(this.jButton1).addGap(29, 29, 29).addComponent(this.jPanel9, -2, 447, -2)));
    
    this.tabs.addTab("Items", this.jPanel4);
    
    this.jPanel5.setBackground(new Color(255, 255, 255));
    
    this.return_item_barcode_field.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent evt)
      {
        Launcher.this.return_item_barcode_fieldKeyPressed(evt);
      }
    });
    this.jLabel9.setText("showrooms");
    
    this.jLabel10.setFont(new Font("Tahoma", 1, 14));
    this.jLabel10.setText("Transfer Items to showroom");
    
    this.return_items_confirm.setText("Confirm");
    this.return_items_confirm.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent evt)
      {
        Launcher.this.return_items_confirmActionPerformed(evt);
      }
    });
    this.return_items_added_label.setText("0");
    
    this.jLabel11.setText("ITEMS ADDED : ");
    
    this.return_item_table_delete_entry_button.setText("Delete Entry");
    this.return_item_table_delete_entry_button.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent evt)
      {
        Launcher.this.return_item_table_delete_entry_buttonActionPerformed(evt);
      }
    });
    this.return_items_table.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null, null, null }, { null, null, null, null, null, null }, { null, null, null, null, null, null }, { null, null, null, null, null, null } }, new String[] { "item id", "type", "size", "price", "transfer id", "color code" })
    {
      Class[] types = { Integer.class, String.class, String.class, Float.class, Integer.class, String.class };
      
      public Class getColumnClass(int columnIndex)
      {
        return this.types[columnIndex];
      }
    });
    this.jScrollPane6.setViewportView(this.return_items_table);
    
    this.transfer_showroom_list.setModel(new DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
    
    this.jLabel15.setText("BARCODE");
    
    GroupLayout jPanel5Layout = new GroupLayout(this.jPanel5);
    this.jPanel5.setLayout(jPanel5Layout);
    jPanel5Layout.setHorizontalGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jScrollPane6).addGroup(jPanel5Layout.createSequentialGroup().addContainerGap().addGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addGroup(jPanel5Layout.createSequentialGroup().addComponent(this.jLabel11).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.return_items_added_label, -1, -1, 32767)).addGroup(jPanel5Layout.createSequentialGroup().addComponent(this.jLabel9, -2, 57, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.transfer_showroom_list, -2, 270, -2)).addGroup(jPanel5Layout.createSequentialGroup().addComponent(this.jLabel15, -2, 57, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.return_item_barcode_field, -2, 270, -2))).addGap(18, 18, 18).addGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel5Layout.createSequentialGroup().addComponent(this.return_items_confirm, -2, 123, -2).addGap(62, 62, 62).addComponent(this.jLabel10)).addComponent(this.return_item_table_delete_entry_button, -2, 139, -2)).addContainerGap(937, 32767)));
    
    jPanel5Layout.setVerticalGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel5Layout.createSequentialGroup().addContainerGap().addGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel10).addComponent(this.jLabel15, -2, 31, -2).addComponent(this.return_item_barcode_field, -2, 31, -2).addComponent(this.return_items_confirm, -2, 30, -2)).addGap(1, 1, 1).addGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel9, -2, 31, -2).addComponent(this.transfer_showroom_list, -2, -1, -2).addComponent(this.return_item_table_delete_entry_button)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel11).addComponent(this.return_items_added_label, -2, 31, -2)).addGap(31, 31, 31).addComponent(this.jScrollPane6, -1, 477, 32767)));
    
    this.tabs.addTab("Transfer Items", this.jPanel5);
    
    this.jLabel2.setBackground(new Color(255, 255, 255));
    this.jLabel2.setFont(new Font("Tahoma", 0, 14));
    this.jLabel2.setText("CHANGED ITEMS FROM CUSTOMER");
    
    this.customer_return_item_table.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null, null, null }, { null, null, null, null, null, null }, { null, null, null, null, null, null }, { null, null, null, null, null, null } }, new String[] { "item_id", "Type", "Size", "Price", "Color code", "Date" })
    {
      Class[] types = { Integer.class, String.class, String.class, Float.class, String.class, String.class };
      
      public Class getColumnClass(int columnIndex)
      {
        return this.types[columnIndex];
      }
    });
    this.jScrollPane8.setViewportView(this.customer_return_item_table);
    
    GroupLayout jPanel11Layout = new GroupLayout(this.jPanel11);
    this.jPanel11.setLayout(jPanel11Layout);
    jPanel11Layout.setHorizontalGroup(jPanel11Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel11Layout.createSequentialGroup().addContainerGap().addComponent(this.jLabel2, -2, 253, -2).addContainerGap(-1, 32767)).addComponent(this.jScrollPane8, GroupLayout.Alignment.TRAILING, -1, 1681, 32767));
    
    jPanel11Layout.setVerticalGroup(jPanel11Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel11Layout.createSequentialGroup().addContainerGap().addComponent(this.jLabel2, -2, 40, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767).addComponent(this.jScrollPane8, -2, 389, -2).addGap(186, 186, 186)));
    
    GroupLayout jPanel10Layout = new GroupLayout(this.jPanel10);
    this.jPanel10.setLayout(jPanel10Layout);
    jPanel10Layout.setHorizontalGroup(jPanel10Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jPanel11, -1, -1, 32767));
    
    jPanel10Layout.setVerticalGroup(jPanel10Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel10Layout.createSequentialGroup().addComponent(this.jPanel11, -2, 471, -2).addGap(0, 148, 32767)));
    
    this.tabs.addTab("Changed Items", this.jPanel10);
    
    this.jLabel4.setText("Showroom name : ");
    
    this.showroom_name.setText("Shwroom name  ");
    
    this.jLabel17.setText("Vat Regi No.");
    
    this.jLabel19.setText("contact");
    
    this.btn_showroom_info_update.setText("UPDATE");
    this.btn_showroom_info_update.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent evt)
      {
        Launcher.this.btn_showroom_info_updateActionPerformed(evt);
      }
    });
    GroupLayout jPanel12Layout = new GroupLayout(this.jPanel12);
    this.jPanel12.setLayout(jPanel12Layout);
    jPanel12Layout.setHorizontalGroup(jPanel12Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel12Layout.createSequentialGroup().addGap(21, 21, 21).addGroup(jPanel12Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.btn_showroom_info_update, -2, 158, -2).addGroup(jPanel12Layout.createSequentialGroup().addGroup(jPanel12Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jLabel4).addComponent(this.jLabel17).addComponent(this.jLabel19)).addGap(18, 18, 18).addGroup(jPanel12Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(this.showroom_name, -2, 296, -2).addComponent(this.vat_regi, -1, 475, 32767).addComponent(this.showroom_contact)))).addContainerGap(1078, 32767)));
    
    jPanel12Layout.setVerticalGroup(jPanel12Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel12Layout.createSequentialGroup().addGap(20, 20, 20).addGroup(jPanel12Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel4).addComponent(this.showroom_name)).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addGroup(jPanel12Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel17).addComponent(this.vat_regi, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addGroup(jPanel12Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel19).addComponent(this.showroom_contact, -2, -1, -2)).addGap(18, 18, 18).addComponent(this.btn_showroom_info_update).addContainerGap(482, 32767)));
    
    this.tabs.addTab("Showroom information", this.jPanel12);
    
    this.memoTable.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null, null, null, null }, { null, null, null, null, null, null, null }, { null, null, null, null, null, null, null }, { null, null, null, null, null, null, null } }, new String[] { "Token", "Added on", "Sold items", "Changed Items", "Discount", "Updated", "Memo Id " })
    {
      Class[] types = { String.class, String.class, String.class, String.class, Float.class, Integer.class, String.class };
      boolean[] canEdit = { false, false, false, false, false, false, false };
      
      public Class getColumnClass(int columnIndex)
      {
        return this.types[columnIndex];
      }
      
      public boolean isCellEditable(int rowIndex, int columnIndex)
      {
        return this.canEdit[columnIndex];
      }
    });
    this.jScrollPane9.setViewportView(this.memoTable);
    
    GroupLayout jPanel13Layout = new GroupLayout(this.jPanel13);
    this.jPanel13.setLayout(jPanel13Layout);
    jPanel13Layout.setHorizontalGroup(jPanel13Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel13Layout.createSequentialGroup().addComponent(this.jScrollPane9, -2, 1387, -2).addGap(0, 294, 32767)));
    
    jPanel13Layout.setVerticalGroup(jPanel13Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup().addContainerGap(48, 32767).addComponent(this.jScrollPane9, -2, 560, -2).addContainerGap()));
    
    this.tabs.addTab("Cash memos", this.jPanel13);
    
    this.discount_table.setModel(new DefaultTableModel(new Object[][] { { null, null }, { null, null }, { null, null }, { null, null } }, new String[] { "designer_style", "percentage" })
    {
      Class[] types = { String.class, String.class };
      
      public Class getColumnClass(int columnIndex)
      {
        return this.types[columnIndex];
      }
    });
    this.jScrollPane10.setViewportView(this.discount_table);
    
    this.button_discount_check.setText("check");
    this.button_discount_check.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent evt)
      {
        Launcher.this.button_discount_checkActionPerformed(evt);
      }
    });
    GroupLayout jPanel14Layout = new GroupLayout(this.jPanel14);
    this.jPanel14.setLayout(jPanel14Layout);
    jPanel14Layout.setHorizontalGroup(jPanel14Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel14Layout.createSequentialGroup().addGap(19, 19, 19).addGroup(jPanel14Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.button_discount_check).addComponent(this.jScrollPane10, -2, 365, -2)).addContainerGap(1297, 32767)));
    
    jPanel14Layout.setVerticalGroup(jPanel14Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup().addGap(0, 18, 32767).addComponent(this.button_discount_check).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.jScrollPane10, -2, 435, -2).addGap(132, 132, 132)));
    
    this.tabs.addTab("discounts", this.jPanel14);
    
    GroupLayout layout = new GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jPanel1, -1, -1, 32767).addComponent(this.tabs, GroupLayout.Alignment.TRAILING));
    
    layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(this.jPanel1, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.tabs)));
    
    pack();
  }
  
  private void tab_state_changed(ChangeEvent evt)
  {
    if (this.tabs.getSelectedIndex() == 2)
    {
      ArrayList<Transfer> transfers = Transfer_model.get();
      if (transfers.size() > 0)
      {
        Transfer_tab_view.load_table(this.transfers_table, transfers);
        DefaultTableModel model = (DefaultTableModel)this.transfers_table.getModel();
        
        int transfer_id = ((Integer)model.getValueAt(0, 0)).intValue();
        Item_table_view.load_table(this.items_table, this.item_summery_table, Item_model.get(transfer_id));
        this.transfers_table.addRowSelectionInterval(0, 0);
      }
    }
    DefaultTableModel tableModel;
    if (this.tabs.getSelectedIndex() == 4)
    {
      ArrayList<ChangedItem> changedItems = Transfer_model.get_changed_items();
      
      tableModel = (DefaultTableModel)this.customer_return_item_table.getModel();
      tableModel.setRowCount(0);
      for (ChangedItem item : changedItems) {
        tableModel.addRow(new Object[] { Integer.valueOf(item.getItem_id()), item.getType(), item.getSize(), Float.valueOf(item.getSell_price()), item.getColor_code(), item.getChangeDate() });
      }
    }
    if (this.tabs.getSelectedIndex() == 5)
    {
      ArrayList<String> list = Config_model.getShowroomInfo();
      String vat = (String)list.get(0);
      String contact = (String)list.get(1);
      this.vat_regi.setText(vat);
      this.showroom_contact.setText(contact);
    }
    DefaultTableModel model;
    if (this.tabs.getSelectedIndex() == 6)
    {
      ArrayList<Memo> memos = Memo_model.get();
      model = (DefaultTableModel)this.memoTable.getModel();
      model.setRowCount(0);
      for (Memo m : memos)
      {
        Object[] row = { m.getToken(), m.getAdded_on(), m.getItems(), m.getChanged_items(), Float.valueOf(m.getDiscount()), m.getUpdated() == 0 ? "not updated" : "updated", m.getMemo_id() };
        
        model.addRow(row);
      }
    }
    DefaultTableModel model;
    if (this.tabs.getSelectedIndex() == 7)
    {
      ArrayList<Discount> discounts = Discount_model.getDiscounts();
      model = (DefaultTableModel)this.discount_table.getModel();
      model.setRowCount(0);
      for (Discount d : discounts) {
        model.addRow(new Object[] { d.designer_style, Integer.valueOf(d.percent) });
      }
    }
  }
  
  private void return_item_table_delete_entry_buttonActionPerformed(ActionEvent evt)
  {
    int rowselected = this.return_items_table.getSelectedRow();
    if (rowselected >= 0)
    {
      DefaultTableModel model = (DefaultTableModel)this.return_items_table.getModel();
      model.removeRow(rowselected);
      this.return_items_added_label.setText(this.return_items_table.getRowCount() + "");
    }
  }
  
  private void return_items_confirmActionPerformed(ActionEvent evt)
  {
    int[] items = Return_table_item_view.get_items_from_table(this.return_items_table);
    if (items.length > 0)
    {
      String showroom_name = this.transfer_showroom_list.getSelectedItem().toString();
      int showroom_id = showroom_model.getId(showroom_name);
      
      String itemlist = "";
      for (int i = 0; i < items.length; i++)
      {
        int id = items[i];
        itemlist = itemlist + id;
      }
      System.out.println("return item list added : " + itemlist);
      Return ret = new Return(showroom_id, itemlist);
      Return_model.add_return(ret);
      Item_model.delete(items);
      
      DefaultTableModel model = (DefaultTableModel)this.return_items_table.getModel();
      model.setRowCount(0);
      this.return_item_barcode_field.setText("");
      this.return_items_added_label.setText("0");
    }
  }
  
  private void return_item_barcode_fieldKeyPressed(KeyEvent evt)
  {
    if (evt.getKeyCode() == 10)
    {
      int item_id = Integer.parseInt(this.return_item_barcode_field.getText());
      this.return_item_barcode_field.setText("");
      Item item = Item_model.get_single_item(item_id);
      if ((item != null) && (!Return_table_item_view.item_exists_in_table(this.return_items_table, item)))
      {
        Return_table_item_view.add_item(this.return_items_table, item);
        this.return_items_added_label.setText(this.return_items_table.getRowCount() + "");
      }
    }
  }
  
  private void jButton1ActionPerformed(ActionEvent evt) {}
  
  private void expense_confirmActionPerformed(ActionEvent evt)
  {
    ArrayList<Expense> expenses = new ArrayList();
    ArrayList<Expense_type> expense_types = Expense_type_model.get_expense_types();
    
    DefaultTableModel model = (DefaultTableModel)this.expense_list_table.getModel();
    int rowCount = model.getRowCount();
    for (int i = 0; i < rowCount; i++)
    {
      int typeid = getTypeID(expense_types, model.getValueAt(i, 0).toString());
      String amoutnString = model.getValueAt(i, 1).toString();
      float amount = Float.parseFloat(amoutnString);
      String explanation = model.getValueAt(i, 2).toString();
      String dateTime = model.getValueAt(i, 3).toString();
      Expense expense = new Expense(typeid, amount, explanation, dateTime);
      expenses.add(expense);
    }
    Expense_model.add_expenses(expenses);
    model.setRowCount(0);
    this.expense_total_label.setText("00");
    this.expense_amount_field.setText("");
    this.expense_explanation_field.setText("");
  }
  
  private void expense_remove_buttonActionPerformed(ActionEvent evt)
  {
    int rowselected = this.expense_list_table.getSelectedRow();
    if (rowselected >= 0)
    {
      DefaultTableModel model = (DefaultTableModel)this.expense_list_table.getModel();
      
      Double rowAmount = Double.valueOf(Double.parseDouble(model.getValueAt(rowselected, 1).toString()));
      model.removeRow(rowselected);
      
      Double amount = Double.valueOf(Double.parseDouble(this.expense_total_label.getText()) - rowAmount.doubleValue());
      this.expense_total_label.setText(amount + "");
    }
  }
  
  private void submit_expenseActionPerformed(ActionEvent evt)
  {
    DefaultTableModel model = (DefaultTableModel)this.expense_list_table.getModel();
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    model.addRow(new Object[] { this.expense_type.getSelectedItem().toString(), this.expense_amount_field.getText(), this.expense_explanation_field.getText(), format.format(new Date()) });
    
    Double d = Double.valueOf(Double.parseDouble(this.expense_total_label.getText()) + Double.parseDouble(this.expense_amount_field.getText()));
    this.expense_total_label.setText(d + "");
    this.expense_amount_field.setText("");
    this.expense_explanation_field.setText("");
  }
  
  private void expense_type_reloadActionPerformed(ActionEvent evt)
  {
    Expense_type[] expense_types = Expense_type_sync.DownloadExpense_types();
    Expense_type_model.insert(expense_types);
    Expense_type_view.reloadView(this.expense_type);
  }
  
  private void sell_barcode_fieldKeyPressed(KeyEvent evt)
  {
    if (evt.getKeyCode() == 10)
    {
      String input = this.sell_barcode_field.getText();
      int id = 0;
      try
      {
        id = Integer.parseInt(input);
      }
      catch (Exception e)
      {
        return;
      }
      this.return_item_barcode_field.setText("");
      
      DefaultTableModel model = (DefaultTableModel)this.sell_item_table.getModel();
      for (int i = 0; i < model.getRowCount(); i++)
      {
        int item_id = ((Integer)model.getValueAt(i, 0)).intValue();
        if (item_id == id)
        {
          this.sell_barcode_field.setText("");
          return;
        }
      }
      Item item = Item_model.get_single_item(id);
      if (item != null)
      {
        Return_table_item_view.add_item(this.sell_item_table, item);
        this.sell_total_items_added.setText(this.return_items_table.getRowCount() + "");
        recalculate_sell_priece();
      }
      this.sell_barcode_field.setText("");
    }
  }
  
  private void change_item_removeActionPerformed(ActionEvent evt)
  {
    int selectedRow = this.change_item_table.getSelectedRow();
    if (selectedRow >= 0)
    {
      DefaultTableModel tableModel = (DefaultTableModel)this.change_item_table.getModel();
      tableModel.removeRow(selectedRow);
      recalculate_sell_priece();
    }
  }
  
  private void change_table_clearActionPerformed(ActionEvent evt)
  {
    DefaultTableModel model = (DefaultTableModel)this.change_item_table.getModel();
    model.setRowCount(0);
    recalculate_sell_priece();
  }
  
  private void memo_row_deleteActionPerformed(ActionEvent evt)
  {
    int selectedRow = this.sell_item_table.getSelectedRow();
    if (selectedRow >= 0)
    {
      DefaultTableModel tableModel = (DefaultTableModel)this.sell_item_table.getModel();
      
      tableModel.removeRow(selectedRow);
      
      recalculate_sell_priece();
    }
  }
  
  private void sell_item_table_clear_buttonActionPerformed(ActionEvent evt)
  {
    DefaultTableModel model = (DefaultTableModel)this.sell_item_table.getModel();
    model.setRowCount(0);
    
    this.sell_total_items_added.setText("00");
    this.sell_total_price.setText("000");
    this.sell_barcode_field.setText("");
    this.sell_discount_field.setText("0.0");
    this.sell_return_amount_field.setText("00");
    this.sell_paid_field.setText("");
  }
  
  private void change_barcode_button_keyPressed(KeyEvent evt)
  {
    if (evt.getKeyCode() == 10)
    {
      String input = this.change_barcode_field.getText();
      int id = 0;
      try
      {
        id = Integer.parseInt(input);
      }
      catch (Exception e)
      {
        return;
      }
      DefaultTableModel tableModel = (DefaultTableModel)this.change_item_table.getModel();
      for (int i = 0; i < tableModel.getRowCount(); i++)
      {
        int item_id = ((Integer)tableModel.getValueAt(i, 0)).intValue();
        if (item_id == id)
        {
          this.change_barcode_field.setText("");
          return;
        }
      }
      this.change_barcode_field.setText("");
      try
      {
        this.change_table_status.setText("checking..");
        HttpRequest req = new HttpRequest(new URL(Config.BASE_URL + "api/check_sold_item/" + id));
        HttpResponse resp = req.get();
        String json_response = resp.getReponse();
        if (json_response.length() <= 0)
        {
          this.change_table_status.setText("Item not found.");
          System.err.println("Change item not found");
        }
        else
        {
          this.change_table_status.setText("Item found.");
          System.out.println(json_response);
          changed_item item = (changed_item)new Gson().fromJson(json_response, changed_item.class);
          
          DefaultTableModel model = (DefaultTableModel)this.change_item_table.getModel();
          model.addRow(new Object[] { Integer.valueOf(item.getItem_id()), item.getItem_type(), item.getItem_size(), item.getPrice(), item.getColor(), item.getSell_showroom(), item.getSell_date() });
          
          recalculate_sell_priece();
        }
      }
      catch (IOException ex)
      {
        Logger.getLogger(Launcher.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }
  
  private void change_barcode_fieldActionPerformed(ActionEvent evt) {}
  
  private void sell_discount_fieldKeyPressed(KeyEvent evt)
  {
    if (evt.getKeyCode() == 10) {
      sell_paid_fieldKeyPressed(evt);
    }
  }
  
  private void sell_item_paid_buttonActionPerformed(ActionEvent evt)
  {
    DefaultTableModel model = (DefaultTableModel)this.sell_item_table.getModel();
    int rows = model.getRowCount();
    int[] items = new int[rows];
    String itms = "";
    float total_discount = 0.0F;
    for (int i = 0; i < model.getRowCount(); i++)
    {
      items[i] = ((Integer)model.getValueAt(i, 0)).intValue();
      itms = itms + items[i];
      total_discount = (float)(total_discount + Discount_model.getDiscountAmount(items[i]));
      if (i < model.getRowCount() - 1) {
        itms = itms + ",";
      }
    }
    DefaultTableModel change_table_model = (DefaultTableModel)this.change_item_table.getModel();
    int change_table_rows = change_table_model.getRowCount();
    String change_items = "";
    for (int i = 0; i < change_table_rows; i++) {
      change_items = change_items + (i < change_table_rows - 1 ? change_table_model.getValueAt(i, 0) + "," : change_table_model.getValueAt(i, 0));
    }
    Date d = new Date();
    SimpleDateFormat format = new SimpleDateFormat("yyMMddhhmmss");
    
    System.out.println("adding memo...");
    
    String receipt = Config.SHOWROOM_ID + format.format(d);
    Memo_model.add_memo(itms, change_items, total_discount, receipt);
    PrintObject object = getPrintObject(receipt, total_discount);
    try
    {
      Printer.print(object);
    }
    catch (Exception e) {}
    System.out.println("removing items from item table...");
    Item_model.delete(items);
    
    System.out.println("clearing purchase table views. .. ");
    model.setRowCount(0);
    change_table_model.setRowCount(0);
    this.change_barcode_field.setText("");
    this.sell_return_amount_field.setText("");
    this.sell_paid_field.setText("");
    this.sell_total_price.setText("000");
    this.sell_total_items_added.setText("0");
    this.sell_discount_field.setText("");
  }
  
  private PrintObject getPrintObject(String receipt, float discount)
  {
    DefaultTableModel sell_item_table_model = (DefaultTableModel)this.sell_item_table.getModel();
    Item[] sell_items = new Item[sell_item_table_model.getRowCount()];
    for (int i = 0; i < sell_item_table_model.getRowCount(); i++)
    {
      int itemid = ((Integer)sell_item_table_model.getValueAt(i, 0)).intValue();
      String itemType = (String)sell_item_table_model.getValueAt(i, 1);
      String size = (String)sell_item_table_model.getValueAt(i, 2);
      float price = ((Float)sell_item_table_model.getValueAt(i, 3)).floatValue();
      int transfer_id = ((Integer)sell_item_table_model.getValueAt(i, 4)).intValue();
      String color = (String)sell_item_table_model.getValueAt(i, 5);
      sell_items[i] = new Item(itemid, itemType, size, price, color, transfer_id);
    }
    DefaultTableModel change_item_table_model = (DefaultTableModel)this.change_item_table.getModel();
    Item[] changed_items = new Item[change_item_table_model.getRowCount()];
    for (int j = 0; j < changed_items.length; j++)
    {
      int itemid = ((Integer)change_item_table_model.getValueAt(j, 0)).intValue();
      String type = (String)change_item_table_model.getValueAt(j, 1);
      String size = (String)change_item_table_model.getValueAt(j, 2);
      float price = Float.parseFloat((String)change_item_table_model.getValueAt(j, 3));
      String color = (String)change_item_table_model.getValueAt(j, 4);
      changed_items[j] = new Item(itemid, type, size, price, color, 4);
    }
    PrintObject obj = new PrintObject();
    obj.setCompanyName("Jhinuk Fashion");
    obj.setShowroomName(Config.SHOWROOM_NAME);
    obj.setShowroomAddress(showroom_model.getShowroomLocation());
    obj.setVatReg(showroom_model.getVatRegiNo());
    obj.setReceiptNumber(receipt);
    obj.setItems(sell_items);
    obj.setReturn_items(changed_items);
    obj.setStaff_name(Config.USERNAME);
    obj.setDiscount(discount);
    
    ArrayList<String> list = Config_model.getShowroomInfo();
    obj.setVatReg((String)list.get(0));
    obj.setMobilePhone((String)list.get(1));
    
    float paid = 0.0F;
    try
    {
      paid = Float.parseFloat(this.sell_paid_field.getText());
    }
    catch (Exception e) {}
    if (paid == 0.0F) {
      try
      {
        paid = Float.parseFloat(this.sell_total_price.getText());
      }
      catch (Exception e) {}
    }
    obj.setCustomerPaid(paid);
    
    Date d = new Date();
    SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd 'at' h:mm a");
    obj.setSell_time(format.format(d));
    
    obj.setComplementaryText("Thank you very much for shopping to Jhinuk Fashion. Hope to see you soon.");
    obj.setWeb("visit us at www.jhinukfashion.com");
    obj.setEmail("email us at info@jhinukfashion.com");
    return obj;
  }
  
  private void sell_paid_fieldKeyPressed(KeyEvent evt)
  {
    if (evt.getKeyCode() == 10)
    {
      String total_string = this.sell_total_price.getText();
      float total = 0.0F;
      if (total_string.length() > 0) {
        try
        {
          total = Float.parseFloat(total_string);
        }
        catch (Exception e) {}
      }
      String discount_string = this.sell_discount_field.getText();
      float discount = 0.0F;
      if (discount_string.length() > 0) {
        try
        {
          discount = Float.parseFloat(discount_string);
        }
        catch (Exception e) {}
      }
      String paid_string = this.sell_paid_field.getText();
      float paid = 0.0F;
      if (paid_string.length() > 0) {
        try
        {
          paid = Float.parseFloat(paid_string);
        }
        catch (Exception e) {}
      }
      float ret = Math.abs(paid - (total - discount));
      this.sell_return_amount_field.setText(ret + "");
    }
  }
  
  private void sell_discount_fieldActionPerformed(ActionEvent evt) {}
  
  private void btn_showroom_info_updateActionPerformed(ActionEvent evt)
  {
    String vat_registration = this.vat_regi.getText();
    String mobileNumber = this.showroom_contact.getText();
    Config_model.updateShowroomInfo(vat_registration, mobileNumber);
  }
  
  private void button_discount_checkActionPerformed(ActionEvent evt) {}
  
  private int getTypeID(ArrayList<Expense_type> expenseTypes, String reasonname)
  {
    int typeid = -1;
    for (Expense_type type : expenseTypes) {
      if (type.getReason().equals(reasonname)) {
        typeid = type.getId();
      }
    }
    return typeid;
  }
  
  public void recalculate_sell_priece()
  {
    DefaultTableModel model = (DefaultTableModel)this.sell_item_table.getModel();
    int rowCount = model.getRowCount();
    
    float sell_price = 0.0F;
    for (int i = 0; i < rowCount; i++)
    {
      int itemId = ((Integer)model.getValueAt(i, 0)).intValue();
      String d = model.getValueAt(i, 3) + "";
      sell_price += Float.parseFloat(d);
      double discountAmount = Discount_model.getDiscountAmount(itemId);
      sell_price = (float)(sell_price - discountAmount);
    }
    DefaultTableModel change_model = (DefaultTableModel)this.change_item_table.getModel();
    int ChangerowCount = change_model.getRowCount();
    
    float change_price = 0.0F;
    for (int i = 0; i < ChangerowCount; i++)
    {
      String d = change_model.getValueAt(i, 3) + "";
      System.out.println(d);
      change_price += Float.parseFloat(d);
    }
    float price = sell_price - change_price;
    this.sell_total_items_added.setText(rowCount + ChangerowCount + "");
    this.sell_total_price.setText(price + "");
  }
  
  public static void main(String[] args)
  {
    try
    {
      for (UIManager.LookAndFeelInfo info : ) {
        if ("Nimbus".equals(info.getName()))
        {
          UIManager.setLookAndFeel(info.getClassName());
          break;
        }
      }
    }
    catch (ClassNotFoundException ex)
    {
      Logger.getLogger(Launcher.class.getName()).log(Level.SEVERE, null, ex);
    }
    catch (InstantiationException ex)
    {
      Logger.getLogger(Launcher.class.getName()).log(Level.SEVERE, null, ex);
    }
    catch (IllegalAccessException ex)
    {
      Logger.getLogger(Launcher.class.getName()).log(Level.SEVERE, null, ex);
    }
    catch (UnsupportedLookAndFeelException ex)
    {
      Logger.getLogger(Launcher.class.getName()).log(Level.SEVERE, null, ex);
    }
    EventQueue.invokeLater(new Runnable()
    {
      public void run()
      {
        new Launcher().setVisible(true);
      }
    });
  }
}
