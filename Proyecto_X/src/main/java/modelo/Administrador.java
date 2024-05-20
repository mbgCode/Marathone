package modelo;

import java.sql.SQLException;

import com.google.gson.Gson;

import dao.DaoAdministrador;
import dao.DaoMiembro;

public class Administrador extends Usuario  {

	
//Atributo	
	private int idadministrador;


//Constructor vacío
	public Administrador(){
		
	}
	
	
//Constructor con id y herencia de Usuario	
	//Permiso de acceso para los administradores es el 2. Siempre le vamos a asignar ese valor.
	public Administrador(String nombre, String apellidos, String email, String poblacion, int permiso, String foto, String pass ,int idaministrador) {
		super(nombre, apellidos, email, poblacion, 2, foto, pass);
		
		this.idadministrador = idaministrador;
	}

	
	
//Contructor con todo pero sin permiso e ID.	
	public Administrador(String nombre, String apellidos, String email, String poblacion, String foto, String pass ) {
		super(nombre, apellidos, email, poblacion, 2, foto, pass);
		
	}

	
	
//Constructor sin PASS.	
	public Administrador(String nombre, String apellidos, String email, String poblacion, int permiso, String foto,int idaministrador) {
		super(nombre, apellidos, email, poblacion, 2, foto);
		
		this.idadministrador = idaministrador;
	}
	

//Constructor SIN ID y solo herencia de Usuario
	public Administrador(String nombre, String apellidos, String email, String poblacion, int permiso, String foto, String pass) {
		super(nombre, apellidos, email, poblacion, 2, foto,pass);
		
	}



//Getter and Setter	
	public int getIdaministrador() {
		return idadministrador;
	}



	public void setIdaministrador(int idaministrador) {
		this.idadministrador = idaministrador;
	}
	
	
	

//Metodos -----------------------------------------------------------------------------------
	
	
//Metodo Insertar Usuarios
	public void insertarAdmin () throws SQLException {
		DaoAdministrador dao = new DaoAdministrador();
		dao.insertar(this);
		
	}
	
	
	
// Logeo para usuarios( tanto miembros como admin) 	
			public boolean logeo (String pass) throws SQLException {
				
				
				boolean ok = false;
				
				DaoAdministrador dao = new DaoAdministrador();
				Administrador aux = dao.logeando (this,pass);//Este metodo va a la BD
				
				if (aux != null){//Si es diferente a null significa que aux contiene todos los datos de ese usuario.
					//Es probale que el id sea necesario en Usuario y sacarlo de miembro y administrador.
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
			
	
	
	
//Sacamos los datos por ID .Una vez dado al editar nos saca los datos por el formulario. Justo antes de introducir los cambios
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
	
	
	
//Actualizamos los datos de Administrador ya introducidos. 
	public void update () throws SQLException {
		DaoAdministrador dao = new DaoAdministrador() ;
		dao.actualizar(this);
	}
	
	
	
//Va a devolver los datos del id de modificarAdmin al cliente.
	public String dameJson () {// entre parentesis podriamos pedirle un int id.
		
		String json = "";
		Gson gson = new Gson();
		json = gson.toJson(this);
		return json;
		
	}
	
	

//Eliminar por id
	public void eliminar (int id) throws SQLException {
		DaoAdministrador dao = new DaoAdministrador();
		
		dao.borrarBD(id);
	}
	
	
	
	
//ToString
	@Override
	public String toString() {
		return "Administrador [idadministrador=" + idadministrador + ", nombre=" + nombre + ", apellidos=" + apellidos
				+ ", email=" + email + ", poblacion=" + poblacion + ", permiso=" + permiso + ", foto=" + foto + "]";
	}





		
}
