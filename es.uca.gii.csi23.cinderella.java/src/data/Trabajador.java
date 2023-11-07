package data;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import util.Database;

public class Trabajador {
	
	private Integer _iId;
	
	public Integer GetId() { return _iId; }
	
	private String _sNombre;
	
	public String GetNombre() { return _sNombre; }
	
	public void SetNombre(String sNombre) {
		if(sNombre == null) throw new IllegalArgumentException("El nombre no puede ser nulo");
		_sNombre = sNombre;
	}
	
	/**
	 * Constructor publico de la clase Trabajador
	 * @param sNombre
	 */
	public Trabajador(String sNombre) { this(null, sNombre); }
	
	/**
	 * Constructor privado de la clase Trabajador
	 * @param iId
	 * @param sNombre
	 */
	private Trabajador(Integer iId, String sNombre) {
		_iId = iId;
		SetNombre(sNombre);
	}
	
	/**
	 * Devuelve una cadena con formato SuperClase.Clase@CodigoHash:Id:Nombre
	 */
	public String toString() { return super.toString() + ":" + GetId() + ":" + GetNombre(); }
	
	/**
	 * Hace una busqueda a la tabla Trabajador. Devuelve una instancia de la clase Trabajador si la busqueda ha tenido exito o NULL si no
	 * @param iId
	 * @return TrabajadorAuxiliar
	 * @throws IOException
	 * @throws SQLException
	 */
	public static Trabajador Get(Integer iId) throws IOException, SQLException {
		Connection con = null;
		ResultSet rs = null;
		
		try {
			con = Database.Connection();
			rs = con.createStatement().executeQuery("SELECT * FROM Trabajador WHERE id = " + iId + ";");
			if(rs.next()) return new Trabajador(rs.getInt("id"), rs.getString("nombre"));
		}
		finally {
			if (rs != null) rs.close();
			if (con != null) con.close();
		}
		
		return null;
	}
}