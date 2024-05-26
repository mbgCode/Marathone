package modelo;

import java.sql.SQLException;

import com.google.gson.Gson;

import dao.DaoDeporte;



/**
 * Clase Deporte
 * Incluye atributos y métodos específicos para la gestión de las entidades deportivas.
 * @author Marcos Barberá Gómez
 * @version 1.0 24/05/2024 
 */
public class Deporte {

	
	//Atributos.
	private int id;
	private String nombre;
	private String descripcion;
	private int telefono;
	private String direccion;
	private String foto;
	private String categoria;
	
	
	
	/**
	* Constructor vacío.
	*/	
	public Deporte() {
		
	}
	

	/**
	 * Constructor completo de 7 parámetros.
	 * @param id ID del deporte.
     * @param nombre Nombre del deporte.
     * @param descripcion Descripción del deporte.
     * @param telefono Teléfono del deporte.
     * @param direccion Dirección del deporte.
     * @param foto URL de la foto del deporte.
     * @param categoria Categoría del deporte.
	 */
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



	@Override
	public String toString() {
		return "Deporte [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", telefono=" + telefono
				+ ", direccion=" + direccion + ", foto=" + foto + ", categoria=" + categoria + "]";
	}

	
	
	//Métodos públicos.
	
	
	/**
	 * Método que inserta los datos recibidos del DaoDeporte en los atributos de la clase Deporte.
	 * Este método sirve de enlace entre el Servlet SV_deporte, donde es llamado, y el DaoDeporte.
	 * @throws SQLException si ocurre algún error al insertarlo.
	 */
	public void insertarDep () throws SQLException {
		DaoDeporte dao = new DaoDeporte();
		dao.insertarDep(this);
	}
	
	
	/**
	 * Método que va a modificar los datos de un deporte por su id.
	 * @param id del deporte a modificar.
	 * @throws SQLException Si ocurre algún error durante la modificación.
	 */
	public void modId(int id) throws SQLException {
		
		DaoDeporte dao = new DaoDeporte();
		Deporte d = dao.listarPorId(id);
		
		this.setNombre(d.getNombre());
		this.setDescripcion(d.getDescripcion());
		this.setTelefono(d.getTelefono());
		this.setDireccion(d.getDireccion());
		this.setFoto(d.getFoto());
		this.setCategoria(d.getCategoria());
		this.setId(d.getId());
	}
	
	
	
	
	/**
	 * Actualiza los datos del deporte en la base de datos.
	 * @throws SQLException
	 */
	public void update() throws SQLException {
		DaoDeporte dao = new DaoDeporte();
		dao.update(this);
	}
	
	
	
	/**
     * Convierte los datos del deporte a formato JSON.
     * @return JSON con los datos del deporte.
     */
	public String dameJson() {
		String txtJson = "";
		Gson gson = new Gson ();
		txtJson = gson.toJson(this);//Llamamos a la funcion listar con los datos de la misma clase."this"
		return txtJson;
	}
	
	
	 /**
     * Elimina un deporte por medio de su ID.
     * @param id ID del deporte a eliminar.
     * @throws SQLException si ocurre un error durante la eliminación.
     */
	public void borrar (int id) throws SQLException {
		DaoDeporte dao = new DaoDeporte();
		dao.borrar(id);
	}
	
	
}
