package generala;


import java.util.ArrayList;

import java.util.Collections;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Bel
 */
public class JugadaEscalera implements Jugada
{

    public JugadaEscalera() 
    {
    	
    }
    
    @Override
	public String nombre() 
    {
    	return "escalera";
	}

	@Override
	public int puntos() 
	{
		return 20;
	}
        
    @Override
    public boolean encontrada(ArrayList<Integer> dados) 
    {
    	if(dados.size() == 0)
    	{
    		return false;
    	}
    	//sort del array de dados
    	Collections.sort(dados);
    	for(int i = 1; i < dados.size(); i++)
    	{
    		if(dados.get(i) - dados.get(i-1) != 1)
    		{
    			return false;
    		}
    	}
    	return true;                   
    }
}
