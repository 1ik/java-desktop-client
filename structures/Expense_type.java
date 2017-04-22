package structures;

public class Expense_type
{
  private int id;
  private String reason;
  
  public Expense_type(int id, String reason)
  {
    this.id = id;
    this.reason = reason;
  }
  
  public int getId()
  {
    return this.id;
  }
  
  public String getReason()
  {
    return this.reason;
  }
  
  public String toString()
  {
    return "Expense_type{id=" + this.id + ", reason=" + this.reason + '}';
  }
}
