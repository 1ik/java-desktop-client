package test;

import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class postest
{
  public static void main(String[] args)
    throws Exception
  {
    JTextPane textPane = new JTextPane();
    
    SimpleAttributeSet sas = new SimpleAttributeSet();
    
    StyleConstants.setItalic(sas, true);
    textPane.getStyledDocument().setCharacterAttributes(4, 6, sas, false);
    textPane.setText("The nigga's gonna rock the whole night...");
    textPane.print();
  }
}
