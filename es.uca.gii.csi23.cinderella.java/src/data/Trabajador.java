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
		if(sNombre == null || sNombre.equals("")) throw new IllegalArgumentException("El nombre no puede ser nulo ni una cadena vacia.");
		_sNombre = sNombre;
	}
	
	private TipoTrabajador _tipoTrabajador;
	public TipoTrabajador GetTipoTrabajador() { return _tipoTrabajador; }
	public void SetTipoTrabajador(TipoTrabajador tipoTrabajador) { 
		if(tipoTrabajador == null) throw new IllegalArgumentException("El tipo de trabajador no puede ser nulo.");
		_tipoTrabajador = tipoTrabajador; 
	}
	
	private Date _dtDeletedAt = null;
	public Date GetDeletedAt() { return _dtDeletedAt; }
	
	public Trabajador(String sNombre, TipoTrabajador tipoTrabajador) { this(null, sNombre, tipoTrabajador); }
	
	private Trabajador(Integer iId, String sNombre, TipoTrabajador tipoTrabajador) {
		SetNombre(sNombre);
		SetTipoTrabajador(tipoTrabajador);
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
			rs = con.createStatement().executeQuery("SELECT id, nombre, tipotrabajador_id FROM Trabajador WHERE id = " + iId + ";");
			
			if(rs.next()) return new Trabajador(rs.getInt("id"), rs.getString("nombre"), TipoTrabajador.Get(rs.getInt("tipotrabajador_id")));
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
				con.createStatement().executeUpdate("INSERT INTO trabajador (nombre, tipotrabajador_id) VALUES (" + Database.String2Sql(_sNombre, true, false) + ", " + _tipoTrabajador.GetId() + ");");
				_iId = Database.LastId(con);
			} 
			else con.createStatement().executeUpdate("UPDATE trabajador SET nombre = " + Database.String2Sql(_sNombre, true, false) + ", " + "tipotrabajador_id = " + _tipoTrabajador.GetId() + " WHERE id = " + _iId + ";");
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
		if(_iId == null || _dtDeletedAt != null) throw new IllegalStateException("El trabajador no existe o ya ha sido eliminado");
		
		Connection con = null;
		
		try {
			con = Database.Connection();
			con.createStatement().executeUpdate("DELETE FROM trabajador WHERE id = " + _iId + ";");			
			_dtDeletedAt = new Date();
		}
		finally {
			if (con != null) con.close();
		}
	}
	
	private static String Where(String sNombre, String sTipoTrabajador) {
        	List<String> aCondiciones = new ArrayList<String>();
        
		if(sNombre != null) aCondiciones.add("trabajador.nombre LIKE " + Database.String2Sql(sNombre, true, true));
		if(sTipoTrabajador != null) aCondiciones.add("tipotrabajador.nombre LIKE " + Database.String2Sql(sTipoTrabajador, true, true));
		if(!aCondiciones.isEmpty()) return " WHERE " + String.join(" AND ", aCondiciones);
		
        	return "";
    	}

	public static List<Trabajador> Search(String sNombre, String sTipoTrabajador) throws IOException, SQLException {
        Connection con = null;
        ResultSet rs = null;

        try {
            con = Database.Connection();
            rs = con.createStatement().executeQuery("SELECT trabajador.id, trabajador.nombre, tipotrabajador.id FROM trabajador "
            		+ "JOIN tipotrabajador ON trabajador.TipoTrabajador_id = tipotrabajador.id " + Where(sNombre, sTipoTrabajador) 
            		+ " ORDER BY trabajador.nombre ASC;");

            List<Trabajador> aResultado = new ArrayList<Trabajador>();
            while(rs.next()) aResultado.add(new Trabajador(rs.getInt("trabajador.id"), rs.getString("trabajador.nombre"), TipoTrabajador.Get(rs.getInt("tipotrabajador.id"))));            
            
            return aResultado;
        }
        finally {
            if (rs != null) rs.close();
            if (con != null) con.close();
        }
    }
}
