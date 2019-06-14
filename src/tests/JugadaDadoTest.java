package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import generala.JugadaDado;


public class JugadaDadoTest {

	@BeforeEach
	public void setUp() 
	{    d = new JugadaDado(1); 
		 d1 = new JugadaDado(6);
	}

@Test
public void testNombre()
{
	assertEquals("1", d.nombre());
}
	
@Test
public void testEncontrada() {

   assertTrue(d.encontrada(Arrays.asList(1, 1, 2, 3, 4)));
   assertEquals(d.puntos(), 2);
   assertFalse(d.encontrada(Arrays.asList(2, 3, 4, 5, 2)));
   assertEquals(d.puntos(), 0);

   assertTrue(d1.encontrada(Arrays.asList(6, 6, 6)));
   assertEquals(d1.puntos(), 18);
}

 	JugadaDado d;
 	JugadaDado d1;
}




	