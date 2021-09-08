/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.Curso;
import entidades.Persona;
import entidades.Tema;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author joan0
 */
public class TemaDao {
   
    private Connection connection;
    public TemaDao()
    {
        connection= Conexion.getConection();
    }
    
    public ArrayList<Tema> ListaTemas() {
        ArrayList<Tema> arrayTemas= new ArrayList<Tema>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from tema");
            while (rs.next()) {
                Tema t = new Tema();
                t.setIdTema(rs.getInt("idtema"));
                t.setNombre(rs.getString("nombre"));
                t.setIdCurso(rs.getInt("idcurso"));
               
               
               
               arrayTemas.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
 
        return arrayTemas;
    }
    
    public boolean insertar(Tema t){
        boolean confir =false;
    try{
        PreparedStatement preparedStatement = connection.prepareStatement("insert into tema(nombre,idcurso) values(?,?)");
         preparedStatement.setString(1, t.getNombre());
          preparedStatement.setInt(2, t.getIdCurso());
                   
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
    
            
    public boolean borrar(int idtema)
    {
        boolean status=false;
        try{
           PreparedStatement preparedStatement = connection.prepareStatement("delete from tema where idtema=?");
           preparedStatement.setInt(1, idtema);
           if(preparedStatement.executeUpdate()==1)
           {
               status=true;
           }
        }catch(SQLException e){
        e.printStackTrace();
        }
        return status;
    }
    
     public boolean borrarTemaCurso(int idtema,int idcurso)
    {
        boolean status=false;
        try{
           PreparedStatement preparedStatement = connection.prepareStatement("delete from tema where idtema=? and  idcurso=?");
           preparedStatement.setInt(1, idtema);
           preparedStatement.setInt(2, idcurso);
           if(preparedStatement.executeUpdate()==1)
           {
               status=true;
           }
        }catch(SQLException e){
        e.printStackTrace();
        }
        return status;
    }
     
     
    public boolean actualizar(Tema t) {
        boolean confir=false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update tema set  nombre=? where idtema=?");
            // Parameters start with 1
           
            preparedStatement.setString(1,t.getNombre());
            preparedStatement.setInt(2,t.getIdTema());
             
           if( preparedStatement.executeUpdate()==1)
           {
               confir=true;
           }
 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return confir;
    }
    
    public ArrayList<Tema> temasDelCurso(int idCurso) {
    	 ArrayList<Tema> temas =new  ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from  tema where idcurso=?");
          
            preparedStatement.setInt(1, idCurso);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
            	Tema t=new Tema();
                t.setIdTema(rs.getInt("idtema"));
                t.setIdCurso(rs.getInt("idcurso"));
                t.setNombre(rs.getString("nombre"));
              
                temas.add(t);
                
                 
                 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return temas;
    }

     
      public static void main(String args[]){
       TemaDao cd=new TemaDao();
       
       for(int i=0; i<cd.ListaTemas().size();i++)
       {    
           System.out.print(cd.ListaTemas().get(i).getNombre());
        }
       }
       
       
       
    
 
}
