import java.io.ObjectInputStream.GetField;
import java.lang.invoke.SwitchPoint;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Juego  {

	private ArrayList<Jugador> listaJugadores;
	private int inputPrincipal;
	private ArrayList<Jugada> jugadas;
	
	public Juego()
	{
		setInputPrincipal(0);
		setListaJugadores(new ArrayList<>());
		setJugadas(new ArrayList<Jugada>());
		
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
                +"\n"+ //reverse?
                "2-REINCORPORAR"+"\n"+
                "3-SUMAR"+"\n"+
                "4-TACHAR"+"\n"+
                "5-SAVE"+"\n"+
                "6-LOAD"+"\n"+
                "0-SALIR O CONTINUAR"));
		setInputPrincipal(input);
	}
	
	
	public void seleccionarMenu(Jugador j)
	{
		switch(getInputPrincipal()) {
  	  case 1: //separar
  	    menuSeparar(j);
  	    menuPrincipal();
  	    //testear si encuentra jugada
  	    break;
  	  case 2:
  	    menuRecuperar(j);
  	    menuPrincipal();
  	    break;
  	  case 3:
    	
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
					jugador.borrarListaseparados();
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


	public ArrayList<Jugada> getJugadas() {
		return jugadas;
	}


	public void setJugadas(ArrayList<Jugada> jugadas) {
		this.jugadas = jugadas;
	}
	
	

	
	
	
	
}
