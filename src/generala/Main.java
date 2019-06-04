package generala;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args){
		
		/*/Juego generala=new Juego();
		generala.Jugar();/*/
                
                JugadaPoker poker = new JugadaPoker();
                ArrayList<Integer>dados=new ArrayList<Integer>();
                dados.add(5);
		dados.add(5);
		dados.add(5);
		dados.add(5);
		dados.add(6);
                
                System.out.println(poker.encontrada(dados));
		
		/*Jugador diego=new Jugador("diego");
		for (int i = 0; i < diego.getSeparados().size(); i++) {
			
			System.out.println(diego.getSeparados().get(i));
		}
		ArrayList<Integer>dados=new ArrayList<Integer>();
		dados.add(5);
		dados.add(5);
		dados.add(4);
		dados.add(4);
		dados.add(4);
		Jugada poker=new JugadaPoker();
		Jugada escalera=new JugadaEscalera();
		Jugada full=new JugadaFull();
		System.out.println(escalera.encontrada(dados));
		System.out.println(full.encontrada(dados));
		System.out.println(poker.encontrada(dados));
	*/
		

	}
	
}