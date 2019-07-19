package generala;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

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
	private Menu menus = new Menu();
	
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
	
	//Pregunta la cantidad de jugadores para iniciar el juego.
	public void cargarCantidadJugadores() throws IOException
	{
		int jugador=1;
		String cantidad=JOptionPane.showInputDialog(menus.MENU_CARGAR_CANT_JUGADORES);
		//Verifica si hay un valor nulo, que vuelva a cargar la funcion.
		if(cantidad == null)
		{
			JOptionPane.showMessageDialog(null, menus.MENU_SALIENDO);
			menuJugar();
		}
		else
		{	
			//Verifica que el valor ingresado es correcto o esta en el rango de 2 a 4
			if(cantidad.matches("[2-4]*"))
			{
				int cant= Integer.parseInt(cantidad);
				for(int i=0; i < cant; i++)
				{
					String nombre= JOptionPane.showInputDialog("Ingrese el nombre " + "JUGADOR " + jugador);
					jugador++;
					if (nombre == null || !Character.isAlphabetic(nombre.charAt(0)))
					{
						JOptionPane.showMessageDialog(null, menus.MENU_ERROR);
						i=cant;
						getListaJugadores().clear();
						menuJugar();
					}
					else
					{
						getListaJugadores().add(new Jugador(nombre));
					}
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null, menus.CANTIDAD_INCORRECTA);
				cargarCantidadJugadores();
			}
		}
	}
	
	//Permite seleccionar una jugada para tachar.
	public boolean tacharJugada(Jugador j) 
	{
		String input= JOptionPane.showInputDialog("ESCRIBA EL NOMBRE DE LA JUGADA QUE DESEA TACHAR: ");
		return input == null ? false : j.anotarResultado(input, Jugador.getPuntostachar());
	}
	
	public void tacharJugar(Jugador j)
	{
		String input= JOptionPane.showInputDialog("ESCRIBA EL NOMBRE DE LA JUGADA QUE DESEA TACHAR: ");
		try 
		{
			if(j.anotarResultado(input, Jugador.getPuntostachar()))
			{
				JOptionPane.showMessageDialog(null, "JUGADA TACHADA");
			}
			else
			{
				tacharJugar(j);
			}
		}
		catch (Exception e) 
		{
			JOptionPane.showMessageDialog(null, "NO INGRESO UNA JUGADA, VUELVA A INTENTARLO");
			tacharJugar(j);
		}
	}
	
	//Indica si la jugada esta tachada o disponible.
    public void menuTachar(Jugador j) throws IOException  
    {
    	try
    	{
    		tacharJugada(j);
    		JOptionPane.showMessageDialog(null, "JUGADA TACHADA");
    		j.setVueltaXJugador(SALIR_WHILE_VUELTA);
		} 
    	catch (Exception e)
    	{
    		JOptionPane.showMessageDialog(null, "NO INGRESO UNA JUGADA, VOLVIENDO AL MENU PRINCIPAL");
			menuPrincipal();
			seleccionarMenu(j);
		}
    }
    
    public void menuReverse(Jugador j)
    {
    	final String SI="1";
    	final String NO="2";
    	String input=JOptionPane.showInputDialog(menus.MENU_REVERSE);
    	try 
    	{
			switch (input) {
			case SI:
				JOptionPane.showMessageDialog(null, j.reverse());
				break;
			case NO:
				JOptionPane.showMessageDialog(null, menus.MENU_SALIENDO);
				break;
			default:
				JOptionPane.showMessageDialog(null, menus.MENU_ERROR);
				menuReverse(j);
				break;
			}
		} 
    	catch (Exception e)
    	{
    		JOptionPane.showMessageDialog(null, menus.MENU_SALIENDO);
		}
    }
	
    /*Permite elegir si se quiere apartar dados para beneficiarse con la proxima jugada o volver al menu principal.
	Si decide separar dados, los mismos seran guardados en una lista previa para poder reincorporarlos, en el caso de querer hacerlo.*/
	public void menuSeparar(Jugador j)
	{
		if(j.getListaDados().size( )== 0)
		{
			JOptionPane.showMessageDialog(null, menus.MENU_TODOS_SEPARADOS);
		}
		else
		{
			String input= JOptionPane.showInputDialog("OPCIONES - ELIJA UN VALOR A SEPARAR:"+ "\n" + j.menuSepararDados());
			try
			{
				if(input.matches("[1-6]*"))
				{
					int input2 =Integer.parseInt(input);
					if(j.separarDados(j.getListaDados(),input2))
					{
						j.agregarSeparadoPrevio(input2);
						menuSeparar(j);
					}
					else
					{
						JOptionPane.showMessageDialog(null, menus.VALOR_INEXISTENTE);
					    menuSeparar(j);
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, menus.VALOR_INEXISTENTE);
				    menuSeparar(j);
				}
			} 
			catch (Exception e)
			{
				JOptionPane.showMessageDialog(null, menus.MENU_SALIENDO);
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
			String input= JOptionPane.showInputDialog("OPCIONES - ELIJA UN VALOR A REINCORPORAR:"+ "\n" + j.menuRecuperarDados());
			try 
			{
				if(input.matches("[1-6]*"))
				{
					int input2=Integer.parseInt(input);
					if(j.recuperarDados(j.getSeparadosPrevio(), input2))
					{
						j.devolverAListaDeDados(input2);
						menuRecuperar(j);
					}
					else
					{
						JOptionPane.showMessageDialog(null, menus.VALOR_INEXISTENTE);
						menuRecuperar(j);
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, menus.VALOR_INEXISTENTE);
					menuRecuperar(j);
				}
			} 
			catch (Exception e)
			{
				JOptionPane.showMessageDialog(null, menus.MENU_SALIENDO);
			}
		}
	}
	
	//Menu donde se elige que opcion realizar.
	public void menuPrincipal()
	{
		String input= JOptionPane.showInputDialog(menus.MENU_PRINCIPAL);
		if(input == null)
		{
			JOptionPane.showMessageDialog(null, "Valor inexistente, vuelva a intentarlo");
			System.exit (0);
		}
		else
		{
			setInputPrincipal(input);
		}
	}
		
	//Recibe un jugador, obtiene el input del menu principal y busca la opcion correcta.
	public void seleccionarMenu(Jugador j) throws IOException 
	{
		final String SEPARAR_DADO="1";
		final String REINCORPORAR_DADO="2";
		final String SUMAR_DADO="3";
		final String TACHAR_JUGADA="4";
		final String SAVE_JUEGO="5";
		final String MOSTRAR_SEPARADOS="6";
		final String IMPRIMIR_TABLA_RESULTADOS="7";
		final String MOSTRAR_DADOS= "8";
		final String SALIR_J="9";
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
        	menuSave(j);
      	    break;
        case MOSTRAR_SEPARADOS:
        	 if(j.getSeparados().size()==0)
        	 {
        		 JOptionPane.showMessageDialog(null, "AUN NO HAY DADOS SEPARADOS");
        		 menuPrincipal();
     			 seleccionarMenu(j);
        	 }
        	 else
        	 {
        		 JOptionPane.showMessageDialog(null, "JUGADOR: " + j.getNombre() + "\n" + j.imprimirSeparados()); 
            	 menuPrincipal();
     			 seleccionarMenu(j); 
        	 }
      	    break;
        case IMPRIMIR_TABLA_RESULTADOS:
        	JOptionPane.showMessageDialog(null, "JUGADOR: " + j.getNombre() + "\n" + j.imprimirTableResults()); 
        	menuPrincipal();
			seleccionarMenu(j);
      	    break;
        case MOSTRAR_DADOS:
      	    JOptionPane.showMessageDialog(null, "JUGADOR: " + j.getNombre() + "\n" + j.imprimirDados()); 
      	    menuPrincipal();
			seleccionarMenu(j);
      	    break;
        case SALIR_J:
        	System.exit(0);
    	    break;    
        case SALIR:
    	    break;    
  	    default:
  		   JOptionPane.showMessageDialog(null, menus.VALOR_INEXISTENTE_2);
  		   menuPrincipal();
  		   seleccionarMenu(j);
		}
	}
	
	public int inputJugada(int input)
	{
		return input;
	}
	
    public void jugar() throws IOException 
    {
    	if(getListaJugadores().size() == 0)
    	{
    		cargarCantidadJugadores();
    	}
        final String GENERALA_ON="on";
    	final String GENERALA_OFF="off";
    	String generalaGana=GENERALA_OFF;
		while(getVueltaPrincipal() <= LIMITE_VUELTAS_GENERALES)
		{ 
			for (int i = 0; i < getListaJugadores().size(); i++) 
			{
				getListaJugadores().get(i).setVueltaXJugador(1);
				getListaJugadores().get(i).borrarListaseparados();
				while(getListaJugadores().get(i).getVueltaXJugador()<=LIMITE_VUELTAS_JUGADOR)
				{  
					JOptionPane.showMessageDialog(null, "\n" +"JUGADOR: " + getListaJugadores().get(i).getNombre().toUpperCase() + "\n" + "RONDA: " + vueltaPrincipal + "\n" + "VUELTA: " + getListaJugadores().get(i).getVueltaXJugador() + "\n" + getListaJugadores().get(i).TirarDados());
					//System.out.println("\n" +"JUGADOR: " + getListaJugadores().get(i).getNombre().toUpperCase() + "\n" + "RONDA: " + vueltaPrincipal + "\n" + "VUELTA: " + getListaJugadores().get(i).getVueltaXJugador());
					//getListaJugadores().get(i).TirarDados();
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
							try 
							{
								seleccionarMenu(getListaJugadores().get(i));
							} 
							catch (Exception e)
							{
								JOptionPane.showMessageDialog(null, "OPCION INCORRECTA");
								menuPrincipal();
							}
							getListaJugadores().get(i).getSeparados().addAll(getListaJugadores().get(i).getSeparadosPrevio());
							getListaJugadores().get(i).borrarListaSeparadosPrevio();
						}
						if(getListaJugadores().get(i).getVueltaXJugador() != SALIR_WHILE_VUELTA)
						{
							if(encontrarJugadaSeparados(getListaJugadores().get(i)) != true)
							{
								if(getListaJugadores().get(i).getVueltaXJugador() == LIMITE_VUELTAS_JUGADOR)
								{
									tacharJugar(getListaJugadores().get(i));
									//while(!tacharJugada(getListaJugadores().get(i)));
									//JOptionPane.showMessageDialog(null, "JUGADA TACHADA");
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
				JOptionPane.showMessageDialog(null, getListaJugadores().get(i).getNombre() + ":" + "\n" + "TOTAL: " + getListaJugadores().get(i).sumarResultadosFinales());
				//System.out.println(getListaJugadores().get(i).getNombre() + ":" + "\n" + "TOTAL: " + getListaJugadores().get(i).sumarResultadosFinales());
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
    
    public void menuSumar(Jugador j) throws IOException 
    {
    	if(j.getListaDados().size( ) == 0)
		{
			JOptionPane.showMessageDialog(null, "NO HAY DADOS PARA SUMAR");
			menuPrincipal();
	    	seleccionarMenu(j);
		}
		else
		{
			try 
			{
				String input= JOptionPane.showInputDialog("OPCIONES - ELIJA UN VALOR A SUMAR:"+ "\n" + "DADOS: " + j.getListaDados());
				if(input.matches("[1-6]*"))
				{
					int input2=Integer.parseInt(input);
					Jugada sumar= new JugadaDado(input2);
					if(sumar.encontrada(j.getListaDados()))
					{
						try 
						{
							String input_dos= JOptionPane.showInputDialog("DESEA ANOTAR LA JUGADA?: " + input + " y " + sumar.puntos() + " PUNTOS ?"  + " ESCRIBA: " +  " 1-SI" + " O CANCELAR");
							if(input_dos.matches("[1-1]*"))
							{
								if(j.anotarResultado(sumar.nombre(), sumar.puntos()))
								{
									JOptionPane.showMessageDialog(null, menus.MENU_JUGADA_ANOTADA);
									j.setVueltaXJugador(SALIR_WHILE_VUELTA);
								}
								else
								{
									JOptionPane.showMessageDialog(null,"LA JUGADA YA ESTA ANOTADA, INTENTE CON OTRA O SELECCIONE SALIR PARA VOLVER AL MENU PRINCIPAL");
									menuSumar(j);
									
								}	
							}
							else
							{
								JOptionPane.showMessageDialog(null, menus.VALOR_INEXISTENTE);
							    menuSumar(j);
							}
						} 
						catch (Exception e)
						{
							JOptionPane.showMessageDialog(null,menus.MENU_SALIENDO);
							menuPrincipal();
			  	    		seleccionarMenu(j);
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, menus.VALOR_INEXISTENTE);
					    menuSumar(j);
					}	
			    }
				else
				{
					JOptionPane.showMessageDialog(null, menus.VALOR_INEXISTENTE);
				    menuSumar(j);
				}
			} 
			catch (Exception e)
			{
				JOptionPane.showMessageDialog(null,menus.MENU_SALIENDO);
				menuPrincipal();
				seleccionarMenu(j);
			}
	    }
    }
    
  //Verifica si se concreto una generala en el primer tiro, de lo contrario sigue el juego.
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
    	    	input= JOptionPane.showInputDialog("Es posible anotar " + jugadas.get(i).puntos() + " puntos a la jugada" + jugadas.get(i).nombre() + " Desea anotar? " + "\n" + "1-ANOTAR" + "\n" + "2-SALIR");
    	    	try
    	    	{
    	    		if(input.equals(ANOTAR))
    	    		{
		        	   if(j.anotarResultado(jugadas.get(i).nombre(),jugadas.get(i).puntos() + puntos))
		        	   {
		        		   JOptionPane.showMessageDialog(null, menus.MENU_JUGADA_ANOTADA);
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
		        	   if(input.equals(SALIR_SIN_ANOTAR))
		        	   {
		        		   bool= false;
		        	   }
		        	   else
		        	   {
		        		   JOptionPane.showMessageDialog(null, menus.MENU_ERROR);
		        	       encontrarJugada(j);
		        	   }
		        	}
				} 
		    	catch (Exception e) 
		    	{
					bool= false;
			    }
    	    }
    	}
    	return bool;
    }
    
    public boolean encontrarJugadaSeparados(Jugador j)
    {
    	final String ANOTAR="1";
    	final String SALIR_SIN_ANOTAR="2";
    	boolean bool=false;
    	for(int i = 0; i < jugadas.size(); i++)
    	{
    		if(jugadas.get(i).encontrada(j.getSeparados())) 
    		{
    			String input= JOptionPane.showInputDialog("Es posible anotar " + jugadas.get(i).puntos() + " puntos a la jugada " + jugadas.get(i).nombre() + " encontrada en los dados separados  " + " Desea anotar? " + "\n" + "1-ANOTAR" + "\n" + "2-SALIR");
    			try
    			{
    				if(input.equals(ANOTAR))
        			{
        				if(j.anotarResultado(jugadas.get(i).nombre(),jugadas.get(i).puntos()))
        				{
        					JOptionPane.showMessageDialog(null, menus.MENU_JUGADA_ANOTADA);
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
        				if(input.equals(SALIR_SIN_ANOTAR))
        				{
        					bool= false;
        				}
        				else
        				{
        					JOptionPane.showMessageDialog(null, menus.MENU_ERROR);
        					encontrarJugada(j);
        				}
        			}
				} 
    			catch (Exception e)
    			{
					bool= false;
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
		this.listaJugadores = new ArrayList<Jugador>();
		for(int i = 0; i < jugadores.length(); i++)
		{
			this.listaJugadores.add(new Jugador(jugadores.getJSONObject(i)));
		}
		setListaJugadores(listaJugadores);
	}
	
	public void menuSave(Jugador j) throws IOException 
	{
		String archivo= JOptionPane.showInputDialog(menus.MENU_SAVE);
		try 
		{
			AdministradorPartidas admin = new AdministradorPartidas();
			admin.salvarPartida(archivo, this.pasarAJson());
			menuPrincipal();
			seleccionarMenu(j);
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null,"SALIENDO AL MENU");
			menuPrincipal();
			seleccionarMenu(j);
		}
	}
	
	public void menuJugar() throws IOException
	{
		final String JUGAR = "1";
		final String LOAD = "2";
		final String SALIR_J = "3";
		String archivo = JOptionPane.showInputDialog(menus.MENU_JUGAR);
		try 
		{
			switch (archivo) {
			case JUGAR:
				jugar();
				break;
			case LOAD:
				load();
				break;
			case SALIR_J:
				System.exit(0);
				break;	
			default:
				JOptionPane.showMessageDialog(null, menus.MENU_ERROR);
				menuJugar();
				break;
			}
		} 
		catch (Exception e) 
		{
			JOptionPane.showMessageDialog(null,menus.MENU_ERROR);
			System.exit (0);
		}
		
	}
	
	public void load() throws IOException
	{
		String archivo = JOptionPane.showInputDialog(menus.getMENU_LOAD());
			try 
			{
				AdministradorPartidas admin = new AdministradorPartidas();
				JSONObject juegoGuardado =  admin.cargarPartida(archivo);
				Juego generala = new Juego(juegoGuardado);
				Load cargarJuego = new Load();
				cargarJuego.cargarJuego(generala);
			}
			catch (Exception e) 
			{
				JOptionPane.showMessageDialog(null,menus.MENU_ERROR_LOAD);
				menuJugar();
			}	
	}
}
