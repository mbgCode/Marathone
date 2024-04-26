package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;

import modelo.Miembro;
import modelo.Usuario;

public class DaoMiembro{

	
	public static Connection con = null; 
	
	
	public DaoMiembro() throws SQLException {
		
		
		this.con = BDconexion.getmiconexion();
		
	}
	
	
//Insertamos en la BD los diferentes datos.
	public void insertar(Miembro m) throws SQLException {
		
		
		String query = "INSERT INTO miembro(nombre,apellidos,email,poblacion,permiso,foto,edad, idmiembro) VALUES (?,?,?,?,?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(query);
		
		ps.setString(1, m.getNombre());
		ps.setString(2, m.getApellidos());
		ps.setString(3, m.getEmail());
		ps.setString(4, m.getPoblacion());
		ps.setInt(5, m.getPermiso());
		ps.setString(6, m.getFoto());
		ps.setInt(7,m.getEdad());
		
		
		int filas = ps.executeUpdate();
		
		ps.close();
	}

	
	
	
//Peticion para listar Miembro
	public ArrayList <Miembro>listar() throws SQLException{
		
		String sql = "SELECT * FROM miembro";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet result = ps.executeQuery();
		
		//SI el array es igual a null lo llenamos.
		ArrayList<Miembro>ls=null;
		while((result.next())) {
			if (ls == null) {
				ls = new ArrayList <Miembro>(); 
			}
			ls.add(new Miembro(result.getString(1), result.getString(2), result.getString(3), result.getString(4)
					, result.getInt(5), result.getString(6), result.getInt(7), result.getInt(8)));
		}
		
		return ls;
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