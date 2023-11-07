package data;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

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
	
	private Date _dtDeletedAt = null;
	
	public Date GetDeletedAt() { return _dtDeletedAt; }
	
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
	public static Trabajador Get(int iId) throws IOException, SQLException {
		Connection con = null;
		ResultSet rs = null;
		
		try {
			con = Database.Connection();
			rs = con.createStatement().executeQuery("SELECT nombre FROM Trabajador WHERE id = " + iId + ";");
			if(rs.next()) return new Trabajador(rs.getString("nombre"));
		}
		finally {
			if (rs != null) rs.close();
			if (con != null) con.close();
		}
		
		return null;
	}
	
	/**
	 * Guarda la instancia de la clase si dicha instancia no existe en la base de datos. En caso contrario, la actualiza
	 * @throws IOException
	 * @throws SQLException
	 */
	public void Save() throws IOException, SQLException {
		Connection con = null;
		ResultSet rs = null;
	
		try {
			con = Database.Connection();
			rs = con.createStatement().executeQuery("SELECT id FROM trabajador WHERE id = " + this.GetId() + ";");
			System.out.println(rs.next());
			
			if(!rs.next()) {
				int iLastId = Database.LastId(con);
				con.createStatement().executeQuery("INSERT INTO trabajador (id, nombre) VALUES (" + iLastId + "," + Database.String2Sql(this.GetNombre(), false, false) + ");");
				_iId = iLastId;
			}
			else con.createStatement().executeUpdate("UPDATE trabajador SET nombre = " + Database.String2Sql(this.GetNombre(), false, false) + " WHERE id = " + this.GetId() + ");");
			
			
		}
		finally {
			if (rs != null) rs.close();
			if (con != null) con.close();
		}
	}
	
	/**
	 * @throws IOException
	 * @throws SQLException
	 */
	public void Delete() throws IOException, SQLException {
		if(_iId == null || _dtDeletedAt != null) throw new IllegalStateException();
		
		Connection con = null;
		
		try {
			con = Database.Connection();
			con.createStatement().executeQuery("DELETE FROM trabajador WHERE id = " + this.GetId() + ";");			
			_dtDeletedAt = new Date();
		}
		finally {
			if (con != null) con.close();
		}
	}	
}