package modelo;

import java.sql.SQLException;

import com.google.gson.Gson;

import dao.DaoAdministrador;

public class Administrador extends Usuario  {

	
//Atributo	
	private int idadministrador;


//Constructor vacío
	public Administrador(){
		
	}
	
	
//Constructor con id y herencia de Usuario	
	public Administrador(String nombre, String apellidos, String email, String poblacion, int permiso, String foto ,int idaministrador) {
		super(nombre, apellidos, email, poblacion, permiso, foto);
		this.idadministrador = idaministrador;
	}



//Constructor SIN ID y solo herencia de Usuario
	public Administrador(String nombre, String apellidos, String email, String poblacion, int permiso, String foto) {
		super(nombre, apellidos, email, poblacion, permiso, foto);
	}



//Getter and Setter	
	public int getIdaministrador() {
		return idadministrador;
	}



	public void setIdaministrador(int idaministrador) {
		this.idadministrador = idaministrador;
	}
	
	
	

//Metodos -----------------------------------------------------------------------------------
	
//Metodo Insertar	
	public void insertarAdmin () throws SQLException {
		DaoAdministrador dao = new DaoAdministrador();
		dao.insertar(this);
		
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
	
	
	
//ToString
	@Override
	public String toString() {
		return "Administrador [idadministrador=" + idadministrador + ", nombre=" + nombre + ", apellidos=" + apellidos
				+ ", email=" + email + ", poblacion=" + poblacion + ", permiso=" + permiso + ", foto=" + foto + "]";
	}





		
}
