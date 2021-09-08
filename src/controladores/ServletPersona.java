/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import com.google.gson.Gson;
import dao.LoginDao;
import dao.PersonaDao;
import entidades.Persona;
import entidades.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import security.Crypto;

/**
 *
 * @author joan0
 */
@WebServlet(name = "ServletPersona", urlPatterns = {"/ServletPersona"})
public class ServletPersona extends HttpServlet {

    private PersonaDao personaDao;
    private LoginDao loginDao;
    private Persona p;
    ArrayList<String>  personas;
    private Crypto  crypto;
    
    public  ServletPersona(){
       personaDao = new PersonaDao(); 
       loginDao =new LoginDao();
       crypto = new Crypto();
       
     
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session=request.getSession();
       
        
       Usuario u= (Usuario) session.getAttribute("user");
        
        if(u!=null){
            try{
                
          
             ArrayList<String> personas=personaDao.personaUsuario(u.getIdUser());
             if(personas!=null){
             session.setAttribute("persona",personas );
              
             }
              
            
            
            }catch(NullPointerException e){
                response.getWriter().print("valor Nulo ");
            }
        }
      //  response.getWriter().print("idUser:"+id);
      response.sendRedirect("index.jsp");
      //  request.getRequestDispatcher("WEB-INF/usuario/indexUsuario.jsp").forward(request, response);
        
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
        final String iv = "0123456789123456"; // This has to be 16 characters
        final String secretKey = "secret key123"; 
       HttpSession   session  =request.getSession();
       String action="";
       
      //se quito idUser!=null  isEmpty..
        if(request.getParameter("action")!=null )
        {
        action=request.getParameter("action");
        
         
        
        if(action.equals("insertar")){
            String idUser=request.getParameter("iduser");
               
            String nombre =request.getParameter("nombre");
            String ape=request.getParameter("apellido");
            String acerca=request.getParameter("acerca");
            
            //String email=request.getParameter("email");
            Persona p = new Persona();
            p.setIdUser(idUser);
            p.setNombre(nombre);
            p.setApellido(ape);
            p.setAcerca(acerca);
            
            if(personaDao.insertar(p))
            {
                 
            
                
                session.removeAttribute("persona");
                ArrayList<String> personas=personaDao.personaUsuario(idUser);
             if(personas!=null){
             session.setAttribute("persona",personas );
              
             }
             
            
               // response.getWriter().print("agregado exitosamente");
                
                response.sendRedirect("index.jsp");
                
            }else
            {
                 response.getWriter().print("ocurrio un problema con la db");
            }
           
        
        }
        
        if(action.equals("agregarUsuario")){
           // String idUser=request.getParameter("iduser");
            
            String nombre =request.getParameter("nombre");
            String ape=request.getParameter("apellido");
            String acerca=request.getParameter("acerca");
            String email =request.getParameter("email");
            String password =request.getParameter("password");
            String encriptPass =crypto.encrypt(password, iv, secretKey);
            String ro=request.getParameter("rol");
            
            
            Persona p = new Persona();
             Usuario u = new Usuario();
             String id="";
        int dato=-1;
        
          if(loginDao.contarUsuarios()!=-1)
          {
             dato=loginDao.contarUsuarios()+1;
          }
          
           id="user-"+dato;
            p.setIdUser(id);
            p.setNombre(nombre);
            p.setApellido(ape);
            p.setAcerca(acerca);
            u.setIdUser(id);
            u.setEmail(email);
            u.setPassword(encriptPass );
            u.setRol(ro);
            
            if(personaDao.insertar(p) && loginDao.insertar(u))
            {
            response.getWriter().print("agregado exitosamente");
                
             //   response.sendRedirect("index.jsp");
                
            }else
            {
                 response.getWriter().print("ocurrio un problema con la db");
            }
           
        
        }
   
        if(action.equals("actualizar")){
            String idUser=request.getParameter("iduser");
                String nombre =request.getParameter("nombre");
            String ape=request.getParameter("apellido");
            String acerca=request.getParameter("acerca");
            
            //String email=request.getParameter("email");
            Persona p = new Persona();
            p.setIdUser(idUser);
            p.setNombre(nombre);
            p.setApellido(ape);
            p.setAcerca(acerca);
            
            if(personaDao.actualizar(p))
            {
            session.removeAttribute("persona");
            ArrayList<String> personas=personaDao.personaUsuario(idUser);
             if(personas!=null){
             session.setAttribute("persona",personas );
              
             }
             
                
                response.sendRedirect("index.jsp");
            }  
          
                       /* out.print();
                        out.flush();*/
                       
                    
        
        }
        
        if(action.equals("actualizarUsuario")){
            String encriptPass="";
              String idUser=request.getParameter("iduser");
                String nombre =request.getParameter("nombre");
            String ape=request.getParameter("apellido");
            String acerca=request.getParameter("acerca");
            String email  = request.getParameter("email");
            String password  =request.getParameter("password");
            String mipassword  =request.getParameter("miPassword");
            if(password!=null && password!=""){
                  encriptPass =crypto.encrypt(password, iv, secretKey);
            }else{
            String descriptarPass = crypto.decrypt(mipassword, iv, secretKey);
            encriptPass =crypto.encrypt(descriptarPass, iv, secretKey);
            }
           
            String rol  =  request.getParameter("rol");
            
            //String email=request.getParameter("email");
            Persona p = new Persona();
            Usuario u  =  new Usuario();
            p.setIdUser(idUser);
            p.setNombre(nombre);
            p.setApellido(ape);
            p.setAcerca(acerca);
            u.setIdUser(idUser);
            u.setEmail(email);
            u.setPassword(encriptPass);
            u.setRol(rol);
            if(personaDao.actualizar(p)&& loginDao.actualizar(u)){
             response.getWriter().print("agregado exitosamente");
                
             //   response.sendRedirect("index.jsp");
                
            }else
            {
                 response.getWriter().print("ocurrio un problema con la db");
            }
            
        
        }
        
        if(action.equals("editarUsuario"))
{   
        String  idUser= (request.getParameter("idUser"));
        
      if(personaDao.personaUsuario(idUser)!=null)
      {
          //serializando un objeto Curso a JSON
          final Gson gson = new Gson();
	final String representacionJSON = gson.toJson(personaDao.personaUsuario(idUser));
        response.getWriter().print(representacionJSON);
      }
}
          /*if(action.equals("infoInstructor")){
              
          int  idInstructor = Integer.parseInt(request.getParameter("idInstructor"));
          
          
          
         if(personaDao.buscarPersona(idInstructor)!=null)
          {
               //serializando un objeto Curso a JSON
          final Gson gson = new Gson();
	final String representacionJSON = gson.toJson(personaDao.buscarPersona(idInstructor));
              response.getWriter().print(representacionJSON);
          }else{
              response.getWriter().print("persona null");
          }
          
          }
        */
        
     
       }else{ response.getWriter().print("actio2 null");} 
    }
    
    
    public Persona buscarPersona(int id)
    {
       // int id2 = Integer.parseInt();
       return personaDao.buscarPersona(id);
    }
    
     public ArrayList<String> buscarUserPersona(String id)
    {
      
       return personaDao.personaUsuario(id);
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
