package tests;

import java.util.Arrays;
import java.util.ArrayList;
import generala.JugadaFull;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Bel
 */
public class JugadaFullTest 
{
    
    public JugadaFullTest() 
    {
    }
    
    
    @Before
    public void setUp() {
    	
    	prueba = new ArrayList<Integer>();
    	prueba.add(1);
    	prueba.add(1);
    	prueba.add(1);
    	prueba.add(5);
    	prueba.add(5);
        FullTest = new JugadaFull();
        
      
    }

    @Test
    public void testName()
    {
        assertEquals("full", FullTest.nombre());
        assertNotEquals("", FullTest.nombre());
        assertNotEquals(" ", FullTest.nombre());
        assertNotEquals("otro", FullTest.nombre());
        
    }


	@Test
    public void testPuntos()
    {
    	assertEquals(30, FullTest.puntos());
    
    }

	@Test
	public void testEncontrada()
	{	 
		
	assertFalse(FullTest.encontrada((Arrays.asList(1, 2, 3, 4, 5))));
	assertFalse(FullTest.encontrada((Arrays.asList(-1,1,2,3,4))));
	assertFalse(FullTest.encontrada((Arrays.asList(-1,1,1,3,3))));
	assertFalse(FullTest.encontrada((Arrays.asList(-1,1,2,3))));
	assertFalse(FullTest.encontrada((Arrays.asList())));
	assertFalse(FullTest.encontrada((Arrays.asList())));
	assertTrue(FullTest.encontrada((Arrays.asList(5, 5, 1, 1, 5))));
	assertTrue(FullTest.encontrada((Arrays.asList(2, 2, 1, 1, 2))));
	assertTrue(FullTest.encontrada(prueba));
	
	
	
	}
	
	JugadaFull FullTest;
	ArrayList<Integer> prueba;

}

 