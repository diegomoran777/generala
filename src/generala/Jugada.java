package generala;

import java.util.ArrayList;

public interface Jugada {

	abstract String nombre();
	abstract int puntos();
	abstract boolean encontrada(ArrayList<Integer> dados); 
}
