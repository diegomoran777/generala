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
	{   Set<Integer> sinrepetir = new HashSet<>();
            for(Integer i : dados)
            {
                sinrepetir.add(i);
            }
            return Collections.frequency(dados, dados.get(2)) == 4 && sinrepetir.size() == 2;
	}

}
