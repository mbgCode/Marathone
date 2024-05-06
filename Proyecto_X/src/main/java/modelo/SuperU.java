package modelo;

import java.sql.SQLException;

import dao.DaoSuperU;

public class SuperU {

	
	//Generamos los atributos.
	private String nombre;
	private String apellidos;
	private String email;
	private int permiso;
	private String foto;
	private String pass;
	private int id;
	
	
	//Constructor vacío.
	public SuperU () {
		
	}


	//Construcctor completo.
	public SuperU(String nombre, String apellidos, String email, int permiso, String foto,
			String pass, int id) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.permiso = permiso;
		this.foto = foto;
		this.pass = pass;
		this.id = id;
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


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	//ToString
	@Override
	public String toString() {
		return "SuperU [nombre=" + nombre + ", apellidos=" + apellidos + ", email=" + email + ", permiso=" + permiso
				+ ", foto=" + foto + ", pass=" + pass + ", id=" + id + "]";
	}




//Metodos

	
//Insertar (Del servlet al Dao)
	public void insertarSuperU () throws SQLException {
		
		DaoSuperU daoSU = new DaoSuperU();
		daoSU.insertar(this);
		
		
	}
	
}
