package gr.polaris.model;

import java.util.ArrayList;

public class Item
{
  public String            a;
  public String            b;
  public ArrayList<String> res;
  public String            description;

  public Item(String A, String B, ArrayList<String> Res, String des)
  {
    a = A;
    b = B;
    res = Res;
    description = des;
  }
}
