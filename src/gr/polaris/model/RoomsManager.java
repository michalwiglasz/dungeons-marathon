package gr.polaris.model;

import gr.polaris.R;
import gr.polaris.application.BlbecekApp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Arrays;


public class RoomsManager 
{
  private BlbecekApp app;
	//private ArrayList<ArrayList<String> > data;
/*	
	private String test[][][] = {{{"L224"}, {""}, {"E106", "A308", "C215", "L129"}},
			{{""}, {""}, {""}},
			};
*/
	ArrayList<Item> data;
	ArrayList<Award> awards;
	ArrayList<Room> rooms;
	
	public RoomsManager(BlbecekApp context)
	{
	  app = context;
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
		
		data.add(new Item("L224", "", new ArrayList<String>(Arrays.asList("A308", "E106", "C215", "L129")), "Doc. Herout stvořil maraton a proto vytváří i základní stavební kameny naší hry.")); // Herout -> Křena, Juřiček, Zendulka, Lampa
		data.add(new Item("E106", "A308", new ArrayList<String>(Arrays.asList("L333")), "Bakaláři (které \"vede\" proděkan Křena) jsou povyšováni do vyšších stavů (ty \"spravuje\" proděkan Růžička) za pomoci insignie, která se často ocitá v rukou pana Juřička."));
		data.add(new Item("L333", "E106", new ArrayList<String>(Arrays.asList("C232")), "Magistři (které \"vede\" proděkan Růžička) jsou povyšováni do vyšších stavů (ty \"spravuje\" proděkan Hruška) za pomoci insignie, která se často ocitá v rukou pana Juřička."));
		data.add(new Item("C232", "C215", new ArrayList<String>(Arrays.asList("C204")), "Minulý a nýnější děkan naší fakulty reprezentují funkci děkana."));;
		data.add(new Item("C215", "C204", new ArrayList<String>(Arrays.asList("L339", "L221.1", "S202", "C206")), "Vědecký pracovník si ve funkci děkana volí pracovní tým proděkanů a dalších pracovníků na své volební období."));;
		data.add(new Item("C204", "C206", new ArrayList<String>(Arrays.asList("G108", "P209", "L104", "E112" ,"D105", "C114")), "Tajemník FITu je pověřen děkanem o správu majetku školy."));;
		data.add(new Item("S202", "L221.1", new ArrayList<String>(Arrays.asList("F120")), "Proděkan Zemčík vyřeší spoustu zahraničních problémů. Společně s doc. Drahanským jsou vskutku schopni komunikovat i s (Jižní?) Koreou. Většinu záležitostí ale stejně vyřešítě u Ing. Studené."));;
		data.add(new Item("P209", "L104", new ArrayList<String>(Arrays.asList("P108")), "Stačí Vám průměrné jídlo z menzy a líbí se Vám v naší kavárně? Potom je pro Vás (nyní již zavřená) restaurace v areálu FIT jako stvořená."));;
		data.add(new Item("D105", "E112", new ArrayList<String>(Arrays.asList("L125")), "Obě naše velké a honosné přednáškové místnosti by dosahovaly bezmála polovičních kvalit, kdyby (ne)bylo možné z nich sledovat online stream na kolejích a záznamy (ne)zrychleně před zkouškou. Nejen od tohoto je na fakultě Mgr. Skokanová, která se o vše postará."));;
		data.add(new Item("L125", "D105", new ArrayList<String>(Arrays.asList("D0206", "D0207")), "Stream umožňuje spojit místnost D105 s menšími D0206 a D0207."));;
		data.add(new Item("L125", "E112", new ArrayList<String>(Arrays.asList("E104", "E105")), "Stream umožňuje spojit místnost E112 s menší E104 a do začátku tohoto semestru bylo možné využívat i E105 (problém se \"nedaří lokalizovat\")."));;
		data.add(new Item("L129", "L125", new ArrayList<String>(Arrays.asList("L022")), "Jak již bylo řečeno, video streaming je velkou (zho|chlo)ubou naší fakulty. K jeho provozu jsou nutné video servery a komunikace mezi pracovníky CVT. Jako sklad videozáznamů slouží 8 videoserverů s celkovou kapacitou 125TB dat (právě v srpnu 2012 byla kapacita zvětšena skoro o 50%)."));;
		data.add(new Item("L129", "L022", new ArrayList<String>(Arrays.asList("M209")), "Mnoho serverů spolu s houževnatým nasazením pracovníků umožňuje provozovat rozsáhlé prostory CVT."));;
		data.add(new Item("G108", "L224", new ArrayList<String>(Arrays.asList("L335", "A204", "C216")), "Pozvání od zástupce vedoucího UPGM doc. Herouta do krásné konferenční místnosti G108 nemůže žádný jiný zástupce vedoucího ústavu odmítnout."));;
		data.add(new Item("P108", "C216", new ArrayList<String>(Arrays.asList("C226")), "Kariérní postup na vedoucího ústavu provede každý zástupce ústavu nejlépe na oslavě v restauraci, rautu v restauraci, obědu v restauraci a nebo večeři v restauraci (restaurace na FIT byla zrušena, nedá se proto počítat s mnoha změnami ve vedení)."));;
		data.add(new Item("P108", "A204", new ArrayList<String>(Arrays.asList("A205")), "Kariérní postup na vedoucího ústavu provede každý zástupce ústavu nejlépe na oslavě v restauraci, rautu v restauraci, obědu v restauraci a nebo večeři v restauraci (restaurace na FIT byla zrušena, nedá se proto počítat s mnoha změnami ve vedení)."));;
		data.add(new Item("P108", "L335", new ArrayList<String>(Arrays.asList("L322")), "Kariérní postup na vedoucího ústavu provede každý zástupce ústavu nejlépe na oslavě v restauraci, rautu v restauraci, obědu v restauraci a nebo večeři v restauraci (restaurace na FIT byla zrušena, nedá se proto počítat s mnoha změnami ve vedení)."));;
		data.add(new Item("P108", "L224", new ArrayList<String>(Arrays.asList("L221.2")), "Kariérní postup na vedoucího ústavu provede každý zástupce ústavu nejlépe na oslavě v restauraci, rautu v restauraci, obědu v restauraci a nebo večeři v restauraci (restaurace na FIT byla zrušena, nedá se proto počítat s mnoha změnami ve vedení)."));;
		data.add(new Item("L221.2", "C232", new ArrayList<String>(Arrays.asList("L205")), "Proděkanovi pro doktorské studium a vedoucímu UPGM se povedlo vměstnat 14 doktorandů do jedné místnosti. Obdivuhodný výkon."));;
		data.add(new Item("C232", "C216", new ArrayList<String>(Arrays.asList("C234")), "O našeho oblíbeného Petra \"S3rváce\" Zemka se starají jeho školitel prof. Meduna a prof. proděkan doktorského studia prof. Hruška."));;
		data.add(new Item("C216", "C114", new ArrayList<String>(Arrays.asList("C008", "A003")), "Profesor Meduna spolu s literaturou, která je samozřejmě dostupná v naší knihovně, pravidelně recituje v malebných prostorách vinárny a sklepa."));;
		data.add(new Item("M209", "L224", new ArrayList<String>(Arrays.asList("H1")), "Je s údivem, že doc. Herout při pravidelných maratonech konaných v prostorách CVT ještě nepřivedl žádného studenta do márnice."));;
		data.add(new Item("P209", "L205", new ArrayList<String>(Arrays.asList("J105")), "Naše menza umí spolu se vstupem z masokombinátu často vyprodukovat velmi kvalitní odpad."));;
		data.add(new Item("C226", "A308", new ArrayList<String>(Arrays.asList("H1", "C109")), "Doc. Kolář společně s Dr. Křenou se každoročně postarají o to, že mnoho studentů musí vyhledávat návštěvy studijního oddělení a myslet přitom na márnici."));;
		data.add(new Item("C109", "F120", new ArrayList<String>(Arrays.asList("L339")), "Ve chvíli kdy studentovi FIT nevyhoví studijní oddělení ani Mgr. Studená zbývá terminální možnost a to návštěva Ing. Eysselta."));;
		//data.add(new item("", "", Arrays.asList(""), ""));;
		
		
		awards = new ArrayList<Award>();
		awards.add(new Award("Kontrarozvědka", "Od doby co jsi poznal všechny skryté uživatele fitušky Tě nic nemůže překvapit. Fórum se pro Tebe zase stalo hostinným prostředím.", Arrays.asList("C234")));
		awards.add(new Award("Profesor", "Objevil jsi celý areál FIT! Katedra \"samočinných počítačů\" byla do areálu Božetěchova 2 přesunuta z elektrotechnické fakulty na Antonínská 1 (dnešní rektorát VUT) v roce 1968 (objekt získalo VUT v roce 1963 ve značně zchátralém stavu). Dnes je celková užitná plocha 17 280m2 z toho výuková 12 323m2. Celkem je v posluchárnách na fakultě 880 míst.", Arrays.asList("A003", "A204", "A205", "A308", "C008", "C109", "C114", "C204", "C206", "C215", "C216", "C226", "C232", "C234", "D0206", "D0207", "D105", "E104", "E105", "E106", "E112", "F120", "G108", "H1", "J105", "L022", "L104", "L125", "L129", "L205", "L221.1", "L221.2", "L224", "L322", "L333", "L335", "L339", "M209", "P108", "P209", "S202")));
		awards.add(new Award("Docent", "Objevil jsi vše v budově Božetěchova 1. FIT se o tyto prostory rozšířil v roce 2006. Stavba nové budovy spolu s rekontrukcí kláštera stála 700mil. kč.", Arrays.asList("L022", "L104", "L125", "L129", "L205", "L221.1", "L221.2", "L224", "L322", "L333", "L335", "L339", "M209", "P108", "P209", "S202")));
		awards.add(new Award("Renesanční osobnost", "Prokázal jsi heroické schopnosti, když jsi pochopil všechny vědomosti FITu. K tomu Ti dopomohlo objevení všech vedoucích jednotlivých ústavů.", Arrays.asList("C226", "A205", "L322", "L221.2")));
		awards.add(new Award("Znalý světa", "Program ERASMUS Ti rozšířil obzory.", Arrays.asList("F120")));
		awards.add(new Award("Apatie k budoucímu studiu", "Po důkladném prozkoumání areálu jsi zjistit, že být doktorantem nemusí být zrovna med.", Arrays.asList("L205")));
		awards.add(new Award("Sympatie k budoucímu studiu", "Objevil jsi všechna místa, kde se dá na fakultě sehnat medicína, která vyřeší všechny Tvé problémy.", Arrays.asList("P108", "E106", "C008", "A003")));
		awards.add(new Award("Zelenáč", "Jsi na správné cestě. Ve své sbírce máš druhého proděkana.", Arrays.asList("L333")));
		
	
		
		rooms = new ArrayList<Room>();
		rooms.add(new Room("A003", "Sklep", "Sklepní místnost v podzemí FIT. Je přístupná pouze z podzemní chodby, která spojuje obě budovy areálu FIT.")); //dalsi?
		rooms.add(new Room("A204", "Doc. Zbořil", "Zástupce vedoucího UITS."));
		rooms.add(new Room("A205", "Doc. Hanáček", "Vedoucí UITS. "));
		rooms.add(new Room("A308", "Dr. Křena", "Proděkan pro bakalářské studium. Pro zajímavoust pracovna A308 obsahuje postel."));
		rooms.add(new Room("C008", "Vinárna", "Přítomnost vína zatím nebyla potvrzena (kavárna to jistí).")); //???
		rooms.add(new Room("C109", "Studijní oddělení", "Nevidím, neslyším, nevím.")); //???
		rooms.add(new Room("C114", "Knihovna", ""));
		rooms.add(new Room("C204", "Pracovna děkana", ""));
		rooms.add(new Room("C206", "Ing. Bouša", "Tajemník"));
		rooms.add(new Room("C215", "Doc. Zendulka", "Děkan FIT"));
		rooms.add(new Room("C216", "Prof. Meduna", "Zástupce vedoucího UIFS."));
		rooms.add(new Room("C226", "Doc. Kolář", "Vedoucí UIFS"));
		rooms.add(new Room("C232", "Prof. Hruška", "Proděkan pro doktorské studium."));
		rooms.add(new Room("C234", "Ing. Zemek", "S3rvác"));
		rooms.add(new Room("D0206", "Posluchárna", "Nejhlubší posluchárna na FIT o kapacitě 90 míst."));
		rooms.add(new Room("D0207", "Posluchárna", "Nejhlubší posluchárna na FIT o kapacitě 154 míst."));
		rooms.add(new Room("D105", "Posluchárna", "Největší posluchárna na FIT o kapacitě 300 míst."));
		rooms.add(new Room("E104", "Posluchárna", "Posluchárna o kapacitě 72 míst."));
		rooms.add(new Room("E105", "Posluchárna", "Posluchárna o kapacitě 72 míst."));
		rooms.add(new Room("E106", "Pan Juřiček", "Práce všeho druhu denně od sedmi do devíti. Nejznámější klapka na FITu - 1191."));
		rooms.add(new Room("E112", "Posluchárna", "Nejstarší posluchárna na FIT pro 156 posluchačů."));
		rooms.add(new Room("F120", "Ing. Studená", "Zahraniční oddělení."));
		rooms.add(new Room("G108", "Zasedací místnost", "Zasedací místnost G108 patří k nejhezčím na FIT. Nachází se v ní velký oválný stůl. Strop místnosti zdobí restaurovaná barokní klenba.")); //???
		rooms.add(new Room("H1", "Márnice", "Dobová márnice "));
		rooms.add(new Room("J105", "Odpady", ""));
		rooms.add(new Room("L022", "Sál serverů", "Serverovna po rekonstrukci v roce 2012 dokáže uchladit a v případě výpadku elektrické energie i zálohovat zařízení o příkonu až 90kW."));
		rooms.add(new Room("L104", "Kavárna Ventana Café", ""));
		rooms.add(new Room("L125", "Mgr. Skokanová", "Streaming a videozáznamy."));
		rooms.add(new Room("L129", "Ing. Lampa", "Správce počítačové sítě FIT."));
		rooms.add(new Room("L205", "Masokombinát", "Studenti doktorského studia zde předvádí pravidelné heroické výkony. Nejspíše z nedostatku místa jich zde musí přebývat 14 a to na ploše ne větší jako 40m2. Druhým vysvětlením může být chyba v IS FIT."));
		rooms.add(new Room("L221.1", "Doc. Zemčík", "Proděkan pro vnější vztahy."));
		rooms.add(new Room("L221.2", "Doc. Černocký", "Vedoucí UPGM."));
		rooms.add(new Room("L224", "Doc. Herout", "Zástupce vedoucího UPGM."));
		rooms.add(new Room("L322", "Doc. Kotásek", "Vedoucí UPSY."));
		rooms.add(new Room("L333", "Doc. Růžička", "Proděkan pro magisterské studium."));
		rooms.add(new Room("L335", "Prof. Sekanina", "Zástupce vedoucího UPSY."));
		rooms.add(new Room("L339", "Ing. Eysselt", "Studijní poradce. Některá písmenka: á, Á, é, É, ě, Ě, í, Í, ó, ú, Ú, ů, Ů, ý, Ý, č, Č, ď, ň, ř, Ř, š, Š, ť, ž, Ž."));
		rooms.add(new Room("M209", "Vrátnice CVT", ""));
		rooms.add(new Room("P108", "Restaurace", "Od 1.9.2012 je restaurace Starý pivovar z neznámého důvodu uzavřena."));
		rooms.add(new Room("P209", "Menza", "Nekřesťansky skvělá jídla za nekřesťansky nízké ceny. Cena jednoho jídla v menze se počítá podle vzorce C = (c - d + 29) * 1.2 + 0.49 Kč, kde c jsou náklady na potraviny v jedné porci a d je dotace (17,45)."));
		rooms.add(new Room("S202", "Doc. Drahanský", "Koordinátor zahraniční mobility."));		
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
    		ArrayList<String> unlocked = model.getUnlockedRooms();
    		
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