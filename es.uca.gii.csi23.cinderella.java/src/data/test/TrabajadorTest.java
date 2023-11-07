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
		Trabajador trabajador = new Trabajador("O'Connell");
		trabajador.Save();
		int iClavePrimaria = trabajador.GetId();
		
		trabajador = Trabajador.Get(iClavePrimaria);
		assertEquals(iClavePrimaria, trabajador.GetId());
		assertEquals("O'Connell", trabajador.GetNombre());
		assertNull(trabajador.GetDeletedAt());

		trabajador.SetNombre("PepitoGrillo");
		trabajador.Save();
		trabajador = Trabajador.Get(iClavePrimaria);
		assertEquals("PepitoGrillo", trabajador.GetNombre());

		trabajador.Delete();
		assertNotNull(trabajador.GetDeletedAt());

		assertNull(Trabajador.Get(iClavePrimaria));
	}
}