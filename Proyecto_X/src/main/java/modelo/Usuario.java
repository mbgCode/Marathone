package modelo;

import java.sql.SQLException;

import dao.DaoUsuario;

public class Usuario {
	
	

	private String nombre;
	
	
	
	
	public Usuario() {
		
	}



	public Usuario(String nombre) {
		super();
		this.nombre = nombre;
	}




	public String getNombre() {
		return nombre;
	}




	public void setNombre(String nombre) {
		this.nombre = nombre;
	}




	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + "]";
	}
	
	
	public void insertarUsuario() throws SQLException {
		
		DaoUsuario dao = new DaoUsuario();
		dao.insertar(this);
		
		
	}
	
	
	
}
