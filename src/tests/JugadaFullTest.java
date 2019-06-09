package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import generala.JugadaFull;

public class JugadaFullTest {
	

    @Before
    public void setUp() 
    {
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