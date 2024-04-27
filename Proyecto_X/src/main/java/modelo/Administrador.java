package modelo;

import java.sql.SQLException;

import dao.DaoAdministrador;

public class Administrador extends Usuario  {

	
//Atributo	
	private int idadministrador;

	
	
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
	
	
	

	@Override
	public String toString() {
		return "Administrador [idaministrador=" + idadministrador + "]";
	}
	
	
	public void insertarAdministrador () throws SQLException {
		
		DaoAdministrador dao = new DaoAdministrador();
		dao.insertar(this);
		
	}
	
	public void borrar (int id) {
		
	}

	
}
