/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.co.model;

import java.io.Serializable;
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
@Table(name="RespuestaMensaje")
public class RespuestaMensaje implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private long estado;

    private String body;

    @ManyToOne(optional = true)
    private MensajeKiosco mensajeKiosco;

    public RespuestaMensaje() {
    
    
    }
    
    public RespuestaMensaje( long estado, String body, MensajeKiosco mensajeKiosco) {
        this.estado = estado;
        this.body = body;
        this.mensajeKiosco = mensajeKiosco;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getEstado() {
        return estado;
    }

    public void setEstado(long estado) {
        this.estado = estado;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public MensajeKiosco getMensajeKiosco() {
        return mensajeKiosco;
    }

    public void setMensajeKiosco(MensajeKiosco mensajeKiosco) {
        this.mensajeKiosco = mensajeKiosco;
    }

    @Override
    public String toString() {
        return "RespuestaMensaje{" + "id=" + id + ", estado=" + estado + ", body=" + body + ", mensajeKiosco=" + mensajeKiosco + '}';
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
