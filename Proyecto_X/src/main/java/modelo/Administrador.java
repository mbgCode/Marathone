package modelo;

import java.sql.SQLException;

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
	
	
	

//Metodos ---------------------------------------------------
	
//Metodo Insertar	
	public void insertarAdmin () throws SQLException {
		
		DaoAdministrador dao = new DaoAdministrador();
		dao.insertar(this);
		
	}
	
	
	
//Metodo modificar por ID
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


	@Override
	public String toString() {
		return "Administrador [idadministrador=" + idadministrador + ", nombre=" + nombre + ", apellidos=" + apellidos
				+ ", email=" + email + ", poblacion=" + poblacion + ", permiso=" + permiso + ", foto=" + foto + "]";
	}



//ToString

		
}
