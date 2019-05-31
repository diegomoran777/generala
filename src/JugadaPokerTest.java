import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

class JugadaPokerTest {

	  @Test
	  public void testPoker()
	    {
	        Jugada p = new JugadaPoker();
	        assertTrue(p.encontrada((ArrayList<Integer>) Arrays.asList(4, 4, 4, 4, 1)));
	        assertFalse(p.encontrada((ArrayList<Integer>) Arrays.asList(2, 2, 5, 1, 2)));
	    }
	}

