package structures;

import config.Config;

public class Return
{
  private int id;
  private String items;
  private int from_showroom_id;
  private int to_showroom_id;
  
  public Return(int showroomid, String items)
  {
    this.to_showroom_id = showroomid;
    this.items = items;
    this.from_showroom_id = Config.SHOWROOM_ID;
    this.id = -1;
  }
  
  public Return(int showroomid, String items, int id)
  {
    this.to_showroom_id = showroomid;
    this.items = items;
    this.from_showroom_id = Config.SHOWROOM_ID;
    this.id = id;
  }
  
  public String getItems()
  {
    return this.items;
  }
  
  public int getFrom_showroom_id()
  {
    return this.from_showroom_id;
  }
  
  public int getTo_showroom_id()
  {
    return this.to_showroom_id;
  }
  
  public String toString()
  {
    return "Return{id=" + this.id + ", items=" + this.items + ", from_showroom_id=" + this.from_showroom_id + ", to_showroom_id=" + this.to_showroom_id + '}';
  }
}
