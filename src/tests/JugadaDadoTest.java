package tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import generala.Jugada;
import generala.JugadaDado;


public class JugadaDadoTest {

class JugadaDadoTest {

	
	  @Test
public void testDado()
{
   Jugada d = new JugadaDado(1);

   assertTrue(d.encontrada(Arrays.asList(1, 1, 2, 3, 4)));
   assertEquals(d.puntos(), 2);
   assertFalse(d.encontrada(Arrays.asList(2, 3, 4, 5, 2)));
   assertEquals(d.puntos(), 0);

   d = new JugadaDado(6);
   assertTrue(d.encontrada(Arrays.asList(6, 6, 6)));

   assertTrue(d.encontrada((ArrayList<Integer>) Arrays.asList(1, 1, 2, 3, 4)));
   assertEquals(d.puntos(), 2);
   assertFalse(d.encontrada((ArrayList<Integer>) Arrays.asList(2, 3, 4, 5, 2)));
   assertEquals(d.puntos(), 0);

   d = new JugadaDado(6);
   assertTrue(d.encontrada((ArrayList<Integer>) Arrays.asList(6, 6, 6)));

   assertEquals(d.puntos(), 18);
}
}




	