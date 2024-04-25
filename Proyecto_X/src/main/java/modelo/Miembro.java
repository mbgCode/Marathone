package modelo;

import java.sql.SQLException;

import dao.DaoMiembro;



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
	
	
	
// Constructor entero con herencia de usuario ----
	public Miembro(String nombre, String apellidos, String email, String poblacion, int permiso, String foto, int edad,int id) {
		super(nombre,apellidos,email,poblacion,permiso,foto);//herencia de Usuario.
		
		this.id = id;
		this.edad = edad;
		
	}

	

//Construcot sin ID (auto incremetnro en BD) para poder inyectarlo en la BD.
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

	
	
	
		
}
