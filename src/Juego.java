
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Juego  {

	private int inputPrincipal;
	int vueltaXJugador;
	private ArrayList<Jugador> listaJugadores;
	private ArrayList<Jugada> jugadas;
	private final int SALIR_WHILE_VUELTA=4;
	private final int SALIR=0;
	private final int LIMITE_VUELTAS_GENERALES=10;
	private final int LIMITE_VUELTAS_JUGADOR=3;
	
	public Juego()
	{
		jugadas=new ArrayList<Jugada>();
		jugadas.add(new JugadaGenerala());
		jugadas.add(new JugadaPoker());
		jugadas.add(new JugadaFull());
		jugadas.add(new JugadaEscalera());
		setInputPrincipal(0);
		setListaJugadores(new ArrayList<>());
		setJugadas(jugadas);
		setVueltaXJugador(1);
	}
	
	public void cargarCantidadJugadores() throws exceptionCantidadPlayers
	{
		final int VALOR_LIMITE_MENOR=2;
		final int VALOR_LIMITE_MAYOR=4;
		int cantidad=Integer.parseInt(JOptionPane.showInputDialog("Ingrese cantidad de jugadores:" + "2,3,4 jugadores"));
		if(cantidad>=VALOR_LIMITE_MENOR || cantidad<=VALOR_LIMITE_MAYOR)
		{
			for(int i=0; i < cantidad; i++)
			{
				String nombre= JOptionPane.showInputDialog("Ingrese el nombre");
				getListaJugadores().add(new Jugador(nombre));
			}
		}
		else
		{
			throw new exceptionCantidadPlayers("Ingresar cantidad correcta de jugadores");
		}
	}
	
	public boolean tacharJugada(Jugador j) throws exceptionjugadaAnotada
	{
		String input= JOptionPane.showInputDialog("ESCRIBA EL NOMBRE DE LA JUGADA QUE DESEA TACHAR: ");
		return j.anotarResultado(input, Jugador.getPuntostachar());
		
	}
	
    public void menuTachar(Jugador j) throws exceptionjugadaAnotada 
    {
			if(tacharJugada(j))
			{
				JOptionPane.showMessageDialog(null, "JUGADA TACHADA");
				setVueltaXJugador(SALIR_WHILE_VUELTA);
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
    	final int SI=1;
    	final int NO=2;
    	int input=Integer.parseInt(JOptionPane.showInputDialog("QUIERE USAR EL VALOR DE LA CARA OPUESTA DE LOS DADOS?:" + "\n" + "1-SI:" + "\n" + "2-NO:" ));
    	if(input==SI)
    	{
    		j.reverse();
    	}
    	else
    	{
    		if(input==NO)
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
			int input= Integer.parseInt(JOptionPane.showInputDialog("OPCIONES - ELIJA UN VALOR A SEPARAR:"+ "\n" + j.menuSepararDados()+ "\n" + "salir= 0"));
			if(input==SALIR)
			{
				JOptionPane.showMessageDialog(null,  "SALIENDO AL MENU PRINCIPAL");
			}
			else
			{
				if(j.separarDados(j.getListaDados(),input))
				{
					j.agregarSeparadoPrevio(input);
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
			int input= Integer.parseInt(JOptionPane.showInputDialog("OPCIONES - ELIJA UN VALOR A REINCORPORAR:"+ "\n" + j.menuRecuperarDados()+ "\n" + "salir= 0"));
			if(input==SALIR)
			{
				JOptionPane.showMessageDialog(null,  "SALIENDO AL MENU PRINCIPAL");
			}
			else
			{
				if(j.recuperarDados(j.getSeparadosPrevio(), input))
				{
					j.agregarDado(input);
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
		int input= Integer.parseInt(JOptionPane.showInputDialog(
				"1-SEPARAR:"+ "\n" + 
                "2-REINCORPORAR" + "\n" +
                "3-SUMAR" + "\n" +
                "4-TACHAR" + "\n" +
                "5-SAVE" + "\n" +
                "6-LOAD" + "\n" +
                "7-IMPRIMIR TABLA RESULTADOS" + "\n" +
                "0-SALIR O CONTINUAR"));
		setInputPrincipal(input);
	}
		
	public void seleccionarMenu(Jugador j) throws exceptionjugadaAnotada
	{
		final int SEPARAR_DADO=1;
		final int REINCORPORAR_DADO=2;
		final int SUMAR_DADO=3;
		final int TACHAR_JUGADA=4;
		final int SAVE_JUEGO=5;
		final int LOAD_JUEGO=6;
		final int IMPRIMIR_TABLA_RESULTADOS=7;
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
	
    public void Jugar() throws exceptionjugadaAnotada
    {
    	try {
    		cargarCantidadJugadores();
    		} 
    	catch (exceptionCantidadPlayers e) 
    	{
			e.printStackTrace();
		}
    	final String GENERALA_ON="on";
    	final String GENERALA_OFF="off";
    	String generalaGana=GENERALA_OFF;
    	int vueltaPrincipal=1;
		while(vueltaPrincipal<=LIMITE_VUELTAS_GENERALES)
		{
			for (int i = 0; i < getListaJugadores().size(); i++) 
			{
				setVueltaXJugador(1);
				getListaJugadores().get(i).borrarListaseparados();
				while(getVueltaXJugador()<=LIMITE_VUELTAS_JUGADOR)
				{   
					getListaJugadores().get(i).TirarDados();
					menuReverse(getListaJugadores().get(i));
					
					if(generalaServida(getListaJugadores().get(i)))
					{
						setVueltaXJugador(SALIR_WHILE_VUELTA);
						i=5;
						vueltaPrincipal=11;
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
						if(getVueltaXJugador() != SALIR_WHILE_VUELTA)
						{
							if(encontrarJugadaSerparados(getListaJugadores().get(i)) != true)
							{
								if(getVueltaXJugador() == LIMITE_VUELTAS_JUGADOR)
								{
									if(tacharJugada(getListaJugadores().get(i)))
									{
										JOptionPane.showMessageDialog(null, "JUGADA TACHADA");
									}
									else
									{
										tacharJugada(getListaJugadores().get(i));
									}
								}
								setVueltaXJugador(getVueltaXJugador()+1);
							}
						}
					}
				}   			
			}       
		}
		
		if(generalaGana.equals(GENERALA_OFF))
		{
			for (int i = 0; i < getListaJugadores().size(); i++) 
			{
				System.out.println(getListaJugadores().get(i).getNombre());
				getListaJugadores().get(i).imprimirTableResults();
				System.out.println(getListaJugadores().get(i).getNombre() + ":" + "\n" + "TOTAL: " + getListaJugadores().get(i).sumarResultadosFinales());
			}
		    JOptionPane.showMessageDialog(null, "EL GANADOR ES " + ganador().getNombre() + "CON UN TOTAL DE: " + ganador().sumarResultadosFinales());	
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
    
    public boolean menuSumar(Jugador j) throws exceptionjugadaAnotada
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
			int input= Integer.parseInt(JOptionPane.showInputDialog("OPCIONES - ELIJA UN VALOR A SUMAR:"+ "\n" + "DADOS: " + j.getListaDados() + "\n" + "salir= 0"));
			if(input==SALIR)
			{
				JOptionPane.showMessageDialog(null,  "SALIENDO AL MENU PRINCIPAL");
				bool=false;
				menuPrincipal();
  	    		seleccionarMenu(j);
			}
			else
			{
				Jugada sumar= new JugadaDado(input);
				if(sumar.encontrada(j.getListaDados()))
				{
					String input_dos= JOptionPane.showInputDialog("DESEA ANOTAR LA JUGADA?:" + input + " PUNTOS: " + sumar.puntos() + " ESCRIBA: " +  "SI" + " O " + "NO");
					if(input_dos.equalsIgnoreCase(OK) && j.anotarResultado(sumar.nombre(), sumar.puntos()))
					{
						bool=true;
						setVueltaXJugador(SALIR_WHILE_VUELTA);
					}
					else
					{
						if(input_dos.equalsIgnoreCase(NOT))
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
    	    	if(jugadas.get(i).nombre().equalsIgnoreCase("generala") && getVueltaXJugador()==1)
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
    
    public boolean encontrarJugada(Jugador j) throws exceptionjugadaAnotada
    {
    	boolean bool=false;
    	int input=0;
    	for(int i = 0; i < jugadas.size(); i++)
    	{   
    		final int ANOTAR=1;
    		final int SALIR_SIN_ANOTAR=2;		
    		int puntos=0;
    	    if(jugadas.get(i).encontrada(j.getListaDados())) 
    	    {
    	    	if(getVueltaXJugador() == 1)
    	    	{
    	    		puntos=5;
    	    	}
    	    		input= Integer.parseInt(JOptionPane.showInputDialog("Es posible anotar " + jugadas.get(i).puntos() + " puntos a " + jugadas.get(i).nombre() + " Desea anotar? " + "\n" + "1-ANOTAR" + "\n" + "2-SALIR"));
    	    	    if(input == ANOTAR)
    	    	    {
    	    	    	if(j.anotarResultado(jugadas.get(i).nombre(),jugadas.get(i).puntos() + puntos))
    	    	    	{
    	    	    		JOptionPane.showMessageDialog(null, "JUGADA ANOTADA CON EXITO");
    	     			    setVueltaXJugador(SALIR_WHILE_VUELTA);
    	     			    bool= true;
    	     		    }
    	    	    	else
    	    	    	{
    	    	    		bool= false;
    	     		    }
    	     		
    	     	    }
    	    	    else
    	    	    {
    	    	    	if(input == SALIR_SIN_ANOTAR)
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
    
    public boolean encontrarJugadaSerparados(Jugador j) throws exceptionjugadaAnotada
    {
    	final int ANOTAR=1;
		final int SALIR_SIN_ANOTAR=2;
    	boolean bool=false;
    	int input=0;
    	for(int i = 0; i < jugadas.size(); i++)
    	{   
    	    if(jugadas.get(i).encontrada(j.getSeparados())) 
    	    {
    	    	input= Integer.parseInt(JOptionPane.showInputDialog("Es posible anotar " + jugadas.get(i).puntos() + " puntos a " + jugadas.get(i).nombre() + " encontrada en los dados separados  " + " Desea anotar? " + "\n" + "1-ANOTAR" + "\n" + "2-SALIR"));
    	    	if(input == ANOTAR)
    	    	{
    	    		if(j.anotarResultado(jugadas.get(i).nombre(),jugadas.get(i).puntos()))
    	    		{
    	    			JOptionPane.showMessageDialog(null, "JUGADA ANOTADA CON EXITO");
    	     		    setVueltaXJugador(SALIR_WHILE_VUELTA);
    	     			bool= true;
    	     		}
    	    		else
    	    		{
    	    			bool= false;
    	     		} 	     		
    	     	}
    	    	else
    	    	{
    	    		if(input == SALIR_SIN_ANOTAR)
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

	public int getInputPrincipal() 
	{
		return inputPrincipal;
	}

	public void setInputPrincipal(int inputPrincipal) 
	{
		this.inputPrincipal = inputPrincipal;
	}

	public ArrayList<Jugada> getJugadas() 
	{
		return jugadas;
	}

	public void setJugadas(ArrayList<Jugada> jugadas) 
	{
		this.jugadas = jugadas;
	}

	public int getVueltaXJugador() 
	{
		return vueltaXJugador;
	}

	public void setVueltaXJugador(int vueltaXJugador) 
	{
		this.vueltaXJugador = vueltaXJugador;
	}
	
	
}
