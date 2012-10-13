package gr.polaris.activities;

import gr.polaris.R;
import gr.polaris.model.DataModel;
import gr.polaris.model.RoomsManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends BaseActivity
{

  private static final String LOG_TAG = "MainActivity";

  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    Log.i(LOG_TAG, "onCreate");
    try
    {

      super.onCreate(savedInstanceState);

      setContentView(R.layout.activity_main);

      ((TextView) findViewById(R.id.text_roomA)).setText("First room: ");
      ((TextView) findViewById(R.id.text_roomB)).setText("Second room: ");

    } catch (Exception e)
    {

      Toast t = Toast.makeText(getApplicationContext(), "Unexpected error, try again.",
        Toast.LENGTH_LONG);
      t.show();
    }
  }

}
