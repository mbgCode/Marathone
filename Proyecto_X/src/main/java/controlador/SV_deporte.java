package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import modelo.Deporte;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

/**
 * Servlet implementation class SV_deporte
 */

//Añadimos el @MULTIPARTCON y EL WEBSERVLET SIN EL nombre del servlet. DARA conflicto ala hora de arrancar el servidor.
@WebServlet()
@MultipartConfig

public class SV_deporte extends HttpServlet {
	private static final long serialVersionUID = 1L;
   

	//Añadimos la ruta de ORIGEN de la carpera...De momento lo hacemos de manera local. Luego se hará con ruta a la base de datos.
	private String pathFiles ="C:\\Users\\mbgco\\git\\repository\\Proyecto_X\\src\\main\\webapp\\fotos_deporte";
	
	//Añadimos la clase FILE para poder introducir fotos en la bd.
	//Hemos creado una carpeta en webapp llamada fotos_deporte que es donde vamos a guardarlas.
	private File uploads = new File (pathFiles);
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SV_deporte() {
        super();
        
    }

    
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//Recogemos todos lo parametros desde el html. 
		String nombre = request.getParameter("nombre");
		String descripcion = request.getParameter("descripcion");
		int telefono = Integer.parseInt(request.getParameter("telefono"));
		String direccion = request.getParameter("direccion");
		
	
		
//Recibimos foto.
		Part part = request.getPart("foto");//recogemos los datos Binarios de foto
		Path path = Paths.get(part.getSubmittedFileName());// Sacamos la ruta del archivo
		String filename = path.getFileName().toString();//Guardamos en la variable efilename el nombre del archivo/ruta
		//Generamos un buffer
		InputStream inpt = part.getInputStream();
		//Guardamos el archivo y lo metemos en la capeta.
		File file = new File (uploads,filename);
		//Copiamos los datos del archivo dentro de la carpeta utilizando el BUFFER.
		Files.copy(inpt,file.toPath());
		
	
		
		
//Creamos el objeto para Deporte.
		Deporte d1 = new Deporte (nombre,descripcion,telefono,direccion,filename);
		
		
// Insertqamos el deporte en el objeto.
		
		try {
			d1.insertarDep();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
