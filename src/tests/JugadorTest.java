package tests;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

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

	
	Jugador jug;
	
 }