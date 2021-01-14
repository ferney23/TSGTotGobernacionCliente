/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.co.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Persistence;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Ferney
 */
@Entity
@Table(name = "MaterialEstudio")
public class MaterialEstudio implements Serializable {

    @Id
    //@GeneratedValue
    private Long idMaterialEstudio;

    @NotNull
    @Column(name = "nombre", nullable = true, length = 50)
    private String nombre;

    @NotNull
    @Column(name = "descripcion", nullable = true, length = 250)
    private String descripcion;

    @Column(name = "nombreArchivo", nullable = true, length = 250)
    private String nombreArchivo;

    @Column(name = "ruta", nullable = true, length = 250)
    private String ruta;

    @ManyToOne(optional = true)
    private Clases clase;

    public MaterialEstudio() {
    }

    public MaterialEstudio(Long idMaterialEstudio, String nombre, String descripcion, Clases clase, String nombreArchivo, String ruta) {
        this.idMaterialEstudio = idMaterialEstudio;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.clase = clase;
        this.nombreArchivo = nombreArchivo;
        this.ruta = ruta;
    }

    public Long getIdMaterialEstudio() {
        return idMaterialEstudio;
    }

    public void setIdMaterialEstudio(Long idMaterialEstudio) {
        this.idMaterialEstudio = idMaterialEstudio;
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

    public Clases getClase() {
        return clase;
    }

    public void setClase(Clases clase) {
        this.clase = clase;
    }

    @Override
    public String toString() {
        return nombre;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }
    
    
public void persist(Object object , EntityManager em) {
       // EntityManagerFactory emf = Persistence.createEntityManagerFactory("tsg");
        //em = emf.createEntityManager();
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
