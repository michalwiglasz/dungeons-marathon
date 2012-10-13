package gr.polaris.blbecci;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private Uri fileUri;
	private static final int SCAN_IMAGE_REQUEST_CODE = 101;
	private static final String LOG_TAG = "MainActivity";

	private String roomA;
	private String roomB;

	public static DataModel userData;

	/**
	 * Check and return proper folder for Application (will be created)
	 * 
	 * @return
	 */
	protected File getAppFolder() {
		File dir = new File(
				Environment
						.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
				"Blbecci");
		if (dir.exists())
			return dir;

		if (!dir.mkdirs()) {
			Log.e(LOG_TAG, "failed to create directory");
			return null;
		}
		return dir;
	}

	protected Uri getImageFileUri() {
		// Check directory
		File dir = new File(getAppFolder().getPath() + File.separator
				+ "last_scan.jpg");
		return Uri.fromFile(dir);
	}

	/**
	 * Start PhotoActivity to scan image file
	 * 
	 * @param view
	 */
	public void scanImage(View view) {
		Intent in = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		fileUri = getImageFileUri();
		in.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

		startActivityForResult(in, SCAN_IMAGE_REQUEST_CODE);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == SCAN_IMAGE_REQUEST_CODE) {
			if (resultCode == RESULT_CANCELED)
				return;
			if (resultCode == RESULT_OK) {
				// Toast t = Toast.makeText(getApplicationContext(),
				// "Image saved to " + fileUri.getPath(), Toast.LENGTH_SHORT);
				// t.show();
				// Upload image on the server
				// http://fit.mikita.eu/upload.php
				// http://lugano.michalwiglasz.cz/maraton.txt
				// ImageUpload iu = new
				// ImageUpload("http://lugano.michalwiglasz.cz/maraton.txt");
				ImageUpload iu = new ImageUpload(
						"http://fit.mikita.eu/upload.php");
				iu.sendImage(fileUri.getPath());
				// TODO wrong image, play wrong sound
				if (iu.getResponse().isEmpty()) {
					Toast t = Toast.makeText(getApplicationContext(),
							"Image was not recognized", Toast.LENGTH_LONG);
					t.show();
					return;
				}

				checkRoom(iu.getResponse());

			}
		}
	}

	public void checkRoom(String room) {
		// Check unlocked room
		if (!userData.hasUnlocked(room)) {
			// TODO wrong image, play wrong sound
			Toast t = Toast.makeText(getApplicationContext(),
					"The room is not unlocked!", Toast.LENGTH_LONG);
			t.show();
			return;
		}

		// Selected first or second room?
		if (roomA.isEmpty())
			roomA = room;
		else {
			roomB = room;
			// Check 
		}

		Toast t = Toast.makeText(getApplicationContext(), room,
				Toast.LENGTH_LONG);
		t.show();

		TextView tv = (TextView) findViewById(R.id.main_text);
		tv.setText(room);

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		userData = new DataModel();

		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
}
