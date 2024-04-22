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
		
		
		String query = "INSERT INTO usuario (Nombre) VALUES (?)";
		PreparedStatement ps = con.prepareStatement(query);
		
		ps.setString(1, u.getNombre());
		
		int filas = ps.executeUpdate();
		
		ps.close();
	}
	
	
	
	//Peticion para listar Usuario
	public ArrayList <Usuario>listar() throws SQLException{
		
		String sql = "SELECT * FROM usuario";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet result = ps.executeQuery();
		
		//SI el array es igual a null lo llenamos.
		ArrayList<Usuario>usuario=null;
		while((result.next())) {
			if (usuario == null) {
				usuario = new ArrayList <Usuario>(); 
			}
			usuario.add(new Usuario(result.getString("Nombre")));
		}
		
		return usuario;
	}
	
	
	
	//Funcion json 
	public String ListarJonson() throws SQLException {
		//Queremos que txtJson se llene con todos los datos que contiene ArrayList<Usuario>
		String txtJson = "";
		
		Gson gson = new Gson ();
		
		txtJson = gson.toJson(this.listar());//Llamamos a la funcion listar con los datos el ArrayList<usuario>
		
		return txtJson;
	}
	
	
}
