package structures;

public class ChangedItem
  extends Item
{
  public String changeDate;
  
  public ChangedItem(int item_id, String type, String size, float sell_price, String color_code, int transfer_id, String change_date)
  {
    super(item_id, type, size, sell_price, color_code, transfer_id);
    this.changeDate = change_date;
  }
  
  public String getChangeDate()
  {
    return this.changeDate;
  }
  
  public String toString()
  {
    return super.toString() + "ChangedItem{" + "changeDate=" + this.changeDate + '}';
  }
}
