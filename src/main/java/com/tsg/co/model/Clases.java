/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.co.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Persistence;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Ferney
 */
@Entity
@Table(name = "Clases")
public class Clases implements Serializable{

    @Id
    //@GeneratedValue
    private Long idClases;
    
    @Column(name = "tema", nullable = true, length = 50)
    private String tema;
    
    @Column(name = "nombre", nullable = true, length = 50)
    private String nombre;
    
    @Column(name = "fechaInicio", nullable = true, length = 50)
    private String fechaInicio;
    
    
    @Column(name = "codigoClase", nullable = true, length = 50)
    private String codigoClase;

    @ManyToOne(optional = true)
    private Materias materia;

    @ManyToOne(optional = true)
    private Profesor profesor;  
  
    @OneToMany(mappedBy = "clase")
    private Set<MaterialEstudio>  materialEstudios = new HashSet();

    
    public Clases() {
    }

    public Clases(Long idClases, String tema, String nombre, String fechaInicio,Materias materias) {
        this.idClases = idClases;
        this.tema = tema;
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.materia=materias;
       
    }
      

    public Long getIdClases() {
        return idClases;
    }

    public void setIdClases(Long idClases) {
        this.idClases = idClases;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getCodigoClase() {
        return codigoClase;
    }

    public void setCodigoClase(String codigoClase) {
        this.codigoClase = codigoClase;
    }

    public Materias getMateria() {
        return materia;
    }

    public void setMateria(Materias materia) {
        this.materia = materia;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public Set<MaterialEstudio> getMaterialEstudios() {
        return materialEstudios;
    }

    public void setMaterialEstudios(Set<MaterialEstudio> materialEstudios) {
        this.materialEstudios = materialEstudios;
    }

   
    @Override
    public String toString() {
      return nombre;
      
    }

  public void persist(Object object ,EntityManagerFactory emf , EntityManager em) {
       // EntityManagerFactory emf = Persistence.createEntityManagerFactory("tsg");
        //em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
           // em.close();
        }
    }

    
    
    
    
    
}
