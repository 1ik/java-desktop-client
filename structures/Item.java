package structures;

public class Item
{
  private int id;
  private int item_id;
  private String type;
  private String size;
  private float sell_price;
  private int transfer_id;
  private int contextual_transfer_id;
  private String color_code;
  public String designer_style;
  
  public Item(int item_id, String type, String size, float sell_price, String color_code, int transfer_id)
  {
    this.item_id = item_id;
    this.type = type;
    this.size = size;
    this.sell_price = sell_price;
    this.transfer_id = transfer_id;
    this.color_code = color_code;
    this.contextual_transfer_id = -1;
  }
  
  public Item(int item_id, String type, String size, float sell_price, String color_code, int transfer_id, int contextual_transfer_id)
  {
    this.item_id = item_id;
    this.type = type;
    this.size = size;
    this.sell_price = sell_price;
    this.transfer_id = transfer_id;
    this.color_code = color_code;
    this.contextual_transfer_id = contextual_transfer_id;
  }
  
  public int getId()
  {
    return this.id;
  }
  
  public int getItem_id()
  {
    return this.item_id;
  }
  
  public String getType()
  {
    return this.type;
  }
  
  public String getSize()
  {
    return this.size;
  }
  
  public float getSell_price()
  {
    return this.sell_price;
  }
  
  public int getTransfer_id()
  {
    return this.transfer_id;
  }
  
  public String getColor_code()
  {
    return this.color_code;
  }
  
  public int getContextual_transfer_id()
  {
    return this.contextual_transfer_id;
  }
  
  public String toString()
  {
    return "Item{id=" + this.id + ", item_id=" + this.item_id + ", type=" + this.type + ", size=" + this.size + ", sell_price=" + this.sell_price + ", transfer_id=" + this.transfer_id + ", contextual_transfer_id=" + this.contextual_transfer_id + ", color_code=" + this.color_code + '}';
  }
}
