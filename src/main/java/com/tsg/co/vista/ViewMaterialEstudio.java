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
public class ViewMaterialEstudio {

    private String clase;
    private String tema;
    private String nombre;
    private String descripcion;
    private Button btnArchivoAdjunto;

    public ViewMaterialEstudio() {
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Button getBtnArchivoAdjunto() {
        return btnArchivoAdjunto;
    }

    public void setBtnArchivoAdjunto(Button btnArchivoAdjunto) {
        this.btnArchivoAdjunto = btnArchivoAdjunto;
    }

}
