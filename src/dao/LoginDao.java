/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;


import entidades.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author joan0
 */
public class LoginDao {
    
     private Connection connection;
     
    public LoginDao()
    {
        connection= Conexion.getConection();
    }
    
    
    public Usuario logear(String email,String pass) {
    	Usuario user=new Usuario();
        try {
            
            PreparedStatement preparedStatement = connection.prepareStatement("select * from usuario where email=? and password=?");
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, pass);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
            	
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setRol(rs.getString("rol"));
                user.setIdUser(rs.getString("iduser"));
                 
                 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
    
    public Usuario buscarIdUser(String id) {
    	Usuario u= new Usuario();
        try {
            
            PreparedStatement preparedStatement = connection.prepareStatement("select * from usuario where iduser=?");
            preparedStatement.setString(1, id);
           
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
            	
                 u.setEmail(rs.getString("email"));
                 u.setPassword(rs.getString("password"));
                 u.setRol(rs.getString("rol"));
                 u.setIdUser(rs.getString("iduser"));
                 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return u;
    }

//FALTA  ROL
     public boolean insertar(Usuario user){
        boolean confir =false;
    try{
        PreparedStatement preparedStatement = connection.prepareStatement("insert into usuario(email,password,rol,iduser) values(?,?,?,?)");
         preparedStatement.setString(1, user.getEmail());
          preparedStatement.setString(2, user.getPassword());
          preparedStatement.setString(3, user.getRol());
           preparedStatement.setString(4, user.getIdUser());
        
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
     
       public int contarUsuarios() {
        int cont=-1;
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT COUNT(iduser) FROM usuario");
            if(rs.next()) {
               
               cont=rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
 
        return cont;
    }
       
       
         public boolean borrar(String idUser)
    {
        boolean status=false;
        try{
           PreparedStatement preparedStatement = connection.prepareStatement("delete from usuario where iduser=?");
           preparedStatement.setString(1, idUser);
           if(preparedStatement.executeUpdate()==1)
           {
               status=true;
           }
        }catch(SQLException e){
        e.printStackTrace();
        }
        return status;
    }
         
           public boolean actualizar(Usuario u) {
         boolean  confir=false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update usuario set  email=?, password=?, rol=? " +
                            "where iduser=?");
            // Parameters start with 1
           
            preparedStatement.setString(1,u.getEmail());
             preparedStatement.setString(2, u.getPassword());
              preparedStatement.setString(3, u.getRol());
              
           
            preparedStatement.setString(4,u.getIdUser());
            if(preparedStatement.executeUpdate()==1)
            {
                confir=true;
            }
 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return confir;
    }
           
    
            public boolean actualizarPass(String pass,String idUser) {
         boolean  confir=false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update usuario set   password=?  where iduser=?");
            // Parameters start with 1

             preparedStatement.setString(1,pass);
            preparedStatement.setString(2,idUser);
            
            if(preparedStatement.executeUpdate()==1)
            {
                confir=true;
            }
 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return confir;
    }
    public static void main(String []args)
    {
        LoginDao  l= new LoginDao();
       System.out.print( l.contarUsuarios());
    }

}

    

