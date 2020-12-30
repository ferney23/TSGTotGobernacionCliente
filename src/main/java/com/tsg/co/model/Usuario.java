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
    @Column(name = "contraseña", nullable = false, length = 100)
    private String contraseña;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Estudiante estudiante;

    public Usuario() {
    }

    public Usuario(String username, String contraseña) {
        this.username = username;
        this.contraseña = contraseña;
    }

    public Usuario(String username, String contraseña, Estudiante estudiante) {
        this.username = username;
        this.contraseña = contraseña;
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

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    @Override
    public String toString() {
        return "Usuario{" + "username=" + username + ", contrase\u00f1a=" + contraseña + '}';
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
            System.out.println("com.tsg.co.model.Usuario.persist()" + "Ferney ");
            
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

}
