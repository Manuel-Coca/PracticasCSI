package data;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import util.Database;

public class Trabajador {
	
	private Integer _iId;
	public Integer GetId() { return _iId; }
	
	private String _sNombre;
	public String GetNombre() { return _sNombre; }
	public void SetNombre(String sNombre) {
		if(sNombre == null) throw new IllegalArgumentException("El nombre no puede ser nulo.");
		_sNombre = sNombre;
	}
	
	private Date _dtDeletedAt = null;
	public Date GetDeletedAt() { return _dtDeletedAt; }
	
	public Trabajador(String sNombre) { this(null, sNombre); }
	
	private Trabajador(Integer iId, String sNombre) {
		SetNombre(sNombre);
		_iId = iId;
	}
	
	/**
	 * Devuelve una cadena con formato SuperClase.Clase@CodigoHash:Id:Nombre.
	 */
	public String toString() { return super.toString() + ":" + _iId + ":" + _sNombre; }
	
	public static Trabajador Get(int iId) throws IOException, SQLException {
		Connection con = null;
		ResultSet rs = null;
		
		try {
			con = Database.Connection();
			rs = con.createStatement().executeQuery("SELECT id, nombre FROM Trabajador WHERE id = " + iId + ";");
			
			if(rs.next()) return new Trabajador(rs.getInt("id"), rs.getString("nombre"));
			return null;
		}
		finally {
			if (rs != null) rs.close();
			if (con != null) con.close();
		}
	}
	
	/**
	 * Guarda la instancia de la clase si dicha instancia no existe en la base de datos. En caso contrario, la actualiza.
	 * @throws IOException
	 * @throws SQLException
	 */
	public void Save() throws IOException, SQLException {
		Connection con = null;
	
		try {
			con = Database.Connection();
			
			if(_iId == null) {
				con.createStatement().executeUpdate("INSERT INTO trabajador (id, nombre) VALUES (" + _iId + ", " + Database.String2Sql(_sNombre, true, false) + ");");
				_iId = Database.LastId(con);
			} 
			else con.createStatement().executeUpdate("UPDATE trabajador SET nombre = " + Database.String2Sql(_sNombre, true, false) + " WHERE id = " + _iId + ";");
		}
		finally {
			if (con != null) con.close();
		}
	}
	
	/**
	 * Elimina un registro de la base de datos.
	 * @throws IOException
	 * @throws SQLException
	 */
	public void Delete() throws IOException, SQLException { 
		if(_iId == null || _dtDeletedAt != null) throw new IllegalStateException();
		
		Connection con = null;
		
		try {
			con = Database.Connection();
			con.createStatement().executeUpdate("DELETE FROM trabajador WHERE id = " + this.GetId() + ";");			
			_dtDeletedAt = new Date();
		}
		finally {
			if (con != null) con.close();
		}
	}
	
	private static String Where(String sNombre) {
        if(sNombre != null) return " WHERE nombre LIKE " + Database.String2Sql(sNombre, true, true);
        return new String("");
    }

	public static List<Trabajador> Search(String sNombre) throws IOException, SQLException {
        Connection con = null;
        ResultSet rs = null;

        try {
            con = Database.Connection();
            rs = con.createStatement().executeQuery("SELECT id, nombre FROM trabajador" + Where(sNombre) + " ORDER BY nombre ASC;");

            List<Trabajador> aResultado = new ArrayList<Trabajador>();
            while(rs.next()) aResultado.add(new Trabajador(rs.getInt("id"), rs.getString("nombre")));            
            
            return aResultado;
        }
        finally {
            if (rs != null) rs.close();
            if (con != null) con.close();
        }
    }
}