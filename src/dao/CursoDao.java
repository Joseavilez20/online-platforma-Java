/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.Curso;
import entidades.Persona;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author joan0
 */
public class CursoDao {
   
    private Connection connection;
    public CursoDao()
    {
        connection= Conexion.getConection();
    }
    
    public ArrayList<Curso> ListaCursos() {
        ArrayList<Curso> arrayCursos= new ArrayList<Curso>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from curso");
            while (rs.next()) {
                Curso c = new Curso();
                c.setIdCurso(rs.getInt("id_curso"));
                c.setNombre(rs.getString("nombre"));
               c.setNivel(rs.getString("nivel"));
                c.setDescripcion(rs.getString("descripcion"));
                c.setSubTitulo(rs.getString("subtitulo"));
                c.setInstructor(rs.getString("instructor"));
               
               
               
               arrayCursos.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
 
        return arrayCursos;
    }
    
    public boolean insertar(Curso c){
        boolean confir =false;
    try{
        PreparedStatement preparedStatement = connection.prepareStatement("insert into curso(id_curso,nombre,subtitulo,nivel,descripcion,instructor) values(?,?,?,?,?,?)");
        preparedStatement.setInt(1, c.getIdCurso()); 
        preparedStatement.setString(2, c.getNombre());
          preparedStatement.setString(3, c.getNivel());
           preparedStatement.setString(4, c.getDescripcion());
           preparedStatement.setString(5, c.getSubTitulo() );
            preparedStatement.setString(6, c.getInstructor());
             
             
              if(preparedStatement.executeUpdate()==1)
              {
                  confir=true;
              }
    }catch(SQLException e)
    {
        e.printStackTrace();
    }
    return confir;
    }
    
            public boolean borrarUsuarioDeCurso(int idcurso,String iduser)
    {
        boolean status=false;
        try{
           PreparedStatement preparedStatement = connection.prepareStatement("delete from persona_curso where idcurso=?  and  idusuario=? ");
           preparedStatement.setInt(1, idcurso);
           preparedStatement.setString(2, iduser);
           if(preparedStatement.executeUpdate()==1)
           {
               status=true;
           }
        }catch(SQLException e){
        e.printStackTrace();
        }
        return status;
    }
    public boolean borrar(int idcurso)
    {
        boolean status=false;
        try{
           PreparedStatement preparedStatement = connection.prepareStatement("delete from curso where id_curso=?");
           preparedStatement.setInt(1, idcurso);
           if(preparedStatement.executeUpdate()==1)
           {
               status=true;
           }
        }catch(SQLException e){
        e.printStackTrace();
        }
        return status;
    }
    
    public boolean actualizar(Curso s) {
        boolean confir=false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update curso set  nombre=?, subtitulo=?, nivel=?, descripcion=? , instructor=?" +
                            "where id_curso=?");
            // Parameters start with 1
           
            preparedStatement.setString(1,s.getNombre());
             preparedStatement.setString(2, s.getSubTitulo());
              preparedStatement.setString(3, s.getNivel());
               
            preparedStatement.setString(4, s.getDescripcion());
            preparedStatement.setString(5, s.getInstructor());
           
            preparedStatement.setInt(6,s.getIdCurso());
           if( preparedStatement.executeUpdate()==1)
           {
               confir=true;
           }
 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return confir;
    }
    
    
     public Curso buscarCurso(int id) {
    	 Curso c= new Curso();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from curso where id_curso=?");
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
            	c.setIdCurso(rs.getInt("id_curso"));
                 c.setNombre(rs.getString("nombre"));
                 c.setSubTitulo(rs.getString("subtitulo"));
                 c.setNivel(rs.getString("nivel"));
                 c.setInstructor(rs.getString("instructor"));
                 c.setDescripcion(rs.getString("descripcion"));
                 
                 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return c;
    }
    
     
      public int contarCursos() {
        int cont=-1;
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT MAX(id_curso) AS maximo FROM curso");
            if(rs.next()) {
               if(rs.getInt("maximo") != 0) {
            	   cont=rs.getInt("maximo");
               }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
 
        return cont;
    }
      
       public int contarCursos2() {
        int cont=0;
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT count(id_curso) AS conteo FROM curso");
            if(rs.next()) {
            	
            	if(rs.getInt("conteo") != 0) {
             	   cont=rs.getInt("conteo");
                }
               
               //cont=rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
 
        return cont;
    }
       
         public boolean inscribirEnCurso(int idCurso, String idUser){
        boolean confir =false;
        //no se especifican las columnas, en la expresio sql
    try{
        PreparedStatement preparedStatement = connection.prepareStatement("insert into persona_curso values(?,?)");
         preparedStatement.setInt(1, idCurso); 
        preparedStatement.setString(2, idUser);
         
           
             
              if(preparedStatement.executeUpdate()==1)
              {
                  confir=true;
              }
    }catch(SQLException e)
    {
        e.printStackTrace();
    }
    return confir;
    }
         
          public boolean buscarSiIncritoCurso(int idCurso,String idUser) {
    	 boolean confir=false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from persona_curso where idcurso=? and idusuario=?");
            preparedStatement.setInt(1, idCurso);
            preparedStatement.setString(2, idUser);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
            	confir=true;
                 
                 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return confir;
    }
          
    
     public ArrayList<Persona> personasEnCurso(int idCurso) {
    	 ArrayList<Persona> datos= new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT  p.iduser ,p.nombre,p.apellido FROM persona_curso AS pc,persona AS p WHERE pc.idcurso=? and pc.idusuario=p.iduser");
            preparedStatement.setInt(1, idCurso);
            
            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()) {
                Persona p=new Persona();
                p.setIdUser(rs.getString("iduser"));
                p.setNombre(rs.getString("nombre"));
                p.setApellido(rs.getString("apellido"));
            	datos.add(p);
                 
                 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return datos;
    } 
           public ArrayList<Curso> misCursos(String idUser) {
    	 ArrayList<Curso> cursos =new  ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select id_curso,nombre, subtitulo, nivel, descripcion,instructor from  persona_curso AS pc, curso AS c where pc.idcurso=c.id_curso and pc.idusuario=?");
          
            preparedStatement.setString(1, idUser);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
            	Curso curso=new Curso();
                curso.setIdCurso(rs.getInt("id_curso"));
                curso.setNombre(rs.getString("nombre"));
                curso.setSubTitulo(rs.getString("subtitulo"));
                curso.setInstructor(rs.getString("instructor"));
                curso.setDescripcion(rs.getString("descripcion"));
                curso.setNivel(rs.getString("nivel"));
                cursos.add(curso);
                
                 
                 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cursos;
    }
           /* public static void main(String args[]) throws SQLException{
    	  
    	  Connection connection2= Conexion.getConection();
    	  Statement statement = connection2.createStatement();
          ResultSet rs = statement.executeQuery("SELECT MAX(id_curso) AS maximo FROM curso");
          rs.next();
          System.out.print(rs.getInt("maximo"));
       
       }*/
       
       
       
    
 
}
