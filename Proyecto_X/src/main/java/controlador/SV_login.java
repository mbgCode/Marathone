package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelo.Miembro;

import java.io.IOException;

/**
 * Servlet implementation class SV_login
 */
public class SV_login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	HttpSession sesion; //Instanciamos el http sesion.
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SV_login() {
        super();
        // TODO Auto-generated constructor stub
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

		
		String email = request.getParameter("email");//Recibimos los parametro de login.html
		String pass = request.getParameter("pass");//Recibimos los parametro de login.html
		
		Miembro m = new Miembro ();//Creamos un miembro "m"
		
		m.setEmail(email);
		
		//protección
		
		if (true) {
			
			sesion = request.getSession(); //la inicializamos la sesion.
			
			sesion.setAttribute("id", m.getId());//En este caso vamos a guardar en la sesion el id y el permiso.
			sesion.setAttribute("permiso", m.getPermiso());
			
			response.sendRedirect("insertarDeporte.html");// que nos envie a alguna pagina concreta.
			
		}else {
			response.sendRedirect("Login.html");
		}
		
	}

}
