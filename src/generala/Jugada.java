package generala;
import java.util.List;

public interface Jugada {

	abstract String nombre();
	abstract int puntos();
	abstract boolean encontrada(List<Integer> dados); 
}
