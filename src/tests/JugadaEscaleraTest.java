/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import generala.JugadaEscalera;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Alejandra
 */
public class JugadaEscaleraTest {
    
    public JugadaEscaleraTest() {
        
    }
    
    @Before
    public void setUp() {
        escaTest = new JugadaEscalera();
    }
    
    @Test
    public void testNombre()
    {
    	assertEquals(escaTest.nombre(), "escalera");
    	assertNotEquals(escaTest.nombre(), "");
    	assertNotEquals(escaTest.nombre(), " ");
    	assertNotEquals(escaTest.nombre(), "otro");
    }
    @Test
    public void testPuntos()
    {
    	assertEquals(20, escaTest.puntos());
    }

    @Test
    public void testEncontrada()
    {
    	assertTrue(escaTest.encontrada((Arrays.asList(1, 2, 3, 4, 5))));
    	assertTrue(escaTest.encontrada((Arrays.asList(2, 3, 4, 5, 6))));
    	assertTrue(escaTest.encontrada((Arrays.asList(6, 2, 4, 5, 3))));
    	assertTrue(escaTest.encontrada((Arrays.asList(5, 1, 2, 3, 4))));
    	
    	assertFalse(escaTest.encontrada((Arrays.asList(0,0,0,0,0))));
    	assertFalse(escaTest.encontrada((Arrays.asList())));
    	assertFalse(escaTest.encontrada((Arrays.asList(1, 2, 3, 4, -5))));
    	assertFalse(escaTest.encontrada((Arrays.asList(1, 2, 3, 4))));
    	assertFalse(escaTest.encontrada((Arrays.asList(0, 2, 3, 4, 5))));
    }
    
    
    
    JugadaEscalera escaTest;
}
