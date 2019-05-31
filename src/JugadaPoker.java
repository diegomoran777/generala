import java.util.ArrayList;

import java.util.Collections;

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
	{
		if(dados.size() == 0)
		{
			return false;
		}
		Collections.sort(dados);
		for(int i = 1; i < dados.size(); i++)
		{
			if(i == 4)
			{
				return true;
			}
			if(dados.get(i) != dados.get(i-1) && i != 1)
			{
				return false;
			}
		}
		return true;
	}

}
