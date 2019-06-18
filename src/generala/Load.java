package generala;

import javax.swing.JOptionPane;

public class Load {
	
	public Load()
	{
		
	}
	
	public void cargarJuego(Juego j)
	{
	        final String GENERALA_ON="on";
	    	final String GENERALA_OFF="off";
	    	String generalaGana=GENERALA_OFF;
	    	int band=0;
			while(j.getVueltaPrincipal() <= 10)
			{ 
				for (int i = 0; i < j.getListaJugadores().size(); i++) 
				{
					while(j.getListaJugadores().get(i).getVueltaXJugador()<=3)
					{  
						System.out.println("\n" +"JUGADOR: " + j.getListaJugadores().get(i).getNombre().toUpperCase() + "\n" + "RONDA: " + j.getVueltaPrincipal() + "\n" + "VUELTA: " + j.getListaJugadores().get(i).getVueltaXJugador());
						if(band>0)
						{
							j.getListaJugadores().get(i).TirarDados();
							j.menuReverse(j.getListaJugadores().get(i));
						}
						else
						{
							j.getListaJugadores().get(i).imprimirDados();
						}
						band++;
						
						if(j.generalaServida(j.getListaJugadores().get(i)))
						{
							j.getListaJugadores().get(i).setVueltaXJugador(4);
							i=5;
							j.setVueltaPrincipal(11);
							generalaGana=GENERALA_ON;
						}
						else
						{
							if(j.encontrarJugada(j.getListaJugadores().get(i)) != true)
							{
								j.menuPrincipal();
								try 
								{
									j.seleccionarMenu(j.getListaJugadores().get(i));
								} 
								catch (Exception e)
								{
									e.printStackTrace();
								}
								j.getListaJugadores().get(i).getSeparados().addAll(j.getListaJugadores().get(i).getSeparadosPrevio());
								j.getListaJugadores().get(i).borrarListaSeparadosPrevio();
							}
							if(j.getListaJugadores().get(i).getVueltaXJugador() != 4)
							{
								if(j.encontrarJugadaSeparados(j.getListaJugadores().get(i)) != true)
								{
									if(j.getListaJugadores().get(i).getVueltaXJugador() == 3)
									{
										while(!j.tacharJugada(j.getListaJugadores().get(i)));
										JOptionPane.showMessageDialog(null, "JUGADA TACHADA");
									}
									j.getListaJugadores().get(i).setVueltaXJugador(j.getListaJugadores().get(i).getVueltaXJugador()+1);
								}
							}
						}
					}  
					j.getListaJugadores().get(i).setVueltaXJugador(1);
					j.getListaJugadores().get(i).borrarListaseparados();
				}	
				if(j.getVueltaPrincipal() == 10)
				{
					if(generalaGana.equals(GENERALA_OFF))
					{
						for (int i = 0; i < j.getListaJugadores().size(); i++) 
						{
							j.getListaJugadores().get(i).imprimirTableResults();
							System.out.println(j.getListaJugadores().get(i).getNombre() + ":" + "\n" + "TOTAL: " + j.getListaJugadores().get(i).sumarResultadosFinales());
						}
					    JOptionPane.showMessageDialog(null, "EL GANADOR ES " + j.ganador().getNombre() + " CON UN TOTAL DE: " + j.ganador().sumarResultadosFinales());	
					}
				j.setVueltaPrincipal(j.getVueltaPrincipal()+1);
				}
			}
	    }
	
	
}
