/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.co.vista;

import javafx.scene.control.Button;

/**
 *
 * @author Ferney
 */
public class VistaModeloClases {

    private String nombre;
    private String tema;
    private Button verMas;
    
    public VistaModeloClases() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public Button getVerMas() {
        return verMas;
    }

    public void setVerMas(Button verMas) {
        this.verMas = verMas;
    }
    
    
}
