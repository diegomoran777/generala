package generala;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Juego  {

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

	//Pregunta la cantidad de jugadores para iniciar el juego.
	public void cargarCantidadJugadores()
	{
		
		int jugador=1;
		String cantidad=JOptionPane.showInputDialog("GENERALA"+ "\n" + "Ingrese cantidad de jugadores:" + " 2,3 o 4 jugadores");
		
		//Verifica si hay un valor nulo, que vuelva a cargar la funcion.
		if(cantidad == null)
		{
			JOptionPane.showMessageDialog(null, "Cantidad incorrecta,vuelva a intentarlo");
			cargarCantidadJugadores();
		}
		else
		{	
			/*Verifica que el valor ingresado es correcto o esta en el rango de 2 a 4.
			Luego te pide ingresar el nombre de cada jugador y agrega a una lista de jugadores, los jugadores recien instanciados.*/
			
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
	
	//
	public boolean tacharJugada(Jugador j) 
	{
		String input= JOptionPane.showInputDialog("ESCRIBA EL NOMBRE DE LA JUGADA QUE DESEA TACHAR: ");
		return input == null ? false : j.anotarResultado(input, Jugador.getPuntostachar());
	}
	
    public void menuTachar(Jugador j)  
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
			JOptionPane.showMessageDialog(null, "SE HAN RECUPERADO TODOS LOS DADOS");
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
					j.agregarDado(input2);
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
		
	public void seleccionarMenu(Jugador j) 
	{
		final String SEPARAR_DADO="1";
		final String REINCORPORAR_DADO="2";
		final String SUMAR_DADO="3";
		final String TACHAR_JUGADA="4";
		final String SAVE_JUEGO="5";
		final String LOAD_JUEGO="6";
		final String IMPRIMIR_TABLA_RESULTADOS="7";
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
      	
      	    break;
        case LOAD_JUEGO:
        	
      	    break;
        case IMPRIMIR_TABLA_RESULTADOS :
        	j.imprimirTableResults();
        	menuPrincipal();
			seleccionarMenu(j);
      	    break;    
        case SALIR:
        	//j.getSeparados().addAll(j.getSeparadosPrevio());
        	//j.borrarListaSeparadosPrevio();
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
	
    public void Jugar() 
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
							seleccionarMenu(getListaJugadores().get(i));
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
    
    public boolean menuSumar(Jugador j) 
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
	
	

	
}
