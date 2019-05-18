import java.io.ObjectInputStream.GetField;
import java.lang.invoke.SwitchPoint;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Juego  {

	private ArrayList<Jugador> listaJugadores;
	private int inputPrincipal;
	
	public Juego()
	{
		setInputPrincipal(0);
		setListaJugadores(new ArrayList<>());
	}
	
	
	public void cargarCantidadJugadores() throws exceptionCantidadPlayers
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
			throw new exceptionCantidadPlayers("Ingresar cantidad correcta de jugadores");
		}
	}
	
	public void menuPrincipal()
	{
		int input= Integer.parseInt(JOptionPane.showInputDialog("1-SEPARAR:"
                +"\n"+ //reverse? 
                "2- SUMAR"+"\n"+
                "3-TACHAR"+"\n"+
                "4-SALIR"));
		setInputPrincipal(input);
	}
	
	public void menuSeparar(Jugador j)
	{
		int input= Integer.parseInt(JOptionPane.showInputDialog("OPCIONES"+ "\n" + j.menuSepararDados()+ "\n" + "salir=0"));
		if(input==0)
		{
			//break sale del menu y pasa de vuelta
		}
		else
		{
			if(j.separarDados(j.getListaDados(),input) && j.getSeparados().size()<=4)
			{
				j.agregarSeparado(input);
				menuSeparar(j);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Valor inexistente o ya no puede separar mas valores, vuelva a inbtentar o elija salir para continuar");
				menuSeparar(j);
			}
			
		}
	}
	
	public void seleccionarMenu(Jugador j)
	{
		switch(getInputPrincipal()) {
  	  case 1: //separar
  	    menuSeparar(j);
  	    //testear si encuentra jugada
  	    break;
  	  case 2:
  	    //sumar
  	    break;
  	  case 3:
    	// tachar
    	break;  
  	  default:
  		  JOptionPane.showMessageDialog(null, "Valor inexistente, vuelva a intentarlo");
  		  menuPrincipal();
  		  seleccionarMenu(j);
		}
	}
    public void Jugar()
    {
    	try {
			cargarCantidadJugadores();
		} catch (exceptionCantidadPlayers e) {
			
			e.printStackTrace();
		}
    	
    	int vueltaPrincipal=1;
		while(vueltaPrincipal<=10)
		{
			for(Jugador jugador:getListaJugadores())
			{
					int vueltaXJugador=1;
				    while(vueltaXJugador<=3)
				    {
				    	jugador.TirarDados();
				    	//ver si encuentra una jugada sino
				    	menuPrincipal();
				    	seleccionarMenu(jugador);
				    	}
				    
				    }			
             }
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

	
	
	
	
}
