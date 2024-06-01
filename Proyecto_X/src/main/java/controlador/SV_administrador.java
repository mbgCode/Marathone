package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import modelo.Administrador;
import modelo.Miembro;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.DaoAdministrador;
import dao.DaoMiembro;

/**
 * Servlet implementation class SV_administrador
 */

//Añadimos el @MULTIPARTCON y EL WEBSERVLET SIN EL nombre del servlet. Da conflicto ala hora de arrancar el servidor.
@WebServlet()
@MultipartConfig

public class SV_administrador extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
		private String pathFiles = "C:\\Users\\mbgco\\git\\repository\\Proyecto_X\\src\\main\\webapp\\Fotos";
		
		//Añadimos la clase FILE para poder introducir fotos en la bd.
		//Hemos creado una carpeta en webapp llamada fotos que es donde vamos a guardarlas.
		private File uploads = new File (pathFiles);
		
		//Se utiliza para la sesión. Se crea esto para su instnciación.
		HttpSession sesion;
		
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
		
		sesion = request.getSession();
		PrintWriter out = response.getWriter();
		String op = request.getParameter("op");
		
		
		if (!op.equals("")) {//Si op es = "" es que se va a insertar un nuevo admin. 
			int opcion = Integer.parseInt(request.getParameter("op")); //recogemos la op del form.
			
			
			//Utilizamos el switch para los diferetes opciones.
			switch (opcion) {
			
			
				//OP1 Listar.
				case 1: {
					try {
						DaoAdministrador dao = new DaoAdministrador();
						String resultado = dao.ListarJonson();
						out.print(resultado);		
					}catch (SQLException e) {
						e.printStackTrace();
					}		
				break;
				}
				
				
				// OP2 modificar (sacar los datos por el cliente. Se modificará por el doPost).
				case 2:{
					try {
						int id = Integer.parseInt(request.getParameter("idadministrador")); //recogemos el id del form
						Administrador a = new Administrador();
						a.modificarAdmin(id);
						String resultado = a.dameJson();
						out.print(resultado);
					} catch (SQLException e) {
						e.printStackTrace();
					}
					break;
				}
				
				
				//OP3 Eliminar adnministrador.
				case 3 :{
					Administrador a1 = new Administrador();
					int id = Integer.parseInt(request.getParameter("idadministrador"));
					try {
						a1.eliminar(id);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				break;
					
				
				//OP4 Añadir foto por id al header.	
				}case 4:{
					Administrador a = new Administrador ();
					int idsesion = (int)sesion.getAttribute("id");
					try {
						a.foto(idsesion);
						String resultado = a.fotoJson();
						out.print(resultado);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				break;
					
				
				//OP5 Cerrar sesión.
				}case 5:{
					sesion.invalidate();
					System.out.println("sesion cerrada del Admin");
	
				break;	
				}
			}
		}		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("indexAdmin.html");//una vez insertado reedirigir al index de administrador.
		
		String opi = request.getParameter("op"); //recogemos la op del form
		
		//Recogemos todos lo parametros desde el html. 
		String nombre = request.getParameter("nombre");
		String apellidos = request.getParameter("apellidos");
		String email = request.getParameter("email");
		String poblacion = request.getParameter("poblacion");
		String pass =getMD5( request.getParameter("pass"));
		System.out.println("pass es" +pass);

			
		//Para recibir foto.
		Part part = request.getPart("foto"); //recogemos los datos Binarios de foto.
		Path path = Paths.get(part.getSubmittedFileName());// Sacamos la ruta del archivo.
		String filename = path.getFileName().toString(); //Guardamos en la variable efilename el nombre del archivo/ruta.	
		//Creamos el camino BUFFER para enviarlo.
		InputStream inspt = part.getInputStream();
		//Guardamos el archivo y lo metemos en la capeta.
		File file = new File (uploads,filename);
		//Copiamos los datos del archivo dentro de la carpeta utilizando el BUFFER.
		Files.copy(inspt,file.toPath());
		
	
		//Creamos el objeto para administrador.
		Administrador a1 = new Administrador(nombre, apellidos, email, poblacion, filename, pass);
		String op = request.getParameter("op");//Instanciamos el String "op" para que no me de error en el server al parsear un null
		if (op != null) {
			int opcion = Integer.parseInt(request.getParameter("op")); //recogemos la op del formulñario
			System.out.println("llega al post la opcion : "+opcion);
		}
		
		
		//Insertamos o hacemos Update en la clase Administrador.	
		String ids = request.getParameter("idadministrador");
		System.out.println("este es el valor de ids"+ids);
		
		
			//Insertamos nuevo admin.		
			try {
				if (ids.isEmpty()) {//Si el id es cadena vacia quiere decir que va a insertarse un admin nuevo.
					a1.insertarAdmin();
				//Update de los datos a ingresar en la BD				
				}else {//si es diferente de "", significa que si vamos a modificar algun dato de un ID ya registrado.
					int id = Integer.parseInt(request.getParameter("idadministrador"));
						;
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
	
	public static String getMD5(String pass) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(pass.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);

            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}