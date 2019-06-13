package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import generala.JugadaGenerala;

public class JugadaGeneralaTest {

      
	@BeforeEach
	
	public void setUp() {
		
		j = new JugadaGenerala();
	}
	
	  @Test
	  public void testEncontrada()
	    {
	     
	        assertTrue(j.encontrada((ArrayList<Integer>) Arrays.asList(1, 1, 1, 1, 1)));
	        assertFalse(j.encontrada((ArrayList<Integer>) Arrays.asList(3, 2, 4, 5, 2)));
	    }
	
	  @Test
	  public void testNombre() {
		  assertEquals(j.nombre(),"generala");
		  assertNotEquals(j.nombre(), "hola");
	  }


 JugadaGenerala j;
 }












