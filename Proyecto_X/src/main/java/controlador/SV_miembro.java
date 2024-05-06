package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import modelo.Miembro;
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

import dao.DaoMiembro;
import dao.DaoUsuario;

/**
 * Servlet implementation class Servlet_usuario
 */


//Añadimos el @MULTIPARTCON y EL WEBSERVLET SIN EL nombre del servlet. DARA conflicto ala hora de arrancar el servidor.
@WebServlet()
@MultipartConfig

public class SV_miembro extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	
	//Añadimos la ruta de ORIGEN de la carpera...De momento lo hacemos de manera local. Luego se hará con ruta a la base de datos.
	private String pathFiles = "C:\\Users\\mbgco\\git\\repository\\Proyecto_X\\src\\main\\webapp\\Fotos";
	
	
	//Añadimos la clase FILE para poder introducir fotos en la bd.
	//Hemos creado una carpeta en webapp llamada fotos que es donde vamos a guardarlas.
	private File uploads = new File (pathFiles);
	
	
	//Se utiliza para la sesión. Se crea esto para su instnciación.
	HttpSession sesion;
	
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SV_miembro() {
        super();      
    }

    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
//Abrir a la sesión.		
		sesion = request.getSession();//desde aqui ya si se puede acceder al HttpSession.
		
		
		int idSesion = (int)sesion.getAttribute("id");//Esto puede venir de un formulario con un request.getparameter
	
		if (idSesion != 0) {//Si idSesion es distinto de 0 se podra ejecutar el programa
		
			
			int opcion = Integer.parseInt(request.getParameter("op"));
			PrintWriter out = response.getWriter();//Este es el objeto de salida. Para escribir datos de vuelta a la web.
			System.out.println("la opcion es " +opcion);
			
			switch (opcion) {
			
				case 1:{//listar en html.
					try {
						DaoMiembro dao = new DaoMiembro();
						String resultado = dao.listarJonson();
						out.print(resultado);
						response.sendRedirect("miembro.html");
						
					} catch (SQLException e) {
						System.out.println("Error en case 1 de SV_miembro");
						e.printStackTrace();
					}
					break;
				}
				
				
				case 2: { //Modificar...
					
					break;
				}
				
				
				case 3: { //listar por tipo
					int tipo = Integer.parseInt(request.getParameter("tipoUsuario"));
						try {
							DaoMiembro dao = new DaoMiembro();
							String resultado = dao.listarJonsonTipo(tipo);
							out.print(resultado);
							dao.listarTipo(tipo);
						} catch (SQLException e) {
							System.out.println("Error en case 3 de SV_miembro");
							e.printStackTrace();
						}
					
					break;
				}
	
			}
			
		}else {
			System.out.println("No PUEDES PASARRRR");
			response.sendRedirect("miembro.html");
		}
		
		
		
		
	} 
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("miembro.html");// Una vez enviado los datos del formulario redirigir al index miembro.

		String nombre = request.getParameter("nombre");
		String apellidos = request.getParameter("apellidos");
		String email = request.getParameter("email");
		String poblacion = request.getParameter("poblacion");
		String permiso = request.getParameter("permiso");//recibimos un tipo String
		int permiss=Integer.parseInt(permiso);//lo cambiamos a int para poder insertarlo en su clase.
		//String id = request.getParameter("id");
		//int idint = Integer.parseInt(id);
		String edad = request.getParameter("edad");
		int edadint = Integer.parseInt(edad);
		String pass = request.getParameter("pass");
		
		
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
		Miembro m1 = new Miembro(nombre, apellidos, email, poblacion, permiss, filename, edadint, pass);
		
		
		
//Isertar Usuario
		try {
			m1.insertarMiembro();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	
//Listar miembro
		DaoMiembro lista;
			try {
				lista = new DaoMiembro();
				ArrayList <Miembro> listarMiembro = lista.listar();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		
	
	
	}

}
