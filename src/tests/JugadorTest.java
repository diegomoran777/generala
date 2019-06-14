package tests;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import generala.Jugador;

public class JugadorTest {

	@BeforeEach
	public void setUp() {
		jug = new Jugador("");
		
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
	
	Jugador jug;
	
 }