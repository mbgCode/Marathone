package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import modelo.SuperU;

public class DaoSuperU {
	
	
	//Instanciamos la conexion.
	public static Connection con = null;
	
	
	
	public DaoSuperU() throws SQLException {
		
		//Establecemos conexion con la BD
		this.con = BDconexion.getmiconexion();	
	}
	
	
	
	//Insertamos en la BD los datos recibidos de la clase SuperU
	public void insertar (SuperU su) throws SQLException {
		
		//Creamos la consulta.
		String query = "INSERT INTO super (nombre, apellidos, email, permiso, foto, pass) VALUES (?,?,?,?,?,?)";
		
		//Creamos el prepared para lanzar la consulta.
		PreparedStatement ps = con.prepareStatement(query);
		
		//Lanzamos con set y el tipo cada uno de los datos con get.
		ps.setString(1, su.getNombre());
		ps.setString(2, su.getApellidos());
		ps.setString(3, su.getEmail());
		ps.setInt(4, su.getPermiso());
		ps.setString(5,su.getFoto());
		ps.setString(6, su.getPass());
		
		int filas = ps.executeUpdate();
		
		//cerramos el preparedS.
		ps.close();
	}
	
	
	
}
