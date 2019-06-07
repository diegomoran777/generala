package generala;


import java.util.ArrayList;
import java.util.Arrays;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;



class JugadaGeneralaTest {
      
	
	
	  @Test
	  public void testGenerala()
	    {
	        Jugada j = new JugadaGenerala();
	        assertTrue(j.encontrada((ArrayList<Integer>) Arrays.asList(1, 1, 1, 1, 1)));
	        assertFalse(j.encontrada((ArrayList<Integer>) Arrays.asList(3, 2, 4, 5, 2)));
	    }
	}





