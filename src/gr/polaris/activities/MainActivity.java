package gr.polaris.activities;

import gr.polaris.R;
import gr.polaris.model.DataModel;
import gr.polaris.model.RoomsManager;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends BaseActivity {

	private static final String LOG_TAG = "MainActivity";

	@Override
	public void onCreate(Bundle savedInstanceState) {

		try {

			super.onCreate(savedInstanceState);

			userData = new DataModel();
			rooms = new RoomsManager();
			roomA = roomB = "";

			setContentView(R.layout.activity_main);

		} catch (Exception e) {

			Toast t = Toast.makeText(getApplicationContext(),
					"Unexpected error, try again.", Toast.LENGTH_LONG);
			t.show();
		}
	}

}
