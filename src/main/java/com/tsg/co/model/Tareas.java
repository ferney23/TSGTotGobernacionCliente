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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Persistence;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Ferney
 */
@Entity
@Table(name = "Tareas")
public class Tareas implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "tareaKiosco", nullable = false, length = 50)
    private Long tareaKiosco;

    @NotNull
    @Column(name = "nombreTarea", nullable = false, length = 50)
    private String nombreTarea;

    @NotNull
    @Column(name = "codigo", nullable = false, length = 50)
    private String codigo;

    @Column(name = "registroTarea", nullable = true, length = 200)
    private Long registroTarea;

    @OneToMany(mappedBy = "tarea")
    private Set<Entregas> entregas = new HashSet<>();

    @ManyToOne(optional = true)
    private Materias materia;

    @ManyToOne(optional = true)
    private Subida subida;

    @ManyToOne(optional = true)
    private Estudiante estudiante;

    public Tareas() {

    }

    public Tareas(Long tareaKiosco, Long registroTarea, String nombreTarea, String codigo, Materias materia, Estudiante estudiante) {
        this.tareaKiosco = tareaKiosco;
        this.nombreTarea = nombreTarea;
        this.codigo = codigo;
        this.materia = materia;
        this.registroTarea = registroTarea;
        this.estudiante = estudiante;
    }

    public Tareas(Long tareaKiosco, Long registroTarea, String nombreTarea, String codigo, Materias materia, Subida subida, Estudiante estudiante) {
        this.tareaKiosco = tareaKiosco;
        this.nombreTarea = nombreTarea;
        this.codigo = codigo;
        this.materia = materia;
        this.subida = subida;
        this.registroTarea = registroTarea;
        this.estudiante = estudiante;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTareaKiosco() {
        return tareaKiosco;
    }

    public void setTareaKiosco(Long tareaKiosco) {
        this.tareaKiosco = tareaKiosco;
    }

    public String getNombreTarea() {
        return nombreTarea;
    }

    public void setNombreTarea(String nombreTarea) {
        this.nombreTarea = nombreTarea;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Materias getMateria() {
        return materia;
    }

    public void setMateria(Materias materia) {
        this.materia = materia;
    }

    public Subida getSubida() {
        return subida;
    }

    public void setSubida(Subida subida) {
        this.subida = subida;
    }

    public Set<Entregas> getEntregas() {
        return entregas;
    }

    public void setEntregas(Set<Entregas> entregas) {
        this.entregas = entregas;
    }

    public Long getRegistroTarea() {
        return registroTarea;
    }

    public void setRegistroTarea(Long registroTarea) {
        this.registroTarea = registroTarea;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    @Override
    public String toString() {
        return nombreTarea;
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
