package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import modelo.Usuario;

public class DaoUsuario {

	
	public static Connection con = null; 
	
	
	public DaoUsuario() throws SQLException {
		
		
		this.con = BDconexion.getmiconexion();
		
	}
	
	
	public void insertar(Usuario u) throws SQLException {
		
		
		String query = "INSERT INTO usuario (Nombre) VALUES (?)";
		PreparedStatement ps = con.prepareStatement(query);
		
		ps.setString(1, u.getNombre());
		
		int filas = ps.executeUpdate();
		
		ps.close();
	}
	
}
