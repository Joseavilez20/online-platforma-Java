/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import dao.ContenidoDao;
import dao.CursoDao;
import entidades.Contenido;
import entidades.Usuario;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author joan0
 */
public class ServletContenido extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * 
     */
    
    private ContenidoDao conDao;
    private CursoDao cursoDao;
    
    public  ServletContenido(){
        
        conDao =  new ContenidoDao();
        cursoDao  =  new CursoDao();
    }
  

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
        String action  =request.getParameter("action");
       if(action.equals("verVideo"))
       {
           int  idCurso=-1;
           int idContenido=-1;
           if(request.getSession().getAttribute("user")!=null){
               Usuario u= (Usuario) request.getSession().getAttribute("user");
               
           if(request.getParameter("idCurso")!=null && request.getParameter("idContenido")!=null){
           idCurso = Integer.parseInt(request.getParameter("idCurso"));
           idContenido = Integer.parseInt(request.getParameter("idContenido"));
           if(cursoDao.buscarSiIncritoCurso(idCurso, u.getIdUser())){
           Contenido contenido= (Contenido) conDao.verContenido(idCurso, idContenido);
           
           
           
           
          if(contenido!=null){
              //
              String segmento="verContenido.jspf";
              request.setAttribute("contenido",conDao.verContenido(idCurso, idContenido));
              request.setAttribute("segmento",segmento );
              
            request.getRequestDispatcher("WEB-INF/main/main.jsp").forward(request, response);
          }else{
                response.getWriter().print("no se encontro contenido");
          }
          
           }else{
               
            request.getRequestDispatcher("WEB-INF/main/main.jsp").forward(request, response);
           }
           }
       }else{
           
                
             //response.getWriter().print("<script>alert('no registrado')</script>");  
             
           request.getRequestDispatcher("WEB-INF/main/main.jsp").forward(request, response);
           }
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
     
        String action  =  request.getParameter("action");
        if(action.equals("borrarContenidoDeCurso"))
        {   
            int idcurso =Integer.parseInt(request.getParameter("idcurso"));

            int idcontenido =Integer.parseInt(request.getParameter("idcontenido"));
            int idtema =Integer.parseInt(request.getParameter("idtema"));
            String ruta =(request.getParameter("ruta"));
            
            if(conDao.borrarContenidoDeCurso(idcurso, idtema, idcontenido)){
                File f=new File("C:\\Users\\joan0\\desktop\\proyectoweb\\web\\"+ruta);
                if(f.delete()){response.getWriter().print("Borrado  localmente");}
                response.getWriter().print("Borrado");
            }else{
             response.getWriter().print("Error al Borrar");
            }
        }
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
    
    public ArrayList<Contenido> contenidosDelCurso(int idCurso)
    {
       return conDao.buscarContenidos(idCurso);
    }
    public ArrayList<Contenido> buscarContenidosdelTema(int idCurso,int idTema)
    {
       return conDao.buscarContenidosdelTema(idCurso,idTema);
    }
    
}
