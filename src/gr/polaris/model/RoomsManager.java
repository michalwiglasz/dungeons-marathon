package gr.polaris.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Arrays;


public class RoomsManager {

	List<Item> data;
	List<Award> awards;
	List<Room> rooms;
	
	public RoomsManager() {
/*	
		data = new ArrayList<ArrayList<String> >();
		
		data.add(new ArrayList<String>());
		data.get(data.size()-1).add("asd");
		data.get(data.size()-1).add("asd");
		data.get(data.size()-1).add("asd");
		
		System.out.println(data.get(0).get(0));
*/		
		
		//test read
		data = new ArrayList<Item>();
		
		data.add(new Item("L224", "", Arrays.asList("A308", "E106", "C215", "L129")));
		data.add(new Item("E106", "A308", Arrays.asList("L333")));
		data.add(new Item("L333", "E106", Arrays.asList("C232")));
		data.add(new Item("C232", "C215", Arrays.asList("C204")));
		data.add(new Item("C215", "C204", Arrays.asList("L339", "L221.1", "S202", "C206")));
		data.add(new Item("C204", "C206", Arrays.asList("G108", "P209", "L104", "E112" ,"D105", "C114")));
		data.add(new Item("S202", "L221.1", Arrays.asList("F120")));
		data.add(new Item("P209", "L104", Arrays.asList("P108")));
		data.add(new Item("D105", "E112", Arrays.asList("L125")));
		data.add(new Item("L125", "D105", Arrays.asList("D0206", "D0207")));
		data.add(new Item("L125", "E112", Arrays.asList("E104", "E105")));
		data.add(new Item("L129", "L125", Arrays.asList("L022")));
		data.add(new Item("L129", "L022", Arrays.asList("M209")));
		data.add(new Item("G108", "L224", Arrays.asList("L335", "A204", "C216")));
		data.add(new Item("P108", "C216", Arrays.asList("C226")));
		data.add(new Item("P108", "A204", Arrays.asList("A205")));
		data.add(new Item("P108", "L335", Arrays.asList("L322")));
		data.add(new Item("P108", "L224", Arrays.asList("L221.2")));
		data.add(new Item("L221.2", "C232", Arrays.asList("L205")));
		data.add(new Item("C232", "C216", Arrays.asList("C234")));
		data.add(new Item("C216", "C114", Arrays.asList("C008", "A003")));
		data.add(new Item("M209", "L224", Arrays.asList("H1")));
		data.add(new Item("P209", "L205", Arrays.asList("J105")));
		data.add(new Item("C226", "A308", Arrays.asList("H1", "C109")));
		data.add(new Item("C109", "F120", Arrays.asList("L339")));
		//data.add(new item("", "", Arrays.asList("")));
		
		
		awards = new ArrayList<Award>();
		awards.add(new Award("Kontrarozvìdka", "Od doby co jsi poznal všechny skryté uživatele fitušky Tì nic nemùže pøekvapit. Fórum se pro Tebe zase stalo hostinným prostøedím.", Arrays.asList("C234")));
		awards.add(new Award("Profesor", "Objevil jsi celý areál FIT! Katedra \"samoèinných poèítaèù\" byla do areálu Božetìchova 2 pøesunuta z elektrotechnické fakulty na Antonínská 1 (dnešní rektorát VUT) v roce 1968.", Arrays.asList("A003", "A204", "A205", "A308", "C008", "C109", "C114", "C204", "C206", "C215", "C216", "C226", "C232", "C234", "D0206", "D0207", "D105", "E104", "E105", "E106", "E112", "F120", "G108", "H1", "J105", "L022", "L104", "L125", "L129", "L205", "L221.1", "L221.2", "L224", "L322", "L333", "L335", "L339", "M209", "P108", "P209", "S202")));
		awards.add(new Award("Docent", "Objevil jsi vše v budovì Božetìchova 1. FIT se o tyto prostory rozšíøil v roce 2006. Stavba nové budovy spolu s rekontrukcí kláštera stála 700mil. kè.", Arrays.asList("L022", "L104", "L125", "L129", "L205", "L221.1", "L221.2", "L224", "L322", "L333", "L335", "L339", "M209", "P108", "P209", "S202")));
		awards.add(new Award("Renesanèní osobnost", "Prokázal jsi heroické schopnosti, když jsi pochopil všechny vìdomosti FITu. K tomu Ti dopomohlo objevení všech vedoucích jednotlivých ústavù.", Arrays.asList("C226", "A205", "L322", "L221.2")));
		awards.add(new Award("Znalý svìta", "Program ERASMUS Ti rozšíøil obzory.", Arrays.asList("F120")));
		awards.add(new Award("Apatie k budoucímu studiu", "Po dùkladném prozkoumání areálu jsi zjistit, že být doktorantem nemusí být zrovna med.", Arrays.asList("L205")));
		awards.add(new Award("Sympatie k budoucímu studiu", "Objevil jsi všechna místa, kde se dá na fakultì sehnat medicína, která vyøeší všechny Tvé problémy.", Arrays.asList("P108", "E106", "C008", "A003")));
		awards.add(new Award("Zelenáè", "Jsi na správné cestì. Ve své sbírce máš druhého prodìkana.", Arrays.asList("L333")));
		
	
		
		rooms = new ArrayList<Room>();
		rooms.add(new Room("A003", "Sklep", "Sklepní místnost v podzemí FIT. Je přístupná pouze z podzemní chodby, která spojuje obě budovy areálu FIT.")); //dalsi?
		rooms.add(new Room("A204", "Doc. Zbořil", "Zástupce vedoucího UITS."));
		rooms.add(new Room("A205", "Doc. Hanáček", "Vedoucí UITS. "));
		rooms.add(new Room("A308", "Dr. Křena", "Proděkan pro bakalářské studium. Pro zajímavoust pracovna A308 obsahuje postel."));
		rooms.add(new Room("C008", "", ""));
		rooms.add(new Room("C109", "", ""));
		rooms.add(new Room("C114", "", ""));
		rooms.add(new Room("C204", "", ""));
		rooms.add(new Room("C206", "", ""));
		rooms.add(new Room("C215", "", ""));
		rooms.add(new Room("C216", "", ""));
		rooms.add(new Room("C226", "", ""));
		rooms.add(new Room("C232", "", ""));
		rooms.add(new Room("C234", "", ""));
		rooms.add(new Room("D0206", "", ""));
		rooms.add(new Room("D0207", "", ""));
		rooms.add(new Room("D105", "", ""));
		rooms.add(new Room("E104", "", ""));
		rooms.add(new Room("E105", "", ""));
		rooms.add(new Room("E106", "", ""));
		rooms.add(new Room("E112", "", ""));
		rooms.add(new Room("F120", "", ""));
		rooms.add(new Room("G108", "", ""));
		rooms.add(new Room("H1", "", ""));
		rooms.add(new Room("J105", "", ""));
		rooms.add(new Room("L022", "", ""));
		rooms.add(new Room("L104", "", ""));
		rooms.add(new Room("L125", "", ""));
		rooms.add(new Room("L129", "", ""));
		rooms.add(new Room("L205", "", ""));
		rooms.add(new Room("L221.1", "", ""));
		rooms.add(new Room("L221.2", "", ""));
		rooms.add(new Room("L224", "", ""));
		rooms.add(new Room("L322", "", ""));
		rooms.add(new Room("L333", "", ""));
		rooms.add(new Room("L335", "", ""));
		rooms.add(new Room("L339", "", ""));
		rooms.add(new Room("M209", "", ""));
		rooms.add(new Room("P108", "", ""));
		rooms.add(new Room("P209", "", ""));
		rooms.add(new Room("S202", "", ""));		
	}
	
	
	public List<String> tryPair(String first, String second) {
		List<String> res = (List<String>) new ArrayList<String>();
		
		Iterator<Item> it=data.iterator();
        while(it.hasNext())
        {
          Item value=it.next();
          
          if((value.a.equals(first) && value.b.equals(second)) || (value.a.equals(second) && value.b.equals(first))) {
        	  res = value.res;
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
    		Award award=it.next();
    		List<String> unlocked = model.getUnlockedRooms();
    		
    		//if is awarded
    		if(unlocked.containsAll(award.rooms)) {
    			res |= model.addAward(award.name);
    		}
        }
        
        return res;
	}
	
	
	public void test() {
        Iterator<Item> it=data.iterator();
    	int i = 0;
        
        while(it.hasNext())
        {
          Item value=it.next();

          System.out.print(i + ". "+value.a+" "+value.b+" (");
          
          Iterator<String> it2=value.res.iterator();
          while(it2.hasNext()) {
        	  String value2 = it2.next();
        	  
        	  System.out.print(value2+", ");
          }
      	  i = i + 1;
          System.out.println(")");
        }
	}
}