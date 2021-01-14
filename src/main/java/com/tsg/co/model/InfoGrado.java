/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.co.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Persistence;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Ferney
 */
@Entity
@Table(name = "Infogrado")
public class InfoGrado implements Serializable{
    
    @Id
    //@GeneratedValue
    private Long idInfoGrado;

    @NotNull
    @Column(name = "codigoGrado", nullable = false, length = 50,unique = true)
    private String codigoGrado;


    @NotNull
    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;
    
    
    @NotNull
    @Column(name = "institucion", nullable = false, length = 50)
    private String institucion;
    
    @NotNull
    @Column(name = "ubicacionInstitucion", nullable = false, length = 50)
    private String ubicacionInstitucion;
    
    @NotNull
    @ManyToOne(optional = false)
    private Estudiante estudiante;

    public InfoGrado(Long idInfoGrado, String codigoGrado, String nombre, String institucion, String ubicacionInstitucion, Estudiante estudiante) {
        this.idInfoGrado = idInfoGrado;
        this.codigoGrado = codigoGrado;
        this.nombre = nombre;
        this.institucion = institucion;
        this.ubicacionInstitucion = ubicacionInstitucion;
        this.estudiante = estudiante;
    }
    
    
    
    

    public InfoGrado() {
    }

    public Long getIdInfoGrado() {
        return idInfoGrado;
    }

    public void setIdInfoGrado(Long idInfoGrado) {
        this.idInfoGrado = idInfoGrado;
    }

    public String getCodigoGrado() {
        return codigoGrado;
    }

    public void setCodigoGrado(String codigoGrado) {
        this.codigoGrado = codigoGrado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    public String getUbicacionInstitucion() {
        return ubicacionInstitucion;
    }

    public void setUbicacionInstitucion(String ubicacionInstitucion) {
        this.ubicacionInstitucion = ubicacionInstitucion;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    

    @Override
    public String toString() {
        return nombre;
    }

    public void persist(Object object , EntityManager em) {
       // EntityManagerFactory emf = Persistence.createEntityManagerFactory("tsg");
       //  em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
    }
    
 
}
