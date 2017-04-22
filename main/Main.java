package main;

import config.Config;
import java.io.PrintStream;
import models.Config_model;
import views.initializing_form;

public class Main
{
  public static void main(String[] args)
  {
    
    if (Config.USERNAME != null)
    {
      System.out.println("loading login form");
      new LoginForm().setVisible(true);
    }
    else
    {
      initializing_form form = new initializing_form();
      form.setVisible(true);
    }
  }
}
