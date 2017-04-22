package print;

import java.util.Date;
import structures.Item;

public class PrintObject
{
  public String CompanyName;
  public String vatReg;
  public String showroomName;
  public String showroomAddress;
  public String receiptNumber;
  public String mobilePhone;
  public String memoID;
  public Item[] items;
  public Item[] return_items;
  public Date sellDate;
  public double customerPaid;
  public double customerGets;
  public String complementaryText;
  public float discount;
  public String staff_name;
  public String sell_time;
  public String web;
  public String email;
  
  public PrintObject() {}
  
  public PrintObject(String CompanyName, String showroomName, String showroomAddress, String receiptNumber, String mobilePhone, String memoID, Item[] items, Item[] return_items, Date sellDate, double customerPaid, double customerGets, String complementaryText, float discount, String staff_name, String sell_time, String web, String email)
  {
    this.CompanyName = CompanyName;
    this.showroomName = showroomName;
    this.showroomAddress = showroomAddress;
    this.receiptNumber = receiptNumber;
    this.mobilePhone = mobilePhone;
    this.memoID = memoID;
    this.items = items;
    this.return_items = return_items;
    this.sellDate = sellDate;
    this.customerPaid = customerPaid;
    this.customerGets = customerGets;
    this.complementaryText = complementaryText;
    this.discount = discount;
    this.staff_name = staff_name;
    this.sell_time = sell_time;
    this.web = web;
    this.email = email;
  }
  
  public void setCompanyName(String CompanyName)
  {
    this.CompanyName = CompanyName;
  }
  
  public void setShowroomName(String showroomName)
  {
    this.showroomName = showroomName;
  }
  
  public void setShowroomAddress(String showroomAddress)
  {
    this.showroomAddress = showroomAddress;
  }
  
  public void setReceiptNumber(String receiptNumber)
  {
    this.receiptNumber = receiptNumber;
  }
  
  public void setMobilePhone(String mobilePhone)
  {
    this.mobilePhone = mobilePhone;
  }
  
  public void setMemoID(String memoID)
  {
    this.memoID = memoID;
  }
  
  public void setItems(Item[] items)
  {
    this.items = items;
  }
  
  public void setReturn_items(Item[] return_items)
  {
    this.return_items = return_items;
  }
  
  public void setSellDate(Date sellDate)
  {
    this.sellDate = sellDate;
  }
  
  public void setCustomerPaid(double customerPaid)
  {
    this.customerPaid = customerPaid;
  }
  
  public void setCustomerGets(double customerGets)
  {
    this.customerGets = customerGets;
  }
  
  public void setComplementaryText(String complementaryText)
  {
    this.complementaryText = complementaryText;
  }
  
  public void setDiscount(float discount)
  {
    this.discount = discount;
  }
  
  public void setStaff_name(String staff_name)
  {
    this.staff_name = staff_name;
  }
  
  public void setSell_time(String sell_time)
  {
    this.sell_time = sell_time;
  }
  
  public void setWeb(String web)
  {
    this.web = web;
  }
  
  public void setEmail(String email)
  {
    this.email = email;
  }
  
  public void setVatReg(String vatReg)
  {
    this.vatReg = vatReg;
  }
  
  public String getText()
  {
    String text = "<p class=\"company_name\">Jhinuk Fashion</p>";
    text = text + "<p class=\"showroom_name\"> Orchid Plaza</p>";
    text = text + "<p class=\"showroom_address\">Dhanmondi 27, Mirpur Road, Dhaka.</p>";
    text = text + "<p class=\"showroom_address\">Contact : 01</p>";
    text = text + "<p class=\"receiptno\">RECEIPT NO. 140201122404</p>";
    
    text = text + "<p class=\"table_label\">ITEMS PURCHASED</p>";
    text = text + "<table>";
    
    text = text + "<tr class=\"firstrow\">";
    text = text + "<td>ID<td>";
    text = text + "<td>Item<td>";
    text = text + "<td>Size<td>";
    text = text + "<td>Color<td>";
    text = text + "<td>Price<td>";
    text = text + "</tr>";
    
    text = text + "<tr>";
    text = text + "<td>1234556860<td>";
    text = text + "<td>FROCK<td>";
    text = text + "<td>32<td>";
    text = text + "<td>AA<td>";
    text = text + "<td>950TK<td>";
    text = text + "</tr>";
    
    text = text + "<tr>";
    text = text + "<td>1234556860<td>";
    text = text + "<td>FROCK<td>";
    text = text + "<td>32<td>";
    text = text + "<td>AA<td>";
    text = text + "<td>950TK<td>";
    
    text = text + "</tr>";
    text = text + "<tr>";
    text = text + "<td>1234556860<td>";
    text = text + "<td>FROCK<td>";
    text = text + "<td>32<td>";
    text = text + "<td>AA<td>";
    text = text + "<td>950TK<td>";
    text = text + "</tr>";
    
    text = text + "<tr  class=\"lastrow\">";
    text = text + "<td>Total<td>";
    text = text + "<td><td>";
    text = text + "<td><td>";
    text = text + "<td><td>";
    text = text + "<td>7460TK<td>";
    text = text + "</tr>";
    
    text = text + "</table>";
    
    text = text + "<p class=\"table_label\">ITEMS Returned</p>";
    text = text + "<table>";
    text = text + "<tr class=\"firstrow\">";
    text = text + "<td>ID<td>";
    text = text + "<td>Item<td>";
    text = text + "<td>Size<td>";
    text = text + "<td>Color<td>";
    text = text + "<td>Price<td>";
    text = text + "</tr>";
    
    text = text + "<tr>";
    text = text + "<td>1234556860<td>";
    text = text + "<td>FROCK<td>";
    text = text + "<td>32<td>";
    text = text + "<td>AA<td>";
    text = text + "<td>950TK<td>";
    
    text = text + "</tr>";
    text = text + "<tr>";
    text = text + "<td>1234556860<td>";
    text = text + "<td>FROCK<td>";
    text = text + "<td>32<td>";
    text = text + "<td>AA<td>";
    text = text + "<td>950TK<td>";
    
    text = text + "<tr  class=\"lastrow\">";
    text = text + "<td>Total<td>";
    text = text + "<td><td>";
    text = text + "<td><td>";
    text = text + "<td><td>";
    text = text + "<td>7460TK<td>";
    text = text + "</tr>";
    text = text + "</table>";
    
    text = text + "<table>";
    text = text + "<tr>";
    text = text + "<td>Item amount<td>";
    text = text + "<td><td>";
    text = text + "<td>7460TK<td>";
    text = text + "</tr>";
    
    text = text + "<tr>";
    text = text + "<td>Return amount<td>";
    text = text + "<td><td>";
    text = text + "<td>-320TK<td>";
    text = text + "</tr>";
    
    text = text + "<tr class =\"firstrow\">";
    text = text + "<td>Discount amount<td>";
    text = text + "<td><td>";
    text = text + "<td>-20TK<td>";
    text = text + "</tr>";
    
    text = text + "<tr>";
    text = text + "<td>Net amount<td>";
    text = text + "<td><td>";
    text = text + "<td>300TK<td>";
    text = text + "</tr>";
    
    text = text + "<tr class =\"firstrow\">";
    text = text + "<td>Cash Paid<td>";
    text = text + "<td><td>";
    text = text + "<td>500TK<td>";
    text = text + "</tr>";
    
    text = text + "<tr>";
    text = text + "<td>Change Amount<td>";
    text = text + "<td><td>";
    text = text + "<td>200TK<td>";
    text = text + "</tr>";
    text = text + "</table>";
    
    text = text + "<p class=\"smallfont\">Sales Man : Shahid</p>";
    text = text + "<p class=\"salesman\">4:32 PM 30th January, 2014</p>";
    text = text + "<p class=\"compliment\">Thank you very much for shopping with us. Would love to see you again.</p>";
    text = text + "<p class=\"compliment\">visit us at www.jhinukfashion.com</p>";
    text = text + "<p class=\"compliment\">email us at info@jhinukfashion.com</p>";
    
    return text;
  }
  
  public String getPrintedText()
  {
    String text = "<p class=\"company_name\">" + this.CompanyName + "</p>";
    text = text + "<p class=\"showroom_name\">" + this.showroomName + "</p>";
    text = text + "<p class=\"showroom_address\">" + this.showroomAddress + "</p>";
    text = text + "<p class=\"showroom_address\">Contact : " + this.mobilePhone + "</p>";
    text = text + "<p class=\"receiptno\">RECEIPT NO. " + this.receiptNumber + "</p>";
    text = text + "<p class=\"receiptno\">VAT Regi NO. " + this.vatReg + "</p>";
    
    text = text + "<tr class=\"firstrow\">";
    text = text + "<td>ID<td>";
    text = text + "<td>Item<td>";
    text = text + "<td>Size<td>";
    text = text + "<td>Color<td>";
    text = text + "<td>Price<td>";
    text = text + "</tr>";
    
    double total_price = 0.0D;
    for (Item item : this.items)
    {
      text = text + "<tr>";
      text = text + "<td>" + item.getItem_id() + "<td>";
      text = text + "<td>" + item.getType() + "<td>";
      text = text + "<td>" + item.getSize() + "<td>";
      text = text + "<td>" + item.getColor_code() + "<td>";
      text = text + "<td>" + item.getSell_price() + "TK<td>";
      total_price += item.getSell_price();
      text = text + "</tr>";
    }
    text = text + "<tr  class=\"lastrow\">";
    text = text + "<td>Total<td>";
    text = text + "<td><td>";
    text = text + "<td><td>";
    text = text + "<td><td>";
    text = text + "<td>" + total_price + "TK<td>";
    text = text + "</tr>";
    text = text + "</table>";
    
    text = text + "<p class=\"table_label\">ITEMS Returned</p>";
    
    text = text + "<table>";
    text = text + "<tr class=\"firstrow\">";
    text = text + "<td>ID<td>";
    text = text + "<td>Item<td>";
    text = text + "<td>Size<td>";
    text = text + "<td>Color<td>";
    text = text + "<td>Price<td>";
    text = text + "</tr>";
    
    double returned_items_total = 0.0D;
    for (Item item : this.return_items)
    {
      text = text + "<tr>";
      text = text + "<td>" + item.getItem_id() + "<td>";
      text = text + "<td>" + item.getType() + "<td>";
      text = text + "<td>" + item.getSize() + "<td>";
      text = text + "<td>" + item.getColor_code() + "<td>";
      text = text + "<td>" + item.getSell_price() + "TK<td>";
      returned_items_total += item.getSell_price();
    }
    text = text + "<tr  class=\"lastrow\">";
    text = text + "<td>Total<td>";
    text = text + "<td><td>";
    text = text + "<td><td>";
    text = text + "<td><td>";
    text = text + "<td>" + returned_items_total + "TK<td>";
    text = text + "</tr>";
    text = text + "</table>";
    
    text = text + "<table>";
    text = text + "<tr>";
    text = text + "<td>Item amount<td>";
    text = text + "<td><td>";
    text = text + "<td>" + total_price + "TK<td>";
    text = text + "</tr>";
    
    text = text + "<tr>";
    text = text + "<td>Return amount<td>";
    text = text + "<td><td>";
    text = text + "<td>-" + returned_items_total + "TK<td>";
    text = text + "</tr>";
    
    text = text + "<tr class =\"firstrow\">";
    text = text + "<td>Discount amount<td>";
    text = text + "<td><td>";
    text = text + "<td>-" + this.discount + "TK<td>";
    text = text + "</tr>";
    double net_amount = total_price - (returned_items_total + this.discount);
    text = text + "<tr>";
    text = text + "<td>Net amount<td>";
    text = text + "<td><td>";
    text = text + "<td>" + net_amount + "TK<td>";
    text = text + "</tr>";
    
    text = text + "<tr class =\"firstrow\">";
    text = text + "<td>Cash Paid<td>";
    text = text + "<td><td>";
    text = text + "<td>" + this.customerPaid + "TK<td>";
    text = text + "</tr>";
    
    text = text + "<tr>";
    text = text + "<td>Change Amount<td>";
    text = text + "<td><td>";
    text = text + "<td>" + (net_amount - this.customerPaid) + "TK<td>";
    text = text + "</tr>";
    text = text + "</table>";
    
    text = text + "<p class=\"smallfont\">Sales Man : " + this.staff_name + "</p>";
    text = text + "<p class=\"salesman\">" + this.sell_time + "</p>";
    text = text + "<p class=\"compliment\">" + this.complementaryText + "</p>";
    text = text + "<p class=\"compliment\">visit us at " + this.web + "</p>";
    text = text + "<p class=\"compliment\">email us at " + this.email + "</p>";
    
    return text;
  }
}
