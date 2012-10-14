package gr.polaris.activities;

import gr.polaris.R;
import gr.polaris.application.BlbecekApp;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends BaseActivity
{

  private static final String LOG_TAG = "MainActivity";
  
  public void onResume()
  {
    super.onResume();
    if(localeChanged > 0)
    {
      setContentView(R.layout.activity_main);
      ((TextView) findViewById(R.id.text_roomA)).setText(getString(R.string.main_roomA));
      ((TextView) findViewById(R.id.text_roomB)).setText(getString(R.string.main_roomB));

      --localeChanged;
    }

  }

  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    Log.i(LOG_TAG, "onCreate");
    try
    {

      super.onCreate(savedInstanceState);

      setContentView(R.layout.activity_main);
      
      BlbecekApp.mainActivityInstance = this;

      ((TextView) findViewById(R.id.text_roomA)).setText(getString(R.string.main_roomA));
      ((TextView) findViewById(R.id.text_roomB)).setText(getString(R.string.main_roomB));

    } catch (Exception e)
    {

      Toast t = Toast.makeText(getApplicationContext(), "Unexpected error, try again.",
        Toast.LENGTH_LONG);
      t.show();
    }
  }

}
