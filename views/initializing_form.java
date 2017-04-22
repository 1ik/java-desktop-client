package views;

import config.Config;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;
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
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import main.LoginForm;
import models.Config_model;
import models.showroom_model;
import structures.Showroom;
import sync.Showroom_sync;

public class initializing_form
  extends JFrame
{
  Showroom[] showrooms;
  private JPasswordField init_password;
  private JTextField init_username;
  private JButton jButton1;
  private JLabel jLabel1;
  private JLabel jLabel2;
  private JLabel jLabel3;
  private JLabel jLabel4;
  private JPanel jPanel1;
  private JComboBox showroom_select_comboBox;
  
  public initializing_form()
  {
    initComponents();
    
    this.showrooms = Showroom_sync.get_showrooms();
    this.showroom_select_comboBox.removeAllItems();
    if ((this.showrooms != null) && (this.showrooms.length > 0)) {
      for (Showroom room : this.showrooms) {
        this.showroom_select_comboBox.addItem(room.getName());
      }
    }
  }
  
  private void initComponents()
  {
    this.jPanel1 = new JPanel();
    this.jLabel1 = new JLabel();
    this.showroom_select_comboBox = new JComboBox();
    this.jLabel2 = new JLabel();
    this.jLabel3 = new JLabel();
    this.init_username = new JTextField();
    this.jLabel4 = new JLabel();
    this.jButton1 = new JButton();
    this.init_password = new JPasswordField();
    
    setDefaultCloseOperation(3);
    
    this.jPanel1.setBackground(new Color(255, 255, 255));
    
    this.jLabel1.setText("Initialize the Application");
    
    this.showroom_select_comboBox.setModel(new DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
    
    this.jLabel2.setText("Select Showroom Name");
    
    this.jLabel3.setText("Choose Username :");
    
    this.jLabel4.setText("Choose Password :");
    
    this.jButton1.setText("Initialize");
    this.jButton1.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent evt)
      {
        initializing_form.this.jButton1ActionPerformed(evt);
      }
    });
    GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
    this.jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addGap(117, 117, 117).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jButton1).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(this.jLabel4, -2, 125, -2).addComponent(this.jLabel3, -2, 125, -2).addComponent(this.init_username, -2, 176, -2).addComponent(this.jLabel1, -1, 225, 32767).addComponent(this.jLabel2, -1, -1, 32767).addComponent(this.showroom_select_comboBox, -2, 167, -2)).addComponent(this.init_password, -2, 176, -2)).addContainerGap(38, 32767)));
    
    jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addComponent(this.jLabel1).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.jLabel2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.showroom_select_comboBox, -2, 29, -2).addGap(18, 18, 18).addComponent(this.jLabel3).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.init_username, -2, 25, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel4).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.init_password, -2, 26, -2).addGap(18, 18, 18).addComponent(this.jButton1).addContainerGap(32, 32767)));
    
    GroupLayout layout = new GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jPanel1, -1, -1, 32767).addContainerGap()));
    
    layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addContainerGap().addComponent(this.jPanel1, -1, -1, 32767).addContainerGap()));
    
    pack();
  }
  
  private void jButton1ActionPerformed(ActionEvent evt)
  {
    String showroomname = (String)this.showroom_select_comboBox.getSelectedItem();
    int showroom_id = -1;
    for (Showroom sh : this.showrooms) {
      if (sh.getName().equals(showroomname)) {
        showroom_id = sh.getShowroom_id();
      }
    }
    String username = this.init_username.getText();
    String password = this.init_password.getText();
    System.out.println(showroomname);
    System.out.println(showroom_id);
    System.out.println(username);
    System.out.println(password);
    
    Config.initialize_app(showroomname, showroom_id, username, password);
    setVisible(false);
    Config_model.initialize();
    new LoginForm().setVisible(true);
    
    showroom_model.insert(this.showrooms);
  }
}
