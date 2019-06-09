
package tests;

import java.util.Arrays;

import generala.JugadaFull;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Bel
 */
public class JugadaFullTest {
    
    public JugadaFullTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() 
    {
        JugadaFull FullTest = new JugadaFull();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testName()
    {
        assertEquals("full", FullTest.nombre());
        assertNotEquals("", FullTest.nombre());
        assertNotEquals(" ", FullTest.nombre());

        assertNotEquals("otro", FullTest.nombre());
        
    }
    private void assertNotEquals(String string, String nombre) {
		// TODO Auto-generated method stub
		
	}

	@Test
    public void testPuntos()
    {
    	assertEquals(30, FullTest.puntos());
    	assertNotEquals(35, FullTest.puntos());
    	assertNotEquals(-35, FullTest.puntos());
    }

	private void assertNotEquals(int i, int puntos) {
		// TODO Auto-generated method stub
		
	}

	@Test
	public void testEncontrada()
	{	
	assertFalse(FullTest.encontrada((Arrays.asList(1, 2, 3, 4, 5))));

	
		
		
	}
	
	

	
	
	JugadaFull FullTest;

}

 
