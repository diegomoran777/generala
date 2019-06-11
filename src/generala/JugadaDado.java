package generala;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JugadaDado implements Jugada {

	private int m_numero;
    private int m_puntos;
	
	
	public JugadaDado(int numero)
    {
        this.m_numero = numero;
        this.m_puntos = 0;
    }


	@Override
	public String nombre() {
		
		return "" + this.m_numero;
	}

	@Override
	public int puntos() {
		return this.m_puntos;
	}
	
	@Override
	public boolean encontrada(List<Integer> dados) {
	
		this.m_puntos = 0;

        if(dados.size() == 0)
        {
            return false;
        }

        for(int i = 0; i < dados.size(); i++)
        {
            if(dados.get(i) == this.m_numero)
            {
                this.m_puntos += this.m_numero;
            }
        }

        return this.m_puntos > 0;
   
	}

	
	/*
	 public class JugadaDadoTest
{
    public void testJugadaDado()
    {
        Jugada j = new JugadaDado(1);
        assertTrue(j.encontrada(Arrays.asList(1, 1, 2, 3, 4)));
        assertEquals(j.puntos(), 2);
        assertFalse(j.encontrada(Arrays.asList(2, 3, 4, 5, 2)));
        assertEquals(j.puntos(), 0);

        j = new JugadaDado(6);
        assertTrue(j.encontrada(Arrays.asList(6, 6, 6)));
        assertEquals(j.puntos(), 18);
    }
}
	*/
}
