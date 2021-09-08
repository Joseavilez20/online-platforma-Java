/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.Contenido;
import entidades.Curso;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author joan0
 */
public class ContenidoDao {
  
     private Connection connection;
     
     public ContenidoDao(){
          connection= Conexion.getConection();
     }
     
     public ArrayList<Contenido> ListaContenido() {
        ArrayList<Contenido> arrayContenido= new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from contenido");
            while (rs.next()) {
                Contenido c = new Contenido();
                c.setIdContenido(rs.getInt("id_contenido"));
                c.setIdTema(rs.getInt("idtema"));
                c.setTitulo(rs.getString("titulo"));
                c.setUrl(rs.getString("url"));
                c.setIdCurso(rs.getInt("FK_id_curso"));
                
                
               
               
               
               
               arrayContenido.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
 
        return arrayContenido;
    }
     
     public boolean  insertar(Contenido c){
         boolean  confirmar =false;
    try{
        String sql ="insert into contenido(titulo,url,idcurso,idtema) values(?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
         preparedStatement.setString(1, c.getTitulo());
          preparedStatement.setString(2, c.getUrl());
           preparedStatement.setInt(3, c.getIdCurso());
           preparedStatement.setInt(4, c.getIdTema());
           
           
             
             if( preparedStatement.executeUpdate()==1)
             {
             confirmar=true;
             }
    }catch(SQLException e)
    {
        e.printStackTrace();
    }
    
    return confirmar;
    }
        
     
     public  ArrayList<Contenido> buscarContenidos(int idCurso) {
         
          ArrayList<Contenido> arrayContenido= new ArrayList<>();
    	
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from contenido where idcurso=?");
            preparedStatement.setInt(1, idCurso);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Contenido c = new Contenido();
                c.setIdContenido(rs.getInt("id_contenido"));
                 c.setTitulo(rs.getString("titulo"));
                  c.setUrl(rs.getString("url"));
                  c.setIdCurso(rs.getInt("idcurso"));
                  c.setIdTema(rs.getInt("idtema"));
                 
                 arrayContenido.add(c);
            
                 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return arrayContenido;
    }
     
       
     public  ArrayList<Contenido> buscarContenidosdelTema(int idCurso,int idTema) {
         
          ArrayList<Contenido> arrayContenido= new ArrayList<>();
    	
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from contenido where idcurso=? and idtema=?");
            preparedStatement.setInt(1, idCurso);
             preparedStatement.setInt(2, idTema);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Contenido c = new Contenido();
                c.setIdContenido(rs.getInt("id_contenido"));
                 c.setTitulo(rs.getString("titulo"));
                  c.setUrl(rs.getString("url"));
                  c.setIdCurso(rs.getInt("idcurso"));
                  c.setIdTema(rs.getInt("idtema"));
                 
                 arrayContenido.add(c);
            
                 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return arrayContenido;
    }
    
       public  Contenido verContenido(int idCurso,int id_contenido) {
         
         
    	Contenido c=null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from contenido where idcurso=? and id_contenido=?");
            preparedStatement.setInt(1, idCurso);
             preparedStatement.setInt(2, id_contenido);
            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next()) {
                 c = new Contenido();
                c.setIdContenido(rs.getInt("id_contenido"));
                 c.setTitulo(rs.getString("titulo"));
                  c.setUrl(rs.getString("url"));
                  c.setIdCurso(rs.getInt("idcurso"));
                  c.setIdTema(rs.getInt("idtema"));
                 
                
            
                 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return c;
    }
    
       public boolean borrarContenidoDeCurso(int idcurso,int idtema, int idcontenido)
    {
        boolean status=false;
        try{
           PreparedStatement preparedStatement = connection.prepareStatement("delete from contenido where idcurso=?  and  idtema=? and id_contenido=? ");
           preparedStatement.setInt(1, idcurso);
           preparedStatement.setInt(2, idtema);
            preparedStatement.setInt(3, idcontenido);
           if(preparedStatement.executeUpdate()==1)
           {
               status=true;
           }
        }catch(SQLException e){
        e.printStackTrace();
        }
        return status;
    }
       public static void main(String[]a){
       ContenidoDao cd=  new ContenidoDao();
      System.out.print( cd.verContenido(44, 43).getUrl());
      
       }
}
