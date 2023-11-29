package data.test;

import data.TipoTrabajador;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Test;

class TipoTrabajadorTest {
	
	@Test
	void testConstructor() {
		TipoTrabajador tipoTrabajador = new TipoTrabajador("Coordinador");
		
		assertNull(tipoTrabajador.GetId());
		assertEquals("Coordinador", tipoTrabajador.GetNombre());
		assertNull(tipoTrabajador.GetDeletedAt());
	}
	
	@Test
	void testSets() {
		TipoTrabajador tipoTrabajador = new TipoTrabajador("Coordinador");
		
		tipoTrabajador.SetNombre("Evaluador");
		assertEquals("Evaluador", tipoTrabajador.GetNombre());
	}
	
	@Test
	void testGet() throws IOException, SQLException {
		TipoTrabajador tipoTrabajador = TipoTrabajador.Get(1);
		assertEquals("Gestor", tipoTrabajador.GetNombre());
		
		assertNull(TipoTrabajador.Get(8));
	}
	
	@Test
	void testSaveDelete() throws IOException, SQLException {
		TipoTrabajador tipoTrabajador = new TipoTrabajador("Coordinador");
		tipoTrabajador.Save();
		int iId = tipoTrabajador.GetId();
		
		tipoTrabajador = TipoTrabajador.Get(iId);
		assertEquals(iId, tipoTrabajador.GetId());
		assertEquals("Coordinador", tipoTrabajador.GetNombre());
		assertNull(tipoTrabajador.GetDeletedAt());

		tipoTrabajador.SetNombre("Evaluador");
		tipoTrabajador.Save();
		tipoTrabajador = TipoTrabajador.Get(iId);
		assertEquals("Evaluador", tipoTrabajador.GetNombre());

		tipoTrabajador.Delete();
		assertNotNull(tipoTrabajador.GetDeletedAt());
		assertNull(TipoTrabajador.Get(iId));
	}
	
	@Test
	void testSearch() throws IOException, SQLException {
		List<TipoTrabajador> aTipoTrabajador = TipoTrabajador.Search(null);
		
		assertEquals(3, aTipoTrabajador.size());
		assertEquals("Gestor", aTipoTrabajador.get(0).GetNombre());
		assertEquals("Logística", aTipoTrabajador.get(1).GetNombre());
		
		aTipoTrabajador = TipoTrabajador.Search("Logística");
	    assertEquals(1, aTipoTrabajador.size());
	    assertEquals("Logística", aTipoTrabajador.get(0).GetNombre());
		
	    aTipoTrabajador = TipoTrabajador.Search("r");
	    assertEquals(2, aTipoTrabajador.size());
		assertEquals("Gestor", aTipoTrabajador.get(0).GetNombre());
		assertEquals("Supervisor", aTipoTrabajador.get(1).GetNombre());
	    
		aTipoTrabajador = TipoTrabajador.Search("Evaluador");
	    assertEquals(0, aTipoTrabajador.size());
	}
}