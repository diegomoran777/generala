package generala;


import java.util.Collections;
import java.util.HashSet;
import java.util.List;
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

	public boolean encontrada(List<Integer> dados) 
	{
		if (dados.size() < 5) 
		{
			return false;
		}
		Set<Integer> sinrepetidos= new HashSet<>();
		for(Integer i : dados) {
			sinrepetidos.add(i);
		}
		return Collections.frequency(dados, dados.get(2)) == 4 && sinrepetidos.size() == 2;
	}
	 

}
