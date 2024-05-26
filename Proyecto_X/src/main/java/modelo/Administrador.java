package modelo;

import java.sql.SQLException;

import com.google.gson.Gson;

import dao.DaoAdministrador;
import dao.DaoMiembro;


/**
 * Clase Administrador
 * Esta clase se extiende a la clase Usuario.
 * Incluye atributos y métodos específicos para la gestión de administradores.
 * @author Marcos Barberá Gómez
 * @version 1.0 24/05/2024 
 */
public class Administrador extends Usuario  {

	
	//Atributos.	
	private int idadministrador;

	
	
	/**
	* Constructor vacío.
	*/
	public Administrador(){
		
	}
	
	
	
	/**
	 * Constructor completo de 7 parámetros con herencia de Usuario.
	 * El permiso de acceso para los administradores es siempre 2. Este valor se asigna automáticamente en la base de datos.
	 * Este constructor se utiliza para listar todos los datos del administrador, así como para la inserción.
	 * También se utiliza para hacer inicio de sesión y pdoer guardar los datos deseados.
	 * @param nombre Nombre del administrador.
     * @param apellidos Apellidos del administrador.
     * @param email Correo electrónico del administrador.
     * @param poblacion Lugar de residencia del administrador.
     * @param permiso Permiso de acceso (siempre 2 para administradores).
     * @param foto Foto personal del administrador.
     * @param idaministrador ID del administrador.
	 */
	public Administrador(String nombre, String apellidos, String email, String poblacion, int permiso, String foto, String pass ,int idaministrador) {
		super(nombre, apellidos, email, poblacion, 2, foto, pass);
		
		this.idadministrador = idaministrador;
	}

	
	
	/**
	 * Constructor con herencia de Usuario, sin los atributos id y permiso.
	 * Es utilizado para recibir por primera vez los datos del Formulario (DaoAdministrador). 
	 * El id se recibe en la base de datos, y permiso en el constructor.
	 * @param nombre Nombre del administrador.
     * @param apellidos Apellidos del administrador.
     * @param email Correo electrónico del administrador.
     * @param poblacion Lugar de residencia del administrador.
     * @param foto Foto personal del administrador.
	 */
	public Administrador(String nombre, String apellidos, String email, String poblacion, String foto, String pass ) {
		super(nombre, apellidos, email, poblacion, 2, foto, pass);
		
	}

	
	
	/**
	 * Constructor con herencia de Usuario, sin atributo de pass.
	 * Este constructor se utiliza para listar los datos en el formulario. Por lo que no es necesario el pass.
	 * @param nombre Nombre del administrador.
     * @param apellidos Apellidos del administrador.
     * @param email Correo electrónico del administrador.
     * @param poblacion Lugar de residencia del administrador.
     * @param permiso Permiso de acceso (siempre 2 para administradores).
     * @param foto Foto personal del administrador.
     * @param idaministrador ID del administrador.
	 */
	public Administrador(String nombre, String apellidos, String email, String poblacion, int permiso, String foto,int idaministrador) {
		super(nombre, apellidos, email, poblacion, 2, foto);
		
		this.idadministrador = idaministrador;
	}
	

	
	/**
	 * Constructor con herencia de Usuario.
	 * Solo atributo Foto.
	 * Se utiliza para hacer la petición a la base de datos y recibir la foto del id concreto (DaoAdministrador).
	 * Esta foto se envía al header del formulario para identificar al Administrador cuando hace login.
     * @param foto Foto personal del administrador.
	 */
	public Administrador(String foto) {
		super(foto);
	}


	
	/**
	 * Get del id del administrador
	 * @return devuelve el id del administrador
	 */
	public int getIdaministrador() {
		return idadministrador;
	}

	

	/**
     * Establece el ID del administrador.
     * @param idaministrador ID del administrador.
     */
	public void setIdaministrador(int idaministrador) {
		this.idadministrador = idaministrador;
	}
	
	
	

	//Métodos públicos.
	
	/**
	 * Método que inserta los datos recibidos del DaoAdministrador en los atributos de la clase Administrador.
	 * Este método sirve de enlace entre el Servlet SV_administrador, donde es llamado, y el DaoAdministrador.
	 * @throws SQLException si ocurre algún error al insertarlo.
	 */
	public void insertarAdmin () throws SQLException {
		DaoAdministrador dao = new DaoAdministrador();
		dao.insertar(this);
	}
	
	
	
	
	/**
	 * Este método se utiliza en el momento del login del administrador.
	 * Se recibe el parametro pass del servlet SV_administrador y se compara con el recibido de la base de datos.
	 * 
	 * @param pass Password recibido del servlet SV_administrador.
	 * @return  <ul>
	 * 				<li> true: Si la condición es diferente a null, significa que ese pass y el mail coinciden con los de la base de datos,
	 * 					  y se inyectan en el objeto Administrador. La variable "ok" devolvería true.</li>
	 * 				<li> false: Si es null, quiere decir que no existen esos parámetros en la base de datos. "ok" sería false.</li>
	 * @throws SQLException si ocurre algun tipo de error.
	 */
	public boolean logeo (String pass) throws SQLException {
		boolean ok = false;
				
		DaoAdministrador dao = new DaoAdministrador();
		Administrador aux = dao.logeando (this,pass);//Este metodo va a la BD.
				
		if (aux != null){//Si es diferente a null significa que aux contiene todos los datos de ese usuario.
			this.setNombre(aux.getNombre());
			this.setApellidos(aux.getApellidos());
			this.setEmail(aux.getEmail());
			this.setPoblacion(aux.getPoblacion());
			this.setPermiso(aux.getPermiso());
			this.setFoto(aux.getFoto());
			this.setIdaministrador(aux.getIdaministrador());
			this.setPass(aux.getPass());
			ok=true; //entonces si esta aqui dentro ok es cierto y lo devovlemos con el retunr
			
			}	
			return ok;
		}
			
	
	
	
	/**
	 * Método que va a modificar los datos de un administrador por su id. Recibimos los datos del dao y los insertamos en el objeto "a".
	 * @param id del administrador a modificar.
	 * @throws SQLException Si ocurre algún error durante la modificación.
	 */
	public void modificarAdmin (int id) throws SQLException {
	
		DaoAdministrador dao = new DaoAdministrador();//generamos el objeto dao.
		Administrador a = dao.modificar(id); // recibimos los datos del id para modificar
		
			this.setNombre(a.getNombre());
			this.setApellidos(a.getApellidos());
			this.setEmail(a.getEmail());
			this.setPoblacion(a.getPoblacion());
			this.setPermiso(a.getPermiso());
			this.setFoto(a.getFoto());
			this.setIdaministrador(a.getIdaministrador());
	}
	

	

	/**
	 * Recibe la foto del administrador mediante su ID para mostrarla en el formulario después del inicio de sesión.
	 * @param id del administrador.
	 * @throws SQLException si hay algún error durante la petició.
	 */
	public void foto(int id) throws SQLException {
			Administrador a = new Administrador();
			DaoAdministrador d = new DaoAdministrador();

			a=d.foto(id);
			this.setFoto(a.getFoto());
	}
			
		
	
	
    /**
     * Convierte la foto del administrador a formato JSON.
     * @return JSON con la foto del administrador.
     */	
	public String fotoJson () {
			String json = "";
			Gson gson = new Gson();
			json = gson.toJson(this.foto);
			return json;		
	}	
			
			
	
	
    /**
     * Actualiza los datos del administrador en la base de datos.
     * @throws SQLException si ocurre un error durante la actualización.
     */
	public void update () throws SQLException {
		DaoAdministrador dao = new DaoAdministrador() ;
		dao.actualizar(this);
	}
	
	
	
	
    /**
     * Convierte los datos del administrador a formato JSON. Este JSON es para listar los datos a modificar.
     * @return JSON con los datos del administrador.
     */
	public String dameJson () {// entre parentesis podriamos pedirle un int id.
		String json = "";
		Gson gson = new Gson();
		json = gson.toJson(this);
		return json;
	}
	
	

	  /**
     * Elimina un administrador por su ID.
     * @param id ID del administrador a eliminar.
     * @throws SQLException si ocurre un error durante la eliminación.
     */
	public void eliminar (int id) throws SQLException {
		DaoAdministrador dao = new DaoAdministrador();
		dao.borrarBD(id);
	}
	
	
	
//toString
	@Override
	public String toString() {
		return "Administrador [idadministrador=" + idadministrador + ", nombre=" + nombre + ", apellidos=" + apellidos
				+ ", email=" + email + ", poblacion=" + poblacion + ", permiso=" + permiso + ", foto=" + foto + "]";
	}





		
}
