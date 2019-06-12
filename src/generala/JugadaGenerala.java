package generala;
import java.util.ArrayList;
import java.util.List;

public class JugadaGenerala implements Jugada {

	
	public JugadaGenerala()
	{
		
	}

	@Override
	public String nombre() 
	{
		return "generala";
	}

	@Override
	public int puntos()
	{
		return 50;
	}

	@Override
	public boolean encontrada(List<Integer> dados) 
	{
		if(dados.size() < 5)
		{
			return false;
		}
		
		for(int i = 0; i < dados.size(); i++)
		{
			if(dados.get(i) != dados.get(0)) 
			{
				return false;
			}
			
		}
		
		return true;
	}
}


