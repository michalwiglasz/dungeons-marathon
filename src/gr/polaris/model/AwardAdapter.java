package gr.polaris.model;

import gr.polaris.application.BlbecekApp;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class AwardAdapter extends BaseAdapter
{
//  private DataModel        userData;

  private Context context;
  private ArrayList<Award> awards;

  public AwardAdapter(Context context, ArrayList<Award> awards)
  {
    // TODO Auto-generated constructor stub
    this.awards = (ArrayList<Award>) awards.clone();
    this.context = context;
  }

  public int getCount()
  {
    // TODO Auto-generated method stub
    return awards.size();
  }

  public Award getItem(int position)
  {
    // TODO Auto-generated method stub
    return awards.get(position);
  }

  public long getItemId(int position)
  {
    // TODO Auto-generated method stub
    return position;
  }

  public View getView(int position, View convertView, ViewGroup parent)
  {
    // TODO Auto-generated method stub
    BlbecekApp app = (BlbecekApp)context;
    Award actAward = awards.get(position);
    AwardView av;
    if(convertView == null)
      av = new AwardView(context, awards.get(position));
    else
    {
      av = (AwardView)convertView;
      av.setName( actAward.name );
    }
    
    if(app.userData.hasAward(actAward.name))
    {
      av.setClickable(false);
      //av.setEnabled(false);
      av.setFocusable(false);
      //av.set
      av.setBackgroundColor(Color.rgb(255, 194, 0));
    }
    else
    {
      av.setClickable(true);
      av.setEnabled(true);
      av.setBackgroundColor(Color.GRAY);
    }
    
    return av;
  }
  
  /**
   * Show information about Award at position
   * @param position
   */
  public void showInfo(int position)
  {
    Log.i("AwardAdapter", "INFO FOR Award " + String.valueOf(position) + " ?? " + awards.get(position).name);
    BlbecekApp app = (BlbecekApp)context;
    if(!app.userData.hasAward(awards.get(position).name))
      return;
    // TODO make dialog
    Log.i("AwardAdapter", "INFO FOR Award " + String.valueOf(position));
  }

}
