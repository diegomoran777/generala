package generala;

import java.util.ArrayList;
//Esta es la interfaz que cada una de las jugadas aplicará
public interface Jugada {
        //devuelve un string con el nombre de cada jugada
	abstract String nombre();
        //devuelve la cantidad de puntos que cada jugada implica
	abstract int puntos();
        //determina si se encontró el tipo de jugada 
	abstract boolean encontrada(ArrayList<Integer> dados); 
}
