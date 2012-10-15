package gr.polaris.model;

import java.util.ArrayList;

public class DataModel
{
  private ArrayList<Room> unlockedRooms;
  private ArrayList<String> unlockedRoomsString;

  private ArrayList<String> awards;
  
  private RoomsManager rm;

  public DataModel(RoomsManager rm)
  {
    unlockedRooms = new ArrayList<Room>();
    unlockedRoomsString = new ArrayList<String>();
    awards = new ArrayList<String>();
    this.rm = rm;    
    init();
  }

  /**
   * Initialize Model
   */
  public void init()
  {
    // Insert elementary objects to opened
    addUnlocked("L224"); // Herout
    //addUnlocked("C215");
    //addUnlocked("C20");
    addUnlocked("A308");
    addUnlocked("L333");
    addUnlocked("E106");
    addUnlocked("C215");
    addUnlocked("C226");
    addUnlocked("C205");
    
  }

  /**
   * Clean profile data and initialize
   */
  public void clean()
  {
    unlockedRooms.clear();
    awards.clear();
    init();
  }

  /**
   * Add unlocked room to the list
   * 
   * @param r
   *          Unlocked room
   * @return Room is newly unlocked (was locked)
   */
  public boolean addUnlocked(String room)
  {
    if (hasUnlocked(room))
      return false;
    unlockedRoomsString.add(room);
    unlockedRooms.add(rm.getRoom(room));
    return true;
  }

  /**
   * User has already unlocked room
   * 
   * @param room
   *          Name of room
   * @return room is unlocked
   */
  public boolean hasUnlocked(String room)
  {
    return unlockedRoomsString.contains(room);
  }

  public int sizeUnlocked()
  {
    return unlockedRooms.size();
  }

  /**
   * Add award for the user
   * 
   * @param award
   *          Name of award
   * @return award was new
   */
  public boolean addAward(String award)
  {
    if (hasAward(award))
      return false;
    awards.add(award);
    return true;
  }

  /**
   * User has already award
   * 
   * @param award
   *          Name of award
   * @return user has award
   */
  public boolean hasAward(String award)
  {
    return awards.contains(award);
  }

  /**
   * Return list of user unlocked rooms
   * 
   * @return ArrayList<String>
   */
  public ArrayList<Room> getUnlockedRooms()
  {
    return unlockedRooms;
  }
  
  /**
   * Return list of user unlocked rooms
   * 
   * @return ArrayList<String>
   */
  public ArrayList<String> getUnlockedRoomsString()
  {
    return unlockedRoomsString;
  }
}
