import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.JOptionPane;

public class Jugador {

	private String nombre;
	private ArrayList<Integer> separados;
	private Map<String,Integer> tablaResults= new TreeMap<String,Integer>();
	private ArrayList<Integer> listaDados;
	private ArrayList<Integer> separadosPrevio;
	private static final int puntosTachar=0;
	
	public Jugador(String nombre)
	{
		tablaResults.put("generala",null);
		tablaResults.put("poker",null);
		tablaResults.put("full",null);
		tablaResults.put("escalera",null);
		tablaResults.put("6",null);
		tablaResults.put("5",null);
		tablaResults.put("4",null);
		tablaResults.put("3",null);
		tablaResults.put("2",null);
		tablaResults.put("1",null);
		setNombre(nombre);
		setListaDados(new ArrayList<Integer>());
		setSeparados(new ArrayList<Integer>());
		setTablaResults(new TreeMap<String, Integer>());
		setSeparadosPrevio(new ArrayList<Integer>());	
	}
	
	public boolean recuperarDados(ArrayList<Integer>listaSeparadosPrevio, int input)
	{
		if(separadosPrevio.contains(input))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public void devolverAListaDeDados(int input)
	{
		int posicion= getSeparadosPrevio().indexOf(input);
		getSeparadosPrevio().remove(posicion);
		agregarDado(input);
	}
	
	public boolean separarDados(ArrayList<Integer>listaDeDados, int input)
	{
		if(listaDeDados.contains(input))
		{
			return true;
		}
		else
		{
			return false;
		}
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
	
	public void imprimirTableResults()
	{
		 Set<Map.Entry<String,Integer>>
		 lista=getTablaResults().entrySet();
		 
		 for (Map.Entry<String,Integer> e:lista) 
		 {
			 System.out.println("TABLA:" + "\n" + getNombre() + "\n" + e.getKey() + " = " + e.getValue());
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
	
	public boolean availableplay(String nombreJugada) 
	{
		 Integer jugada= getTablaResults().get(nombreJugada);
		 if(jugada == null)
		 {
			 return true;
		 }
		 else
		 {
			 return false;
		 }
	}
	
	public boolean existeJugada(String input)
	{
		if(getTablaResults().containsKey(input.toLowerCase()))
		{
			return true;
		}
		return false;
	}
	

	public boolean  anotarResultado(String nombreJugada , int puntos)  throws ExceptionJugadaAnotada

	public boolean  anotarResultado(String nombreJugada , int puntos)  throws exceptionjugadaAnotada

	{
			if(availableplay(nombreJugada.toLowerCase()) && existeJugada(nombreJugada))
			{
				getTablaResults().put(nombreJugada, puntos);
				return true;
			}
			else
			{

				throw new ExceptionJugadaAnotada("La jugada ya esta anotada o no existe, vuelva a intentarlo");

				throw new exceptionjugadaAnotada("La jugada ya esta anotada o no existe, vuelva a intentarlo");

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
	
	
	
	
	
	
}
