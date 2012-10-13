package gr.polaris.activities;

import gr.polaris.blbecci.R;
import gr.polaris.blbecci.R.layout;
import gr.polaris.blbecci.R.menu;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class RoomsActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rooms);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.all_activities, menu);
        return true;
    }
}
