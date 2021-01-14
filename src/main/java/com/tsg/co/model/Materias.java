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
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.Persistence;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "Materias")
public class Materias implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    //@GeneratedValue
    private Long idMateria;

    @NotNull
    @Column(name = "titulo", nullable = false, length = 50)
    private String titulo;

    @Column(name = "subtitulo", length = 50)
    private String subtitulo;

    @Column(name = "descripcion", length = 50)
    private String descripcion;

    @Column(name = "imagen", length = 50, nullable = true)
    private String imagen;

    @Column(name = "codigo", length = 50)
    private String codigo;

    @OneToMany(mappedBy = "materia")
    private Set<Tareas> tareas = new HashSet<>();

    @OneToMany(mappedBy = "materia")
    private Set<Clases> clases = new HashSet<>();

    @ManyToOne(optional = true)
    private Profesor profesor;

    @ManyToMany(mappedBy = "materiases")
    private Set<Estudiante> estudiantes = new HashSet<>();

    public Materias() {

    }

    public Materias(Long idMateria, String titulo, String subtitulo, String descripcion, String codigo) {
        this.idMateria = idMateria;
        this.titulo = titulo;
        this.subtitulo = subtitulo;
        this.descripcion = descripcion;
        // this.imagen = imagen;
        this.codigo = codigo;
    }

    public void addEstudiante(Estudiante estudiante) {
        this.estudiantes.add(estudiante);
//        estudiante.addMaterias(this);

    }

    public Long getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(Long idMateria) {
        this.idMateria = idMateria;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSubtitulo() {
        return subtitulo;
    }

    public void setSubtitulo(String subtitulo) {
        this.subtitulo = subtitulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Set<Tareas> getTareas() {
        return tareas;
    }

    public void setTareas(Set<Tareas> tareas) {
        this.tareas = tareas;
    }

    public Set<Clases> getClases() {
        return clases;
    }

    public void setClases(Set<Clases> clases) {
        this.clases = clases;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    @Override
    public String toString() {
        return titulo;

    }

    public Set<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(Set<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
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
