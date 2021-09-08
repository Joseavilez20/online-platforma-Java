/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.Curso;
import entidades.Persona;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joan0
 */
public class PersonaDao {
    
    private Connection connection;
    
    
    public PersonaDao(){
       connection= Conexion.getConection();
    }
   /*  public ArrayList<Persona> ListaPersonas(String rol) {
        ArrayList<Persona> arrayPersonas= new ArrayList<>();
        try {
            String sql= "select  nombre, apellido,acerca ,id_perssona,"+
                    "p.iduser from persona AS p, usuario AS u where  p.iduser=u.iduser and u.rol=?"; 
            PreparedStatement preparedStatement =connection.prepareStatement(sql);
            preparedStatement.setString(1, rol);
          
             ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Persona c = new Persona();
               // c.setIdPersona(rs.getInt("id_perssona"));
                c.setNombre(rs.getString("nombre"));
               c.setApellido(rs.getString("apellido"));
                c.setAcerca(rs.getString("acerca"));
                c.setIdPersona(rs.getInt("id_perssona"));
                c.setIdUser(rs.getString("iduser"));
               
               
               
               arrayPersonas.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
 
        return arrayPersonas;
    }*/
     public ArrayList<Persona> ListarPersonas() {
        ArrayList<Persona> arrayPersonas= new ArrayList<>();
        try {
            String sql= "select  nombre, apellido,acerca,iduser  from persona "; 
           Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Persona c = new Persona();
               // c.setIdPersona(rs.getInt("id_perssona"));
                c.setNombre(rs.getString("nombre"));
               c.setApellido(rs.getString("apellido"));
                c.setAcerca(rs.getString("acerca"));
                c.setIdUser(rs.getString("iduser"));
               
               
               
               arrayPersonas.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
 
        return arrayPersonas;
    }
     
     public Persona buscarPersona(int id) {
    	Persona c= new Persona();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from persona where id_perssona=?");
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
            	c.setIdPersona(rs.getInt("id_perssona"));
                 c.setNombre(rs.getString("nombre"));
                 c.setApellido(rs.getString("apellido"));
                 c.setAcerca("acerca");
                 
                 c.setIdUser("iduser");
                 
                 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return c;
    }
     public String test()
     {
         return "hello";
     }
     
     
      public ArrayList<String> personaUsuario(String id) {
    	//Persona c=new Persona();
          ArrayList<String> datos =new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(" select  p.nombre, p.apellido,p.acerca ,p.email,p.password,p.rol,p.iduser from persona AS p, usuario AS u "+
                    " where  p.iduser=u.iduser and p.iduser=?");
            preparedStatement.setString(1, id);
            ResultSet rs = preparedStatement.executeQuery();

             while(rs.next()) {
               
          
               datos.add(rs.getString("nombre"));
                datos.add(rs.getString("apellido"));
                datos.add(rs.getString("acerca"));
                datos.add(rs.getString("rol"));
                datos.add(rs.getString("email"));
                datos.add(rs.getString("password"));
                
            datos.add(rs.getString("iduser"));
                 
                 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return datos;
    }
     
      public boolean insertar(Persona c){
        boolean confir =false;
    try{
        PreparedStatement preparedStatement = connection.prepareStatement("insert into persona(nombre,apellido,acerca,iduser) values(?,?,?,?)");
         preparedStatement.setString(1, c.getNombre());
          preparedStatement.setString(2, c.getApellido());
           
            preparedStatement.setString(3, c.getAcerca());
            
             preparedStatement.setString(4, c.getIdUser());
             
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
    
     public boolean actualizar(Persona  p) {
         boolean  confir=false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update persona set  nombre=?, apellido=?, acerca=? " +
                            "where iduser=?");
            // Parameters start with 1
           
            preparedStatement.setString(1,p.getNombre());
             preparedStatement.setString(2, p.getApellido());
              preparedStatement.setString(3, p.getAcerca());
              
           
            preparedStatement.setString(4,p.getIdUser());
            if(preparedStatement.executeUpdate()==1)
            {
                confir=true;
            }
 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return confir;
    }
    
   public static void main(String args[]){
    
        PersonaDao  p= new PersonaDao();
        
       System.out.println(p.buscarPersona(3).getNombre());
   }
   
}

