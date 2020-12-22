/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.co.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Persistence;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import static org.hibernate.engine.internal.Cascade.cascade;

/**
 *
 * @author Ferney
 */
@Entity
@Table(name = "Estudiante")
public class Estudiante implements Serializable {

    @Id
    //@GeneratedValue
    private Long idEstudiante;

    @NotNull
    @Column(name = "nombres", nullable = false, length = 50)
    private String nombres;

    @NotNull
    @Column(name = "apellidos", nullable = false, length = 50)
    private String apellidos;

    @NotNull
    @Column(name = "edad", nullable = false, length = 50)
    private Long edad;

    @OneToMany(mappedBy = "estudiante")
    private Set<Entregas> entregas = new HashSet<>();

    @OneToMany(mappedBy = "estudiante")
    private Set<Tareas> tareas = new HashSet<>();
    @JoinTable(
            name = "rel_estudiante_materias",
            joinColumns = @JoinColumn(name = "FK_ESTUDIANTE", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "FK_MATERIA", nullable = false)
    )
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private Set<Materias> materiases = new HashSet<>();

    @OneToMany(mappedBy = "estudiante")
    private Set<InfoGrado> infoGrados = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "FK_USUARIO", updatable = true, nullable = true)
    private Usuario usuario;
    
    @OneToMany(mappedBy = "estudiante")
    private Set<Subida> subidas =  new HashSet<>();

    public Estudiante() {
    }

    public Estudiante(Long idEstudiante, String nombres, String apellidos, Long edad,Usuario usuario) {
        this.idEstudiante = idEstudiante;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.edad = edad;
        this.usuario= usuario;
    }

    public void addMaterias(Materias materias) {
        if (this.materiases == null) {
            this.materiases = new HashSet<>();
        }
        System.out.println("com.tsg.co.model.Estudiante.addMaterias()");
        this.materiases.add(materias);

    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Long getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(Long idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Long getEdad() {
        return edad;
    }

    public void setEdad(Long edad) {
        this.edad = edad;
    }

    public Set<Entregas> getEntregas() {
        return entregas;
    }

    public void setEntregas(Set<Entregas> entregas) {
        this.entregas = entregas;
    }

    public Set<InfoGrado> getInfoGrados() {
        return infoGrados;
    }

    public void setInfoGrados(Set<InfoGrado> infoGrados) {
        this.infoGrados = infoGrados;
    }

    public Set<Materias> getMateriases() {
        return materiases;
    }

    public void setMateriases(Set<Materias> materiases) {
        this.materiases = materiases;
    }

    public Set<Tareas> getTareas() {
        return tareas;
    }

    public void setTareas(Set<Tareas> tareas) {
        this.tareas = tareas;
    }

    public Set<Subida> getSubidas() {
        return subidas;
    }

    public void setSubidas(Set<Subida> subidas) {
        this.subidas = subidas;
    }
    
    

    @Override
    public String toString() {
        return " nombres=" + nombres;

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
