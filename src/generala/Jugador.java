package generala;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.JOptionPane;

import org.json.JSONArray;
import org.json.JSONObject;


public class Jugador implements ObjetoJasoneable {

	private String nombre;
	private ArrayList<Integer> separados;
	private Map<String,Integer> tablaResults= new TreeMap<String,Integer>();
	private ArrayList<Integer> listaDados;
	private ArrayList<Integer> separadosPrevio;
	private static final int puntosTachar=0;
	int vueltaXJugador;
	
	public Jugador(String nombre)
	{
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
		setNombre(nombre);
		setListaDados(new ArrayList<Integer>());
		setSeparados(new ArrayList<Integer>());
		setTablaResults(tablaResults);
		setSeparadosPrevio(new ArrayList<Integer>());	
		setVueltaXJugador(1);
	}
	
	public Jugador(JSONObject jsonObjJugador)
	{
		this.setNombre(jsonObjJugador.getString("nombre"));
		JSONArray jseparados = jsonObjJugador.getJSONArray("separados");
		for (int i = 0; i < jseparados.length(); i++) 
		{
			this.separados.add(jseparados.getInt(i));
		}
		
		JSONArray listaDados = jsonObjJugador.getJSONArray("dados");
		for (int i = 0; i < listaDados.length(); i++)
		{
			this.listaDados.add(listaDados.getInt(i));
		}
		
		JSONArray jseparadosPrevio = jsonObjJugador.getJSONArray("separadosPrevio");
		for (int i = 0; i < jseparadosPrevio.length(); i++) 
		{
			this.separadosPrevio.add(jseparadosPrevio.getInt(i));
		}
		
		this.setVueltaXJugador(jsonObjJugador.getInt("vueltaXJugador"));
		
		Map<String,Integer> results= new TreeMap<String,Integer>();
		JSONObject mapa = jsonObjJugador.getJSONObject("tablaResult");
	    Iterator<String> iter = mapa.keys();
		while (iter.hasNext())
		{
			String key = (String) iter.next();
			Integer value = mapa.getInt(key);
			results.put(key,value);
		}
		this.setTablaResults(results);
	}

	public boolean recuperarDados(ArrayList<Integer>listaSeparadosPrevio, int input)
	{
		return separadosPrevio.contains(input);
	}
	
	public void devolverAListaDeDados(int input)
	{
		int posicion= getSeparadosPrevio().indexOf(input);
		getSeparadosPrevio().remove(posicion);
		agregarDado(input);
	}
	
	public boolean separarDados(ArrayList<Integer>listaDeDados, int input)
	{
		return listaDeDados.contains(input);
	}
	
	public void agregarSeparadoPrevio(int input)
	{
		int posicion= getListaDados().indexOf(input);
		getListaDados().remove(posicion);
		getSeparadosPrevio().add(input);
	}
	
	public String menuRecuperarDados()
	{
		int num=0;
		String menu="";
		for(int i=0; i < getSeparadosPrevio().size(); i++)
		{
			num++;
			menu= menu + "Dado: " + num + " valor: " + getSeparadosPrevio().get(i).toString() + "\n";
		}
		return menu;
	}
	
	public String menuSepararDados()
	{
		int num=0;
		String menu="";
		for(int i=0; i < getListaDados().size(); i++)
		{
			num++;
			menu= menu + "Dado: " + num + " valor: " + getListaDados().get(i).toString() + "\n";
		}
		return menu;
	}
	
	public void agregarDado(int dado)
	{
		getListaDados().add(dado);
	}
	
	public void borrarListaDados()
	{
		getListaDados().clear();
	}
	
	public void borrarListaseparados()
	{
		getSeparados().clear();
	}
	
	public void borrarListaSeparadosPrevio()
	{
		getSeparadosPrevio().clear();
	}
	
	public void reverse()
	{
		final int RESTA=7;
		ArrayList<Integer>lista=new ArrayList<Integer>();
		for (int i = 0; i < getListaDados().size(); i++) 
		{
			lista.add(RESTA-getListaDados().get(i));
		}
			
		getListaDados().clear();
		getListaDados().addAll(lista);
		lista.clear();
		
		int dado=0;
		for(int i = 0; i <getListaDados().size(); i++)
		{
			dado++;
			System.out.println("Reverse" + "Dado: " + dado + " valor " + getListaDados().get(i));
		}
	}
	
	public void TirarDados() 
	{
		int[ ] lista = {(int) Math.floor(Math.random()*(6-1+1)+1), (int) Math.floor(Math.random()*(6-1+1)+1), (int) Math.floor(Math.random()*(6-1+1)+1), (int) Math.floor(Math.random()*(6-1+1)+1), (int) Math.floor(Math.random()*(6-1+1)+1)};  
		borrarListaDados();
		int dado=0;
		for(int i=0; i < lista.length-separados.size(); i++)
		{
			dado++;
			System.out.println("Dado: " + dado + " valor " + lista[i]);
			agregarDado(lista[i]);
		}
	}
	
	public void imprimirDados()
	{
		for (int i = 0; i < getListaDados().size(); i++)
		{
			System.out.println( " DADO: " + getListaDados().get(i));
		}
	}
	
	public void imprimirTableResults()
	{
		 Set<Map.Entry<String,Integer>>
		 lista=getTablaResults().entrySet();
		 System.out.println("TABLA:" + " " +getNombre().toUpperCase());
		 for (Map.Entry<String,Integer> e:lista) 
		 {
			 System.out.println(e.getKey() + " = " + e.getValue());
		 }
	}
	
	public int sumarResultadosFinales()
	{
		Set<Map.Entry<String,Integer>>
		lista=tablaResults.entrySet();
		int value=0;
		for (Map.Entry<String,Integer> e:lista) 
		{
			value+= e.getValue();	
		}
		return value;
	}
	
	public boolean jugadaDisponible(String nombreJugada) 
	{
		Integer jugada= getTablaResults().get(nombreJugada);
		return jugada == -1;
	}
	
	public boolean existeJugada(String input)
	{
		return getTablaResults().containsKey(input.toUpperCase());
	}
	
	public boolean  anotarResultado(String nombreJugada , int puntos)
	{
		if(jugadaDisponible(nombreJugada.toUpperCase()) && existeJugada(nombreJugada))
		{
			getTablaResults().put(nombreJugada.toUpperCase(), puntos);
			return true;
		}
		else
		{
			JOptionPane.showMessageDialog(null,"La jugada ya esta anotada o no existe, vuelva a intentarlo");
			return false;
		}	
	}
	

	public String getNombre() 
	{
		return nombre;
	}

	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}

	public ArrayList<Integer> getSeparados()
	{
		return separados;
	}

	public void setSeparados(ArrayList<Integer> separados2)
	{
		this.separados = separados2;
	}

	public Map<String, Integer> getTablaResults()
	{
		return tablaResults;
	}

	public void setTablaResults(Map<String, Integer> tablaResults)
	{
		this.tablaResults = tablaResults;
	}

	public ArrayList<Integer> getListaDados() 
	{
		return listaDados;
	}

	public void setListaDados(ArrayList<Integer> listaDados2) 
	{
		this.listaDados = listaDados2;
	}

	public ArrayList<Integer> getSeparadosPrevio() 
	{
		return separadosPrevio;
	}

	public void setSeparadosPrevio(ArrayList<Integer> separadosPrevio) 
	{
		this.separadosPrevio = separadosPrevio;
	}

	public static int getPuntostachar() 
	{
		return puntosTachar;
	}

	public int getVueltaXJugador() {
		return vueltaXJugador;
	}

	public void setVueltaXJugador(int vueltaXJugador) {
		this.vueltaXJugador = vueltaXJugador;
	}

	@Override
	public JSONObject pasarAJson() {
		JSONObject jugador= new JSONObject();
		jugador.put("nombre", this.getNombre());
		jugador.put("separados", this.getSeparados());
		jugador.put("separadosPrevio", this.getSeparadosPrevio());
		jugador.put("dados", this.getListaDados());
		jugador.put("tablaResult", this.getTablaResults());
		jugador.put("vueltaXJugador", this.getVueltaXJugador());
		return jugador;
	}
	
	
	
	
	
	
	
	
	
	
}
