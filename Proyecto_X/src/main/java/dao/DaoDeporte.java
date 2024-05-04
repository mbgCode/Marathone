package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import modelo.Deporte;

public class DaoDeporte {
	
	
	public static Connection con = null;
	
	
	public DaoDeporte() throws SQLException {
		
		con = BDconexion.getmiconexion();
			
	}
	
	
	public void insertarDep (Deporte d) throws SQLException {
		
		String sql = "INSERT INTO deporte (nombre,descripcion,telefono,dirección,foto)";
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setString(1, d.getNombre());
		ps.setString(2, d.getDescripcion());
		ps.setInt(3,d.getTelefono());
		ps.setString(4, d.getDomicilio());
		ps.setString(5, d.getFoto());
		
	}
	
	
	

}
