/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.co.model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Persistence;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Ferney
 */
@Entity
@Table(name = "Usuarios")
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "username", nullable = false, length = 100, unique = true)
    private String username;

    @NotNull
    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @OneToOne(mappedBy = "usuario" )
    private Estudiante estudiante;

    public Usuario() {
    }

    public Usuario(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Usuario(String username, String password, Estudiante estudiante) {
        this.username = username;
        this.password = password;
        this.estudiante = estudiante;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    @Override
    public String toString() {
        return "Usuario{" + "username=" + username + ", password=" + password+ '}';
    }

    public void persist(Object object ,EntityManagerFactory emf , EntityManager em) {
       // EntityManagerFactory emf = Persistence.createEntityManagerFactory("tsg");
        em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
           // System.out.println("Usuario creado" + "Ferney ");
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
           // e.printStackTrace();
            System.out.println("com.tsg.co.model.Usuario.persist()" + "Ferney ");
            
            em.getTransaction().rollback();
        } finally {
           // em.close();
        }
    }

}
