package gr.polaris.activities;

import java.io.File;
import java.util.List;

import gr.polaris.R;
import gr.polaris.model.DataModel;
import gr.polaris.model.ImageUpload;
import gr.polaris.model.RoomsManager;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public abstract class BaseActivity extends Activity {

	private static final String LOG_TAG = "BaseActivity";

	protected Uri fileUri;
	protected static final int SCAN_IMAGE_REQUEST_CODE = 101;

	public static DataModel userData;
	public static RoomsManager rooms;

	protected String roomA;
	protected String roomB;

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
				if(fileUri == null)
				{
					Log.e(LOG_TAG, "fileUri us null!");
					return;
				}
				// Toast t = Toast.makeText(getApplicationContext(),
				// "Image saved to " + fileUri.getPath(), Toast.LENGTH_SHORT);
				// t.show();
				// Upload image on the server
				// http://fit.mikita.eu/upload.php
				// http://lugano.michalwiglasz.cz/maraton.txt
				//ImageUpload iu = new ImageUpload("http://lugano.michalwiglasz.cz:5000/img");
				ImageUpload iu = new ImageUpload("http://fit.mikita.eu/upload.php");
				try {
					iu.sendImage(fileUri.getPath());
					String res = iu.getResponse(); 
					// TODO wrong image, play wrong sound
					if (res == null || res.isEmpty() || res.equals("???")) {
						Toast t = Toast.makeText(getApplicationContext(),
								"Image was not recognized", Toast.LENGTH_LONG);
						t.show();
						return;
					}
				} catch (RuntimeException e) {
					Log.e(LOG_TAG, "Runtime exception: " + e.getMessage(), e);
					return;
				}

				checkRoom(iu.getResponse());

			}
		}
	}

	public void checkRoom(String room) {
		// TODO debug it...
//		TextView tv = (TextView) findViewById(R.id.main_text);
//		tv.setText("Found room: " + room);
//		if(true)
//			return;
		// ~ TODO

//		Log.i("checkRoom", "RoomA " + roomA + "; roomB " + roomB + ";  rom " + room);
		
		// Check unlocked room
		if (!userData.hasUnlocked(room)) {
			// TODO wrong image, play wrong sound
			Toast t = Toast.makeText(getApplicationContext(), "The room "
					+ room + " is not unlocked!", Toast.LENGTH_LONG);
			t.show();
			return;
		}
		
		// Selected first or second room?
		if (roomA.isEmpty()) {
			roomA = room;
		} else {
			roomB = room;
		}

		Log.i("checkRoom", "RoomA " + roomA + "; roomB " + roomB + ";  rom "
				+ room);
		
		// If first unlocked rooms, check only one room
		if (userData.sizeUnlocked() != 1
				&& (roomA.isEmpty() || roomB.isEmpty()))
			return;

		// Check combination
		List<String> res = rooms.tryPair(roomA, roomB);
		if (res.isEmpty()) {
			// TODO wrong image, play wrong sound
			Toast t = Toast.makeText(getApplicationContext(),
					"The combination is not allowed", Toast.LENGTH_LONG);
			t.show();
			// clear rooms
			roomA = roomB = "";
			return;
		}
		// Last String is description for combination (will be showed)
		Log.i(LOG_TAG, "Found pair for rooms " + roomA + "-" + roomB);
		String desc = res.get(res.size()-1);
		// remove last string from array
		res.remove(res.size()-1);
		Log.i(LOG_TAG, "Desc " + desc);
		String rooms = "Unlocked rooms: ";
		// For each string, add unlocked room
		for (String s : res) {
			userData.addUnlocked(s);
			rooms += s + ", ";
		}
// ((ArrayList<String>) rooms).;
		// Show rooms
		Toast t = Toast.makeText(getApplicationContext(), rooms,
				Toast.LENGTH_LONG);
		t.show();
		Log.i(LOG_TAG, rooms);
		TextView tv2 = (TextView) findViewById(R.id.main_text);
		tv2.setText(rooms + "\r\n\r\n" + desc);
		
		// clear rooms
		roomA = roomB = "";
		Log.i(LOG_TAG, "roomA " + roomA + "; roomB " + roomB);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.all_activities, menu);
		return true;
	}

	public void showRoomsActivity(View view) {
		Intent i = new Intent(this, RoomsActivity.class);
		startActivity(i);
	}

	public void showAwardsActivity(View view) {
		Intent i = new Intent(this, RoomsActivity.class);
		startActivity(i);
	}
}
