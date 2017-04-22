package structures;

import config.Config;

public class Memo
{
  private int id;
  private int showroom_id;
  private String added_on;
  private String items;
  private String changed_items;
  private String token;
  private float discount;
  private String salesman;
  private String memo_id;
  private int updated;
  
  public Memo(String pAddingTime, String items, String changed_items)
  {
    this.showroom_id = Config.SHOWROOM_ID;
    this.added_on = pAddingTime;
    this.items = items;
    this.changed_items = changed_items;
  }
  
  public Memo(int id, String pAddingTime, String items, String changed_items, float pDiscount, String pToken, String pSalesMan, int updated, String memo_id)
  {
    this.id = id;
    this.showroom_id = Config.SHOWROOM_ID;
    this.added_on = pAddingTime;
    this.items = items;
    this.changed_items = changed_items;
    this.discount = pDiscount;
    this.token = pToken;
    this.salesman = pSalesMan;
    this.updated = updated;
    this.memo_id = memo_id;
  }
  
  public String getToken()
  {
    return this.token;
  }
  
  public void setToken(String token)
  {
    this.token = token;
  }
  
  public float getDiscount()
  {
    return this.discount;
  }
  
  public void setDiscount(float discount)
  {
    this.discount = discount;
  }
  
  public String getSalesman()
  {
    return this.salesman;
  }
  
  public void setSalesman(String salesman)
  {
    this.salesman = salesman;
  }
  
  public String getMemo_id()
  {
    return this.memo_id;
  }
  
  public void setMemo_id(String memo_id)
  {
    this.memo_id = memo_id;
  }
  
  public int getUpdated()
  {
    return this.updated;
  }
  
  public void setUpdated(int updated)
  {
    this.updated = updated;
  }
  
  public int getId()
  {
    return this.id;
  }
  
  public int getShowroom_id()
  {
    return this.showroom_id;
  }
  
  public String getAdded_on()
  {
    return this.added_on;
  }
  
  public String getItems()
  {
    return this.items;
  }
  
  public String getChanged_items()
  {
    return this.changed_items;
  }
  
  public String toString()
  {
    return "Memo{id=" + this.id + ", showroom_id=" + this.showroom_id + ", added_on=" + this.added_on + ", items=" + this.items + ", changed_items=" + this.changed_items + ", token=" + this.token + ", discount=" + this.discount + ", salesman=" + this.salesman + ", memo_id=" + this.memo_id + ", updated=" + this.updated + '}';
  }
}
