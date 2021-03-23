/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.co.model;

import java.io.Serializable;
import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Ferney
 */
@Entity
@Table
public class ArchivoMensajeKiosco implements Serializable  {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private Long fileMensajeId; 
    
    private String nombre;
    
    private String fechaDescarga;
    
    private String idD2L;
    
    private String url;
    
    @ManyToOne(optional = true)
    private  MensajeKiosco mensajeKiosco;

    public ArchivoMensajeKiosco() {
    }

    public ArchivoMensajeKiosco(Long fileMensajeId, String nombre, String fechaDescarga, String idD2L, String url, MensajeKiosco mensajeKiosco) {
        this.fileMensajeId = fileMensajeId;
        this.nombre = nombre;
        this.fechaDescarga = fechaDescarga;
        this.idD2L = idD2L;
        this.url = url;
        this.mensajeKiosco = mensajeKiosco;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
    
    
    public Long getFileMensajeId() {
        return fileMensajeId;
    }

    public void setFileMensajeId(Long fileMensajeId) {
        this.fileMensajeId = fileMensajeId;
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

    public String getIdD2L() {
        return idD2L;
    }

    public void setIdD2L(String idD2L) {
        this.idD2L = idD2L;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public MensajeKiosco getMensajeKiosco() {
        return mensajeKiosco;
    }

    public void setMensajeKiosco(MensajeKiosco mensajeKiosco) {
        this.mensajeKiosco = mensajeKiosco;
    }

    @Override
    public String toString() {
        return "ArchivoMensajeKiosco{" + "fileMensajeId=" + fileMensajeId + ", nombre=" + nombre + ", fechaDescarga=" + fechaDescarga + ", idD2L=" + idD2L + '}';
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
