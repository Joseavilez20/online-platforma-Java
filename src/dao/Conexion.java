/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author joan0
 */
import java.sql.Connection;
import java.sql.DriverManager;


public class Conexion {
  private static  Connection c = null;
  
    public   static Connection  getConection(){
        
        if(c!= null)
        {
        
        return c;}
        
        else{
      try {
         Class.forName("org.postgresql.Driver");
         c = DriverManager
            .getConnection("jdbc:postgresql://localhost:5432/postgres",
            "postgres", "admin");
      } catch (Exception e) {
         e.printStackTrace();
         System.err.println(e.getClass().getName()+": "+e.getMessage());
         System.exit(0);
      }
      
      return c;
    }
     
   }
}