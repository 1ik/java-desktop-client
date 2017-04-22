package main;

import config.Config;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

public class LoginForm
  extends JFrame
{
  private JLabel jLabel1;
  private JLabel jLabel2;
  private JPanel jPanel1;
  private JButton login_button;
  private JLabel login_info_label;
  private JPasswordField login_password;
  private JLabel login_showroom_label;
  private JTextField login_username;
  
  public LoginForm()
  {
    initComponents();
    this.login_showroom_label.setText(Config.SHOWROOM_NAME);
  }
  
  private void initComponents()
  {
    this.jPanel1 = new JPanel();
    this.login_showroom_label = new JLabel();
    this.jLabel1 = new JLabel();
    this.login_username = new JTextField();
    this.jLabel2 = new JLabel();
    this.login_password = new JPasswordField();
    this.login_button = new JButton();
    this.login_info_label = new JLabel();
    
    setDefaultCloseOperation(3);
    
    this.jPanel1.setBackground(new Color(255, 255, 255));
    
    this.jLabel1.setText("Usenrame : ");
    
    this.jLabel2.setText("Password : ");
    
    this.login_button.setText("Login");
    this.login_button.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent evt)
      {
        LoginForm.this.login_buttonActionPerformed(evt);
      }
    });
    GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
    this.jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addGap(95, 95, 95).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.login_button).addComponent(this.login_showroom_label, -2, 194, -2).addComponent(this.jLabel1, -2, 127, -2).addComponent(this.login_username, -2, 236, -2).addComponent(this.jLabel2, -2, 75, -2).addComponent(this.login_password, -2, 194, -2).addComponent(this.login_info_label, -2, 210, -2)).addContainerGap(69, 32767)));
    
    jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addGap(23, 23, 23).addComponent(this.login_showroom_label).addGap(50, 50, 50).addComponent(this.jLabel1).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.login_username, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.login_password, -2, -1, -2).addGap(18, 18, 18).addComponent(this.login_button).addGap(18, 18, 18).addComponent(this.login_info_label).addContainerGap(82, 32767)));
    
    GroupLayout layout = new GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jPanel1, GroupLayout.Alignment.TRAILING, -1, -1, 32767));
    
    layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jPanel1, GroupLayout.Alignment.TRAILING, -1, -1, 32767));
    
    pack();
  }
  
  private void login_buttonActionPerformed(ActionEvent evt)
  {
    System.out.println(Config.SHOWROOM_NAME);
    
    String username = this.login_username.getText();
    String password = this.login_password.getText();
    if ((username.equals(Config.USERNAME)) && (password.equals(Config.PASSWORD)))
    {
      new Launcher().setVisible(true);
      setVisible(false);
    }
    else
    {
      this.login_info_label.setText("The username and password doesnt match");
    }
  }
}
