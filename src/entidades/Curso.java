/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.util.ArrayList;

/**
 *
 * @author joan0
 */
public class Curso {
    
    private int idCurso;
    private String nombre;
    private String descripcion;
    private String subTitulo;
     private String instructor;
     private String nivel;
     ArrayList<Contenido> videos;

    public ArrayList<Contenido> getVideos() {
        return videos;
    }

    public void setVideos(ArrayList<Contenido> videos) {
        this.videos = videos;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getSubTitulo() {
        return subTitulo;
    }

    public void setSubTitulo(String subTitulo) {
        this.subTitulo = subTitulo;
    }

   public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }
    
   
}
