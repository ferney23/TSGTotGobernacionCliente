/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.co.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Ferney
 */
@Entity
@Table(name = "Profesor")
public class Profesor implements  Serializable{

    @Id
    //@GeneratedValue
    private Long idProfesor;
    
    @NotNull
    @Column(name = "nombres", nullable = false, length = 50)
    private String nombre;
    
    @NotNull
    @Column(name = "apellidos", nullable = false, length = 50)
    private String apellidos;
    
    @NotNull
    @Column(name = "codigo", nullable = false, length = 50,unique=true)
    private Long codigo;
    
    @OneToMany(mappedBy = "profesor")
    private Set<Materias> materias = new HashSet<>();

    @OneToMany(mappedBy = "profesor")
    private Set<Clases> clases = new HashSet<>();

    
    public Profesor() {
    }

    public Long getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(Long idProfesor) {
        this.idProfesor = idProfesor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    @Override
    public String toString() {
        return "Profesor{" + "idProfesor=" + idProfesor + ", nombre=" + nombre + ", apellidos=" + apellidos + ", codigo=" + codigo + '}';
    }

 public void persist(Object object ,EntityManagerFactory emf , EntityManager em) {
       // EntityManagerFactory emf = Persistence.createEntityManagerFactory("tsg");
        em = emf.createEntityManager();
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
