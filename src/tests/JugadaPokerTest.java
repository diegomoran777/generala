package tests;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import generala.JugadaPoker;



class JugadaPokerTest {

	@BeforeEach
	public void setUp() {
		
		 p = new JugadaPoker();
	
	}
	
	  @Test
	  public void testEncontrada()
	    {
	        
	        assertTrue(p.encontrada((ArrayList<Integer>) Arrays.asList(4, 4, 4, 4, 1)));
	        assertFalse(p.encontrada((ArrayList<Integer>) Arrays.asList(2, 2, 5, 1, 2))); 	        
	    }
	  
	  @Test
	  public void testNombre() {
		  
		  assertEquals(p.nombre(), "poker");
		  assertNotEquals(p.nombre(), "lalal");
	  }
	  
	  public void testPuntos() {
		  assertEquals(p.puntos(), 40);
	  }
	  
	  JugadaPoker p;
	  
	  
	}

