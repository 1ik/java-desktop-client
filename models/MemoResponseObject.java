package models;

public class MemoResponseObject
{
  public int id;
  public int memo_id;
  
  public int getId()
  {
    return this.id;
  }
  
  public int getMemo_id()
  {
    return this.memo_id;
  }
  
  public String toString()
  {
    return "MemoResponseObject{id=" + this.id + ", memo_id=" + this.memo_id + '}';
  }
}
