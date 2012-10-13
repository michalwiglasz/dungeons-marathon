package gr.polaris.activities;

import java.util.ArrayList;

import gr.polaris.R;
import gr.polaris.application.BlbecekApp;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * 
 * @author MIKI
 * 
 */
public class RoomsActivity extends BaseActivity
{

  private static final String LOG_TAG = "RoomsActivity";

  private ListView            roomsList;

  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    BlbecekApp app = (BlbecekApp) getApplication();
    Log.i(LOG_TAG, "onCreate");
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_rooms);

    roomsList = (ListView) findViewById(R.id.roomsList);

    Log.i("RoomsActivity", app.userData.getUnlockedRooms().toString());
    ArrayList<String> rr = (ArrayList<String>) app.userData.getUnlockedRooms().clone();
    String[] rooms = new String[rr.size()];
    rooms = rr.toArray(rooms);

    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
      android.R.layout.simple_list_item_1, rooms);
    roomsList.setAdapter(adapter);
    roomsList.setOnItemClickListener(new OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> a, View v, int pos, long id)
      {
        // TODO Auto-generated method stub
        Toast.makeText(getBaseContext(), "Clicked " + String.valueOf(id), Toast.LENGTH_LONG).show();
      }
    });
  }
}
