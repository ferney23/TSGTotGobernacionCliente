/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.co.vista;

import com.tsg.co.model.MensajeKiosco;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

/**
 *
 * @author Ferney
 */
public class ViewMensaje {
    private String nombre;
    private String materia;
    private MensajeKiosco mensajeKiosco;
    private HBox verMas = new HBox();

    public ViewMensaje() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public HBox getVerMas() {
        return verMas;
    }

    public void setVerMas(HBox verMas) {
        this.verMas = verMas;
           
    }

    public MensajeKiosco getMensajeKiosco() {
        return mensajeKiosco;
    }

    public void setMensajeKiosco(MensajeKiosco mensajeKiosco) {
        this.mensajeKiosco = mensajeKiosco;
    }

  
    
    
    
    
}
