package data.test;

import data.Trabajador;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

class trabajadorTest {
	
	@Test
	void testConstructor() {
		Trabajador trabajadorTest = new Trabajador("Pepe");
		
		assertNull(trabajadorTest.GetId());
		assertEquals("Pepe", trabajadorTest.GetNombre());
	}
	
	@Test
	void testSets() {
		Trabajador trabajadorTest = new Trabajador("Pepe");
		
		trabajadorTest.SetNombre("Alberto");	
		assertEquals("Alberto", trabajadorTest.GetNombre());
	}
	
	@Test
	void testGet() throws IOException, SQLException {
		Trabajador trabajadorTest = Trabajador.Get(1);
		assertEquals(1, trabajadorTest.GetId());
		assertEquals("Javier", trabajadorTest.GetNombre());
		
		trabajadorTest = Trabajador.Get(8);
		assertNull(trabajadorTest);
	}
}