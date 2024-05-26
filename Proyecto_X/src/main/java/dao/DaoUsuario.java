package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;

import modelo.Usuario;

public class DaoUsuario {

	
	public static Connection con = null; 
	
	
	public DaoUsuario() throws SQLException {
		
		
		this.con = BDconexion.getmiconexion();
		
	}
	
	
//Insertamos en la BD los diferentes datos.
	public void insertar(Usuario u) throws SQLException {
		
		
		String query = "INSERT INTO usuario (nombre,apellidos,email,poblacion,permiso,foto) VALUES (?,?,?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(query);
		
		ps.setString(1, u.getNombre());
		ps.setString(2, u.getApellidos());
		ps.setString(3, u.getEmail());
		ps.setString(4, u.getPoblacion());
		ps.setInt(5, u.getPermiso());
		ps.setString(6, u.getFoto());
		
		int filas = ps.executeUpdate();
		
		ps.close();
	}

}
