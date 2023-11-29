package data;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import util.Database;

public class TipoTrabajador {
	
	private Integer _iId;
	public Integer GetId() { return _iId; }
	
	private String _sNombre;
	public String GetNombre() { return _sNombre; }
	public void SetNombre(String sNombre) {
		if(sNombre == null || sNombre.equals("")) throw new IllegalArgumentException("El nombre no puede ser nulo ni una cadena vacia.");
		_sNombre = sNombre;
	}
	
	private Date _dtDeletedAt = null;
	public Date GetDeletedAt() { return _dtDeletedAt; }
	
	public TipoTrabajador(String sNombre) { this(null, sNombre); }
	
	private TipoTrabajador(Integer iId, String sNombre) {
		SetNombre(sNombre);
		_iId = iId;
	}
	
	/**
	 * Devuelve una cadena con formato SuperClase.Clase@CodigoHash:Id:Nombre.
	 */
	public String toString() { return super.toString() + ":" + _iId + ":" + _sNombre; }
	
	public static TipoTrabajador Get(int iId) throws IOException, SQLException {
		Connection con = null;
		ResultSet rs = null;
		
		try {
			con = Database.Connection();
			rs = con.createStatement().executeQuery("SELECT id, nombre FROM tipotrabajador WHERE id = " + iId + ";");
			
			if(rs.next()) return new TipoTrabajador(rs.getInt("id"), rs.getString("nombre"));
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
				con.createStatement().executeUpdate("INSERT INTO tipotrabajador (nombre) VALUES (" + Database.String2Sql(_sNombre, true, false) + ");");
				_iId = Database.LastId(con);
			} 
			else con.createStatement().executeUpdate("UPDATE tipotrabajador SET nombre = " + Database.String2Sql(_sNombre, true, false) + " WHERE id = " + _iId + ";");
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
		if(_iId == null || _dtDeletedAt != null) throw new IllegalStateException("El tipo de trabajador no existe o ya ha sido eliminado");
		
		Connection con = null;
		
		try {
			con = Database.Connection();
			con.createStatement().executeUpdate("DELETE FROM tipotrabajador WHERE id = " + _iId + ";");			
			_dtDeletedAt = new Date();
		}
		finally {
			if (con != null) con.close();
		}
	}
	
	private static String Where(String sNombre) {
        if(sNombre != null) return " WHERE nombre LIKE " + Database.String2Sql(sNombre, true, true);
        return "";
    }

	public static List<TipoTrabajador> Search(String sNombre) throws IOException, SQLException {
        Connection con = null;
        ResultSet rs = null;

        try {
            con = Database.Connection();
            rs = con.createStatement().executeQuery("SELECT id, nombre FROM tipotrabajador" + Where(sNombre) + " ORDER BY nombre ASC;");

            List<TipoTrabajador> aResultado = new ArrayList<TipoTrabajador>();
            while(rs.next()) aResultado.add(new TipoTrabajador(rs.getInt("id"), rs.getString("nombre")));            
            
            return aResultado;
        }
        finally {
            if (rs != null) rs.close();
            if (con != null) con.close();
        }
    }
}