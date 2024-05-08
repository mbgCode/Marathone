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
	private String categoria;
	
	
//Constructor vacío.	
	public Deporte() {
		
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


// Constructor con todos los atributos.	
	public Deporte(int id, String nombre, String descripcion, int telefono, String direccion, String foto,
			String categoria) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.telefono = telefono;
		this.direccion = direccion;
		this.foto = foto;
		this.categoria = categoria;
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



	public String getDireccion() {
		return direccion;
	}



	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}



	public String getFoto() {
		return foto;
	}



	public void setFoto(String foto) {
		this.foto = foto;
	}



	public String getCategoria() {
		return categoria;
	}



	public void setCategoria(String categoria) {
		this.categoria = categoria;
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
				+ ", direccion=" + direccion + ", foto=" + foto + "]";
	}
	
	

	
}
