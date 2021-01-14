/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.co.model;

import java.io.Serializable;
import java.sql.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.FetchType;
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
@Table(name = "Subida")
public class Subida implements Serializable {

    @Id
   // @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idSubida;

    @NotNull
    @Column(name = "subidaKisoco", nullable = true, length = 50)
    private Long subidaKisoco;
    
    @NotNull
    @Column(name = "fecha", nullable = false, length = 50)
    private String fecha;

    @NotNull
    @Column(name = "fechaDescarga", nullable = false, length = 50)
    private String fechaDescarga;

    @OneToMany(mappedBy = "subida")
    private Set<Tareas> tareas = new HashSet<>();

    @OneToMany(mappedBy = "subida")
    private Set<Entregas> entregas = new HashSet<>();

    @OneToMany(mappedBy = "subida")
    private Set<AchivosTot> achivosTot = new HashSet<>();

    @ManyToOne(optional = true)
    private Estudiante estudiante;

    public Subida(Long idSubida ,Long subidaKisoco, String fecha, String fechaDescarga, Estudiante estudiante) {
        this.idSubida=idSubida;
        this.subidaKisoco = subidaKisoco;
        this.fecha = fecha;
        this.fechaDescarga = fechaDescarga;
        this.estudiante = estudiante;
    }

    public Subida() {
    }

    public Long getIdSubida() {
        return idSubida;
    }

    public Long getSubidaKisoco() {
        return subidaKisoco;
    }

    public void setSubidaKisoco(Long subidaKisoco) {
        this.subidaKisoco = subidaKisoco;
    }

    
    
    public void setIdSubida(Long idSubida) {
        this.idSubida = idSubida;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getFechaDescarga() {
        return fechaDescarga;
    }

    public void setFechaDescarga(String fechaDescarga) {
        this.fechaDescarga = fechaDescarga;
    }

    public Set<Tareas> getTareas() {
        return tareas;
    }

    public void setTareas(Set<Tareas> tareas) {
        this.tareas = tareas;
    }

    public Set<Entregas> getEntregas() {
        return entregas;
    }

    public void setEntregas(Set<Entregas> entregas) {
        this.entregas = entregas;
    }

    public Set<AchivosTot> getAchivosTot() {
        return achivosTot;
    }

    public void setAchivosTot(Set<AchivosTot> achivosTot) {
        this.achivosTot = achivosTot;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    @Override
    public String toString() {
        return "idSubida=" + idSubida;
    }

     public void persist(Object object, EntityManager em) {
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
