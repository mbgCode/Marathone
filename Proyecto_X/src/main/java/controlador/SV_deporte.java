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
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

import dao.DaoDeporte;

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
		
		PrintWriter out = response.getWriter();//Este es el objeto de salida. Para escribir datos de vuelta a la web.
		
		String op = request.getParameter("op");//Recibimos el valor de op en tipo string
		System.out.println("op "+op);
		
		if (!op.equals("")) {//si op no es null se ejecuta 
			
		int opcion =Integer.parseInt(request.getParameter("op")) ;
		System.out.println("opcion" +opcion);
			
		switch (opcion) {
			case 1:{//Listar Deportes
				try {
					DaoDeporte dao = new DaoDeporte();
					String resultado = dao.ListarJonson();
					out.print(resultado);//Recibimos el listado.
				} catch (SQLException e) {
					// TODO Auto-generated catch blockA
					e.printStackTrace();
				}
				break;			
				
				
			}case 2 :{//UPdate por ID.
				int id = Integer.parseInt(request.getParameter("id"));
				Deporte d = new Deporte();
				try {
					d.modId(id);//Peticion de listado por id
					String resultado = d.dameJson();	
					out.print(resultado);//Devolucion de listado para pintar por id.
				}catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			break;
			
			
			}case 3:{//Borrar en la BD por ID
				int id = Integer.parseInt(request.getParameter("id"));		
				Deporte d = new Deporte();
				
				try {
					d.borrar(id);
					DaoDeporte dao = new DaoDeporte();
					String resultado = dao.ListarJonson();
					System.out.println(resultado);
					out.print(resultado);//Recibimos el listado.
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				break;
				
				
			}case 4:{//listar por categoria elegida.
				String cat = request.getParameter("categoria");
				System.out.println("has recogido categoria :" +cat);
				try {
					DaoDeporte dao = new DaoDeporte();
					String resultado = dao.ListarCatJonson(cat);
					out.print(resultado);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				break;
				
				
			}case 5:{//Pintar informacion por id. Igual que case2 
				int id = Integer.parseInt(request.getParameter("id"));
				System.out.println(id);
				Deporte d = new Deporte();
				
				
				try {
					d.modId(id);
					String resultado = d.dameJson();
					out.print(resultado);
					System.out.println(resultado);
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
		
		response.sendRedirect("insertarDep.html");
		
//Recogemos todos lo parametros desde el html. 
		String nombre = request.getParameter("nombre");
		String descripcion = request.getParameter("descripcion");
		int telefono = Integer.parseInt(request.getParameter("telefono"));
		String direccion = request.getParameter("direccion");
		String categoria = request.getParameter("categoria");
	
		
//Recibimos foto.
		Part part = request.getPart("foto");//recogemos los datos Binarios de foto
		Path path = Paths.get(part.getSubmittedFileName());// Sacamos la ruta del archivo
		String filename = path.getFileName().toString();//Guardamos en la variable filename el nombre del archivo/ruta
		//Generamos un buffer
		InputStream inpt = part.getInputStream();
		//Guardamos el archivo y lo metemos en la capeta.
		File file = new File (uploads,filename);
		//Copiamos los datos del archivo dentro de la carpeta utilizando el BUFFER.
		Files.copy(inpt,file.toPath());
				
		
//Creamos el objeto para Deporte.
		Deporte d1 = new Deporte(telefono, nombre, descripcion, telefono, direccion, filename, categoria);
		
		String ids = request.getParameter("id");
		
				
		
// Insertamos el deporte en el objeto.
		
		if (ids.equals("")) { //Si id es "" es que es un nuevo deporte 
			try {
				d1.insertarDep();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
		}else {//SI tiene id asignado quiere decir que es un update
			int id =Integer.parseInt(request.getParameter("id"));
				try {
					d1.setId(id);
					d1.update();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}		
		}
			
		
	}

}
