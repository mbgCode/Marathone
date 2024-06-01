package modelo;

/**
 * Clase Deporte Incluye atributos y métodos específicos para la gestión de las
 * entidades deportivas.
 * 
 * @author Marcos Barberá Gómez
 * @version 1.0 24/05/2024
 */
public class Instalacion {

	
	// Atributos.
	private int id_instalacion;
	private String nombre;
	private String foto;
	private String direccion;
	private String iddeporte;


	/**
	 * Constructor vacío.
	 */
	public Instalacion() {

	}

	/**
	 * Constructor completo de 5 parámetros.
	 * 
	 * @param id_instalacion    ID de la instalación.
	 * @param nombre      		Nombre de la instalación.
	 * @param foto        		URL de la foto del deporte.
	 * @param direccion 		Dirección de la instalacion.
	 * @param iddporte 			iddeporte al que pertenece la instalación.
	 */



	public Instalacion(int id_instalacion, String nombre, String foto, String direccion, String iddeporte) {
		super();
		this.id_instalacion = id_instalacion;
		this.nombre = nombre;
		this.foto = foto;
		this.direccion = direccion;
		this.iddeporte = iddeporte;
	}

	
	
	
	public int getId_instalacion() {
		return id_instalacion;
	}

	public void setId_instalacion(int id_instalacion) {
		this.id_instalacion = id_instalacion;
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

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getIddeporte() {
		return iddeporte;
	}

	public void setIddeporte(String iddeporte) {
		this.iddeporte = iddeporte;
	}

	
	@Override
	public String toString() {
		return "Instalacion [id_instalacion=" + id_instalacion + ", nombre=" + nombre + ", foto=" + foto
				+ ", direccion=" + direccion + ", iddeporte=" + iddeporte + "]";
	}


	
}
