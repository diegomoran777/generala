package generala;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import generala.Jugador;

public class JugadorTest {

	@BeforeEach
	public void setUp() 
	{
		jug = new Jugador("");
		jug.getSeparadosPrevio().add(1);
		jug.getSeparadosPrevio().add(2);
		jug.getSeparadosPrevio().add(5);
		
		
		
	}

	@Test
	public void nombre() {
		assertEquals(jug.getNombre(),"");
		assertNotEquals(jug.getNombre(), 1);
		jug.setNombre("rocio");
		assertNotEquals(jug.getNombre(), "juan");
		assertEquals(jug.getNombre(),"rocio");
	}

	 @Test
		public void JugadaDisponible() {
			 jug.anotarResultado("ESCALERA", 20);
			assertTrue(jug.jugadaDisponible("GENERALA"));
			assertFalse(jug.jugadaDisponible("ESCALERA"));
			
		}
	 
	 @Test
		public void anotarResultado() {
			assertTrue(jug.anotarResultado("GENERALA", 20));
		}
	 @Test
	 public void recuperarDadosTest() 
	 {
		 
		 ArrayList<Integer>listaSeparadosPrevio = new ArrayList<Integer>();
		 listaSeparadosPrevio.add(1);
		 listaSeparadosPrevio.add(2);
		 assertTrue(jug.recuperarDados(listaSeparadosPrevio, 1));
		 assertFalse(jug.recuperarDados(listaSeparadosPrevio,3));
	 }
	@Test
	public void ValoresDeGetSeparadosPrevioTest() 
	{	
		assertTrue(jug.getSeparadosPrevio().contains(5));
		assertFalse(jug.getSeparadosPrevio().contains(4));
	}
	@Test
	public void devolverAListaDeDadosTest()
	{
		jug.devolverAListaDeDados(5);
		assertFalse(jug.getSeparadosPrevio().contains(5));
		
	}
	@Test
	public void separarDadosTest() {
		ArrayList<Integer> dados = new ArrayList<Integer>();
		dados.add(1);
		 assertTrue(jug.separarDados(dados,1));
	}
	@Test
	public void agregarSeparadorTest() {
		jug.getListaDados().add(6);
		jug.agregarSeparadoPrevio(6);
		assertFalse(jug.getListaDados().contains(6));
		assertTrue(jug.getSeparadosPrevio().contains(6));
	}
	@Test
	public void agregarDadoTest() {
		jug.agregarDado(4);
		assertTrue(jug.getListaDados().contains(4));
		assertFalse(jug.getListaDados().contains(2));
	}
	@Test
	public void borrarListaDados() {
		jug.getListaDados().add(1);
		jug.borrarListaDados();
		assertTrue(jug.getListaDados().size()==0);
	}
	@Test
	public void borrarListaSeparadosTest() {
		jug.getSeparados().add(1);
		jug.borrarListaseparados();
		assertTrue(jug.getSeparados().size()==0);
	}
	@Test
	public void borrarListaSeparadosPrevioTest() {
		jug.getSeparadosPrevio().add(1);
		jug.borrarListaSeparadosPrevio();
		assertTrue(jug.getSeparadosPrevio().size()==0);
	}
	@Test
	public void reverseTest() {
		jug.getListaDados().add(5);
		jug.reverse();
		assertTrue(jug.getListaDados().get(0)==2);
		
	}
	@Test
	public void TirarDados() {
		jug.TirarDados();
		assertTrue(jug.getListaDados().size()==5);
	}
	@Test
	public void pasarAJson()
	{
		Map<String,Integer> mapa = new TreeMap<String, Integer>();
		mapa.put("GENERALA",1 );
		mapa.put("ESCALERA",2 );
		mapa.put("FULL",3);
		mapa.put("1",30);
		mapa.put("2",30);
		mapa.put("3",40);
		
		ArrayList<Integer> dados = new ArrayList<Integer>();
		dados.add(1);
		dados.add(1);
		dados.add(1);
		dados.add(1);
		dados.add(1);
		jug.setNombre("belen");
		jug.setSeparados(dados);
		jug.setSeparadosPrevio(new ArrayList<Integer>());
		jug.setTablaResults(mapa);
		jug.setListaDados(dados);
		jug.setVueltaXJugador(3);
		assertTrue(jug.pasarAJson() instanceof JSONObject);
	}
	Jugador jug;
	
	
 }