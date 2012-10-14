package gr.polaris.model;

import gr.polaris.application.BlbecekApp;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class RoomsAdapter extends BaseAdapter
{
//  private DataModel        userData;

  private Context context;
  private ArrayList<Room> rooms;

  public RoomsAdapter(Context context, ArrayList<Room> rooms)
  {
    // TODO Auto-generated constructor stub
    this.rooms = (ArrayList<Room>) rooms.clone();
    this.context = context;
  }

  public int getCount()
  {
    // TODO Auto-generated method stub
    return rooms.size();
  }

  public Room getItem(int position)
  {
    // TODO Auto-generated method stub
    return rooms.get(position);
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
    Room actRoom = rooms.get(position);
    RoomView rv;
    if(convertView == null)
      rv = new RoomView(context, rooms.get(position));
    else
    {
      rv = (RoomView)convertView;
      rv.setName( actRoom.name );
    }
//    
//    if(app.userData.hasAward(actRoom.name))
//    {
//      rv.setClickable(true);
//      rv.setEnabled(true);
//      rv.setBackgroundColor(Color.rgb(255, 237, 0));
//    }
//    else
//    {
//      rv.setClickable(false);
//      rv.setEnabled(false);
//      rv.setBackgroundColor(Color.GRAY);
//    }
    
    return rv;
  }
  
  /**
   * Show information about Award at position
   * @param position
   */
  public void showInfo(int position)
  {
    BlbecekApp app = (BlbecekApp)context;
    //if(!app.userData.hasAward(rooms.get(position).name))
     // return;
    // TODO make dialog
    Log.i("RoomsAdapter", "INFO FOR Room " + String.valueOf(position));
  }

}
