package tests;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import generala.Juego;
import generala.Jugador;

class JuegoTest {

	@BeforeEach
	public void setUp(){
		juego = new Juego();
	}	
	
	@Test
	void juego() {
		Jugador rocio = new Jugador("rocio");
		Jugador belen = new Jugador("belen");
		juego.getListaJugadores().add(belen);
		juego.getListaJugadores().add(rocio);
		assertEquals(juego.getListaJugadores().size(),2);			
	}

 Juego juego;
}
