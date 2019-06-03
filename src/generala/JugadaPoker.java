package generala;

import java.util.ArrayList;
import java.util.HashSet;

import java.util.Collections;
import java.util.Set;

public class JugadaPoker implements Jugada{

	public JugadaPoker() 
	{
		
	}
	
	
	@Override
	public String nombre() 
	{
		return "Poker";
	}

	@Override
	public int puntos()
	{
		return 40;
	}

	@Override
	public boolean encontrada(ArrayList<Integer> dados) 
	{ //En el set se guardan los numeros sin repetirse y ordenados:
            Set<Integer>sinrepetir = new HashSet<>();
            for(Integer i : dados)
            {
              sinrepetir.add(i);
            }
         //La jugada poker se logra teniendo 4 n°s iguales (y quedaría un 5to dado con valor distinto al resto)       
            return Collections.frequency(dados, dados.get(2)) == 4 && sinrepetir.size() == 2 ;
        /*/Al tener los dados ordenados, en la posición 2 siempre estará el n° que está repetido
           es decir, el n° que nos interesa saber si aparece 4 veces. Se filtra eso. Por ultimo
            Si tenemos 4 dados iguales y 1 distinto, eso implica que nuestro Set tendrá un tamaño
            igual a 2. Se filtra eso también. Si ambas condiciones se cumplen, la jugada es poker/*/
         


          }
}
