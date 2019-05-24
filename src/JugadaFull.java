
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
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
    public boolean encontrada(ArrayList<Integer> dados) 
    {
        //En el set se guardan los numeros sin repetirse:
        Set<Integer> repetido = new HashSet<>();

        
         for (int i = 0; i < dados.size(); i++)
          {
            repetido.add(i);
            //obtengo la cantidad de veces que aparece cadavalor para filtrar
            int freq = Collections.frequency(dados, dados.get(i));
            //si un n aparece 4 veces ya no es jugada Full
              if(freq==4)
                {
                 return false;
                }
           }
          /*Para que haya Full debe haber 1 n repetido 3 veces y otro n repetido 2 veces
          por lo tanto en el Set repetido solo deberia haber 2 numeros*/
            if(repetido.size()!=2)
                {
                  return false;
                }
          //Si hay mass de dos numeros almacenados, no hay Full, caso contrario, devuelve true
        return true;
    }
        
}
