package generala;
import java.util.ArrayList;
import java.util.List;
//Interfaz del juego que sirve para hacer de molde para las clases de jugada que utilizarán los s
public interface Jugada {

	abstract String nombre();
	abstract int puntos();
	abstract boolean encontrada(List<Integer> dados); 
}
