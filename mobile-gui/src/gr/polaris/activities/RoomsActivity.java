package gr.polaris.activities;

import gr.polaris.R;
import gr.polaris.application.BlbecekApp;
import gr.polaris.model.Room;
import gr.polaris.model.RoomsAdapter;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

/**
 * 
 * @author MIKI
 * 
 */
public class RoomsActivity extends BaseActivity
{

  private static final String LOG_TAG = "RoomsActivity";

  private ListView            roomsList;

  public void onResume()
  {
    super.onResume();
    if (localeChanged > 0)
    {
      setContentView(R.layout.activity_rooms);
      roomsList.invalidateViews();
      --localeChanged;
    }

  }

  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    BlbecekApp app = (BlbecekApp) getApplication();
    Log.i(LOG_TAG, "onCreate");
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_rooms);

    roomsList = (ListView) findViewById(R.id.roomsList);

    Log.i("RoomsActivity", app.userData.getUnlockedRooms().toString());
    // ArrayList<String> rr = (ArrayList<String>)
    // app.userData.getUnlockedRooms().clone();
    // String[] rooms = new String[rr.size()];
    // rooms = rr.toArray(rooms);

    // ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
    // android.R.layout.simple_list_item_1, rooms);
    RoomsAdapter adapter = new RoomsAdapter(getApplicationContext(),
      app.userData.getUnlockedRooms());
    roomsList.setAdapter(adapter);
    roomsList.setOnItemClickListener(new OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> a, View v, int pos, long id)
      {
        // TODO Auto-generated method stub
        // Toast.makeText(getBaseContext(), "Clicked " + String.valueOf(id),
        // Toast.LENGTH_LONG).show();
        RoomsAdapter ra = ((RoomsAdapter) a.getAdapter());
        AlertDialog.Builder adb = new AlertDialog.Builder(a.getContext());
        Room r = ra.getItem(pos);
        adb.setMessage(r.description);
        adb.setTitle(r.name + " - " + r.person);
        adb.setPositiveButton(R.string.buttonOk, new DialogInterface.OnClickListener()
        {

          public void onClick(DialogInterface arg0, int arg1)
          {
            // TODO Auto-generated method stub
            // nothing special?
            arg0.cancel();
          }
        });

        AlertDialog d = adb.create();
        // DialogFragment ndf = AlertDialog
        d.show();

      }
    });
  }
}
