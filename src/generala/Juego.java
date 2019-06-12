package generala;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.json.JSONArray;
import org.json.JSONObject;

public class Juego implements ObjetoJasoneable  {

	private String inputPrincipal;
	private ArrayList<Jugador> listaJugadores;
	private ArrayList<Jugada> jugadas;
	private final int SALIR_WHILE_VUELTA=4;
	private final String SALIR="0";
	private final int LIMITE_VUELTAS_GENERALES=10;
	private final int LIMITE_VUELTAS_JUGADOR=3;
	private int vueltaPrincipal;
	
	public Juego()
	{
		jugadas=new ArrayList<Jugada>();
		jugadas.add(new JugadaGenerala());
		jugadas.add(new JugadaPoker());
		jugadas.add(new JugadaFull());
		jugadas.add(new JugadaEscalera());
		setInputPrincipal("");
		setListaJugadores(new ArrayList<>());
		setJugadas(jugadas);
		setVueltaPrincipal(1);
	}
	
	public void cargarCantidadJugadores()
	{
		int jugador=1;
		String cantidad=JOptionPane.showInputDialog("GENERALA"+ "\n" + "Ingrese cantidad de jugadores:" + " 2,3 o 4 jugadores");
		if(cantidad == null)
		{
			JOptionPane.showMessageDialog(null, "Cantidad incorrecta,vuelva a intentarlo");
			cargarCantidadJugadores();
		}
		else
		{	
			if(cantidad.equals("2") || cantidad.equals("3") || cantidad.equals("4"))
			{
				int cant= Integer.parseInt(cantidad);
				for(int i=0; i < cant; i++)
				{
					String nombre= JOptionPane.showInputDialog("Ingrese el nombre " + "JUGADOR " + jugador);
					jugador++;
					if(nombre == null)
					{
						JOptionPane.showMessageDialog(null, "Incorrecto,vuelva a intentarlo");
						i=cant;
						getListaJugadores().clear();
						cargarCantidadJugadores();
					}
					else
					{
						getListaJugadores().add(new Jugador(nombre));
					}
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Cantidad incorrecta,vuelva a intentarlo");
				cargarCantidadJugadores();
			}
		}
	}
	
	public boolean tacharJugada(Jugador j) 
	{
		String input= JOptionPane.showInputDialog("ESCRIBA EL NOMBRE DE LA JUGADA QUE DESEA TACHAR: ");
		return input == null ? false : j.anotarResultado(input, Jugador.getPuntostachar());
	}
	
    public void menuTachar(Jugador j) throws IOException  
    {
    	if(tacharJugada(j))
		{
			JOptionPane.showMessageDialog(null, "JUGADA TACHADA");
			j.setVueltaXJugador(SALIR_WHILE_VUELTA);
		}
		else
		{
			JOptionPane.showMessageDialog(null, "VOLVIENDO AL MENU PRINCIPAL");
			menuPrincipal();
			seleccionarMenu(j);
		}
    }
    
    public void menuReverse(Jugador j)
    {
    	final String SI="si";
    	final String NO="no";
    	String input=JOptionPane.showInputDialog("QUIERE USAR EL VALOR DE LA CARA OPUESTA DE LOS DADOS?:" + "\n" + "1-SI:" + "\n" + "2-NO:" );
    	if(input.equalsIgnoreCase(SI))
    	{
    		j.reverse();
    	}
    	else
    	{
    		if(input.equalsIgnoreCase(NO) || input == null)
    		{
    			JOptionPane.showMessageDialog(null, "SALIENDO AL MENU");
    		}
    		else
    		{
    			JOptionPane.showMessageDialog(null, "OPCION INCORRECTA, VUELVA A INTENTARLO");
    			menuReverse(j);
    		}
    	}
    }
	
	public void menuSeparar(Jugador j)
	{
		if(j.getListaDados().size( )== 0)
		{
			JOptionPane.showMessageDialog(null, "SE HAN SEPARADO TODOS LOS DADOS, REINCORPORE DADOS O SALGA DEL MENU PRINCIPAL PARA CONTINUAR");
		}
		else
		{
			String input= JOptionPane.showInputDialog("OPCIONES - ELIJA UN VALOR A SEPARAR:"+ "\n" + j.menuSepararDados()+ "\n" + "salir= 0");
			if(input.equals(SALIR) || input == null)
			{
				JOptionPane.showMessageDialog(null,  "SALIENDO AL MENU PRINCIPAL");
			}
			else
			{
				int input2 =Integer.parseInt(input);
				if(j.separarDados(j.getListaDados(),input2))
				{
					j.agregarSeparadoPrevio(input2);
					menuSeparar(j);
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Valor inexistente , vuelva a intentar o elija salir para continuar");
				    menuSeparar(j);
				}	
		    }
	    }
	}
	
	public void menuRecuperar(Jugador j)
	{
		if(j.getSeparadosPrevio().size() == 0)
		{
			JOptionPane.showMessageDialog(null, "NO HAY DADOS A RECUPERAR");
		}
		else
		{
			String input= JOptionPane.showInputDialog("OPCIONES - ELIJA UN VALOR A REINCORPORAR:"+ "\n" + j.menuRecuperarDados()+ "\n" + "salir= 0");
			if(input.equals(SALIR) || input == null)
			{
				JOptionPane.showMessageDialog(null,  "SALIENDO AL MENU PRINCIPAL");
			}
			else
			{
				int input2=Integer.parseInt(input);
				if(j.recuperarDados(j.getSeparadosPrevio(), input2))
				{
					j.devolverAListaDeDados(input2);
					menuRecuperar(j);
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Valor inexistente , vuelva a intentar o elija salir para continuar");
					menuRecuperar(j);
				}	
			}
	    }
	}
	
	public void menuPrincipal()
	{
		String input= JOptionPane.showInputDialog(
				"1-SEPARAR:"+ "\n" + 
                "2-REINCORPORAR" + "\n" +
                "3-SUMAR" + "\n" +
                "4-TACHAR" + "\n" +
                "5-SAVE" + "\n" +
                "6-LOAD" + "\n" +
                "7-IMPRIMIR TABLA RESULTADOS" + "\n" +
                "8-IMPRIMIR TABLA RESULTADOS" + "\n" +
                "0-SALIR O CONTINUAR");
		if(input == null)
		{
			JOptionPane.showMessageDialog(null, "Valor inexistente, vuelva a intentarlo");
	  		menuPrincipal();
		}
		else
		{
			setInputPrincipal(input);
		}
	}
		
	public void seleccionarMenu(Jugador j) throws IOException 
	{
		final String SEPARAR_DADO="1";
		final String REINCORPORAR_DADO="2";
		final String SUMAR_DADO="3";
		final String TACHAR_JUGADA="4";
		final String SAVE_JUEGO="5";
		final String LOAD_JUEGO="6";
		final String IMPRIMIR_TABLA_RESULTADOS="7";
		final String MOSTRAR_DADOS= "8";
		switch(getInputPrincipal()) {
		case SEPARAR_DADO: 
			menuSeparar(j);
			menuPrincipal();
			seleccionarMenu(j);
			break;
	    case REINCORPORAR_DADO:
			menuRecuperar(j);
			menuPrincipal();
			seleccionarMenu(j);
			break;
  	    case SUMAR_DADO:
  	    	menuSumar(j);
  	    	break;
        case TACHAR_JUGADA:
        	menuTachar(j);
    	    break;
        case SAVE_JUEGO:
        	save(j);
      	    break;
        case LOAD_JUEGO:
        	
      	    break;
        case IMPRIMIR_TABLA_RESULTADOS :
        	j.imprimirTableResults();
        	menuPrincipal();
			seleccionarMenu(j);
      	    break;
      	    case MOSTRAR_DADOS:
      	    System.out.println("\n" +"JUGADOR: " + j.getNombre().toUpperCase() + "\n" + "RONDA: " + vueltaPrincipal + "\n" + "VUELTA: " + j.getVueltaXJugador());
      	    j.imprimirDados();
      	    break;    
        case SALIR:
    	    break;    
  	    default:
  		   JOptionPane.showMessageDialog(null, "Valor inexistente, vuelva a intentarlo");
  		   menuPrincipal();
  		   seleccionarMenu(j);
		}
	}
	
	public int inputJugada(int input)
	{
		return input;
	}
	
    public void Jugar() throws IOException 
    {
    	cargarCantidadJugadores();
        final String GENERALA_ON="on";
    	final String GENERALA_OFF="off";
    	String generalaGana=GENERALA_OFF;
		while(getVueltaPrincipal()<=LIMITE_VUELTAS_GENERALES)
		{ 
			for (int i = 0; i < getListaJugadores().size(); i++) 
			{
				getListaJugadores().get(i).setVueltaXJugador(1);
				getListaJugadores().get(i).borrarListaseparados();
				while(getListaJugadores().get(i).getVueltaXJugador()<=LIMITE_VUELTAS_JUGADOR)
				{   
					System.out.println("\n" +"JUGADOR: " + getListaJugadores().get(i).getNombre().toUpperCase() + "\n" + "RONDA: " + vueltaPrincipal + "\n" + "VUELTA: " + getListaJugadores().get(i).getVueltaXJugador());
					getListaJugadores().get(i).TirarDados();
					menuReverse(getListaJugadores().get(i));
					
					if(generalaServida(getListaJugadores().get(i)))
					{
						getListaJugadores().get(i).setVueltaXJugador(SALIR_WHILE_VUELTA);
						i=5;
						setVueltaPrincipal(11);
						generalaGana=GENERALA_ON;
					}
					else
					{
						if(encontrarJugada(getListaJugadores().get(i)) != true)
						{
							menuPrincipal();
							try {
								seleccionarMenu(getListaJugadores().get(i));
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							getListaJugadores().get(i).getSeparados().addAll(getListaJugadores().get(i).getSeparadosPrevio());
							getListaJugadores().get(i).borrarListaSeparadosPrevio();
						}
						if(getListaJugadores().get(i).getVueltaXJugador() != SALIR_WHILE_VUELTA)
						{
							if(encontrarJugadaSerparados(getListaJugadores().get(i)) != true)
							{
								if(getListaJugadores().get(i).getVueltaXJugador() == LIMITE_VUELTAS_JUGADOR)
								{
									while(!tacharJugada(getListaJugadores().get(i)));
								}
								getListaJugadores().get(i).setVueltaXJugador(getListaJugadores().get(i).getVueltaXJugador()+1);
							}
						}
					}
				}   			
			}
			setVueltaPrincipal(getVueltaPrincipal()+1);
		}
		
		if(generalaGana.equals(GENERALA_OFF))
		{
			for (int i = 0; i < getListaJugadores().size(); i++) 
			{
				getListaJugadores().get(i).imprimirTableResults();
				System.out.println(getListaJugadores().get(i).getNombre() + ":" + "\n" + "TOTAL: " + getListaJugadores().get(i).sumarResultadosFinales());
			}
		    JOptionPane.showMessageDialog(null, "EL GANADOR ES " + ganador().getNombre() + " CON UN TOTAL DE: " + ganador().sumarResultadosFinales());	
		}
    }	
    
    public Jugador ganador()
    {
    	int mayor=0;
    	int total=0;
    	Jugador ganador=null;
    	for (int i = 0; i < getListaJugadores().size(); i++) 
    	{
    		total= getListaJugadores().get(i).sumarResultadosFinales();
			if(mayor < total)
			{
				mayor=total;
				ganador=getListaJugadores().get(i);
			}
		}
    	return ganador;
    }
    
    public boolean menuSumar(Jugador j) throws IOException 
    {
    	boolean bool=false;
    	final String OK="SI";
    	final String NOT="NO";
    	if(j.getListaDados().size( )== 0)
		{
			JOptionPane.showMessageDialog(null, "NO HAY DADOS PARA SUMAR");
			bool=false;
			menuPrincipal();
	    	seleccionarMenu(j);
		}
		else
		{
			String input= JOptionPane.showInputDialog("OPCIONES - ELIJA UN VALOR A SUMAR:"+ "\n" + "DADOS: " + j.getListaDados() + "\n" + "salir= 0");
			if(input.equals(SALIR) || input == null)
			{
				JOptionPane.showMessageDialog(null,  "SALIENDO AL MENU PRINCIPAL");
				bool=false;
				menuPrincipal();
  	    		seleccionarMenu(j);
			}
			else
			{
				int input2=Integer.parseInt(input);
				Jugada sumar= new JugadaDado(input2);
				if(sumar.encontrada(j.getListaDados()))
				{
					String input_dos= JOptionPane.showInputDialog("DESEA ANOTAR LA JUGADA?: " + input + " PUNTOS: " + sumar.puntos() + " ESCRIBA: " +  "SI" + " O " + "NO");
					if(input_dos.equalsIgnoreCase(OK) && j.anotarResultado(sumar.nombre(), sumar.puntos()))
					{
						bool=true;
						j.setVueltaXJugador(SALIR_WHILE_VUELTA);
					}
					else
					{
						if(input_dos.equalsIgnoreCase(NOT) || input_dos == null)
						{
							bool=false;
							menuPrincipal();
			  	    		seleccionarMenu(j);
						}
						else
						{
							JOptionPane.showMessageDialog(null,"LA JUGADA YA ESTA ANOTADA, INTENTE CON OTRA O SELECCIONE SALIR PARA VOLVER AL MENU PRINCIPAL");
							menuSumar(j);
						}	
					}	
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Valor inexistente , vuelva a intentar o elija salir para continuar");
				    menuSumar(j);
				}	
		    }
	    }
		return bool;
    }
    
    
    public boolean generalaServida(Jugador j)
    {
    	boolean bool=false;
    	for(int i = 0; i < jugadas.size(); i++)
    	{   
    	    if(jugadas.get(i).encontrada(j.getListaDados())) 
    	    {
    	    	if(jugadas.get(i).nombre().equalsIgnoreCase("generala") && j.getVueltaXJugador()==1)
    	    	{
    	    		JOptionPane.showMessageDialog(null, j.getNombre() +" GANASTE!!!" + "OBTUVISTE "+ jugadas.get(i).nombre());
    	    		bool= true;
    	    	}
    	    	else
    	    	{
    	    		bool= false;
    	    	}
    	    }
    	}
    	return bool;
    }
    
    public boolean encontrarJugada(Jugador j) 
    {
    	boolean bool=false;
    	String input="";
    	for(int i = 0; i < jugadas.size(); i++)
    	{   
    		final String ANOTAR="1";
    		final String SALIR_SIN_ANOTAR="2";		
    		int puntos=0;
    	    if(jugadas.get(i).encontrada(j.getListaDados())) 
    	    {
    	    	if(j.getVueltaXJugador() == 1)
    	    	{
    	    		puntos=5;
    	    	}
    	    		input= JOptionPane.showInputDialog("Es posible anotar " + jugadas.get(i).puntos() + " puntos a " + jugadas.get(i).nombre() + " Desea anotar? " + "\n" + "1-ANOTAR" + "\n" + "2-SALIR");
    	    	    if(input.equals(ANOTAR))
    	    	    {
    	    	    	if(j.anotarResultado(jugadas.get(i).nombre(),jugadas.get(i).puntos() + puntos))
    	    	    	{
    	    	    		JOptionPane.showMessageDialog(null, "JUGADA ANOTADA CON EXITO");
    	     			    j.setVueltaXJugador(SALIR_WHILE_VUELTA);
    	     			    bool= true;
    	     		    }
    	    	    	else
    	    	    	{
    	    	    		bool= false;
    	     		    }
    	     		
    	     	    }
    	    	    else
    	    	    {
    	    	    	if(input.equals(SALIR_SIN_ANOTAR) || input == null)
    	    	    	{
    	    	    		bool= false;
    	    		    }
    	    		    else
    	    		    {
    	    		    	JOptionPane.showMessageDialog(null, "OPCION INCORRECTA, VUELVA A INTENTARLO");
    	    			    encontrarJugada(j);
    	    		    }
    	    	    }
    	    	 
    	    }
    	}
    	return bool;
    }
    
    public boolean encontrarJugadaSerparados(Jugador j)
    {
    	final String ANOTAR="1";
    	final String SALIR_SIN_ANOTAR="2";
    	boolean bool=false;
    	for(int i = 0; i < jugadas.size(); i++)
    	{
    		if(jugadas.get(i).encontrada(j.getSeparados())) 
    		{
    			String input= JOptionPane.showInputDialog("Es posible anotar " + jugadas.get(i).puntos() + " puntos a " + jugadas.get(i).nombre() + " encontrada en los dados separados  " + " Desea anotar? " + "\n" + "1-ANOTAR" + "\n" + "2-SALIR");
    			if(input.equals(ANOTAR))
    			{
    				if(j.anotarResultado(jugadas.get(i).nombre(),jugadas.get(i).puntos()))
    				{
    					JOptionPane.showMessageDialog(null, "JUGADA ANOTADA CON EXITO");
    					j.setVueltaXJugador(SALIR_WHILE_VUELTA);
    					bool= true;
    				}
    				else
    				{
    					bool= false;
    				} 	     		
    			}
    			else
    			{
    				if(input.equals(SALIR_SIN_ANOTAR) || input == null)
    				{
    					bool= false;
    				}
    				else
    				{
    					JOptionPane.showMessageDialog(null, "OPCION INCORRECTA, VUELVA A INTENTARLO");
    					encontrarJugada(j);
    				}
    			}
    	    }
    	}
     return bool;
    }
    
	public ArrayList<Jugador> getListaJugadores() 
	{
		return listaJugadores;
	}

	public void setListaJugadores(ArrayList<Jugador> listaJugadores) 
	{
		this.listaJugadores = listaJugadores;
	}

	public String getInputPrincipal() 
	{
		return inputPrincipal;
	}

	public void setInputPrincipal(String input) 
	{
		this.inputPrincipal = input;
	}

	public ArrayList<Jugada> getJugadas() 
	{
		return jugadas;
	}

	public void setJugadas(ArrayList<Jugada> jugadas) 
	{
		this.jugadas = jugadas;
	}

	public int getVueltaPrincipal() {
		return vueltaPrincipal;
	}

	public void setVueltaPrincipal(int vueltaPrincipal) {
		this.vueltaPrincipal = vueltaPrincipal;
	}	

	@Override
	public JSONObject pasarAJson() 
	{
		JSONObject jjuego = new JSONObject();
		JSONArray jjugadores = new JSONArray();
		for (Jugador j : this.getListaJugadores()) 
		{
			jjugadores.put(j.pasarAJson());
		}
		jjuego.put("jugadores", jjugadores);
		jjuego.put("vueltaPrincipal", this.getVueltaPrincipal());
		return jjuego;
	}
	

	public Juego(JSONObject JsonObjJuego)
	{
		jugadas=new ArrayList<Jugada>();
		jugadas.add(new JugadaGenerala());
		jugadas.add(new JugadaPoker());
		jugadas.add(new JugadaFull());
		jugadas.add(new JugadaEscalera());
		this.setVueltaPrincipal(JsonObjJuego.getInt("vueltaPrincipal"));
		setInputPrincipal("");
		setJugadas(jugadas);
		JSONArray jugadores = JsonObjJuego.getJSONArray("jugadores");
		for(int i = 0; i < jugadores.length(); i++)
		{
			this.listaJugadores.add(new Jugador(jugadores.getJSONObject(i)));
		}
		setListaJugadores(listaJugadores);
	}
	
	public void save(Jugador j) throws IOException 
	{
		String archivo= JOptionPane.showInputDialog("CON QUE NOMBRE QUIERE GUARDAR LA PARTIDA");
		
		if(archivo != null)
		{
			try { 
				OutputStream save = new FileOutputStream(archivo + ".json");
				Writer writer = new OutputStreamWriter(save,"UTF-8");
				writer.write(this.pasarAJson().toString());
				writer.close();
				save.close();
				} 
			catch (UnsupportedEncodingException e) 
			{
				JOptionPane.showMessageDialog(null, "ERROR AL GUARDAR");
				save(j);
			}
		}
		else
		{
			JOptionPane.showMessageDialog(null, "SALIENDO");
			menuPrincipal();
			seleccionarMenu(j);
		}
	}
	
	public void load()
	{
		String archivo = JOptionPane.showInputDialog("INGRESE EL NOMBRE DE LA PARTIDA GUARDADA");
		if(archivo != null)
		{
			try 
			{
				JSONObject load =  new JSONObject(new String(Files.readAllBytes(Paths.get(archivo + ".json"))));
				
			}
			catch (Exception e) 
			{
				JOptionPane.showMessageDialog(null, "LA PARTIDA NO EXISTE");
				load();
			}
		}
		else
		{
			JOptionPane.showMessageDialog(null, "SALIENDO");
		}
	}
}

}
