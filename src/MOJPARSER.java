import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

import javax.naming.directory.SearchResult;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MOJPARSER extends DefaultHandler {
	
	String temp;
	
    private CpeItem cpeitem;
    private ArrayList<CpeItem> cpeItems = new ArrayList<CpeItem>();
    SAXParserFactory spfac;
    SAXParser sp;
    
	MOJPARSER() throws SAXException, IOException, ParserConfigurationException {
       
		spfac = SAXParserFactory.newInstance();
        sp = spfac.newSAXParser();
        System.out.println("Czytam plik..........");
        sp.parse("file:C:/Users/Micha³/Desktop/eclipse_JEE/PPB_XML/XML_PARSER/src/official-cpe-dictionary_v2.3.xml", this);
        this.read();
    }
    
    public void characters(char[] buffer, int start, int length) {
           temp = new String(buffer, start, length);
    } 
   
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        
    	temp = "";
           
        if (qName.equalsIgnoreCase("cpe-item")) {
        	cpeitem = new CpeItem();
            cpeitem.name=attributes.getValue("name");
        }
  
        if (qName.equalsIgnoreCase("title")) {
        	cpeitem.titleLang="titlelang: ";
            cpeitem.titleLang+=attributes.getValue("xml:lang");
        }
        
        if (qName.equalsIgnoreCase("cpe-23:cpe23-item")) {
        	cpeitem.name+="\n";
        	cpeitem.name+=attributes.getValue("name");
        }
        
        if (qName.equalsIgnoreCase("reference")) {
           cpeitem.addRef(attributes.getValue("href"),"");
		}
    }
    
    public void endElement(String uri, String localName, String qName) throws SAXException {

	    if (qName.equalsIgnoreCase("cpe-item")) { 	  // add it to the list  
	    	cpeItems.add(cpeitem);
	    }
	    else if (qName.equalsIgnoreCase("title")) {
	    	cpeitem.title=temp;
	    } 
	    else if (qName.equalsIgnoreCase("reference")) {
	    	cpeitem.references.get(cpeitem.references.size()-1).ref=temp;
	    }
    }
    
    public void read() {
    	
        System.out.println("Liczba elementow: " + cpeItems.size()  + ".");
        
        for(CpeItem i : cpeItems) {
        	 //System.out.println(i.toString());												
        }
   		System.out.println("SKONCZYLEM\n.");													
    }       
   			
    public ArrayList<CpeItem> search(String query, String whatfield) {	
    	
    	query = query.toLowerCase();
    	
    	int matchesCounter=0;
    	ArrayList<CpeItem> result = new ArrayList<CpeItem>();
    	
    	if(whatfield=="name"){
    		for(CpeItem i : cpeItems){
    			if(i.name.toLowerCase().contains(query)==true){
    				result.add(i);
    				matchesCounter++;
    			}
    		}
    	}else if(whatfield=="title"){
    		for(CpeItem i : cpeItems){
    			if(i.title.toLowerCase().contains(query)==true){
    				result.add(i);
    				matchesCounter++;
    			}
    		}
    	}else if(whatfield=="cpe23"){
    		for(CpeItem i : cpeItems){
    			if(i.cpe23.toLowerCase().contains(query)==true){
    				result.add(i);
    				matchesCounter++;
    			}
    		}
    	}else if(whatfield=="href"){
    		for(CpeItem i : cpeItems){
    			for(Ref j : i.references){
    				if(j.ref.toLowerCase().contains(query) ){
    					result.add(i);
    					matchesCounter++;
    				} 
    			}
    		}
    	}
    	else if(whatfield=="description"){
    		for(CpeItem i : cpeItems){
    			for(Ref j : i.references){
    				if(j.ref.toLowerCase().contains(query)){
    					result.add(i);
    					matchesCounter++;
    				} 
    			}
    		}
    	}
    	return result;
    }
    
    public String returnElements(int odKtorego, int ile) {
    
    	String result = "";
    		
    	for(int i=odKtorego; i<odKtorego+ile; i++){
    		result += cpeItems.get(i).toStringLadny();
    	}
    	//System.out.println("WESZ£O DO RETURN ELEMENST");
    	return result;
    }
}