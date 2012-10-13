package gr.polaris.model;

import java.util.ArrayList;

public class DataModel {

	private ArrayList<String> unlockedRooms;
	
	private ArrayList<String> awards;
	
	//private ArrayList<ArrayList<String>> ttt;
	
	public DataModel()
	{
		unlockedRooms = new ArrayList<String>();
		awards = new ArrayList<String>();
		init();
	}
	
	/**
	 * Initialize Model
	 */
	public void init()
	{
		// Insert elementary objects to opened
		unlockedRooms.add("L224");	// Herout
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
	 * @param r		Unlocked room
	 * @return		Room is newly unlocked (was locked)
	 */
	public boolean addUnlocked(String room)
	{
		if(hasUnlocked(room))
			return false;
		unlockedRooms.add(room);
		return true;
	}
	
	/**
	 * User has already unlocked room
	 * @param room		Name of room
	 * @return room is unlocked
	 */
	public boolean hasUnlocked(String room)
	{
		return unlockedRooms.contains(room);
	}
	
	public int sizeUnlocked()
	{
		return unlockedRooms.size();
	}
	
	/**
	 * Add award for the user
	 * @param award		Name of award
	 * @return award was new
	 */
	public boolean addAward(String award)
	{
		if(hasAward(award))
			return false;
		awards.add(award);
		return true;
	}
	
	/**
	 * User has already award
	 * @param award		Name of award
	 * @return	user has award
	 */
	public boolean hasAward(String award)
	{
		return awards.contains(award);
	}
	
	/**
	 * Return list of user unlocked rooms
	 * @return ArrayList<String>
	 */
	public ArrayList<String> getUnlockedRooms()
	{
		return unlockedRooms;
	}
}
