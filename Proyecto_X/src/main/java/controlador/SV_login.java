package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelo.Administrador;
import modelo.Miembro;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

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
		String pass = getMD5(request.getParameter("pass"));//Recibimos los parametro de login.html
		
		Miembro m = new Miembro ();//Creamos un miembro "m".
		m.setEmail(email);
		
		Administrador a = new Administrador ();//Creamos "a" de administrador.
		a.setEmail(email);
		
		
		//protección (Según el tipo de permiso).
		try {
			if (m.logeo(pass)) {//Si son cierts la credenciales entramos en el programa.
				sesion = request. getSession(); //la inicializamos la sesion.
				sesion.setAttribute("id", m.getId());//En este caso vamos a guardar en la sesion el id y el permiso.
				sesion.setAttribute("permiso", m.getPermiso());
				int permiso=m.getPermiso();
				response.sendRedirect("index.html");//que nos envie a la pagina index par miembros

			}else if(a.logeo(pass)){//Si no son ciertas las credenciales buscamos si estan en la bd de administrador.
				sesion = request. getSession(); //la inicializamos la sesion.
				sesion.setAttribute("id", a.getIdaministrador());//en este caso vamos a guardar en la sesion el id y el permiso.
				sesion.setAttribute("permiso", a.getPermiso());
				int permiso=a.getPermiso();
				response.sendRedirect("indexAdmin.html");//nos envia a la pagina index par miembros.
				
			}else {
				response.sendRedirect("Login.html");
				System.out.println("el logeo es erroneo, vuelve a intentarlo");
			}
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	//Cifrado de contraseña
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
