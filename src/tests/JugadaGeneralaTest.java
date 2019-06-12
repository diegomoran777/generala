package tests;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import generala.Jugada;
import generala.JugadaGenerala;




public class JugadaGeneralaTest {

class JugadaGeneralaTest {

      
	@BeforeEach
	
	
	  @Test
	  public void testGenerala()
	    {
	        Jugada j = new JugadaGenerala();
	        assertTrue(j.encontrada((ArrayList<Integer>) Arrays.asList(1, 1, 1, 1, 1)));
	        assertFalse(j.encontrada((ArrayList<Integer>) Arrays.asList(3, 2, 4, 5, 2)));
	    }
	}












