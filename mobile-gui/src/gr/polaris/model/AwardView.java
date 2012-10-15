package gr.polaris.model;

import android.content.Context;
import android.graphics.Color;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AwardView extends LinearLayout
{
  private TextView name;

  public AwardView(Context context, Award award)
  {
    super(context);
    // TODO Auto-generated constructor stub
    this.setOrientation(LinearLayout.HORIZONTAL);
    //this.setLayoutParams(new LinearLayout.LayoutParams(Layou));
    
    name = new TextView(context);
    name.setText(award.name);
    name.setTextSize(21);
    name.setLineSpacing(10, 2);
    name.setTextColor(Color.BLACK);
    
    addView(name);
  }

  public String getName()
  {
    return this.name.getText().toString();
  }
  
  public void setName(String name)
  {
    this.name.setText(name);
  }

}
