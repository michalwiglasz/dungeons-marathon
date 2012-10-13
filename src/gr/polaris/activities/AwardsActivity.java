package gr.polaris.activities;

import gr.polaris.R;
import android.os.Bundle;
import android.util.Log;

public class AwardsActivity extends BaseActivity
{

  private static final String LOG_TAG = "AwardsActivity";

  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    Log.i(LOG_TAG, "onCreate");
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_awards);
  }
}