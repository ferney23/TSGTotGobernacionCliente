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
@Table(name = "Version")
public class Version implements Serializable {

    @Id
    //@GeneratedValue
    private Long idVersion;

    @NotNull
    @Column(name = "numero", nullable = false, length = 50)
    private double numero;

    public Version() {
    }

    public Version(Long idVersion, double numero) {
        this.idVersion = idVersion;
        this.numero = numero;
    }

    public Long getIdVersion() {
        return idVersion;
    }

    public void setIdVersion(Long idVersion) {
        this.idVersion = idVersion;
    }

    public double getNumero() {
        return numero;
    }

    public void setNumero(double numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return String.valueOf(numero);
    }

    public void persist(Object object ,EntityManagerFactory emf , EntityManager em) {
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("tsg");
      //   em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
          //  em.close();
        }
    }
    
    

}
