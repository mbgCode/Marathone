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
	
	
	
	//login.	
	public Administrador logeando (Administrador a, String pass) throws SQLException {		
		String query = "SELECT * FROM administrador WHERE email=? AND pass=? ";
		PreparedStatement ps = con.prepareStatement(query);
		
		ps.setString (1,a.getEmail());
		ps.setString(2, pass);		
		
		ResultSet rs = ps.executeQuery();
		Administrador a1 = null;//Instanciamos miembro con un null.
				
		if (rs.next()) {// si no esta vacía la query (contiene datos) los inyectamos.
				a1 = new Administrador(rs.getString("nombre"), rs.getString("apellidos"), rs.getString("email"), rs.getString("poblacion")
						, rs.getInt("permiso"), rs.getString("foto"), rs.getString("pass"), rs.getInt("idadministrador"));
		}//si esta vacía la dejamos null para que lo devuelva... de no ser así nos daría una SQLexception.	
				
		return a1;
		}	
	
				
	
			
	//Insertamos los datos en tabla administrador.	
	public void insertar(Administrador a) throws SQLException {
		String query = "INSERT INTO administrador (nombre, apellidos, email, "
						+ "poblacion, permiso, foto, pass, idadministrador) VALUES (?,?,?,?,?,?,?,?)";
		
		PreparedStatement ps = con.prepareStatement(query);
			System.out.println("este es el permiso"+ a.getPermiso());
			ps.setString(1, a.getNombre());
			ps.setString(2, a.getApellidos());
			ps.setString(3, a.getEmail());
			ps.setString(4, a.getPoblacion());
			ps.setInt(5, a.getPermiso());
			ps.setString(6, a.getFoto());
			ps.setString(7, a.getPass());
			ps.setInt(8, a.getIdaministrador());
			int filas = ps.executeUpdate();
			
			ps.close();	
	}
	
	
	

	//Una vez damos a editar en listar cualquier admin, este metodo le va a pedir los datos de ese id para pintarlos en el cliente.
	//Petición para visualizar los datos para luego actualiza.
	public Administrador modificar (int id) throws SQLException {
		String query = "SELECT * FROM administrador WHERE idadministrador=? ";
		PreparedStatement ps = con.prepareStatement(query);
		
		ps.setInt (1,id);	
		
		ResultSet rs = ps.executeQuery();
		
		rs.next();
		Administrador a = new Administrador (rs.getString("nombre"), rs.getString("apellidos"), rs.getString("email"),
				rs.getString("poblacion"), rs.getInt("permiso"), rs.getString("foto"),rs.getString("pass"), rs.getInt("idadministrador"));
		
		return a;
	}


	
	
//Update los datos(Para modificar los datos que queramos).
	public void actualizar (Administrador a) throws SQLException {
		String query = "UPDATE administrador SET nombre=?,apellidos=?,email=?,poblacion=?,permiso=?,foto=?"
				+ "WHERE idadministrador=?";
		PreparedStatement ps = con.prepareStatement(query);
		
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


	
	
//Peticion de la foto por id para la zona login.
		public Administrador foto(int id) throws SQLException {
		    String query = "SELECT foto FROM administrador WHERE idADMINISTRADOR = ?;";
		    Administrador a = null;
		    PreparedStatement ps = con.prepareStatement(query);
		    
	        ps.setInt(1, id);
	        
	        ResultSet rs = ps.executeQuery();
	       
	            rs.next();
	                a = new Administrador(
	                    rs.getString(1));
	    return a;
		}	
	
	
		
		
//Peticion para listar Adminiistrador.
		public ArrayList <Administrador>listar() throws SQLException{
			
			String query = "SELECT * FROM administrador";
			
			PreparedStatement ps = con.prepareStatement(query);
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
		
		
		
		
//Borrar.		
		public void borrarBD (int id) throws SQLException {
			String query = "DELETE FROM administrador WHERE idadministrador = "+id;
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.executeUpdate(query);
		
			ps.close();
			
		}
	
}