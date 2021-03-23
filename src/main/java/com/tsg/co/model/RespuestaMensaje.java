/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.co.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Ferney
 */
@Entity
@Table
public class RespuestaMensaje implements Serializable{
    @Id
    private Long id;
    
    private long estado;
    
    private String body;
    
    @ManyToOne(optional = true)
    private MensajeKiosco mensajeKiosco;

    public RespuestaMensaje(Long id, long estado, String body, MensajeKiosco mensajeKiosco) {
        this.id = id;
        this.estado = estado;
        this.body = body;
        this.mensajeKiosco = mensajeKiosco;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getEstado() {
        return estado;
    }

    public void setEstado(long estado) {
        this.estado = estado;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public MensajeKiosco getMensajeKiosco() {
        return mensajeKiosco;
    }

    public void setMensajeKiosco(MensajeKiosco mensajeKiosco) {
        this.mensajeKiosco = mensajeKiosco;
    }
    
    
    
    
}
