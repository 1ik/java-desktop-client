package structures;

public class changed_item
{
  int item_id;
  String item_type;
  String item_size;
  String price;
  String color;
  String sell_showroom;
  String sell_date;
  
  public int getItem_id()
  {
    return this.item_id;
  }
  
  public String getItem_type()
  {
    return this.item_type;
  }
  
  public String getItem_size()
  {
    return this.item_size;
  }
  
  public String getPrice()
  {
    return this.price;
  }
  
  public String getColor()
  {
    return this.color;
  }
  
  public String getSell_showroom()
  {
    return this.sell_showroom;
  }
  
  public String getSell_date()
  {
    return this.sell_date;
  }
  
  public String toString()
  {
    return "changed_item{item_id=" + this.item_id + ", item_type=" + this.item_type + ", item_size=" + this.item_size + ", price=" + this.price + ", color=" + this.color + ", sell_showroom=" + this.sell_showroom + ", sell_date=" + this.sell_date + '}';
  }
}
