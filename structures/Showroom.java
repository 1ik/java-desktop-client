package structures;

public class Showroom
{
  public int id;
  public String name;
  public String location;
  
  public Showroom(int showroom_id, String name, String location)
  {
    this.id = showroom_id;
    this.name = name;
    this.location = location;
  }
  
  public int getShowroom_id()
  {
    return this.id;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public String toString()
  {
    return "Showroom{id=" + this.id + ", name=" + this.name + ", location=" + this.location + '}';
  }
}
