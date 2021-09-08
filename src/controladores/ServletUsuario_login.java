/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import dao.CursoDao;
import dao.LoginDao;
import dao.PersonaDao;
import entidades.Curso;
import entidades.Persona;
import entidades.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import security.Crypto;

/**
 *
 * @author joan0
 */
public class ServletUsuario_login extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    private Usuario user;
    private LoginDao loginDao;
    private CursoDao cursoDao;
    private Crypto  crypto;
    private PersonaDao personaDao;
     
    public ServletUsuario_login()
    {
        loginDao=  new LoginDao();
        cursoDao = new CursoDao();
        crypto = new Crypto();
        personaDao = new PersonaDao();
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
        
        String url = "";
         response.setContentType("text/html;charset=UTF-8");
       
        String userPath = request.getServletPath();
        HttpSession   session  =request.getSession();
        Usuario u =  (Usuario) session.getAttribute("user");
         
        String segmento="";

        
        if (userPath.equals("/perfil")) {
            segmento="formUsuario.jspf";
            if(u!=null){
           userPath = "/indexUsuario";
           
            }else{
                response.sendRedirect("index.jsp");
            }
            request.setAttribute("segmento", segmento);
            

        
        } else if (userPath.equals("/inicio")) {
            // TODO: Implement cart page request

            userPath = "/perfil";
            
        } else if (userPath.equals("/logout")) {
            session  =request.getSession(false);
            if(session!=null)
            {
                session.removeAttribute("user");
                session.removeAttribute("persona");
                session.invalidate();
                response.sendRedirect("index.jsp");
            }
            // TODO: Implement checkout page request
                userPath="/index";
        // if user switches language
        } else if (userPath.equals("/cambiarPass")) {
            
            segmento="cambiarPass.jspf";
             if(u!=null){
           userPath = "/indexUsuario";
           
            }else{
                response.sendRedirect("index.jsp");
            }
                
                request.setAttribute("segmento", segmento);
        }

        // use RequestDispatcher to forward request internally
       
    url = "/WEB-INF/usuario" + userPath + ".jsp";

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
      //validar form
      response.setContentType("text/html;charset=UTF-8");
      
        /*if(session.getAttribute("idUser")!=null){
        response.sendRedirect("index.jsp");
        }*/
         final String iv = "0123456789123456"; // This has to be 16 characters
         final String secretKey = "secret key123";
      
       
        String action="";
      
        Usuario usuario=null;
      if(request.getParameter("action")!=null){
          
        action= request.getParameter("action");
        
      if(action.equals("login"))
      {
        String email= request.getParameter("email");
      
        String password= request.getParameter("password");
         String encriptPass =crypto.encrypt(password, iv, secretKey);
        //final String decryptedData = crypto.decrypt(encriptPass, iv, secretKey);
         
        usuario=loginDao.logear(email, encriptPass);
       if(usuario!= null )
       {
          
              HttpSession session = request.getSession();
          session.setAttribute("user", usuario); //////MANDAMOS EL OBJETO
        
           response.sendRedirect("ServletPersona");
          // request.getRequestDispatcher("ServletPersona").forward(request, response);
       }else
        {
            response.getWriter().print("<script>alert(error no se encontro);</script>");
       }
      }
      
      if(action.equals("registrar"))
      {
        String email= request.getParameter("email");
        String password= request.getParameter("password");
        String password2= request.getParameter("password2");
        if(password2.equals(password)){
         String idUser="";
        int dato=-1;
       
     
          
          if(loginDao.contarUsuarios()!=-1)
          {
             dato=loginDao.contarUsuarios()+1;
          }
          
           idUser="user-"+dato;
         
         String encript =crypto.encrypt(password, iv, secretKey);
          user= new Usuario();
          user.setEmail(email);
          
          user.setPassword(encript);
          user.setRol("estudiante");
          user.setIdUser(idUser);
       if( loginDao.insertar(user))
       {
           response.getWriter().print("se ha registrado existosamente!");
       }
         
          
      }else
          {
              response.getWriter().print("<h1>contraseñas no  iguales</h1>");
              
            }
        
      }
      
      if(action.equals("actualizarPass"))
      {
        String iduser= request.getParameter("iduser");
        String passwordActual= request.getParameter("passactual");
        String passwordNueva= request.getParameter("passnueva");
       
       
         String encript =crypto.encrypt(passwordActual, iv, secretKey);
          
          
       if( loginDao.buscarIdUser(iduser)!=null)
       { 
           if(loginDao.buscarIdUser(iduser).getPassword().equals(encript))
           {
               String encript2 =crypto.encrypt(passwordNueva, iv, secretKey);
              if( loginDao.actualizarPass(encript2 , iduser)){
                response.getWriter().print("se ha actualizado existosamente!");
              }else{
              response.getWriter().print("problema al guardar!");
              }
           }else{
              response.getWriter().print("errorpass");
              }
           
       }
         
          
      
        
      }
      
      if(action.equals("borrarUsuario")){
         String idUser = (request.getParameter("idUser"));
           //ServletCurso sc = new ServletCurso();
          if(loginDao.borrar(idUser)){
              response.getWriter().print("borrado exitoso");
          }else{
              response.getWriter().print("Error al eliminar");
              
          }
      }
                
      }else{
             response.getWriter().print("action nula!");

       }
    }
    
    public String random(){
    int valorEntero = (int) Math.floor(Math.random()*(1-1000+1)+1000);// Valor entre M y N, ambos incluidos.
    return String.valueOf(valorEntero);
    }
    
    public Usuario buscarUsuario(String id)
    {
        
       return loginDao.buscarIdUser(id);
    }
    
     public int contarUsuario(){
        return loginDao.contarUsuarios();
     }
      public int contarCursos2(){
        return (cursoDao.contarCursos2());
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
