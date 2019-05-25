import java.awt.HeadlessException;
import java.io.ObjectInputStream.GetField;
import java.lang.invoke.SwitchPoint;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Juego  {

	private ArrayList<Jugador> listaJugadores;
	private int inputPrincipal;
	int vueltaXJugador;
	private ArrayList<Jugada> jugadas;
	
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
	
	
	public void cargarCantidadJugadores() throws ExceptionCantidadPlayers
	{
		int cantidad=Integer.parseInt(JOptionPane.showInputDialog("Ingrese cantidad de jugadores:" + "2,3,4 jugadores"));
		        
		if(cantidad>=2 || cantidad<=4)
		{
			for(int i=0;i<cantidad;i++)
			{
				String nombre= JOptionPane.showInputDialog("Ingrese el nombre");
				getListaJugadores().add(new Jugador(nombre));
			}
		}
		else
		{
			throw new ExceptionCantidadPlayers("Ingresar cantidad correcta de jugadores");
		}
	}
	
	public boolean tacharJugada(Jugador j) throws ExceptionJugadaAnotada
	{
		String input= JOptionPane.showInputDialog("ESCRIBA EL NOMBRE DE LA JUGADA QUE DESEA TACHAR: ");
		if(j.anotarResultado(input, Jugador.getPuntostachar()))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
    public void menuTachar(Jugador j) throws ExceptionJugadaAnotada 
    {
			if(tacharJugada(j))
			{
				JOptionPane.showMessageDialog(null, "JUGADA TACHADA");
				setVueltaXJugador(4);
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
    	int input=Integer.parseInt(JOptionPane.showInputDialog("QUIERE USAR EL VALOR DE LA CARA OPUESTA DE LOS DADOS?:"
                +"\n"+"1-SI:"
                +"\n"+"2-NO:" ));
    	if(input==1)
    	{
    		j.reverse();
    	}
    	else
    	{
    		if(input==2)
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
		if(j.getListaDados().size()==0)
		{
			JOptionPane.showMessageDialog(null, "SE HAN SEPARADO TODOS LOS DADOS, REINCORPORE DADOS O SALGA DEL MENU PRINCIPAL PARA CONTINUAR");
		}
		else
		{
		int input= Integer.parseInt(JOptionPane.showInputDialog("OPCIONES - ELIJA UN VALOR A SEPARAR:"+ "\n" + j.menuSepararDados()+ "\n" + "salir= 0"));
		if(input==0)
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
		if(j.getSeparadosPrevio().size()==0)
		{
			JOptionPane.showMessageDialog(null, "SE HAN RECUPERADO TODOS LOS DADOS");
		}
		else
		{
		int input= Integer.parseInt(JOptionPane.showInputDialog("OPCIONES - ELIJA UN VALOR A REINCORPORAR:"+ "\n" + j.menuRecuperarDados()+ "\n" + "salir= 0"));
		if(input==0)
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
		int input= Integer.parseInt(JOptionPane.showInputDialog("1-SEPARAR:"
                +"\n"+ 
                "2-REINCORPORAR"+"\n"+
                "3-SUMAR"+"\n"+
                "4-TACHAR"+"\n"+
                "5-SAVE"+"\n"+
                "6-LOAD"+"\n"+
                "0-SALIR O CONTINUAR"));
		setInputPrincipal(input);
	}
	
	
	public void seleccionarMenu(Jugador j) throws ExceptionJugadaAnotada
	{
		switch(getInputPrincipal()) {
  	  case 1: //separar
  	    menuSeparar(j);
  	    menuPrincipal();
  	    seleccionarMenu(j);
  	    //testear si encuentra jugada
  	    break;
  	  case 2:
  	    menuRecuperar(j);
  	    menuPrincipal();
  	    seleccionarMenu(j);
  	    break;
  	  case 3:
    	
    	break;
      case 4:
    	menuTachar(j);
    	break;
      case 0:
    	j.getSeparados().addAll(j.getSeparadosPrevio());
    	j.borrarListaSeparadosPrevio();
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
    public void Jugar() throws ExceptionJugadaAnotada
    {
    	try {
			cargarCantidadJugadores();
		} catch (ExceptionCantidadPlayers e) {
			
			e.printStackTrace();
		}
    	
    	int vueltaPrincipal=1;
		while(vueltaPrincipal<=10)
		{
			for (int i = 0; i < getListaJugadores().size(); i++) 
			{
				setVueltaXJugador(1);
				getListaJugadores().get(i).borrarListaseparados();
				while(getVueltaXJugador()<=3)
				    {
					    getListaJugadores().get(i).TirarDados();
				    	menuReverse(getListaJugadores().get(i));
				        
				    	if(generalaServida(getListaJugadores().get(i)))
				    	{
				    		setVueltaXJugador(4);
				    		i=5;
				    		vueltaPrincipal=11;
				    	}
				    	else
				    	{
							if(encontrarJugada(getListaJugadores().get(i))!=true)
							{
								menuPrincipal();
								seleccionarMenu(getListaJugadores().get(i));
							}
							
							if(getVueltaXJugador()==3)
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
    public boolean encontrarJugada(Jugador j) throws  ExceptionJugadaAnotada
    {
    	boolean bool=false;
    	int input=0;
    	for(int i = 0; i < jugadas.size(); i++)
    	{   
    		int puntos=0;
    	    if(jugadas.get(i).encontrada(j.getListaDados())) 
    	    {
    	    	if(getVueltaXJugador()==1)
    	    	{
    	    		puntos=5;
    	    	}
    	    		input= Integer.parseInt(JOptionPane.showInputDialog("Es posible anotar " + jugadas.get(i).puntos() + " puntos a " + jugadas.get(i).nombre()+" Desea anotar? "
    	                +"\n"+"1-ANOTAR"
    	                +"\n"+"2-SALIR"));
    	    	    if(input==1)
    	    	    {
    	    	    	if(j.anotarResultado(jugadas.get(i).nombre(),jugadas.get(i).puntos()+puntos))
    	    	    	{
    	    	    		JOptionPane.showMessageDialog(null, "JUGADA ANOTADA CON EXITO");
    	     			    setVueltaXJugador(4);
    	     			    bool= true;
    	     		    }
    	     		   else
    	     		   {
    	     			  bool= false;
    	     		   }
    	     		
    	     	    }
    	    	     else
    	    	     {
    	    		    if(input==2)
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
    
	public ArrayList<Jugador> getListaJugadores() {
		return listaJugadores;
	}

	public void setListaJugadores(ArrayList<Jugador> listaJugadores) {
		this.listaJugadores = listaJugadores;
	}


	public int getInputPrincipal() {
		return inputPrincipal;
	}


	public void setInputPrincipal(int inputPrincipal) {
		this.inputPrincipal = inputPrincipal;
	}


	public ArrayList<Jugada> getJugadas() {
		return jugadas;
	}


	public void setJugadas(ArrayList<Jugada> jugadas) {
		this.jugadas = jugadas;
	}


	public int getVueltaXJugador() {
		return vueltaXJugador;
	}


	public void setVueltaXJugador(int vueltaXJugador) {
		this.vueltaXJugador = vueltaXJugador;
	}
	
	
	

	
	
	
	
}
