package modelo;

import java.sql.SQLException;

import dao.DaoUsuario;

public class Usuario {
	
	

	private String nombre;
	private String apellidos;
	private String email;
	private String poblacion;
	private int permiso;
	private String foto;
	
	
	
	public Usuario() {
		
	}


	
	
	public Usuario(String nombre, String apellidos, String email, String poblacion, int permiso, String foto) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.poblacion = poblacion;
		this.permiso = permiso;
		this.foto = foto;
	}



//Sin foto y permiso.
	public Usuario(String nombre, String apellidos, String email, String poblacion) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.poblacion = poblacion;
	}




	public String getNombre() {
		return nombre;
	}




	public void setNombre(String nombre) {
		this.nombre = nombre;
	}




	public String getApellidos() {
		return apellidos;
	}




	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}




	public String getEmail() {
		return email;
	}




	public void setEmail(String email) {
		this.email = email;
	}




	public String getPoblacion() {
		return poblacion;
	}




	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}




	public int getPermiso() {
		return permiso;
	}




	public void setPermiso(int permiso) {
		this.permiso = permiso;
	}




	public String getFoto() {
		return foto;
	}




	public void setFoto(String foto) {
		this.foto = foto;
	}

	
	
	



	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", apellidos=" + apellidos + ", email=" + email + ", poblacion="
				+ poblacion + ", permiso=" + permiso + ", foto=" + foto + "]";
	}




	public void insertarUsuario() throws SQLException {
		
		DaoUsuario dao = new DaoUsuario();
		dao.insertar(this);
		
		
	}
	
	
	
}
