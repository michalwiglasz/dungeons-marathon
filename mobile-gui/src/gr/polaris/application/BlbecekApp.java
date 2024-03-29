package gr.polaris.application;

import gr.polaris.activities.MainActivity;
import gr.polaris.model.DataModel;
import gr.polaris.model.RoomsManager;
import android.app.Application;
import android.net.Uri;

public final class BlbecekApp extends Application
{
  public static MainActivity mainActivityInstance;
  
  /** Rooms for scanning and combination */
  public String       roomA;
  public String       roomB;

  /** User data */
  public DataModel    userData;
  /** Manager for rooms and awards */
  public RoomsManager rooms;

  /** Scanning picture file Uri */
  public Uri          fileUri;

  /**
   * Constructor of Blbeček Application class
   */
  public BlbecekApp()
  {
    super();
  }
  
  public void onCreate()
  {
    // Prepare CS localization
    //Locale loc = new Locale("cs");
    //Locale.setDefault(loc);
    
    rooms = new RoomsManager(this);
    userData = new DataModel(rooms);
    roomA = roomB = "";
    rooms.testAward(userData);
  }
}
