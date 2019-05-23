import java.util.ArrayList;

public class JugadaGenerala implements Jugada {

	
	public JugadaGenerala() {
		
	}

	@Override
	public String nombre() {
		
		return "generala";
	}

	@Override
	public int puntos() {
		
		return 50;
	}

	@Override
	public boolean encontrada(ArrayList<Integer> dados) {
		
		if(dados.size()< 5)
		{
			return false;
		}
		
		for(int i = 0; i < dados.size(); i++){
			if(dados.get(i)!= dados.get(0)) {
				return false;
			}
			
		}
		
		return true;
	}
}

/*
 public class JugadaGeneralaTest
{
    public void testGenerala()
    {
        Jugada j = new JugadaGenerala();
        assertTrue(j.encontrada(Arrays.asList(1, 1, 1, 1, 1)));
        assertFalse(j.encontrada(Arrays.asList(3, 2, 4, 5, 2)));
    }
}

 hacer un array de jugadas, por ejemplo:
ArrayList<Jugada> jugadas = new ArrayList<>();
jugadas.add(new JugadaGenerala());
jugadas.add(new JugadaPoker());
jugadas.add(new JugadaFull());
// etc.... y después:

for(int i = 0; i < jugadas.size(); i++)
{
    if(jugadas.get(i).encontrada(dados)) // se asume que dados es un array con números
    {
        // Por ejemplo:
        System.out.println("Es posible anotar " + jugadas.get(i).puntos() + " puntos a " + jugadas.get(i).nombre());
    }
}
 */
