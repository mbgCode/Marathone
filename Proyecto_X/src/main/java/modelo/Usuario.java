package modelo;

import java.sql.SQLException;

import dao.DaoUsuario;



//Va a dar herencia a administrador y a miebro.
public class Usuario {
	
	
	
//aributos con protected para dar herencia.
	protected String nombre;
	protected String apellidos;
	protected String email;
	protected String poblacion;
	protected int permiso;
	protected String foto;
	protected String pass;
	
	
//Constructor vacío.	
	public Usuario() {
		
	}



//Construcrtor con todo	
	public Usuario(String nombre, String apellidos, String email, String poblacion, int permiso, String foto, String pass) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.poblacion = poblacion;
		this.permiso = permiso;
		this.foto = foto;
		this.pass = pass;
	}


//Constructor sin pass
	public Usuario(String nombre, String apellidos, String email, String poblacion, int permiso, String foto) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.poblacion = poblacion;
		this.permiso = permiso;
		this.foto = foto;
	}



//Sin foto, permiso y pass
	public Usuario(String nombre, String apellidos, String email, String poblacion) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.poblacion = poblacion;
	}
  
	
	

//Coontructor solo con foto para la zona login
	public Usuario(String foto) {
		super();
		this.foto = foto;
}



//Getter and Setter.
	
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



	public String getPass() {
		return pass;
	}



	public void setPass(String pass) {
		this.pass = pass;
	}
	
	
	
	
@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", apellidos=" + apellidos + ", email=" + email + ", poblacion="
				+ poblacion + ", permiso=" + permiso + ", foto=" + foto + ", pass=" + pass + "]";
	}



	//Metdos
	public void insertarUsuario() throws SQLException {
			
		DaoUsuario dao = new DaoUsuario();
		dao.insertar(this);
			
			
		}	
}
