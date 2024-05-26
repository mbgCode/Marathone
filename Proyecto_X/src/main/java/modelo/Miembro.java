package modelo;

import java.sql.SQLException;

import com.google.gson.Gson;

import dao.DaoAdministrador;
import dao.DaoMiembro;
import dao.DaoUsuario;

/**
 * Clase Miembro Incluye atributos y métodos específicos para la gestión de
 * Miembros. El rol de Miembro es la de un Usuario registrado. Tiene mas
 * permisisos que el usuario visitante.
 * 
 * @author Marcos Barberá Gómez
 * @version 1.0 24/05/2024
 */
public class Miembro extends Usuario {
	/*
	 * Esta es la clase principal de miembro. hereda atributos de la clase Usuario.
	 */

//Atributos	
	int id;
	int edad;

	/**
	 * Constructor vacío.
	 */
	public Miembro() {

	}

	/**
	 * Constructor completo con 9 parámetros y con herencia de Usuario. El permiso
	 * de acceso para el miembro es siempre 1. Este valor es asignado
	 * automaticmanete en la base de datos. Este constructor se utiliza para:
	 * <ul>
	 * <li>Insertar los datos del miembro en la base de datos.</li>
	 * <li>Listar los datos por id para modificar los datos.</li>
	 * <li>Iniciar sesión y poder guardar los datos deseados en ella.</li>
	 * <ul>
	 * 
	 * @param nombre    Nombre del miembro.
	 * @param apellidos Apellidos del miembro.
	 * @param email     Correo electrónico del miembro.
	 * @param poblacion Lugar de residencia del miembro.
	 * @param permiso   Permiso de acceso.
	 * @param foto      Foto personal del miembro.
	 * @param edad      Edad del miembro.
	 * @param id        ID del miembro.
	 * @param pass      Contraseña del miembro.
	 */
// Constructor entero con herencia de usuario.
	public Miembro(String nombre, String apellidos, String email, String poblacion, int permiso, String foto, int edad,
			int id, String pass) {
		super(nombre, apellidos, email, poblacion, 1, foto, pass);// herencia de Usuario.

		this.id = id;
		this.edad = edad;
	}

	/**
	 * Constructor con herencia de Usuario, sin los atributos id y permiso. Es
	 * utilizado para recibir por primera vez los datos del Formulario (DaoMiembro).
	 * El id se recibe en la base de datos, y permiso en el constructor.
	 * 
	 * @param nombre    Nombre del miembro.
	 * @param apellidos Apellidos del miembro.
	 * @param email     Correo electrónico del miembro.
	 * @param poblacion Lugar de residencia del miembro.
	 * @param foto      Foto personal del miembro.
	 * @param edad      Edad del miembro.
	 * @param pass      Contraseña del miembro.
	 */
// Constructor sin permiso e id. (Para insertar del formulario a la BD)
	public Miembro(String nombre, String apellidos, String email, String poblacion, String foto, int edad,
			String pass) {
		super(nombre, apellidos, email, poblacion, 1, foto, pass);// herencia de Usuario.

		this.edad = edad;
	}

	/**
	 * Constructor con herencia de Usuario. Solo atributo Foto. Se utiliza para
	 * hacer la petición a la base de datos y recibir la foto del id concreto
	 * (DaoMiembro). Esta foto se envía al header del formulario para identificar al
	 * Miembro cuando hace login.
	 * 
	 * @param foto Foto personal del miembro.
	 */
	public Miembro(String foto) {
		super(foto);
	}

//Getter and Setter.
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

//Metodo toString ---
	@Override
	public String toString() {
		return "Miembro [id=" + id + ", edad=" + edad + "]";
	}

	// Métoos públicos.

	/**
	 * Método que inserta los datos recibidos del DaoMiembro en los atributos de la
	 * clase Miembro. Este método sirve de enlace entre el Servlet SV_miembro, donde
	 * es llamado, y el DaoMiembro.
	 * 
	 * @throws SQLException si ocurre algún error al insertarlo.
	 */
	public void insertarMiembro() throws SQLException {
		DaoMiembro dao = new DaoMiembro();
		dao.insertar(this);

	}

	/**
	 * Este método se utiliza en el momento del login del miembro. Se recibe el
	 * parametro pass del servlet SV_miembro y se compara con el recibido de la base
	 * de datos.
	 * 
	 * @param pass Password recibido del servlet SV_miembro.
	 * @return
	 *         <ul>
	 *         <li>true: Si la condición es diferente a null, significa que ese pass
	 *         y el mail coinciden con los de la base de datos, y se inyectan en el
	 *         objeto Miembro. La variable "ok" devolvería true.</li>
	 *         <li>false: Si es null, quiere decir que no existen esos parámetros en
	 *         la base de datos. "ok" sería false.</li>
	 * @throws SQLException si ocurre algun tipo de error.
	 */
	public boolean logeo(String pass) throws SQLException {
		boolean ok = false;

		DaoMiembro dao = new DaoMiembro();
		Miembro aux = dao.logeando(this, pass);// Este metodo va a la BD

		if (aux != null) {// Si es diferente a null significa que aux contiene todos los datos de ese
							// usuario.
			this.setNombre(aux.getNombre());
			this.setApellidos(aux.getApellidos());
			this.setEmail(aux.getEmail());
			this.setPoblacion(aux.getPoblacion());
			this.setPermiso(aux.getPermiso());
			this.setFoto(aux.getFoto());
			this.setEdad(aux.getEdad());
			this.setId(aux.getId());
			this.setPass(aux.getPass());
			ok = true; // entonces si esta aqui dentro ok es cierto y lo devovlemos con el retunr
		}
		return ok;
	}

	/**
	 * Método que va a modificar los datos de un miembro por su id. Recibimos los
	 * datos del dao y los insertamos en el objeto "m".
	 * 
	 * @param id del modificar a modificar.
	 * @throws SQLException Si ocurre algún error durante la modificación.
	 */
	public void listPorId(int id) throws SQLException {
		Miembro m = new Miembro();
		DaoMiembro d = new DaoMiembro();

		m = d.listarPorId(id);
		this.setNombre(m.getNombre());
		this.setApellidos(m.getApellidos());
		this.setEmail(m.getEmail());
		this.setPoblacion(m.getPoblacion());
		this.setPermiso(m.getPermiso());
		this.setFoto(m.getFoto());
		this.setEdad(m.getEdad());
		this.setId(m.getId());

	}

	/**
	 * Recibe la foto del miembro mediante su ID para mostrarla en el formulario
	 * después del inicio de sesión.
	 * 
	 * @param id del miembro.
	 * @throws SQLException si hay algún error durante la petició.
	 */
	public void foto(int id) throws SQLException {
		Miembro m = new Miembro();
		DaoMiembro d = new DaoMiembro();
		m = d.foto(id);
		this.setFoto(m.getFoto());
	}

	/**
	 * Convierte la foto del miembro a formato JSON.
	 * 
	 * @return JSON con la foto del miembro.
	 */
	public String fotoJson() {
		String json = "";
		Gson gson = new Gson();
		json = gson.toJson(this.foto);
		return json;
	}

	/**
	 * Convierte los datos del administrador a formato JSON. Este JSON es para
	 * listar los datos a modificar.
	 * 
	 * @return JSON con los datos del administrador.
	 */
	public String dameJson() {
		String json = "";
		Gson gson = new Gson();
		json = gson.toJson(this);
		return json;
	}

	/**
	 * Actualiza los datos del miembro en la base de datos.
	 * 
	 * @throws SQLException si ocurre un error durante la actualización.
	 */
	public void update() throws SQLException {
		DaoMiembro dao = new DaoMiembro();
		dao.update(this);
	}

	/**
	 * Elimina un miembro por su ID.
	 * 
	 * @param id ID del miembro a eliminar.
	 * @throws SQLException si ocurre un error durante la eliminación.
	 */
	public void eliminar(int id) throws SQLException {
		DaoMiembro dao = new DaoMiembro();
		dao.borrarBD(id);
	}

}
