/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Persistence;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Ferney
 */
@Entity
@Table(name = "Kiosco")
public class Kiosko implements Serializable{
    
    @Id
    //@GeneratedValue
    private Long idKisco;
    
    @NotNull
    @Column(name = "PORT", nullable = false, length = 50)
    private String PORT;
    
    
    @NotNull
    @Column(name = "IP", nullable = false, length = 50)
    private String IP;

    public Kiosko() {
    }

    public Kiosko(Long idKisco, String PORT, String IP) {
        this.idKisco = idKisco;
        this.PORT = PORT;
        this.IP = IP;
    }

    public Long getIdKisco() {
        return idKisco;
    }

    public void setIdKisco(Long idKisco) {
        this.idKisco = idKisco;
    }

    public String getPORT() {
        return PORT;
    }

    public void setPORT(String PORT) {
        this.PORT = PORT;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }
    
  

    @Override
    public String toString() {
        return PORT + ", IP=" + IP + '}';
    }

    public void persist(Object object) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("tsg");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
    
}
