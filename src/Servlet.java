import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;

import com.google.gson.*;
import com.google.gson.Gson;

import javax.naming.directory.SearchResult;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import com.sun.scenario.effect.light.SpotLight;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;
import javafx.collections.ArrayChangeListener;
import sun.net.www.content.text.plain;

@WebServlet("/Servlet") // mapowanie na serwlet jest potrzebne - tej nazwy uzywamy w dokumencie web.XML aby wskazac miejsce do ktorego przychodz¹ ¿addania

public class Servlet extends HttpServlet{

	private int flag = -1;
	private CpeItem cpeitem;
	private ArrayList<CpeItem> cpeItems = new ArrayList<CpeItem>();
	private static final long serialVersionUID = 1L;
	public  int returnElementsParameter = 0;
	public String searchParam1 = "";
	private int rozmiar = 0;
	public String[] names = new String[10];
	public String[] descr = new String[10];
	ArrayList<String> list = new ArrayList<>();
	ArrayList<CpeItem> searchList = new ArrayList<>();
	ArrayList<CpeItem> resultSearch = new ArrayList<>();
	ArrayList<String> wynik = new ArrayList<>();
	String SearchString="";
	private int searchFlag = 0;
    SAXParser sp = null;
    public int splittingForAll = 0;
   
    public Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	if(flag == -1){
    	try {
    		flag++;
			MainAction();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); 	
			
		}
    	/*for(int i = 0 ; i<(descr.length*2); i++){
    		//System.out.println(descr[i]);
    		if(i<=9){
    		list.add(descr[i]);
    		}
    		if(i>=10){
    		list.add(names[i-10]);
    		}
    	}*/
    	}
    	String json = new Gson().toJson(wynik);
    	response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json); 
}   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String whichPageRequest = request.getParameter("number"); //pobranie do Stringa paramtru przesy³anego przez ¿¹danie
		
		int decision = Integer.parseInt(whichPageRequest); 
		
		/*Jesli decision = 1 to ¿adanie przychodz¹ce do serwera pochodzi od przegladarki*/
		
		if(decision == 1){
		String NRSTRONY = request.getParameter("name"); //Pobranie do stringa nr strony
		System.out.println(NRSTRONY); 
		returnElementsParameter = Integer.parseInt(NRSTRONY);
		
			try {
				MainAction();
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			list.clear();
			/*for(int i = 0 ; i < (descr.length * 2); i++){
				
	    		//System.out.println(descr[i]);
	    		if(i<=9){
	    		list.add(descr[i]);
	    		}
	    		if(i>=10){
	    		list.add(names[i-10]);
	    		}
	    	}	*/
			String json2 = new Gson().toJson(wynik);
	    	response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");
	        response.getWriter().write(json2);
		}
		/*=====Jeœli decision = 2 to ¿¹danie przychodz¹ce do serwera pochodzi od wyszukiwarki*/
		if(decision == 2){
			wynik.clear();
			searchFlag = 1; 
			String QUERY = request.getParameter("query");
			searchParam1 = QUERY;
			try {
				MainAction();
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			
			
			searchFlag =0;
			splittingForAll =0;
			String json3 = new Gson().toJson(wynik);
	    	response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");
	        response.getWriter().write(json3);    
		}
	}
	
	public void splitString(String text){
			
		if(splittingForAll == 0){
			//wynik.clear();
			String[] arrayOfStrings = text.split("cpe:/a:");
			String[] SearchTitlesMatch = new String[10];
			String[] SearchDescrMatch = new  String[10];
			
			for(int i=0; i < 10; i++){
				SearchTitlesMatch[i] = null;
				SearchDescrMatch[i] = null;
			}
			
		for(int i = 0 ; i < 10; i++) {
			
		SearchTitlesMatch[i] = arrayOfStrings[i+1];
		String[] arrayOfStrings2 = SearchTitlesMatch[i].split("titlelang:");
		SearchTitlesMatch[i] =  "cpe:/a: " + arrayOfStrings2[0];
		String[] arrayOfStrings3 = arrayOfStrings2[1].split("title");
		SearchDescrMatch[i] = "Language" + arrayOfStrings2[1];	
		
		
		//System.out.println("\n TITLESMATCHE         " +SearchTitlesMatch[i]);
		}
		wynik.clear();
		for(int i = 0; i < (20); i++){
			if(i < 10){
			wynik.add(SearchTitlesMatch[i]);
			}
			if(i >= 10){
			wynik.add(SearchDescrMatch[i - 10]);
			}
		}
		
	}
		
				if(splittingForAll == 1){
					wynik.clear();
					String[] arrayOfStrings = text.split("cpe:/a:");
					String[] SearchTitlesMatch = new String[resultSearch.size()];
					String[] SearchDescrMatch = new  String[resultSearch.size()];
					
					for(int i=0; i < resultSearch.size(); i++){
						SearchTitlesMatch[i] = null;
						SearchDescrMatch[i] = null;
					}
					
				for(int i = 0 ; i < resultSearch.size(); i++) {
					
				SearchTitlesMatch[i] = arrayOfStrings[i+1];
				String[] arrayOfStrings2 = SearchTitlesMatch[i].split("titlelang:");
				SearchTitlesMatch[i] =  "cpe:/a: " + arrayOfStrings2[0];
				String[] arrayOfStrings3 = arrayOfStrings2[1].split("title");
				SearchDescrMatch[i] = "Language" + arrayOfStrings2[1];	
				//System.out.println("\n TITLESMATCHE         " +SearchTitlesMatch[i]);
				}
				
				for(int i = 0; i < (rozmiar * 2); i++){
					if(i < rozmiar){
					wynik.add(SearchTitlesMatch[i]);
					}
					if(i >= rozmiar){
					wynik.add(SearchDescrMatch[i - SearchDescrMatch.length]);
					}
				}
				
			}
	}
	public void MainAction() throws IOException, SAXException, ParserConfigurationException{

		MOJPARSER handler = null;
				try {
						handler = new MOJPARSER();
					} catch (SAXException | IOException | ParserConfigurationException e) {
					// TODO Auto-generated catch block
						e.printStackTrace();
					}
				if(flag == 0 && searchFlag == 0){ //jesli flaga wyszukiwarki = 0 oraz flaga pierwszego za³adowania = 0
					
					splitString(handler.returnElements(0,10)); //zwracanie pierwszych dziesieciu podatnosci na pierwszym za³adowaniu strony
						flag++;
						//System.out.println(handler.search("Adobe", "name"));
						
				}
				if(flag != 0 && searchFlag == 0){ //kolejne dynamiczne prze³adowania zawarotsci divów, zalezne od podanego parametru
					
						splitString(handler.returnElements((returnElementsParameter * 10), 10)); //0-9 / 10-19 / 20-29/ 30-39 itd
						
				}	
				if(searchFlag == 1){ // jesli wyszukiwarka to flaga searchFlag == 1
					splittingForAll = 1; // teraz dzielenie stringa jest wykonywane dla innych, nieznanych rozmiarow elementow
					resultSearch.clear(); // przed zapisem wyniku funkcji lista jest czyszczona
					
					SearchString = ""; // strig do ktorego zapisyway jest wyik szukania jest za kazdm razem czyszczony
						resultSearch = handler.search(searchParam1, "title"); //wynik funkcji search jest zapisywany do nowej listy typu CpeItem
						//System.out.println(resultSearch.get(1));
						for(int i = 0; i < resultSearch.size(); i++){
							SearchString = SearchString + resultSearch.get(i).toString(); //Poprzednio uzyskany wynik jest zapisywany do jednego Stringa
						}
					
						rozmiar = resultSearch.size();
						System.out.println(rozmiar);
					//	SearchDescrMatch.clear(); //przed wykonaniem podzia³u gotowego stringa listy nalezy wyczyscic
						//SearchTitlesMatch.clear();
						
						splitString(SearchString); //wywolanie funkcji rozdzielajacej nazwy od opisow dla znalezionych wyników
					//	System.out.println(SearchDescrMatch.get(0));
						//searchFlag = 0; //flaga szukania spowrotem ustawiana na 0
						//splittingForAll = 0; // flaga funkcji splitString - mowiaca o tym ze dzielonym nie bedize juz string z zadania wyszukiwarki
				}	
	}
				
}