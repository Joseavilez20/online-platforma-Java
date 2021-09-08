/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import com.google.gson.Gson;
import dao.TemaDao;
import dao.deleteDirectory;
import entidades.Tema;
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
public class ServletTema extends HttpServlet {
    private TemaDao  temaDao;
    private deleteDirectory dd;
    
    public ServletTema(){
    temaDao  = new TemaDao();
    dd = new deleteDirectory();
    }
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action =   request.getParameter("action");
        if(action.equals("registrarT"))
        {
            String nombre=request.getParameter("nombre");
            String idCurso =request.getParameter("idCurso");
            Tema t  = new Tema();
            t.setIdCurso(Integer.parseInt(idCurso));
            t.setNombre(nombre);
          if(temaDao.insertar(t)){
              response.getWriter().print("registro  exitoso");
          }else{
              response.getWriter().print("error de registro");
          }
        }
        
        if(action.equals("rellenarSelect")){
            //serializando un objeto Curso a JSON
          final Gson gson = new Gson();
	final String representacionJSON = gson.toJson(temaDao.ListaTemas());
        response.getWriter().print(representacionJSON);
        }
        if(action.equals("borrarTemaDeCurso")){
         
         int idCurso =Integer.parseInt(request.getParameter("idcurso"));
         int idTema = Integer.parseInt(request.getParameter("idtema"));
         String ruta ="contenido\\curso"+idCurso+"\\tema"+idTema;
                 if(temaDao.borrarTemaCurso(idTema, idCurso))
                 {
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
                     response.getWriter().print("Error borrado");
                 
                 }
       
        }
        
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
        processRequest(request, response);
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
        processRequest(request, response);
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
    
    public ArrayList<Tema> buscarTemaCurso(int idCurso)
    {
        return temaDao.temasDelCurso(idCurso);
    }

}
