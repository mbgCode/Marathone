package modelo;

import java.sql.SQLException;

import com.google.gson.Gson;

import dao.DaoAdministrador;
import dao.DaoMiembro;
import dao.DaoUsuario;



public class Miembro extends Usuario{
/*Esta es la clase principal de miembro. 
 * hereda atributos de la clase Usuario.
 */
	

	
//Atributos	
	int id;
	int edad;
	
	

//Constructor vacío	
	public Miembro () {
		
	}
	
	
	
// Constructor entero con herencia de usuario.
	public Miembro(String nombre, String apellidos, String email, String poblacion, int permiso, String foto, int edad,int id,String pass) {
		super(nombre,apellidos,email,poblacion,1,foto,pass);//herencia de Usuario.
		
		this.id = id;
		this.edad = edad;
	}

	

//Construcot sin ID (auto incremetnro en BD) para poder inyectarlo en la BD.
	public Miembro(String nombre, String apellidos, String email, String poblacion, int permiso, String foto, int edad,String pass) {
		super(nombre,apellidos,email,poblacion,1,foto,pass);
		
		this.edad = edad;
	}

	
	
//Constructor sin ID y sin pass.(auto incremetnro en BD) para poder inyectarlo en la BD. 
	public Miembro(String nombre, String apellidos, String email, String poblacion, int permiso, String foto, int edad) {
		super(nombre,apellidos,email,poblacion,1,foto);
			
		this.edad = edad;
		}	
	
	
	
// Constructor sin permiso e id. (Para insertar del formulario a la BD)
	public Miembro(String nombre, String apellidos, String email, String poblacion, String foto, int edad,String pass) {
		super(nombre,apellidos,email,poblacion,1,foto,pass);//herencia de Usuario.
		
		this.edad = edad;
	}
	
	
	
//Coontructor solo con foto para la zona login	
	public Miembro(String foto) {
		super(foto);
	}






//Getter and Setter ----	
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
	
	

	
//Metodos -----------------
	
//Insertamos Miembro.
	public void insertarMiembro() throws SQLException {
		DaoMiembro dao = new DaoMiembro();
		dao.insertar(this);		
	
	}
	
	
	
// Logeo para usuarios(tanto miembros como admin).	
		public boolean logeo (String pass) throws SQLException {
			
			boolean ok = false;
			
			DaoMiembro dao = new DaoMiembro();
			Miembro aux = dao.logeando (this,pass);//Este metodo va a la BD
			
			if (aux != null){//Si es diferente a null significa que aux contiene todos los datos de ese usuario.
				//Es probale que el id sea necesario en Usuario y sacarlo de miembro y administrador.
				this.setNombre(aux.getNombre());
				this.setApellidos(aux.getApellidos());
				this.setEmail(aux.getEmail());
				this.setPoblacion(aux.getPoblacion());
				this.setPermiso(aux.getPermiso());
				this.setFoto(aux.getFoto());
				this.setEdad(aux.getEdad());
				this.setId(aux.getId());
				this.setPass(aux.getPass());
				ok=true; //entonces si esta aqui dentro ok es cierto y lo devovlemos con el retunr		
			}			
			return ok;
		}
	
		
		
		
//	Petición para listar todos los datos por id.
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
		
		
	
// Enviamos id al dao e insertamos foto a los atributos.	
		public void foto(int id) throws SQLException {
			Miembro m = new Miembro();
			DaoMiembro d = new DaoMiembro();

			m=d.foto(id);
			this.setFoto(m.getFoto());
		}
		
		
		
		
//Va a devolver los datos del id elegido de modificarAdmin al cliente para modificarlos.
			public String fotoJson () {
					
				String json = "";
				Gson gson = new Gson();
				json = gson.toJson(this.foto);
				return json;
					
			}		
		
		
		
//Va a devolver los datos del id elegido de modificarAdmin al cliente para modificarlos.
		public String dameJson () {
			
			String json = "";
			Gson gson = new Gson();
			json = gson.toJson(this);
			return json;
			
		}
			

		
// update de los datos por id para la BD.
		public void update () throws SQLException {
			DaoMiembro dao = new DaoMiembro();
			dao.update(this);
			
		}
		
		
		
//Eliminar datos de la bd por id.
		public void eliminar (int id) throws SQLException {
			DaoMiembro dao = new DaoMiembro();
			dao.borrarBD(id);
		}		
		
}
