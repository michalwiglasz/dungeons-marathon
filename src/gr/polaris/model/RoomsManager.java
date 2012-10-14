package gr.polaris.model;

import gr.polaris.R;
import gr.polaris.application.BlbecekApp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;


public class RoomsManager 
{
  private BlbecekApp app;
	//private ArrayList<ArrayList<String> > data;
/*	
	private String test[][][] = {{{"L224"}, {""}, {"E106", "A308", "C215", "L129"}},
			{{""}, {""}, {""}},
			};
*/
	private ArrayList<Item> data;
	private ArrayList<Award> awards;
	private HashMap<String, Room> rooms;
	
	public RoomsManager(BlbecekApp context)
	{
	  app = context;
	  context.getResources();
/*	
		data = new ArrayList<ArrayList<String> >();
		
		data.add(new ArrayList<String>());
		data.get(data.size()-1).add("asd");
		data.get(data.size()-1).add("asd");
		data.get(data.size()-1).add("asd");
		
		System.out.println(data.get(0).get(0));
*/		
		
    // app.getString(R.string.room_);
    
    //test read
    data = new ArrayList<Item>();
    
    data.add(new Item("L224", "", new ArrayList<String>(Arrays.asList("A308", "E106", "C215", "L129")), app.getString(R.string.L224))); // Herout -> Křena, Juřiček, Zendulka, Lampa
    data.add(new Item("E106", "A308", new ArrayList<String>(Arrays.asList("L333")), app.getString(R.string.E106A308)));
    data.add(new Item("L333", "E106", new ArrayList<String>(Arrays.asList("C232")), app.getString(R.string.L333E106)));
    data.add(new Item("C232", "C215", new ArrayList<String>(Arrays.asList("C204")), app.getString(R.string.C232C215)));;
    data.add(new Item("C215", "C204", new ArrayList<String>(Arrays.asList("L339")), app.getString(R.string.C215C204)));;
    data.add(new Item("C204", "C206", new ArrayList<String>(Arrays.asList("G108", "P209", "L104", "E112" ,"D105", "C114")), app.getString(R.string.C204C206)));;
    data.add(new Item("S202", "L221.1", new ArrayList<String>(Arrays.asList("F120")), app.getString(R.string.S202L221_1)));;
    data.add(new Item("P209", "L104", new ArrayList<String>(Arrays.asList("P108")), app.getString(R.string.P209L104)));;
    data.add(new Item("D105", "E112", new ArrayList<String>(Arrays.asList("L125")), app.getString(R.string.D105E112)));;
    data.add(new Item("L125", "D105", new ArrayList<String>(Arrays.asList("D0206", "D0207")), app.getString(R.string.L125D105)));;
    data.add(new Item("L125", "E112", new ArrayList<String>(Arrays.asList("E104", "E105")), app.getString(R.string.L125E112)));;
    data.add(new Item("L129", "L125", new ArrayList<String>(Arrays.asList("L022")), app.getString(R.string.L129L125)));;
    data.add(new Item("L129", "L022", new ArrayList<String>(Arrays.asList("M209")), app.getString(R.string.L129L022)));;
    data.add(new Item("G108", "L224", new ArrayList<String>(Arrays.asList("L335", "A204", "C216")), app.getString(R.string.G108L224)));;
    data.add(new Item("P108", "C216", new ArrayList<String>(Arrays.asList("C226")), app.getString(R.string.P108C216)));;
    data.add(new Item("P108", "A204", new ArrayList<String>(Arrays.asList("A205")), app.getString(R.string.P108A204)));;
    data.add(new Item("P108", "L335", new ArrayList<String>(Arrays.asList("L322")), app.getString(R.string.P108L335)));;
    data.add(new Item("P108", "L224", new ArrayList<String>(Arrays.asList("L221.2")), app.getString(R.string.P108L224)));;
    data.add(new Item("L221.2", "C232", new ArrayList<String>(Arrays.asList("L205")), app.getString(R.string.L221_2C232)));;
    data.add(new Item("C232", "C216", new ArrayList<String>(Arrays.asList("C234")), app.getString(R.string.C232C216)));;
    data.add(new Item("C216", "C114", new ArrayList<String>(Arrays.asList("C008", "A003")), app.getString(R.string.C216C114)));;
    data.add(new Item("M209", "L224", new ArrayList<String>(Arrays.asList("H1")), app.getString(R.string.M209L224)));;
    data.add(new Item("P209", "L205", new ArrayList<String>(Arrays.asList("J105")), app.getString(R.string.P209L205)));;
    data.add(new Item("C226", "A308", new ArrayList<String>(Arrays.asList("H1", "C109")), app.getString(R.string.C226A308)));;
    data.add(new Item("C109", "F120", new ArrayList<String>(Arrays.asList("L339")), app.getString(R.string.C109F120)));;
    //data.add(new item("", "", Arrays.asList(""), ""));;
		
		
    awards = new ArrayList<Award>();
    awards.add(new Award(app.getString(R.string.Zelenáč), app.getString(R.string.Zelenáč_desc), Arrays.asList("L333")));
    awards.add(new Award(app.getString(R.string.Apatie_k_budoucímu_studiu), app.getString(R.string.Apatie_k_budoucímu_studiu_desc), Arrays.asList("L205")));
    awards.add(new Award(app.getString(R.string.Kontrarozvědka), app.getString(R.string.Kontrarozvědka_desc), Arrays.asList("C234")));
    awards.add(new Award(app.getString(R.string.Znalý_světa), app.getString(R.string.Znalý_světa_desc), Arrays.asList("F120")));
    awards.add(new Award(app.getString(R.string.Renesanční_osobnost), app.getString(R.string.Renesanční_osobnost_desc), Arrays.asList("C226", "A205", "L322", "L221.2")));
    awards.add(new Award(app.getString(R.string.Sympatie_k_budoucímu_studiu), app.getString(R.string.Sympatie_k_budoucímu_studiu_desc), Arrays.asList("P108", "E106", "C008", "A003")));
    awards.add(new Award(app.getString(R.string.Docent), app.getString(R.string.Docent_desc), Arrays.asList("L022", "L104", "L125", "L129", "L205", "L221.1", "L221.2", "L224", "L322", "L333", "L335", "L339", "M209", "P108", "P209", "S202")));
    awards.add(new Award(app.getString(R.string.Profesor), app.getString(R.string.Profesor_desc), Arrays.asList("A003", "A204", "A205", "A308", "C008", "C109", "C114", "C204", "C206", "C215", "C216", "C226", "C232", "C234", "D0206", "D0207", "D105", "E104", "E105", "E106", "E112", "F120", "G108", "H1", "J105", "L022", "L104", "L125", "L129", "L205", "L221.1", "L221.2", "L224", "L322", "L333", "L335", "L339", "M209", "P108", "P209", "S202")));
		
	
    rooms = new HashMap<String, Room>();
    rooms.put("A003", new Room("A003", app.getString(R.string.A003N), app.getString(R.string.A003D))); //dalsi?
    rooms.put("A204", new Room("A204", app.getString(R.string.A204N), app.getString(R.string.A204D)));
    rooms.put("A205", new Room("A205", app.getString(R.string.A205N), app.getString(R.string.A205D)));
    rooms.put("A308", new Room("A308", app.getString(R.string.A308N), app.getString(R.string.A308D)));
    rooms.put("C008", new Room("C008", app.getString(R.string.C008N), app.getString(R.string.C008D))); //???
    rooms.put("C109", new Room("C109", app.getString(R.string.C109N), app.getString(R.string.C109D))); //???
    rooms.put("C114", new Room("C114", app.getString(R.string.C114N), app.getString(R.string.C114D)));
    rooms.put("C204", new Room("C204", app.getString(R.string.C204N), app.getString(R.string.C204D)));
    rooms.put("C206", new Room("C206", app.getString(R.string.C206N), app.getString(R.string.C206D)));
    rooms.put("C215", new Room("C215", app.getString(R.string.C215N), app.getString(R.string.C215D)));
    rooms.put("C216", new Room("C216", app.getString(R.string.C216N), app.getString(R.string.C216D)));
    rooms.put("C226", new Room("C226", app.getString(R.string.C226N), app.getString(R.string.C226D)));
    rooms.put("C232", new Room("C232", app.getString(R.string.C232N), app.getString(R.string.C232D)));
    rooms.put("C234", new Room("C234", app.getString(R.string.C234N), app.getString(R.string.C234D)));
    rooms.put("D0206", new Room("D0206", app.getString(R.string.D0206N), app.getString(R.string.D0206D)));
    rooms.put("D0207", new Room("D0207", app.getString(R.string.D0207N), app.getString(R.string.D0207D)));
    rooms.put("D105", new Room("D105", app.getString(R.string.D105N), app.getString(R.string.D105D)));
    rooms.put("E104", new Room("E104", app.getString(R.string.E104N), app.getString(R.string.E104D)));
    rooms.put("E105", new Room("E105", app.getString(R.string.E105N), app.getString(R.string.E105D)));
    rooms.put("E106", new Room("E106", app.getString(R.string.E106N), app.getString(R.string.E106D)));
    rooms.put("E112", new Room("E112", app.getString(R.string.E112N), app.getString(R.string.E112D)));
    rooms.put("F120", new Room("F120", app.getString(R.string.F120N), app.getString(R.string.F120D)));
    rooms.put("G108", new Room("G108", app.getString(R.string.G108N), app.getString(R.string.G108D))); //???
    rooms.put("H1", new Room("H1", app.getString(R.string.H1N), app.getString(R.string.H1D)));
    rooms.put("J105", new Room("J105", app.getString(R.string.J105N), app.getString(R.string.J105D)));
    rooms.put("L022", new Room("L022", app.getString(R.string.L022N), app.getString(R.string.L022D)));
    rooms.put("L104", new Room("L104", app.getString(R.string.L104N), app.getString(R.string.L104D)));
    rooms.put("L125", new Room("L125", app.getString(R.string.L125N), app.getString(R.string.L125D)));
    rooms.put("L129", new Room("L129", app.getString(R.string.L129N), app.getString(R.string.L129D)));
    rooms.put("L205", new Room("L205", app.getString(R.string.L205N), app.getString(R.string.L205D)));
    rooms.put("L221.1", new Room("L221.1", app.getString(R.string.L221_1N), app.getString(R.string.L221_1D)));
    rooms.put("L221.2", new Room("L221.2", app.getString(R.string.L221_2N), app.getString(R.string.L221_2D)));
    rooms.put("L224", new Room("L224", app.getString(R.string.L224N), app.getString(R.string.L224D)));
    rooms.put("L322", new Room("L322", app.getString(R.string.L322N), app.getString(R.string.L322D)));
    rooms.put("L333", new Room("L333", app.getString(R.string.L333N), app.getString(R.string.L333D)));
    rooms.put("L335", new Room("L335", app.getString(R.string.L335N), app.getString(R.string.L335D)));
    rooms.put("L339", new Room("L339", app.getString(R.string.L339N), app.getString(R.string.L339D)));
    rooms.put("M209", new Room("M209", app.getString(R.string.M209N), app.getString(R.string.M209D)));
    rooms.put("P108", new Room("P108", app.getString(R.string.P108N), app.getString(R.string.P108D)));
    rooms.put("P209", new Room("P209", app.getString(R.string.P209N), app.getString(R.string.P209D)));
    rooms.put("S202", new Room("S202", app.getString(R.string.S202N), app.getString(R.string.S202D)));    
	}
	
	
	public ArrayList<String> tryPair(String first, String second) {
		ArrayList<String> res = new ArrayList<String>();
		
		Iterator<Item> it=data.iterator();
        while(it.hasNext())
        {
          Item value=(Item)it.next();
          
          if((value.a.equals(first) && value.b.equals(second)) || (value.a.equals(second) && value.b.equals(first))) {
        	  res = (ArrayList<String>)value.res.clone();
        	  //((ArrayList<String>)res).add(value.description);
        	  res.add(value.description);
        	  break;
          }
        }
		
        
		return res;
	}
	
	
	public boolean testAward(DataModel model) {
		//public boolean hasAward(String award)
		//public boolean addAward(String award)
		//public boolean hasUnlocked(String room)
		
		boolean res = false;

		Iterator<Award> it=awards.iterator();
        while(it.hasNext())
        {
    		Award award=(Award)it.next();
    		ArrayList<String> unlocked = model.getUnlockedRoomsString();
    		
    		//if is awarded
    		if(unlocked.containsAll(award.rooms)) {
    			res |= model.addAward(award.name);
    		}
        }
        
        return res;
	}
  
  public ArrayList<Award> getAwards()
  {
    return awards;
  }
  
  public Room getRoom(String name)
  {
    return rooms.get(name);
  }
	
	
	public void test() {
        Iterator<Item> it=data.iterator();
    	int i = 0;
        
        while(it.hasNext())
        {
          Item value=(Item)it.next();

          System.out.print(i + ". "+value.a+" "+value.b+" (");
          
          Iterator<String> it2=value.res.iterator();
          while(it2.hasNext()) {
        	  String value2 = (String)it2.next();
        	  
        	  System.out.print(value2+", ");
          }
      	  i = i + 1;
          System.out.println(")");
        }
	}
	
}