package structures;

import models.Config_model;

public class Transfer
{
  public int id;
  public int transfer_id;
  public int showroom_id;
  public String created_at;
  public int total_items;
  public int from_showroom_id;
  public int contextual_transfer_id;
  
  public Transfer(int id, int transfer_id, int showroom_id, String created_at, int total_items, int from_showroom_id, int contextual_transfer_id)
  {
    this.id = id;
    this.transfer_id = transfer_id;
    this.showroom_id = Config_model.getShowroomID();
    this.created_at = created_at;
    this.total_items = total_items;
    this.from_showroom_id = from_showroom_id;
    
    this.contextual_transfer_id = contextual_transfer_id;
  }
  
  public int getId()
  {
    return this.id;
  }
  
  public int getTransfer_id()
  {
    return this.transfer_id;
  }
  
  public int getShowroom_id()
  {
    return Config_model.getShowroomID();
  }
  
  public String getCreated_at()
  {
    return this.created_at;
  }
  
  public int getTotal_items()
  {
    return this.total_items;
  }
  
  public int getFrom_showroom_id()
  {
    return this.from_showroom_id;
  }
  
  public int getContextual_transfer_id()
  {
    return this.contextual_transfer_id;
  }
  
  public String toString()
  {
    return "Transfer{id=" + this.id + ", transfer_id=" + this.transfer_id + ", showroom_id=" + this.showroom_id + ", created_at=" + this.created_at + ", total_items=" + this.total_items + ", from_showroom_id=" + this.from_showroom_id + ", contextual_transfer_id=" + this.contextual_transfer_id + '}';
  }
}
