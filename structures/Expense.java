package structures;

import config.Config;

public class Expense
{
  private int expense_id;
  private int expense_type_id;
  private float amount;
  private String explanation;
  private String created_at;
  private int showroom_id;
  
  public Expense(int expense_type_id, float amount, String explanation)
  {
    this.expense_type_id = expense_type_id;
    this.amount = amount;
    this.explanation = explanation;
    this.showroom_id = Config.SHOWROOM_ID;
  }
  
  public Expense(int expense_type_id, float amount, String explanation, String created)
  {
    this(expense_type_id, amount, explanation);
    this.created_at = created;
  }
  
  public Expense(int expense_id, int expense_type_id, float amount, String explanation, String created_at, int showroom_id)
  {
    this.expense_type_id = expense_type_id;
    this.amount = amount;
    this.explanation = explanation;
    this.created_at = created_at;
    this.expense_id = expense_id;
    this.showroom_id = showroom_id;
  }
  
  public int getExpense_type_id()
  {
    return this.expense_type_id;
  }
  
  public float getAmount()
  {
    return this.amount;
  }
  
  public String getExplanation()
  {
    return this.explanation;
  }
  
  public String getCreated_at()
  {
    return this.created_at;
  }
  
  public String toString()
  {
    return "Expense{expense_id=" + this.expense_id + ", expense_type_id=" + this.expense_type_id + ", amount=" + this.amount + ", explanation=" + this.explanation + ", created_at=" + this.created_at + ", showroom_id=" + this.showroom_id + '}';
  }
}
