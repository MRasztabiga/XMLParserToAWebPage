import java.util.ArrayList;

public class CpeItem {
	
	String name, titleLang, title, cpe23;
	ArrayList<Ref> references = new ArrayList<Ref>();
	
	void addRef(String s1, String s2) {
		references.add(new Ref(s1,s2));
	}
	
	public String toStringLadny() {	//ladnie na konsoli
		
		String result = "";
		
		result+="\n"+name;
		result+="\n \t"+titleLang;
		result+="\nTitle: \t\t"+title;
	//	result+="\ncpe23: \t\t"+cpe23;
		
		for(Ref r : references) {
			result+="\nHref: \t\t"+r.href;
			result+="\nDescription: \t"+r.ref;
		}
		
		return result;
	}
	
	public String toString() {	//bez zadnych innych znakow i napisow przed
		
		String result="";
		
		result+=name;
		result+=titleLang;
		result+=title;
		
		for(Ref r : references) {
			result+=r.href;
			result+=r.ref;
		}
		return result;
	}
	
	public static String ArrayCpeItemToString(ArrayList<CpeItem> array) {
    	
		String result = "";
    	
		for(CpeItem i : array) {
    		result += i.toStringLadny();
    	}
    	return result;
    }
}






































/*
import java.util.ArrayList;

public class CpeItem {
	String name;
	String titleLang, title;
	String cpe23;
	ArrayList<Ref> references=new ArrayList<Ref>();
	
	void addRef(String s1, String s2){
		references.add(new Ref(s1,s2));
		}
	
	public String toStringLadny(){	//ladnie na konsoli
		String result="";
		
		result+="name: "+name;
		result+="\n\t titlelang: "+titleLang;
		result+="\n\t title: "+title;
		result+="\n\t cpe23: "+cpe23;
		for(Ref r : references){
			result+="\n\t href: "+r.href;
			result+="\n\t description: "+r.ref;
		}
		
		return result;
	}
	public String toString(){	//bez zadnych innych znakow i napisow przed
		String result="";
		
		result+=name;
		result+="\n"+titleLang;
		result+="\n"+title;
		result+="\n"+cpe23;
		for(Ref r : references){
			result+="\n"+r.href;
			result+="\n"+r.ref;
		}
		
		return result;
	}
	
	public static String ArrayCpeItemToString(ArrayList<CpeItem> array){
    	String result = "";
    	for(CpeItem i : array){
    		result+=i.toString();
    	}
    	
    	return result;
    }
}
*/

