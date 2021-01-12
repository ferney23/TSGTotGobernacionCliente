/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.co.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
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
@Table(name = "Entregas")
public class Entregas implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "upp", nullable = true, length = 50)
    private Long upp;

    @Column(name = "creado", nullable = true, length = 50)
    private Calendar creado;

    @Column(name = "codigoTarea", nullable = true, length = 50)
    private String codigoTarea;

    @Column(name = "codigoEntrega", nullable = true, length = 50)
    private String codigoEntrega;

    @Column(name = "rtEntrega", nullable = true, length = 50)
    private Long rtEntrega;

    @ManyToOne(optional = true)
    private Estudiante estudiante;

    @ManyToOne(optional = true)
    private Subida subida;

    @ManyToOne(optional = true)
    private Tareas tarea;

    @OneToMany(mappedBy = "entrega")
    private Set<AchivosTot> blob =  new HashSet<>();

    public Entregas() {

    }

    public Entregas(Long upp, Estudiante estudiante, Subida subida, Tareas tarea,Long rtEntrega) {

        this.upp = upp;
        this.estudiante = estudiante;
        this.subida = subida;
        this.tarea = tarea;
        this.rtEntrega = rtEntrega;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUpp() {
        return upp;
    }

    public void setUpp(Long upp) {
        this.upp = upp;
    }

    public Calendar getCreado() {
        return creado;
    }

    public void setCreado(Calendar creado) {
        this.creado = creado;
    }

    public String getCodigoTarea() {
        return codigoTarea;
    }

    public void setCodigoTarea(String codigoTarea) {
        this.codigoTarea = codigoTarea;
    }

    public String getCodigoEntrega() {
        return codigoEntrega;
    }

    public void setCodigoEntrega(String codigoEntrega) {
        this.codigoEntrega = codigoEntrega;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Subida getSubida() {
        return subida;
    }

    public void setSubida(Subida subida) {
        this.subida = subida;
    }

    public Set<AchivosTot> getBlob() {
        return blob;
    }

    public void setBlob(Set<AchivosTot> blob) {
        this.blob = blob;
    }

    public Tareas getTarea() {
        return tarea;
    }

    public void setTarea(Tareas tarea) {
        this.tarea = tarea;
    }

    public Long getRtEntrega() {
        return rtEntrega;
    }

    public void setRtEntrega(Long rtEntrega) {
        this.rtEntrega = rtEntrega;
    }
    
    

    @Override
    public String toString() {
        return "id=" + id;
    }

   public void persist(Object object ,EntityManagerFactory emf , EntityManager em) {
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("tsg");
          em = emf.createEntityManager();
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
