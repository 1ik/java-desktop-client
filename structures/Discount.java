package structures;

public class Discount
{
  public int percent;
  public String designer_style;
  
  public Discount(int percent, String designer_style)
  {
    this.percent = percent;
    this.designer_style = designer_style;
  }
  
  public String toString()
  {
    return "Discount{percent=" + this.percent + ", designer_style=" + this.designer_style + '}';
  }
}
