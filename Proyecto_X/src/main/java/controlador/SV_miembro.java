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
	
			PrintWriter out = response.getWriter();//Este es el objeto de salida. Para escribir datos de vuelta a la web.
			sesion = request.getSession();
			String op = request.getParameter("op");
			
			
			if (!op.isEmpty()) {
				int opcion = Integer.parseInt(request.getParameter("op"));
				
				switch (opcion) {
				
					case 1:{//listar.
						try {
							DaoMiembro dao = new DaoMiembro();
							String resultado = dao.listarJonson();
							out.print(resultado);
							
						} catch (SQLException e) {
							System.out.println("Error en case 1 de SV_miembro");
							e.printStackTrace();
						}
						
					break;
					}
					
						
					case 2: { //Modificar.
							int id =Integer.parseInt(request.getParameter("idmiembro")) ;
							Miembro m = new Miembro();
							try {
								m.listPorId(id);
								String resultado = m.dameJson();
								out.print(resultado);
								System.out.println(resultado);
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
												
						break;
					}
					
						//Case 3 eliminado por desuso
					
					
						case 4:{//Sección eliminar miembro por id.
						
						Miembro m1 = new Miembro();
						int id = Integer.parseInt(request.getParameter("idmiembro"));
							try {							
								m1.eliminar(id);	
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					break;
						
							
					}case 5:{//Pedimos el id para insertar la foto del usuario en el index.
						Miembro m = new Miembro ();
						
						int idsesion = (int)sesion.getAttribute("id");
						
						try {
							m.foto(idsesion);
							String resultado = m.fotoJson();
							System.out.println("el resultado es "+resultado);
							out.print(resultado);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					break;
							
						
					}case 6:{//Cerrar sesión
						sesion.invalidate();
						System.out.println("sesion cerrada del miembro");
						
						break;
					}
			}			
		}			
	} 
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Obtenemos de donde viene es formulario para enviar a un lado u otro.
		String proviene =request.getParameter("proviene");
		
		if("registro1".equals(proviene)){
			response.sendRedirect("Login.html");
			
		}else {
			response.sendRedirect("ListarMiembro.html");// Una vez enviado los datos del formulario redirigir al index miembro.

		}
			
		String nombre = request.getParameter("nombre");
		String apellidos = request.getParameter("apellidos");
		String email = request.getParameter("email");
		String poblacion = request.getParameter("poblacion");
		String edad = request.getParameter("edad");
		int edadint = Integer.parseInt(edad);
		String pass = request.getParameter("pass");
		
		
		//Fotos
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

		
		//Creamos el objeto Usuario y lo insertamos en dao.
		//filename es para la foto
		Miembro m1 = new Miembro(nombre, apellidos, email, poblacion, filename, edadint, pass);		
	
		//Listar miembro
		DaoMiembro lista;
			try {
				lista = new DaoMiembro();
				ArrayList <Miembro> listarMiembro = lista.listar();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
				
		//Insertar nuevo usuario y update.			
		String ids= request.getParameter("idmiembro");
		System.out.println("esto es el ids>"+ids+"<");

		//Isertar nuevo miembro	
		if(ids==null|| ids.isEmpty()) {
			try {
				m1.insertarMiembro();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			
		//update miembro ya registrado	
		}else {
			int id = Integer.parseInt(request.getParameter("idmiembro"));
			try {
				m1.setId(id);
				m1.update();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
