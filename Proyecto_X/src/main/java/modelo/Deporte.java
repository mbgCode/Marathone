package modelo;

import java.sql.SQLException;

import com.google.gson.Gson;

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
	
	

//Constructor sin ID y categoria
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


	
//Construcot sin id
	public Deporte(String nombre, String descripcion, int telefono, String direccion, String foto, String categoria) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.telefono = telefono;
		this.direccion = direccion;
		this.foto = foto;
		this.categoria = categoria;
	}



		
	
	
	
//Metodos-------------------------------------------------

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



	@Override
	public String toString() {
		return "Deporte [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", telefono=" + telefono
				+ ", direccion=" + direccion + ", foto=" + foto + ", categoria=" + categoria + "]";
	}



	//Insertar deporte
	public void insertarDep () throws SQLException {
		DaoDeporte dao = new DaoDeporte();
		dao.insertarDep(this);
	}
	
	
	
	//Update del id concreto.
	public void update() throws SQLException {
		DaoDeporte dao = new DaoDeporte();
		dao.update(this);
	}
	
	
	
	//Recibimos el id del Form y llamamos al dao para listar por id
	public void modId(int id) throws SQLException {
		
		
		DaoDeporte dao = new DaoDeporte();
		Deporte d = dao.listarPorId(id);
		System.out.println("esto es puro "+d);
		this.setNombre(d.getNombre());
		this.setDescripcion(d.getDescripcion());
		this.setTelefono(d.getTelefono());
		this.setDireccion(d.getDireccion());
		this.setFoto(d.getFoto());
		this.setCategoria(d.getCategoria());
		this.setId(d.getId());
		
		
	}

	
	//Jnson Por listar por id
	public String dameJson() {
		
		String txtJson = "";
		
		Gson gson = new Gson ();
		
		txtJson = gson.toJson(this);//Llamamos a la funcion listar con los datos de la misma clase."this"
		
		return txtJson;
	}
	
	
	//Borrar datos por id
	public void borrar (int id) throws SQLException {
		DaoDeporte dao = new DaoDeporte();
		dao.borrar(id);
	}
	
}
