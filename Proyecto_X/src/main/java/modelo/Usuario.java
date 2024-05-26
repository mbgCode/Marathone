package modelo;

import java.sql.SQLException;

import dao.DaoUsuario;

/**
 * Clase Usuario Esta clase da herencia de sus atributos a administrador y
 * miembro.
 * 
 * @author Marcos Barberá Gómez
 * @version 1.0 24/05/2024
 */
public class Usuario {

	// Atributos.
	protected String nombre;
	protected String apellidos;
	protected String email;
	protected String poblacion;
	protected int permiso;
	protected String foto;
	protected String pass;

	/**
	 * Contructor vacío.
	 */
	public Usuario() {

	}

	/**
	 * Constructor completo de 7 parámetros utilizado en la clase miembro y la clase
	 * administrador para poder listar insertar y el inicion de sesión.
	 * 
	 * @param nombre    Nombre del Usuario (Miembro o administrador).
	 * @param apellidos Apellidos del Usuario (Miembro o administrador).
	 * @param email     Mail del Usuario (Miembro o administrador).
	 * @param poblacion Población del Usuario (Miembro o administrador).
	 * @param permiso   Tipod de permiso del Usuario (Miembro o administrador).
	 * @param foto      Foto del Usuario (Miembro o administrador).
	 * @param pass      Contraseña del Usuario (Miembro o administrador).
	 */
	public Usuario(String nombre, String apellidos, String email, String poblacion, int permiso, String foto,
			String pass) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.poblacion = poblacion;
		this.permiso = permiso;
		this.foto = foto;
		this.pass = pass;
	}

	/**
	 * Construcot sin pass utilizado con la clase Administrador para el listrado de los datos.
	 * 
	 * @param nombre    Nombre del Usuario (Miembro o administrador).
	 * @param apellidos Apellidos del Usuario (Miembro o administrador).
	 * @param email     Mail del Usuario (Miembro o administrador).
	 * @param poblacion Población del Usuario (Miembro o administrador).
	 * @param permiso   Tipod de permiso del Usuario (Miembro o administrador).
	 * @param foto      Foto del Usuario (Miembro o administrador).
	 */
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

}
