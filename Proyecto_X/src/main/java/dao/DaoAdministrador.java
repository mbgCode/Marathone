package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.google.gson.Gson;

import modelo.Administrador;
import modelo.Miembro;

public class DaoAdministrador {

	
	public static Connection con = null;
	
	

	public DaoAdministrador() throws SQLException {
		
		//no es nece sario utilizar el this ya que la delclaracion "con" es static.
		con = BDconexion.getmiconexion();
			
		
	}
	
	
//Insertamos los datos en BD Administrador	
	public void insertar(Administrador a) throws SQLException {
		 
		
		String sql = "INSERT INTO administrador (nombre, apellidos, email, "
						+ "poblacion, permiso, foto, idadministrador) VALUES (?,?,?,?,?,?,?)";
		
		PreparedStatement ps = con.prepareStatement(sql);
		
			ps.setString(1, a.getNombre());
			ps.setString(2, a.getApellidos());
			ps.setString(3, a.getEmail());
			ps.setString(4, a.getPoblacion());
			ps.setInt(5, a.getPermiso());
			ps.setString(6, a.getFoto());
			ps.setInt(7, a.getIdaministrador());
			int filas = ps.executeUpdate();
			
			ps.close();	
	}
	
	

//Una vez damos a editar en listar un admin este metodo le va a pedir los datos de ese id para pintarlos en el cliente.
//Petición para visualizar los datos pora luego actualiza.
	public Administrador modificar (int id) throws SQLException {
		String sql = "SELECT * FROM administrador WHERE idadministrador=? ";
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setInt (1,id);
		
		ResultSet rs = ps.executeQuery();
		
		rs.next();
		Administrador a = new Administrador (rs.getString("nombre"), rs.getString("apellidos"), rs.getString("email"),
				rs.getString("poblacion"), rs.getInt("permiso"), rs.getString("foto"),rs.getInt("idadministrador"));
		
		return a;
	}


	
//Update los datos(Para modificar los datos que queramos)
	public void actualizar (Administrador a) throws SQLException {
		String sql = "UPDATE administrador SET nombre=?,apellidos=?,email=?,poblacion=?,permiso=?,foto=?"
				+ "WHERE idadministrador=?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setString(1, a.getNombre());
		ps.setString(2, a.getApellidos());
		ps.setString(3, a.getEmail());
		ps.setString(4, a.getPoblacion());
		ps.setInt(5, a.getPermiso());
		ps.setString(6, a.getFoto());
		ps.setInt(7, a.getIdaministrador());
		int filas = ps.executeUpdate();
		
		ps.close();	
	}

	
	
//Peticion para listar Adminiistrador
		public ArrayList <Administrador>listar() throws SQLException{
			
			String sql = "SELECT * FROM administrador";
			
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet result = ps.executeQuery();
			
			//SI el array es igual a null lo llenamos.
			ArrayList<Administrador>ls=null;
			while((result.next())) {
				if (ls == null) {
					ls = new ArrayList <Administrador>(); 
				}
				ls.add(new Administrador(result.getString(1), result.getString(2), result.getString(3)
						, result.getString(4), result.getInt(5), result.getString(6), result.getInt(7)));
				
			
			}
			
			return ls;
		}	
	
	
		
//Funcion json para listar los datos en cliente.
		public String ListarJonson() throws SQLException {
			//Queremos que txtJson se llene con todos los datos que contiene ArrayList<Usuario>
			String txtJson = "";
			
			Gson gson = new Gson ();
			
			txtJson = gson.toJson(this.listar());//Llamamos a la funcion listar con los datos el ArrayList<usuario>
			
			return txtJson;
		} 	
		
		
		
//borrar		
		public void borrarBD (int id) throws SQLException {
			
			String sql = "DELETE FROM administrador WHERE idadministrador = "+id;
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.executeUpdate(sql);
		
			ps.close();
			
		}
	
}