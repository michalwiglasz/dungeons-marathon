package gr.polaris.activities;

import java.io.File;
import java.util.List;

import gr.polaris.R;
import gr.polaris.application.BlbecekApp;
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

/**
 * 
 * @author MIKI
 * 
 */
public abstract class BaseActivity extends Activity
{

  private static final String LOG_TAG                 = "BaseActivity";

  protected static final int  SCAN_IMAGE_REQUEST_CODE = 101;

  /**
   * Check and return proper folder for Application (will be created)
   * 
   * @return
   */
  protected File getAppFolder()
  {
    File dir = new File(
      Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "Blbecci");
    if (dir.exists())
      return dir;

    if (!dir.mkdirs())
    {
      Log.e(LOG_TAG, "failed to create directory");
      return null;
    }
    return dir;
  }

  /**
   * Get Uri for image file
   * 
   * @return
   */
  protected Uri getImageFileUri()
  {
    // Check directory
    File dir = new File(getAppFolder().getPath() + File.separator + "last_scan.jpg");
    return Uri.fromFile(dir);
  }

  @Override
  /**
   * 
   */
  protected void onActivityResult(int requestCode, int resultCode, Intent data)
  {
    if (requestCode == SCAN_IMAGE_REQUEST_CODE)
    {
      if (resultCode == RESULT_CANCELED)
        return;
      if (resultCode == RESULT_OK)
      {
        if (((BlbecekApp) getApplication()).fileUri == null)
        {
          Log.e(LOG_TAG, "fileUri us null!");
          return;
        }
        // Show main activity
        if (this.getClass() != MainActivity.class)
        {
          Intent i = new Intent(this, MainActivity.class);
          i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
          Log.i(LOG_TAG, "Starting MainActivity class");
          startActivity(i);
        }
        // Toast t = Toast.makeText(getApplicationContext(),
        // "Image saved to " + fileUri.getPath(), Toast.LENGTH_SHORT);
        // t.show();
        // Upload image on the server
        // http://fit.mikita.eu/upload.php
        // http://lugano.michalwiglasz.cz/maraton.txt
        // ImageUpload iu = new
        // ImageUpload("http://lugano.michalwiglasz.cz:5000/img");
        ImageUpload iu = new ImageUpload((BlbecekApp) getApplication(),
//            "http://lugano.michalwiglasz.cz:5000/img");
          "http://fit.mikita.eu/upload.php");
        try
        {
          iu.sendImage(((BlbecekApp) getApplication()).fileUri.getPath());
          String res = iu.getResponse();
          // TODO wrong image, play wrong sound
          if (res == null || res.isEmpty() || res.equals("???"))
          {
            Toast t = Toast.makeText(getApplicationContext(), "Image was not recognized",
              Toast.LENGTH_LONG);
            t.show();
            return;
          }
        } catch (RuntimeException e)
        {
          Log.e(LOG_TAG, "Runtime exception: " + e.getMessage(), e);
          return;
        }

        checkRoom(iu.getResponse());

      }
    }
  }

  public void checkRoom(String room)
  {
    
    BlbecekApp app = (BlbecekApp) getApplication();
    TextView tvd = (TextView) findViewById(R.id.text_desc);
    // TODO should never be null!
    if(tvd != null)
      tvd.setText("");

    // TODO debug it...
    // TextView tv = (TextView) findViewById(R.id.main_text);
    // tv.setText("Found room: " + room);
    // if(true)
    // return;
    // ~ TODO

    Log.i("checkRoom", "RoomA " + app.roomA + "; roomB " + app.roomB + ";  rom " + room);

    // Check unlocked room
    if (!app.userData.hasUnlocked(room))
    {
      // TODO wrong image, play wrong sound
      Toast t = Toast.makeText(getApplicationContext(), "The room " + room + " is not unlocked!",
        Toast.LENGTH_LONG);
      tvd.setText("The room " + room + " is not unlocked!");
      t.show();
      return;
    }

    TextView tv;
    // Selected first or second room?
    if (app.roomA.isEmpty())
    {
      app.roomA = room;
      tv = (TextView) findViewById(R.id.text_roomA);

    }
    else
    {
      app.roomB = room;
      tv = (TextView) findViewById(R.id.text_roomB);
    }
    tv.append(room);

    Log.i("checkRoom", "RoomA " + app.roomA + "; roomB " + app.roomB + ";  rom " + room);

    // If first unlocked rooms, check only one room
    if (app.userData.sizeUnlocked() != 1 && (app.roomA.isEmpty() || app.roomB.isEmpty()))
      return;

    // Check combination
    List<String> res = app.rooms.tryPair(app.roomA, app.roomB);
    if (res.isEmpty())
    {
      // TODO wrong image, play wrong sound
      Toast t = Toast.makeText(getApplicationContext(), "The combination is not allowed",
        Toast.LENGTH_LONG);
      t.show();
    }
    else
    {
      // Last String is description for combination (will be showed)
      Log.i(LOG_TAG, "Found pair for rooms " + app.roomA + "-" + app.roomB);
      String desc = res.get(res.size() - 1);
      // remove last string from array
      res.remove(res.size() - 1);
      Log.i(LOG_TAG, "Desc " + desc);
      String rooms = "Unlocked rooms: ";
      // For each string, add unlocked room
      for (String s : res)
      {
        app.userData.addUnlocked(s);
        rooms += s + ", ";
      }
      if(app.rooms.testAward(app.userData))
      {
        // TODO play song...
      }
      // ((ArrayList<String>) rooms).;
      // Show rooms
      Toast t = Toast.makeText(getApplicationContext(), rooms, Toast.LENGTH_LONG);
      t.show();
      Log.i(LOG_TAG, rooms);
      tv = (TextView) findViewById(R.id.text_desc);
      tv.setText(rooms + "\r\n\r\n" + desc);
    }

    // clear rooms
    app.roomA = app.roomB = "";
    ((TextView) findViewById(R.id.text_roomA)).setText(getString(R.string.main_roomA));
    ((TextView) findViewById(R.id.text_roomB)).setText("Second room: ");

    Log.i(LOG_TAG, "roomA " + app.roomA + "; roomB " + app.roomB);
  }

  /**
   * Create common options menu
   */
  @Override
  public boolean onCreateOptionsMenu(Menu menu)
  {
    getMenuInflater().inflate(R.menu.all_activities, menu);
    return true;
  }

  /**
   * Start Camera to scan image file
   * 
   * @param view
   */
  public void scanImage(View view)
  {
    BlbecekApp app = (BlbecekApp) getApplication();
    Intent in = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    app.fileUri = getImageFileUri();
    in.putExtra(MediaStore.EXTRA_OUTPUT, app.fileUri);

    startActivityForResult(in, SCAN_IMAGE_REQUEST_CODE);
  }

  /**
   * Show rooms Activity
   */
  public void showRoomsActivity(View view)
  {
    Intent i = new Intent(this, RoomsActivity.class);
    // i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
    // Intent.FLAG_ACTIVITY_SINGLE_TOP);
    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    startActivity(i);
  }

  /**
   * Show awards Activity
   */
  public void showAwardsActivity(View view)
  {
    Intent i = new Intent(this, AwardsActivity.class);
    // i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
    // Intent.FLAG_ACTIVITY_SINGLE_TOP);
    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    startActivity(i);
  }

  /**
   * Show main Activity
   */
  public void showMainActivity(View view)
  {
    Intent i = new Intent(this, MainActivity.class);
    // i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
    // Intent.FLAG_ACTIVITY_SINGLE_TOP);
    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    startActivity(i);
    // i.
  }
}
