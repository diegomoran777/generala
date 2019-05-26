import java.util.ArrayList;
import java.util.Collection;
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
		Collections.sort(dados);
		for(int i = 0; i < dados.size(); i++)
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
