package gr.polaris.model;

import android.content.Context;
import android.graphics.Color;
import android.widget.LinearLayout;
import android.widget.TextView;

public class RoomView extends LinearLayout
{
  private TextView name;
  private TextView person;

  public RoomView(Context context, Room room)
  {
    super(context);
    // TODO Auto-generated constructor stub
    this.setOrientation(LinearLayout.HORIZONTAL);
    //this.setLayoutParams(new LinearLayout.LayoutParams(Layou));
    
    name = new TextView(context);
    name.setText(room.name);
    name.setTextSize(18);
    name.setLineSpacing(8, 2);
    name.setTextColor(Color.BLACK);
    
    person = new TextView(context);
    person.setText(room.person);
    person.setTextSize(16);
    person.setLineSpacing(8, 2);
    person.setTextColor(Color.BLACK);
    
    addView(person);
  }

  public String getName()
  {
    return this.name.getText().toString();
  }
  
  public void setName(String name)
  {
    this.name.setText(name);
  }
  
  public String getPerson()
  {
    return this.person.getText().toString();
  }
  
  public void setPerson(String person)
  {
    this.person.setText(person);
  }

}
