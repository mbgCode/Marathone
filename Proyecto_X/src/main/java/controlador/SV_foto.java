package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Servlet implementation class SV_foto
 */
//Añadimos el @MULTIPARTCON y EL WEBSERVLET SIN EL nombre del servlet. DARA conflicto ala hora de arrancar el servidor.
@WebServlet()
@MultipartConfig
public class SV_foto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	//Añadimos la ruta de ORIGEN de la carpera...De momento lo hacemos de manera local. Luego se hará con ruta a la base de datos.
	private String pathFiles = "C:\\Users\\mbgco\\git\\repository\\Proyecto_X\\src\\main\\webapp\\Fotos";
	//Añadimos la clase FILE para poder introducir fotos en la bd.
	//Hemos creado una carpeta en webapp llamada fotos que es donde vamos a guardarlas.
	private File uploads = new File (pathFiles);
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SV_foto() {
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
		
		
		//Para recibir foto.
				Part part = request.getPart("foto"); //recogemos los datos Binarios de foto
				Path path = Paths.get(part.getSubmittedFileName());// Sacamos la ruta del archivo
				String filename = path.getFileName().toString(); //Guardamos en la variable efilename el nombre del archivo/ruta
				
				//Creamos el camino BUFFER para enviarlo.
				InputStream inspt = part.getInputStream();
				//Guardamos el archivo y lo metemos en la capeta.
				File file = new File (uploads,filename);
				
				Files.copy(inspt,file.toPath());
	}
	
	public 
	

}
