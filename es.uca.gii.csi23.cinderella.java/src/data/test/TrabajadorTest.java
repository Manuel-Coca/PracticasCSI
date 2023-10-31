package data.test;

import data.Trabajador;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class TrabajadorTest {
	
	@Test
	void testConstructor() {
		Trabajador TrabajadorTest = new Trabajador("Pepe");
		
		assertNull(TrabajadorTest.GetId());
		assertEquals("Pepe", TrabajadorTest.GetNombre());
	}
	
	@Test
	void testSets() {
		Trabajador TrabajadorTest = new Trabajador("Pepe");
		
		TrabajadorTest.SetId(1);
		assertEquals(1, TrabajadorTest.GetId());
		TrabajadorTest.SetNombre("Alberto");	
		assertEquals("Alberto", TrabajadorTest.GetNombre());
		System.out.println(TrabajadorTest.toString());
	}
}