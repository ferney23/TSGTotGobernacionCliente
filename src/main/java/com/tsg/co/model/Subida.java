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
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
    //@GeneratedValue
    private Long idSubida;

    @NotNull
    @Column(name = "fecha", nullable = false, length = 50)
    private String fecha;

    @NotNull
    @Column(name = "fechaDescarga", nullable = false, length = 50)
    private String fechaDescarga;

    @OneToMany(mappedBy = "subida")
    private Set<Tareas> tareas =  new HashSet<>();

    @OneToMany(mappedBy = "subida")
    private Set<Entregas> entregas =  new HashSet<>();

    @OneToMany(mappedBy = "subida")
    private Set<AchivosTot> achivosTot =  new HashSet<>();

    public Subida(Long idSubida, String fecha, String fechaDescarga) {
        this.idSubida = idSubida;
        this.fecha = fecha;
        this.fechaDescarga = fechaDescarga;
    }

    public Subida() {
    }

    public Long getIdSubida() {
        return idSubida;
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

    @Override
    public String toString() {
        return "idSubida=" + idSubida;
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
