package controladores;

import dao.CursoDao;
import dao.PersonaDao;
import entidades.Curso;
import entidades.Persona;
import entidades.Usuario;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Peticiones
 */

public class Peticiones extends HttpServlet {
	private static final long serialVersionUID = 1L;
       CursoDao  cursosDao;
       PersonaDao personaDao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Peticiones() {
        super();
        cursosDao =  new CursoDao();
        personaDao =  new PersonaDao();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String path  ="", pagina="";
		path= request.getServletPath();
		
		
		if(path == null)
			path = "dashboard.jspf";
		
		switch(path)
		{
			case "/verCursos":
				//this.addToCart(request, response);
				//path="cursos";
                           ArrayList<Curso> cursos =cursosDao.ListaCursos();
                            request.setAttribute("cursos",cursos );
                           path = "cursos.jspf";
                         
			break;
			
			case "/Usuarios":
                            ArrayList<Persona> personas =personaDao.ListarPersonas();
                            request.setAttribute("personas",personas );
				path = "usuarios.jspf";
			break;
                        case "/addContenido":
                            int idcurso = Integer.parseInt(request.getParameter("idcurso"));
                             request.setAttribute("idcurso", idcurso);
                            
			     path = "subirContenido.jspf";
			break;
			
			case "/home":
			default:
				//this.browse(request, response);
				path ="dashboard.jspf";
			break;
		}
		
		
		request.setAttribute("path", path);
		request.getRequestDispatcher("WEB-INF/admin/indexAdmin.jsp").forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
	}

}
