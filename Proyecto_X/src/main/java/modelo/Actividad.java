package modelo;

/**
 * Clase Actividad Incluye atributos y métodos específicos para la gestión de las
 * actividades.
 * 
 * @author Marcos Barberá Gómez
 * @version 1.0 24/05/2024
 */
public class Actividad {

	
	// Atributos.
	private int id_actividad;
	private String nombre;
	private String foto;
	private String iddeporte;


	/**
	 * Constructor vacío.
	 */
	public Actividad() {

	}


	/**
	 * Constructor completo de 4 parámetros.
	 * 
	 * @param id_instalacion    ID de la actividad.
	 * @param nombre      		Nombre de la actividad.
	 * @param foto        		URL de la foto del deporte.
	 * @param iddporte 			iddeporte al que pertenece la actividad.
	 */

	public Actividad(int id_actividad, String nombre, String foto, String iddeporte) {
		super();
		this.id_actividad = id_actividad;
		this.nombre = nombre;
		this.foto = foto;
		this.iddeporte = iddeporte;
	}




	public int getId_actividad() {
		return id_actividad;
	}




	public void setId_actividad(int id_actividad) {
		this.id_actividad = id_actividad;
	}




	public String getNombre() {
		return nombre;
	}




	public void setNombre(String nombre) {
		this.nombre = nombre;
	}




	public String getFoto() {
		return foto;
	}




	public void setFoto(String foto) {
		this.foto = foto;
	}




	public String getIddeporte() {
		return iddeporte;
	}




	public void setIddeporte(String iddeporte) {
		this.iddeporte = iddeporte;
	}




	@Override
	public String toString() {
		return "Actividad [id_actividad=" + id_actividad + ", nombre=" + nombre + ", foto=" + foto + ", iddeporte="
				+ iddeporte + "]";
	}
	
}