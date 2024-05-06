package modelo;

import java.sql.SQLException;

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
		super(nombre,apellidos,email,poblacion,permiso,foto,pass);//herencia de Usuario.
		
		this.id = id;
		this.edad = edad;
	}

	

//Construcot sin ID (auto incremetnro en BD) para poder inyectarlo en la BD.
	public Miembro(String nombre, String apellidos, String email, String poblacion, int permiso, String foto, int edad,String pass) {
		super(nombre,apellidos,email,poblacion,permiso,foto,pass);
		
		this.edad = edad;
	}

	
//Construcot sin ID (auto incremetnro en BD) para poder inyectarlo en la BD. Sin pass
		public Miembro(String nombre, String apellidos, String email, String poblacion, int permiso, String foto, int edad) {
			super(nombre,apellidos,email,poblacion,permiso,foto);
			
			this.edad = edad;
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
	

	
//Metodos ---
	public void insertarMiembro() throws SQLException {
		DaoMiembro dao = new DaoMiembro();
		dao.insertar(this);
			
	}
	
	
	
// Logeo para usuarios( tanto miembros como admin) 	
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
	
	
	
		
}
