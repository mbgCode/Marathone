package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import modelo.Administrador;
import modelo.Miembro;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.DaoAdministrador;
import dao.DaoMiembro;

/**
 * Servlet implementation class SV_administrador
 */

//Añadimos el @MULTIPARTCON y EL WEBSERVLET SIN EL nombre del servlet. DARA conflicto ala hora de arrancar el servidor.
@WebServlet()
@MultipartConfig

public class SV_administrador extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
//Añadimos la ruta de ORIGEN de la carpera...De momento lo hacemos de manera local. Luego se hará con ruta a la base de datos.
		private String pathFiles = "C:\\Users\\mbgco\\git\\repository\\Proyecto_X\\src\\main\\webapp\\Fotos";
		
//Añadimos la clase FILE para poder introducir fotos en la bd.
//Hemos creado una carpeta en webapp llamada fotos que es donde vamos a guardarlas.
		private File uploads = new File (pathFiles);
		
		
			
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SV_administrador() {
        super();
     
    }

    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		PrintWriter out = response.getWriter();//Este es el objeto de salida. Para escribir datos de vuelta a la web.
		
		String op = request.getParameter("op");
		
		
		if (!op.equals("")) {//Si op es = "" es que se va a insertar un nuevo admin 
			int opcion = Integer.parseInt(request.getParameter("op")); //recogemos la op del form
			
			
			//Utilizamos el switch para los diferetes opciones
			switch (opcion) {
			
			
				case 1: {//op1 listar. Pintar en cliente del listado de todos los administradores
					try {
					
						DaoAdministrador dao = new DaoAdministrador();
						String resultado = dao.ListarJonson();
						out.print(resultado);
						
					} catch (SQLException e) {
						e.printStackTrace();
					}		
					break;
				}
				
				
				
				// op2 modificar (sacar los datos por el cliente, que se modificará por el doPost)
				case 2:{
					Administrador a = new Administrador();
				
					try {
						int id = Integer.parseInt(request.getParameter("idadministrador")); //recogemos el id del form
						a.modificarAdmin(id);
						out.print(a.dameJson());
						System.out.println(a.dameJson());
					} catch (SQLException e) {
						e.printStackTrace();
					}
					break;
				}
				
				case 3 :{
					Administrador a1 = new Administrador();
					int id = Integer.parseInt(request.getParameter("idadministrador"));
					
					try {
						
						a1.eliminar(id);
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			
			}
		}

				
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("administrador.html");//una vez insertado reedirigir al index de administrador.
		
		String opi = request.getParameter("op"); //recogemos la op del form
		
//Recogemos todos lo parametros desde el html. 
		String nombre = request.getParameter("nombre");
		String apellidos = request.getParameter("apellidos");
		String email = request.getParameter("email");
		String poblacion = request.getParameter("poblacion");
		String permiso = request.getParameter("permiso");
		int permi = Integer.parseInt(permiso);//Parseamos el String del parametro permiso a Integer.

			
		
//Para recibir foto.
		Part part = request.getPart("foto"); //recogemos los datos Binarios de foto
		Path path = Paths.get(part.getSubmittedFileName());// Sacamos la ruta del archivo
		String filename = path.getFileName().toString(); //Guardamos en la variable efilename el nombre del archivo/ruta	
		//Creamos el camino BUFFER para enviarlo.
		InputStream inspt = part.getInputStream();
		//Guardamos el archivo y lo metemos en la capeta.
		File file = new File (uploads,filename);
		//Copiamos los datos del archivo dentro de la carpeta utilizando el BUFFER.
		Files.copy(inspt,file.toPath());
		
		
	
//Creamos el objeto para administrador.
		Administrador a1 = new Administrador(nombre, apellidos, email, poblacion, permi, filename);
		String op = request.getParameter("op");//Instanciamos el String "op" para que no me de error en el server al parsear un null
		if (op != null) {
			int opcion = Integer.parseInt(request.getParameter("op")); //recogemos la op del formulñario
			System.out.println("llega al post la opcion : "+opcion);
		}
		
		
		
//Insertamos o hacemos Update en la clase Administrador.	
		String ids = request.getParameter("idadministrador");
		
//Insertamos nuevo admin.		
			try {
				if(ids.equals("")) {//Si el id es cadena vacia quiere decir que va a insertarse un admin nuevo.
					a1.insertarAdmin();
					
//Update de los datos a ingresar en la BD				
				}else {//si es diferente de "", significa que si vamos a modificar algun dato de un ID ya registrado.
					int id = Integer.parseInt(request.getParameter("idadministrador"));
						a1.setIdaministrador(id);//Insertamos el id en Administrador.
						a1.update();//Llamamos al update para poder modificar							
				}
	
			}catch (SQLException e) {
			e.printStackTrace();
			}
	
		
		
//Listar Administrador 
		DaoAdministrador lista;
			try {
				lista = new DaoAdministrador();
				ArrayList <Administrador> listarAdministrador = lista.listar();
			} catch (SQLException e) {
				e.printStackTrace();
			}			
	}
	
	
}
