package generala;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.swing.JOptionPane;

import org.json.JSONObject;

public class Main {

	public static void main(String[] args){
		
		/*Juego generala=new Juego();
		generala.Jugar();*/
		
		ArrayList<Integer> dados = new ArrayList<Integer>();
		
		dados.add(5);
		dados.add(5);
		dados.add(1);
		dados.add(5);
		dados.add(5);
		
		Jugada full= new JugadaFull();
		
		System.out.println(full.encontrada(dados));
		/*Map<String,Integer> tablaResults= new TreeMap<String,Integer>();
		
		
		tablaResults.put("GENERALA",-1);
		tablaResults.put("POKER",-1);
		tablaResults.put("FULL",-1);
		tablaResults.put("ESCALERA",-1);
		tablaResults.put("6",-1);
		tablaResults.put("5",-1);
		tablaResults.put("4",-1);
		tablaResults.put("3",-1);
		tablaResults.put("2",-1);
		tablaResults.put("1",-1);
	
		JSONObject json = new JSONObject();
		json.put("map", tablaResults);
		 
		Map<String,Integer> tablar= new TreeMap<String,Integer>();
		
		JSONObject mapa = json.getJSONObject("map");
		Integer a = mapa.getInt("GENERALA");
		Integer b = mapa.getInt("ESCALERA");
		Integer c = mapa.getInt("POKER");
		Integer d = mapa.getInt("FULL");
		Integer k = mapa.getInt("1");
		Integer f = mapa.getInt("2");
		Integer g = mapa.getInt("3");
		Integer h = mapa.getInt("4");
		Integer i = mapa.getInt("5");
		Integer j = mapa.getInt("6");
		tablar.put("GENERALA",a );
		tablar.put("ESCALERA",b );
		tablar.put("POKER",c );
		tablar.put("FULL",d );
		tablar.put("1", k );
		tablar.put("2",f );
		tablar.put("3",g );
		tablar.put("4",h );
		tablar.put("5",i );
		tablar.put("5",j );*/
		
	    /*Iterator iter = mapa.keys();
		while (iter.hasNext()) {
			String key = (String) iter.next();
			Integer value = mapa.getInt(key);
			
			tablar.put(key,value);
			
		}*/
		 
		/* Set<Map.Entry<String,Integer>>
		 lista=tablar.entrySet();
         		
		 for (Map.Entry<String,Integer> e:lista) 
		 {
			 System.out.println(e.getKey() + " " + e.getValue());
			 
		 }*/
	}
	
}