/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import dao.CursoDao;
import entidades.Curso;
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
public class ServletLinker extends HttpServlet {
    
    private CursoDao cursoDao;

    
    public  ServletLinker(){
    
    cursoDao =new  CursoDao();}
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
  

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
        String url = "";
         response.setContentType("text/html;charset=UTF-8");
       
        String userPath = request.getServletPath();
        
        
        String segmento="";

        // if category page is requested
        if (userPath.equals("/Cursos")) {
            segmento="Cursos.jspf";
           userPath = "/main";
           
            ArrayList<Curso> datos = cursoDao.ListaCursos();
            
            request.setAttribute("cursos", datos);
            request.setAttribute("segmento", segmento);
            

        // if cart page is requested
        } else if (userPath.equals("/inicio")) {
            // TODO: Implement cart page request

            userPath = "/main";

        // if checkout page is requested
        }  else if (userPath.equals("/SS")) {
            

        }

        // use RequestDispatcher to forward request internally
       
    url = "/WEB-INF/main" + userPath + ".jsp";

        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
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

}
