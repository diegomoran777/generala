package tests;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import generala.Jugador;

public class JugadorTest {

	@BeforeEach
	public void setUp() 
	{
		jug = new Jugador("");
		jug.getSeparadosPrevio().add(1);
		jug.getSeparadosPrevio().add(2);
		jug.getSeparadosPrevio().add(5);
		
		
	}

	@Test
	public void nombre() {
		assertEquals(jug.getNombre(),"");
		assertNotEquals(jug.getNombre(), 1);
		jug.setNombre("rocio");
		assertNotEquals(jug.getNombre(), "juan");
		assertEquals(jug.getNombre(),"rocio");
	}

	 @Test
		public void JugadaDisponible() {
			 jug.anotarResultado("ESCALERA", 20);
			assertTrue(jug.jugadaDisponible("GENERALA"));
			assertFalse(jug.jugadaDisponible("ESCALERA"));
			
		}
	 
	 @Test
		public void anotarResultado() {
			assertTrue(jug.anotarResultado("GENERALA", 20));
		}
	 @Test
	 public void recuperarDadosTest() 
	 {
		 
		 ArrayList<Integer>listaSeparadosPrevio = new ArrayList<Integer>();
		 listaSeparadosPrevio.add(1);
		 listaSeparadosPrevio.add(2);
		 assertTrue(jug.recuperarDados(listaSeparadosPrevio, 1));
		 assertFalse(jug.recuperarDados(listaSeparadosPrevio,3));
	 }
	@Test
	public void ValoresDeGetSeparadosPrevioTest() 
	{	
		assertTrue(jug.getSeparadosPrevio().contains(5));
		assertFalse(jug.getSeparadosPrevio().contains(4));
	}
	@Test
	public void devolverAListaDeDadosTest()
	{
		jug.devolverAListaDeDados(2);
		assertFalse(jug.getSeparadosPrevio().contains(5));
		
	}
	
	Jugador jug;
	
	
 }