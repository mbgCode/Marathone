package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;

import modelo.Administrador;
import modelo.Deporte;

public class DaoDeporte {
	
	
	public static Connection con = null;
	
	
	public DaoDeporte() throws SQLException {
		
		con = BDconexion.getmiconexion();
			
	}
	
	//Insertar deporte
	public void insertarDep (Deporte d) throws SQLException {
		
		String sql = "INSERT INTO deporte (nombre,descripcion,telefono,direccion,foto,categoria) VALUES (?,?,?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setString(1, d.getNombre());
		ps.setString(2, d.getDescripcion());
		ps.setInt(3,d.getTelefono());
		ps.setString(4, d.getDireccion());
		ps.setString(5, d.getFoto());
		ps.setString(6,d.getCategoria());
		
		int file = ps.executeUpdate();
		ps.close();
	}
	
	
	
	
	//Llamada deporte por Id para Update
	public Deporte listarPorId(int id) throws SQLException{
		
		String query = "SELECT * FROM deporte WHERE iddeporte = ?";
	
		PreparedStatement ps = con.prepareStatement(query);
		
		ps.setInt(1, id);
		
		ResultSet rs = ps.executeQuery();
		
		rs.next();
		
		Deporte d = new Deporte(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4),
				rs.getString(5), rs.getString(6), rs.getString(7));
		System.out.println("en dao "+rs.getString(7));
			ps.close();
	
	return d;	
		
	}
	
	
	
	
	//Update del id concreto.
	public void update (Deporte d) throws SQLException {

		String sql = "UPDATE deporte SET nombre=?,descripcion=?,telefono=?,direccion=?,foto=?,categoria=?"
				+ "WHERE iddeporte=?";
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setString(1, d.getNombre());
		ps.setString(2, d.getDescripcion());
		ps.setInt(3,d.getTelefono());
		ps.setString(4, d.getDireccion());
		ps.setString(5, d.getFoto());
		ps.setString(6,d.getCategoria());
		ps.setInt(7, d.getId());
		
		int file = ps.executeUpdate();
	
		ps.close();
		
	}
	
	
	
	//Peticion para listar todo
	public ArrayList <Deporte>listar() throws SQLException{
		
		String query = "SELECT * FROM deporte ORDER BY iddeporte DESC";
	
		PreparedStatement ps = con.prepareStatement(query);
		
		ResultSet rs = ps.executeQuery();
		
		ArrayList<Deporte>ls=null;
		
		while((rs.next())) {
			if (ls == null) {
				ls = new ArrayList <Deporte>(); 
			}	
			ls.add(new Deporte(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4),
					rs.getString(5), rs.getString(6), rs.getString(7)));
			
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
	
	
	
}
