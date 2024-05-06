package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;

import modelo.Administrador;
import modelo.Miembro;
import modelo.Usuario;

public class DaoMiembro{

	
	public static Connection con = null; 
	
	
	public DaoMiembro() throws SQLException {
		
		
		this.con = BDconexion.getmiconexion();
		
	}
	
	
//Insertamos en la BD los diferentes datos.
	public void insertar(Miembro m) throws SQLException {
		
		
		String query = "INSERT INTO miembro(nombre,apellidos,email,poblacion,permiso,foto,edad,idmiembro,pass) VALUES (?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(query);
		
		ps.setString(1, m.getNombre());
		ps.setString(2, m.getApellidos());
		ps.setString(3, m.getEmail());
		ps.setString(4, m.getPoblacion());
		ps.setInt(5, m.getPermiso());
		ps.setString(6, m.getFoto());
		ps.setInt(7,m.getEdad());
		ps.setInt(8,m.getId());
		ps.setString(9,m.getPass());
		int filas = ps.executeUpdate();
		
		ps.close();
	}

	
	
	
//Peticion para listar Miembro (todos los miembros)
	public ArrayList <Miembro>listar() throws SQLException{
		
		String sql = "SELECT * FROM miembro";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet result = ps.executeQuery();
		System.out.println("llega al dao miembro!!");
		//SI el array es igual a null lo llenamos.
		ArrayList<Miembro>ls=null;
		while((result.next())) {
			if (ls == null) {
				ls = new ArrayList <Miembro>(); 
			}
			
			ls.add(new Miembro(result.getString(1), result.getString(2), result.getString(3), result.getString(4)
					, result.getInt(5), result.getString(6), result.getInt(7), result.getInt(8),result.getString(9)));
			
		}
		
		return ls;
	}
	
	
	
//Peticion para listar Miembro por tipo de permiso
	public ArrayList <Miembro>listarTipo(int tipo) throws SQLException{
		
		String sql = "SELECT * FROM miembro WHERE permiso=?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, tipo);
		ResultSet result = ps.executeQuery();
		
		//SI el array es igual a null lo llenamos.
		ArrayList<Miembro>ls=null;
		while((result.next())) {
			if (ls == null) {
				ls = new ArrayList <Miembro>(); 
			}
			ls.add(new Miembro(result.getString(1), result.getString(2), result.getString(3), result.getString(4)
					, result.getInt(5), result.getString(6), result.getInt(7), result.getInt(8), result.getString(9)));
		}
		
		return ls;
	}	
	
	
	
	
//login	
		public Miembro logeando (Miembro m, String pass) throws SQLException {
			
			String sql = "SELECT * FROM miembro WHERE email=? AND pass=? ";
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setString (1,m.getEmail());
			ps.setString(2, pass);
			
			
			ResultSet rs = ps.executeQuery();
			
			Miembro m1 = null;//Instanciamos miembro con un null
			
			
			if (rs.next()) {// si no esta vacía la query (contiene datos) los inyectamos.
				m1 = new Miembro (rs.getString("nombre"), rs.getString("apellidos"), rs.getString("email"),
						rs.getString("poblacion"), rs.getInt("permiso"), rs.getString("foto"),rs.getInt("edad"),rs.getInt("idmiembro"),rs.getString("pass"));
			}//si esta vacía la dejamos null para que lo devuelva... de no ser así nos daría una SQLexception.	
			
			return m1;
		}	
	
	
	
	
	
	
//Funcion json listar todos los miembros
	public String listarJonson() throws SQLException {
		//Queremos que txtJson se llene con todos los datos que contiene ArrayList<Usuario>
		String txtJson = "";
		
		Gson gson = new Gson ();
		
		txtJson = gson.toJson(this.listar());//Llamamos a la funcion listar con los datos el ArrayList<usuario>
		
		return txtJson;
	} 
	
	
	
//Funcion json listar por filtrado tipo de permiso.
		public String listarJonsonTipo(int tipo) throws SQLException {
			//Queremos que txtJson se llene con todos los datos que contiene ArrayList<Usuario>
			String txtJson = "";
			
			Gson gson = new Gson ();
			
			txtJson = gson.toJson(this.listarTipo(tipo));//Llamamos a la funcion listar con los datos el ArrayList<usuario>
			
			return txtJson;
		} 
}