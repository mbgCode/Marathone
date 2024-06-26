package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import modelo.Usuario;

import java.io.File;
import java.io.IOException;

import java.io.PrintWriter;

import java.io.InputStream;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.DaoUsuario;

/**
 * Servlet implementation class Servlet_usuario
 */


//Añadimos el @MULTIPARTCON y EL WEBSERVLET SIN EL nombre del servlet. DARA conflicto ala hora de arrancar el servidor.
@WebServlet()
@MultipartConfig


public class Servlet_usuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	//Añadimos la ruta de ORIGEN de la carpera...De momento lo hacemos de manera local. Luego se hará con ruta a la base de datos.
	private String pathFiles = "C:\\Users\\mbgco\\git\\repository\\Proyecto_X\\src\\main\\webapp\\Fotos";
	
	//Añadimos la clase FILE para poder introducir fotos en la bd.
	//Hemos creado una carpeta en webapp llamada fotos que es donde vamos a guardarlas.
	private File uploads = new File (pathFiles);
	
	
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
 
    public Servlet_usuario() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		

		try {
			PrintWriter out = response.getWriter();//Este es el objeto de salida. Para escribir datos de vuelta a la web.
			
			DaoUsuario dao = new DaoUsuario();
			String resultado = dao.ListarJonson();
			out.print(resultado);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		

		String nombre = request.getParameter("nombre");
		String apellidos = request.getParameter("apellidos");
		String email = request.getParameter("email");
		String poblacion = request.getParameter("poblacion");
		
		String permiso = request.getParameter("permiso");//recibimos un tipo String
		int permiss=Integer.parseInt(permiso);//lo cambiamos a int para poder insertarlo en su clase.
		
		
//INCLUIR FOTOS ------------------------------------------------------------------------------	
		/*
		 * Creamos un objeto Part para que recoja los DATOS binarios de la FOTO. 
		 * Le indicamos el nombre que tiene en el .html
		 */
		Part part = request.getPart("foto"); //Para añadir mas fotos podremos repetir el codigo tantas veces se quiera.
		
		//Obtenemos la RUTA/nobre del archivo. Sacamos la ruta de part.
		Path path = Paths.get(part.getSubmittedFileName());
		
		//Guardamos en la base de datos el NOMBRE de ese archivo que es un String.
		String filename = path.getFileName().toString();
		
		//Preparamos el camino (BUFFER) para enviar esos datos.
		InputStream inpst = part.getInputStream();
		
		//Vamos a guardar el archivo y a meterlo en la carpeta Fotos.
		File file = new File (uploads,filename);
		
		try {
			//Copiamos los datos del archivo dentro de la carpeta utilizando el BUFFER.
			Files.copy(inpst,file.toPath());
		}catch (Exception e) {
			System.out.println("Error en la COPIA DEL ARCHIVO");
			PrintWriter error = response.getWriter();
			error.print("Se ha producido un error");
		}
		
//---------------------------------------------------------------------------------------------	

		
		
		
//Creamos el objeto Usuario y lo insertamos en dao.
		//filename es para la foto
		Usuario u1 = new Usuario(nombre, apellidos, email, poblacion, permiss, filename);
		
		
		
		
//Isertar Usuario
		try {
			u1.insertarUsuario();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	
//Listar Usuario
		DaoUsuario lista;
			try {
				lista = new DaoUsuario();
				ArrayList <Usuario> listaUsuario = lista.listar();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		
	
	
	}

}
