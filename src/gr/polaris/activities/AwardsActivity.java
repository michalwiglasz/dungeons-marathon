package gr.polaris.activities;

import gr.polaris.R;
import gr.polaris.application.BlbecekApp;
import gr.polaris.model.Award;
import gr.polaris.model.AwardAdapter;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class AwardsActivity extends BaseActivity
{

  private static final String LOG_TAG = "AwardsActivity";

  private ListView            awardsList;

  public void onResume()
  {
    super.onResume();
    if (localeChanged > 0)
    {
      setContentView(R.layout.activity_awards);
      awardsList.invalidateViews();
      --localeChanged;
    }

  }

  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    BlbecekApp app = (BlbecekApp) getApplication();
    Log.i(LOG_TAG, "onCreate");
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_awards);

    awardsList = (ListView) findViewById(R.id.awardsList);

    // Log.i(LOG_TAG, app.rooms.getAwards().toString());

    AwardAdapter adapter = new AwardAdapter(getApplicationContext(), app.rooms.getAwards());
    awardsList.setAdapter(adapter);
    awardsList.setOnItemClickListener(new OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> a, View v, int pos, long id)
      {
        Award aw = ((AwardAdapter) a.getAdapter()).getItem(pos);
        BlbecekApp app = (BlbecekApp) getApplication();
        if (!app.userData.hasAward(aw.name))
          return;
        // TODO make dialog
        // Log.i("AwardAdapter", "INFO FOR Award " + String.valueOf(position));
        AlertDialog.Builder adb = new AlertDialog.Builder(a.getContext());
        adb.setPositiveButton(R.string.buttonOk, new DialogInterface.OnClickListener()
        {

          public void onClick(DialogInterface arg0, int arg1)
          {
            // TODO Auto-generated method stub
            // nothing special?
            arg0.cancel();
          }
        });
        adb.setMessage(aw.description).setTitle(aw.name);

        AlertDialog d = adb.create();
        d.show();
      }
    });
  }
}