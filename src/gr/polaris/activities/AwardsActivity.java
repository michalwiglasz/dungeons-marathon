package gr.polaris.activities;

import java.util.ArrayList;

import gr.polaris.R;
import gr.polaris.application.BlbecekApp;
import gr.polaris.model.Award;
import gr.polaris.model.AwardAdapter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class AwardsActivity extends BaseActivity
{

  private static final String LOG_TAG = "AwardsActivity";

  private ListView            awardsList;

  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    BlbecekApp app = (BlbecekApp) getApplication();
    Log.i(LOG_TAG, "onCreate");
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_awards);

    awardsList = (ListView) findViewById(R.id.awardsList);

//     Log.i(LOG_TAG, app.rooms.getAwards().toString());

    AwardAdapter adapter = new AwardAdapter(getApplicationContext(), app.rooms.getAwards());
    awardsList.setAdapter(adapter);
    awardsList.setOnItemClickListener(new OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> a, View v, int pos, long id)
      {
        ((AwardAdapter) a.getAdapter()).showInfo(pos);
        // TODO Auto-generated method stub
        //Toast.makeText(getBaseContext(), "Clicked " + String.valueOf(id), Toast.LENGTH_SHORT).show();
      }
    });
  }
}