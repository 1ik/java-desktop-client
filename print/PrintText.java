package print;

import javax.swing.JTextPane;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;

public class PrintText
  extends JTextPane
{
  public PrintText()
  {
    setContentType("text/html");
    HTMLEditorKit kit = new HTMLEditorKit();
    StyleSheet styleSheet = kit.getStyleSheet();
    
    styleSheet.addRule("body {font-family : Arial}  .smallfont{ margin-left : 4px; font-size: 5px; font-weight : 300;} .center{text-align : center} .receiptno{font-size : 5px; margin-top : -10px; text-align : center}");
    
    styleSheet.addRule(" .company_name {text-align: center; font-weight : 400; font-size : 15px}");
    styleSheet.addRule(".showroom_name {text-align: center; font-weight: 200; font-size: 10px}");
    styleSheet.addRule(".showroom_address {text-align: center; font-weight: 100; font-size: 6px}");
    
    styleSheet.addRule(" table { margin-top : 0px} .firstrow{ border-bottom : 1px dotted black; } td {font-weight: 300; font-size: 5px;} .table_label{ font-size : 7px; text-align : center; border-bottom : 1px dashed black;}");
    
    styleSheet.addRule(".lastrow{ border-top : 1px dotted black }");
    styleSheet.addRule(".salesman {margin-left : 4px; font-size: 5px; font-weight : 300; margin-top : -10px}");
    styleSheet.addRule(".compliment {margin-left : 4px; font-size: 5px; font-weight : 300; margin-top : -3px}");
    
    Document doc = kit.createDefaultDocument();
    setDocument(doc);
  }
}
