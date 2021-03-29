/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.co.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Ferney
 */

@Entity
@Table
public class MensajeKiosco implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private Long idMensajeKiosco;
    
    private String nombre;
    
    private String idD2l;
    private String fechaDescarga;
    
    private Long registroMensajeKiosco;
    
   
    @ManyToOne(optional = true)
    private Materias materia;
        
    @ManyToOne(optional = true)
    private Estudiante estudiante;
    
    @OneToMany(mappedBy = "mensajeKiosco")
    private Set<ArchivoMensajeKiosco> archivoMensajeKioscos =  new HashSet<>();
 
    
    @OneToMany(mappedBy = "mensajeKiosco")
    private Set<RespuestaMensaje> respuestaMensajes =  new HashSet<>();

    public MensajeKiosco() {
   
    }

    public MensajeKiosco(Long registroMensajeKiosco,Long idMensajeKiosco, String nombre,String idD2l, String fechaDescarga, Materias materia, Estudiante estudiante) {
        
        this.registroMensajeKiosco = registroMensajeKiosco;
        this.idMensajeKiosco = idMensajeKiosco;
        this.nombre = nombre;
        this.idD2l = idD2l;
        this.fechaDescarga = fechaDescarga;
        this.materia = materia;
        this.estudiante = estudiante;
    }
    
    
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdMensajeKiosco() {
        return idMensajeKiosco;
    }

    public void setIdMensajeKiosco(Long idMensajeKiosco) {
        this.idMensajeKiosco = idMensajeKiosco;
    }

    public Long getRegistroMensajeKiosco() {
        return registroMensajeKiosco;
    }

    public void setRegistroMensajeKiosco(Long registroMensajeKiosco) {
        this.registroMensajeKiosco = registroMensajeKiosco;
    }

    public Set<RespuestaMensaje> getRespuestaMensajes() {
        return respuestaMensajes;
    }

    public void setRespuestaMensajes(Set<RespuestaMensaje> respuestaMensajes) {
        this.respuestaMensajes = respuestaMensajes;
    }

    
    
    
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaDescarga() {
        return fechaDescarga;
    }

    public void setFechaDescarga(String fechaDescarga) {
        this.fechaDescarga = fechaDescarga;
    }

    public Materias getMateria() {
        return materia;
    }

    public void setMateria(Materias materia) {
        this.materia = materia;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Set<ArchivoMensajeKiosco> getArchivoMensajeKioscos() {
        return archivoMensajeKioscos;
    }

    public void setArchivoMensajeKioscos(Set<ArchivoMensajeKiosco> archivoMensajeKioscos) {
        this.archivoMensajeKioscos = archivoMensajeKioscos;
    }

    @Override
    public String toString() {
        return "MensajeKiosco{" + "id=" + id + ", nombre=" + nombre + ", fechaDescarga=" + fechaDescarga + '}';
    }

    public String getIdD2l() {
        return idD2l;
    }

    public void setIdD2l(String idD2l) {
        this.idD2l = idD2l;
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
