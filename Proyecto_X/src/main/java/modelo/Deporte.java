package modelo;

import java.sql.SQLException;

import dao.DaoDeporte;

public class Deporte {

	
//Atributos de la case Deporte	
	private int id;
	private String nombre;
	private String descripcion;
	private int telefono;
	private String direccion;
	private String foto;
	
	
	
//Constructor vacío.	
	public Deporte() {
		
	}
	
	
	
// Constructor con todos los atributos.	
	public Deporte(int id, String nombre, String descripcion, int telefono, String domicilio, String foto) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.telefono = telefono;
		this.direccion = domicilio;
		this.foto = foto;
	}


//Constructor sin ID.
	public Deporte(String nombre, String descripcion, int telefono, String domicilio, String foto) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.telefono = telefono;
		this.direccion = domicilio;
		this.foto = foto;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public String getDescripcion() {
		return descripcion;
	}



	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}



	public int getTelefono() {
		return telefono;
	}



	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}



	public String getDomicilio() {
		return direccion;
	}



	public void setDomicilio(String domicilio) {
		this.direccion = domicilio;
	}



	public String getFoto() {
		return foto;
	}



	public void setFoto(String foto) {
		this.foto = foto;
	}



//Metodos-------------------------------------------------

	public void insertarDep () throws SQLException {
		DaoDeporte dao = new DaoDeporte();
		dao.insertarDep(this);
	}
	

	
//toString	
	@Override
	public String toString() {
		return "Deporte [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", telefono=" + telefono
				+ ", domicilio=" + direccion + ", foto=" + foto + "]";
	}
	
	

	
}
