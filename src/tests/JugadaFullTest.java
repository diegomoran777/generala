

package tests;

import java.awt.List;
import java.util.Arrays;
import java.util.ArrayList;

import generala.ArrayList;
import generala.JugadaFull;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

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
    public void setUp() {
    	prueba = new ArrayList<Integer>();
    	prueba.add(1);
    	prueba.add(1);
    	prueba.add(1);
    	prueba.add(1);
    	prueba.add(1);
        FullTest = new JugadaFull();
        
      
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
    	
    	
    
    }


	@Test
	public void testEncontrada()
	{	 
//		
//	assertFalse(FullTest.encontrada((Arrays.asList(1, 2, 3, 4, 5))));
//	assertTrue(FullTest.encontrada((Arrays.asList(5, 5, 1, 1, 5))));
//	assertFalse(FullTest.encontrada((Arrays.asList(-1,1,2,3,4))));
//	assertFalse(FullTest.encontrada((Arrays.asList(-1,1,1,3,3))));
//	assertFalse(FullTest.encontrada((Arrays.asList(-1,1,2,3))));
//	assertTrue(FullTest.encontrada((Arrays.asList())));
	//assertTrue(FullTest.encontrada((ArrayList<Integer>) Arrays.asList(4, 4, 4, 1, 1)));
	//assertTrue(FullTest.encontrada((ArrayList<Integer>)Arrays.asList(1,2,3,4,5)));
	



	

	
		
		
	}
	
	JugadaFull FullTest;
	private ArrayList<Integer> prueba;

}

 