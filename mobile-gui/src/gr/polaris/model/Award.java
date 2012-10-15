package gr.polaris.model;

import java.util.List;

public class Award
{
  public String       name;
  public String       description;
  public List<String> rooms;

  public Award(String n, String d, List<String> r)
  {
    name = n;
    description = d;
    rooms = r;
  }
}