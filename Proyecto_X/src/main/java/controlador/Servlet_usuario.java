package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Usuario;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.DaoUsuario;

/**
 * Servlet implementation class Servlet_usuario
 */
public class Servlet_usuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		
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
		
		
		
		String nombre=request.getParameter("nombre");
		
		//Creamos el objeto Usuario y lo insertamos en dao.
		Usuario u1 = new Usuario(nombre);
		
		
		
		
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
