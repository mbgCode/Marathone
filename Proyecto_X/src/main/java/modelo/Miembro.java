package modelo;

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


	
// Constructor con con herencia de usuario ----
	public Miembro(String nombre, String apellidos, String email, String poblacion, int permiso, String foto,int id, int edad) {
		super(nombre,apellidos,email,poblacion,permiso,foto);//herencia de Usuario.
		this.id = id;
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


	
	
	
	
	
}
