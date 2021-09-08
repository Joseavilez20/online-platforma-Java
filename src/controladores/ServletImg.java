/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import dao.ContenidoDao;
import dao.CursoDao;
import entidades.Contenido;
import entidades.Curso;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author joan0
 */
//@WebServlet("/upload")
@MultipartConfig
public class ServletImg extends HttpServlet {

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
    private ContenidoDao contenidoDao;
    private CursoDao  cursoDao;
    public ServletImg (){
        contenidoDao = new  ContenidoDao();
        cursoDao =new CursoDao();
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, FileUploadException {
        
        response.setContentType("text/html;charset=UTF-8");
  

  String itemName="";
  String url="";
  String idCurso="";
  String idTema="";
  
  Contenido  c;
       boolean isMultipart = ServletFileUpload.isMultipartContent(request);
 if (!isMultipart) {
     
     
 } else {
     
	   FileItemFactory factory = new DiskFileItemFactory();
	   ServletFileUpload upload = new ServletFileUpload(factory);
	   List items = null;
	   try {
		   items = upload.parseRequest(request);
                   response.getWriter().println("<em>"+items.get(0).toString()+"</em>");
                   
	   } catch (FileUploadException e) {
		   e.printStackTrace();
	   }
	   Iterator itr = items.iterator();
	   while (itr.hasNext()) {
               
	   FileItem item = (FileItem) itr.next();
	   if (item.isFormField()) { //select ,text,number,submit,|radio|checkbox
               String name = item.getFieldName();
                String value = item.getString();
              //  response.getWriter().println("<em>"+name+"</em>");
                // response.getWriter().println("<em>"+ value+"</em>");
                if(item.getFieldName().equals("elegirtema")){
               idTema=item.getString();
                response.getWriter().println(" idTema: "+idTema);
           }   
                 if(item.getFieldName().equals("idCurso")){
               idCurso=item.getString();
                response.getWriter().println(" idCurso: "+idCurso);
           }
           
             
                 
	   }  
            else{
               c = new Contenido();
              
		   try {
                       
			    itemName = item.getName();
                          String ruta="contenido\\curso"+idCurso+"\\tema"+idTema;
                            //File savedFile = new File("c:/videos/"+itemName);
			   File path = new File("C:\\Users\\joan0\\desktop\\proyectoweb\\web\\"+ruta);
                           //File savedFile = new File(request.getServletContext().getRealPath("/")+"uploadedFiles/"+itemName.substring(itemName.lastIndexOf("\\")+1));
			   if (!path.exists()) {
                                boolean status = path.mkdirs();
                                response.getWriter().print("path!creado ");
                             }
                           
                            File uploadedFile = new File(path + "/" + itemName);
                           //InputStream input = request.getServletContext().getResourceAsStream("/");
                          //String result = IOUtils.toString(input, StandardCharsets.UTF_8);
                          if(itemName!=null)
                          {
                              url=ruta+"/"+itemName;
                          }else
                          {
                              response.getWriter().print("upps! itemName");
                          }
                           
                           
                          //response.getWriter().print(itemName);                      
                           
			 if(!itemName.equals("")  && !url.equals("") && !idCurso.equals("") && !idTema.equals("") )
                            {
                                url =(ruta+"/"+itemName);//
                                c.setIdCurso(Integer.parseInt(idCurso));
                         if (itemName.contains(".")) {
                            String[] parts = itemName.split(Pattern.quote(".")); // Split on period.
                            c.setTitulo(parts[0]);
                                } else {
                                    c.setTitulo(itemName);
                                    throw new IllegalArgumentException("String " + itemName + " does not contain -");
                                }
                              // String []  parts  = itemName.split(".");//solo me  guarda el nombre
                                
                                c.setUrl(url);
                                c.setIdTema(Integer.parseInt(idTema));
                        if(contenidoDao.insertar(c)){
                            item.write(uploadedFile); 
                             response.getWriter().println("idTema: "+idTema+" idCurso: "+idCurso); 
                              // response.getWriter().println("<tr><td><b>Your file has been saved at the loaction:</b></td></tr><tr><td><b>"+request.getServletContext().getRealPath("/")+"uploadedFiles"+"\\"+itemName+"</td></tr>");
                            
                        }else{
                             response.getWriter().println("fail db insertarcontenido"); 
                        }
                        
            
                        }else{
                             response.getWriter().println("fail datos"); 
                             }
           
			   } catch (Exception e) {
			   e.printStackTrace();
		   }
	   }
           
          
          
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
        
             response.setContentType("text/html;charset=UTF-8");
        PrintWriter out =response.getWriter();
        String action = request.getParameter("action");
     if(action.equals("vervideos")){
         ArrayList<Contenido> con =null; 
         
         if(contenidoDao.ListaContenido()!=null){
         con=contenidoDao.ListaContenido();
         
             //out.print("ok array");
             //out.print(contenidoDao.ListaContenido().get(i).getUrl());
               // out.print(contenidoDao.ListaContenido().get(i).getTitulo());
         //
         //out.print("<video id=\"\" width=\"320\">");
         
         //out.print("<source src=\""+contenidoDao.ListaContenido().get(i).getUrl()+"\" type='video/mp4'>");
          // out.print(" Your browser does not support HTML5 video.  </video>");
         request.setAttribute("contenidos", con);
             request.getRequestDispatcher("subir_Contenido.jsp").forward(request, response);
         }else{
         out.print("null array");
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
        try {
            processRequest(request, response);
        } catch (FileUploadException ex) {
            Logger.getLogger(ServletImg.class.getName()).log(Level.SEVERE, null, ex);
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

}
