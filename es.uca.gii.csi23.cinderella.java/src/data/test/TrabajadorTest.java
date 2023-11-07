package data.test;

import data.Trabajador;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

class trabajador {
	
	@Test
	void testConstructor() {
		Trabajador trabajador = new Trabajador("Pepe");
		
		assertNull(trabajador.GetId());
		assertEquals("Pepe", trabajador.GetNombre());
		assertNull(trabajador.GetDeletedAt());
	}
	
	@Test
	void testSets() {
		Trabajador trabajador = new Trabajador("Pepe");
		
		trabajador.SetNombre("Alberto");
		assertEquals("Alberto", trabajador.GetNombre());
	}
	
	@Test
	void testGet() throws IOException, SQLException {
		Trabajador trabajador = Trabajador.Get(1);
		assertEquals("Javier", trabajador.GetNombre());
		
		trabajador = Trabajador.Get(8);
		assertNull(trabajador);
	}
	
	@Test
	void testSaveDelete() throws IOException, SQLException {
		// Punto 1
		Trabajador trabajador = new Trabajador("O'Connell");
		trabajador.Save();
		int iClave = trabajador.GetId();
		// Punto 2
		trabajador = Trabajador.Get(iClave);
		assertEquals(iClave, trabajador.GetId());
		assertEquals("O'Connell", trabajador.GetNombre());
		assertNull(trabajador.GetDeletedAt());
		//Punto 3
		trabajador.SetNombre("PepitoGrillo");
		trabajador.Save();
		trabajador = Trabajador.Get(iClave);
		assertEquals("PepitoGrillo", trabajador.GetNombre());
		//Punto 4
		trabajador.Delete();
		assertNotNull(trabajador.GetDeletedAt());
		//Punto 5
		assertNull(Trabajador.Get(iClave));		
	}
}