package print;

import java.awt.print.PrinterException;

public class Printer
{
  public static void main(String[] args)
    throws Exception
  {}
  
  public static void print(PrintObject obj)
    throws PrinterException
  {
    PrintText pane = new PrintText();
    pane.setText(obj.getPrintedText());
    pane.print();
  }
}
