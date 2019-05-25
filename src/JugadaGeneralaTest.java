import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

class JugadaGeneralaTest {
      
	
	
	  @Test
	  public void testGenerala()
	    {
	        Jugada j = new JugadaGenerala();
	        assertTrue(j.encontrada((ArrayList<Integer>) Arrays.asList(1, 1, 1, 1, 1)));
	        assertFalse(j.encontrada((ArrayList<Integer>) Arrays.asList(3, 2, 4, 5, 2)));
	    }
	}





