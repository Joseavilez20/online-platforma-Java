/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dao.ContenidoDao;
import dao.CursoDao;
import dao.PersonaDao;
import dao.TemaDao;
import dao.deleteDirectory;
import entidades.Curso;
import entidades.Persona;
import entidades.Usuario;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Random;

/**
 *
 * @author joan0
 */
public class ServletCurso extends HttpServlet {
private static String lista_cursos = "../ListarCursos.jsp";
 private CursoDao  cursoDao;
 private PersonaDao personaDao;
 private ContenidoDao conDao;
 private ArrayList<Curso> cursos= new ArrayList<>();
 private TemaDao  temaDao;
private deleteDirectory dd;
 
 public ServletCurso()
 {
     cursoDao = new CursoDao();
     personaDao = new PersonaDao();
     conDao= new ContenidoDao();
     temaDao=  new  TemaDao();
     dd = new deleteDirectory();
 }
   /* protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        
        
        
    }*/
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String segmento="";
        String action = request.getParameter("action");
        if (action.equalsIgnoreCase("listarCursos")){
            segmento = lista_cursos;
           
            ArrayList<Curso> datos = cursoDao.ListaCursos();
            request.setAttribute("cursos", datos);
            segmento = "../ListarCursos.jsp";
            
           
           request.getRequestDispatcher("vistas/ListarCursos.jsp").forward(request, response);
        }
        if(action.equals("modificar")){
             int id = Integer.parseInt(request.getParameter("id"));
             response.getWriter().print(id);
            Curso c= cursoDao.buscarCurso(id);
             request.setAttribute("curso", c);
             request.getRequestDispatcher("vistas/modificarCurso.jsp").forward(request, response);
            
        }
        
        if(action.equals("detalles")){
            
            int id = Integer.parseInt(request.getParameter("id"));
            Usuario u= null;
             boolean confir=false; 
             
            if(request.getSession().getAttribute("user")!=null){
              u=  (Usuario) request.getSession().getAttribute("user");
              if(cursoDao.buscarSiIncritoCurso(id, u.getIdUser())) 
                 {
                      confir=true;
                      request.setAttribute("inscrito", confir);
                 }
            
            }
          
           
             
             
              request.setAttribute("segmento", "detallesCurso.jspf");
            Curso c= cursoDao.buscarCurso(id);
             request.setAttribute("curso", c);
             //request.setAttribute("videos",conDao.buscarContenidos(id) );/////
             request.setAttribute("temas",temaDao.temasDelCurso(id));
             request.getRequestDispatcher("WEB-INF/main/main.jsp").forward(request, response);
            
        }
        
        if(action.equals("addContenido")){

   int idcurso = Integer.parseInt(request.getParameter("idCurso"));
    String path = "subirContenido.jspf";
     request.setAttribute("path", path);
   request.setAttribute("idcurso", idcurso);
    if(temaDao.ListaTemas()!=null)
   {
       request.setAttribute("temas",temaDao.ListaTemas());
   }
    
    if(temaDao.temasDelCurso(idcurso)!=null)
   {
       request.setAttribute("temasCurso",temaDao.temasDelCurso(idcurso));
   }
    if(cursoDao.buscarCurso(idcurso)!=null){
        
        request.setAttribute("curso", cursoDao.buscarCurso(idcurso));
    }
    
  
   //response.getWriter().print("vistas/subir_videos.jsp");
   request.getRequestDispatcher("WEB-INF/admin/indexAdmin.jsp").forward(request, response);
          
}
         if(action.equals("infoCurso")){

   int idcurso = Integer.parseInt(request.getParameter("idCurso"));
   if(cursoDao.buscarCurso(idcurso)!=null){
    request.setAttribute("curso", cursoDao.buscarCurso(idcurso));
   }
   
   if(cursoDao.personasEnCurso(idcurso)!=null)
   {
        request.setAttribute("personas", cursoDao.personasEnCurso(idcurso));
   }
   
  
    String path = "infoCurso.jspf";
     request.setAttribute("path", path);
  
  
   //response.getWriter().print("vistas/subir_videos.jsp");
   request.getRequestDispatcher("WEB-INF/admin/indexAdmin.jsp").forward(request, response);
          
}
         
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       // processRequest(request, response);
       ////formulario curso<<<<<<<
       


       String action="";
        if(request.getParameter("Action")!=null )
       {
           action=request.getParameter("Action");
       
       
      
        
		
           if(action.equals("insertar")){
		// Obtengo los datos de la peticion
                      int idcurso= -1;

		String nombre = request.getParameter("nombre");
		String descripcion = request.getParameter("descripcion");
		String subtitulo = request.getParameter("subtitulo");
                String instructor =(request.getParameter("instructor"));
		String nivel = request.getParameter("nivel");
		Random random = new Random();
               
        idcurso= (int) random.nextLong();

                
                    
              
 //FALTA VALIDAR MAS
		// Compruebo que los campos del formulario tienen datos para añadir a la tabla
		if (!nombre.equals("") && !descripcion.equals("") && !subtitulo.equals("")  && !nivel.equals("")) {
			// Creo el objeto persona y lo añado al arrayList
			Curso curso = new Curso();
                        curso.setIdCurso(idcurso);
                        curso.setNombre(nombre);
                        curso.setDescripcion(descripcion);
                        curso.setInstructor(instructor);
                        curso.setSubTitulo(subtitulo);
                        curso.setNivel(nivel);
			cursos.add(curso);
                        if(cursoDao.insertar(curso)){
                        
                        response.getWriter().print("agregado exitoso");
                        }else
                        {
                             response.getWriter().print("error al agregar");
                        }
              // request.setAttribute("curso", curso);
            // request.getRequestDispatcher("subir_Contenido.jsp").forward(request, response);
              
		}else{
                    response.getWriter().print("error datosvacios");
                }

                
}
if(action.equals("borrar")){

int idcurso = Integer.parseInt(request.getParameter("idcurso"));
           //ServletCurso sc = new ServletCurso();
           String ruta ="contenido\\curso"+idcurso;
          if(cursoDao.borrar(idcurso)){
              File directory = new File("C:\\Users\\joan0\\desktop\\proyectoweb\\web\\"+ruta);
 
    	//make sure directory exists
    	if(!directory.exists()){
 
           response.getWriter().print("Directory does not exist.");
           //System.exit(0);
 
        }else{
 
           try{
        	   
              dd.delete(directory);
        	 response.getWriter().print("borrado exitoso del directorio");
           }catch(IOException e){
               e.printStackTrace();
               System.exit(0);
           }
        }
             
              response.getWriter().print("borrado exitoso");
          }else{
              response.getWriter().print("Error al eliminar");
              
          }
}


if(action.equals("actualizar")){
             int idCurso=Integer.parseInt(request.getParameter("idcurso"));
                String nombre = request.getParameter("nombre");
		String descripcion = request.getParameter("descripcion");
		String subtitulo = request.getParameter("subtitulo");
                String instructor = (request.getParameter("instructor"));
		String nivel = request.getParameter("nivel"); 
                //FALTA VALIDAR
                Curso curso = new Curso();
                curso.setIdCurso(idCurso);
                curso.setNombre(nombre);
                curso.setDescripcion(descripcion);
                curso.setInstructor(instructor);
                curso.setSubTitulo(subtitulo);
                curso.setNivel(nivel);
                if(cursoDao.actualizar(curso)){
                response.getWriter().print("actualizacion exitosa");
                }else{
                 response.getWriter().print("Error al actualizar");
                }
                    
                 
               
            
        }
if(action.equals("editarCurso"))
{   
        int  idCurso=  Integer.parseInt(request.getParameter("idCurso"));
        
      if(cursoDao.buscarCurso(idCurso)!=null)
      {
          //serializando un objeto Curso a JSON
          final Gson gson = new Gson();
	final String representacionJSON = gson.toJson(cursoDao.buscarCurso(idCurso));
        response.getWriter().print(representacionJSON);
      }
}

if(action.equals("inscribirseCurso"))
{ 
    String idUser=request.getParameter("idUser");
    int idCurso = Integer.parseInt(request.getParameter("idCurso"));
    if(!cursoDao.buscarSiIncritoCurso(idCurso, idUser)){
        if(cursoDao.inscribirEnCurso(idCurso, idUser)){

            response.getWriter().print("Inscrito");
        }else
        {
         response.getWriter().print("inscripcion fallida");
        }
    }else
    {
    response.getWriter().print("usted ya esta inscrito");
    }

    
}

if(action.equals("borrarUsuarioDeCurso")){

int idcurso = Integer.parseInt(request.getParameter("idcurso"));
String iduser =request.getParameter("iduser");
           //ServletCurso sc = new ServletCurso();
          if(cursoDao.borrarUsuarioDeCurso(idcurso,iduser)){
              response.getWriter().print("borrado exitoso");
          }else{
              response.getWriter().print("Error al eliminar");
              
          }
}


       }else
        {
        response.getWriter().print("ACTION ES NULA O VACIA");
        }
 
    
    }

    
   
    
 public ArrayList<Curso>  mostrarCursos(){
 return cursoDao.ListaCursos();
 }
 
// public Curso buscarCurso(int id)
// {
//   return ;
// }
  public ArrayList<Curso>   misCursos(String iduser){
 return cursoDao.misCursos(iduser);
 }
 
public  boolean inscritoCurso(int idCurso,String idUser )
{
return cursoDao.buscarSiIncritoCurso(idCurso, idUser);
}
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
   


    public static void main(String args[]){
    
        ServletCurso  p= new  ServletCurso();
       //System.out.print(p.misCursos("user-2").get(0).getNombre());
       
    }
  
}