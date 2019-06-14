package generala;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Bel
 */
public class JugadaFull implements Jugada
{
  @Override
	public String nombre() 
    {
    	return "full";
	}

	@Override
	public int puntos() 
	{
		return 30;
	}
        
	@Override
    public boolean encontrada(List<Integer> dados) 
    {   
		if (dados.size() < 5) 
		{
			return false;
		}
		Collections.sort(dados); 
		Set<Integer> sinrepetidos= new HashSet<>();
		for(Integer i : dados) {
			sinrepetidos.add(i);
		}
		return Collections.frequency(dados, dados.get(2)) == 3 && sinrepetidos.size() == 2;
    }
}
