package tests;


import java.util.ArrayList;
import java.util.Arrays;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import generala.Jugada;
import generala.JugadaPoker;



class JugadaPokerTest {

	  @Test
	  public void testPoker()
	    {
	        Jugada p = new JugadaPoker();
	        assertTrue(p.encontrada((ArrayList<Integer>) Arrays.asList(4, 4, 4, 4, 1)));
	        assertFalse(p.encontrada((ArrayList<Integer>) Arrays.asList(2, 2, 5, 1, 2)));
	    }
	}

