/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.co.controller;

import static com.ibm.icu.impl.PluralRulesLoader.loader;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.tsg.co.model.Estudiante;
import com.tsg.co.model.Tareas;
import com.tsg.co.model.Usuario;
import java.awt.Color;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * FXML Controller class
 *
 * @author Ferney
 */
public class InicioSesionFXMLController implements Initializable {

    private static EntityManager manager;
    private static EntityManagerFactory enf;
    private Estudiante estudiante;
    @FXML
    private Button btnIniciarSesion;
    private Stage stageInicio;
    private Stage stagePantallaPrincipal;
    @FXML
    private Button btnRegistrarEstudiante;
    @FXML
    private TextField txtUsuarioEstudiante;
    @FXML
    private PasswordField txtContraseña;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.estudiante = null;
        // TODO
    }

    public void IniciarSesion() {

    }

    public Stage getStageInicio() {
        return stageInicio;
    }

    public void setStageInicio(Stage stageInicio) {

        this.stageInicio = stageInicio;

    }

    public Stage getStagePantallaPrincipal() {
        return stagePantallaPrincipal;
    }

    public void setStagePantallaPrincipal(Stage stagePantallaPrincipal) {
        this.stagePantallaPrincipal = stagePantallaPrincipal;
    }

    @FXML
    private void ObtenerUsuario(MouseEvent event) {
        try {
            enf = Persistence.createEntityManagerFactory("tsg");
            manager = enf.createEntityManager();
            // Long estudianteConsultar = Long.parseLong(txtidEstudiante.getText());
            try {
                
                Usuario usuariosExistentes = (Usuario) manager.createQuery("SELECT ma FROM  Usuario ma WHERE ma.username =:usuario and ma.contraseña =:contraseña ").setParameter("usuario", txtUsuarioEstudiante.getText()).setParameter("contraseña", txtContraseña.getText()).getSingleResult();
                this.estudiante=usuariosExistentes.getEstudiante();
              //  estudiante = (Estudiante) manager.createQuery("SELECT ma FROM Estudiante ma WHERE ma.nombres=:id").setParameter("id", txtidEstudiante.getText()).getSingleResult();

            } catch (Exception e) {
            }
            if (estudiante != null) {

                stagePantallaPrincipal = new Stage();
                stagePantallaPrincipal.getIcons().add(new Image("img/TOT-Icon.png"));

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TableView.fxml"));
                stagePantallaPrincipal.setScene(new Scene((Pane) loader.load()));
                TableViewController tableViewController = loader.<TableViewController>getController();
                tableViewController.setEstudiante(estudiante);
                tableViewController.llenarEstudiante();
                tableViewController.listarMaterias();

                stagePantallaPrincipal.setTitle("TOT Learning System - Client");
                stagePantallaPrincipal.setResizable(false);

                stagePantallaPrincipal.show();
                this.stageInicio.close();

            } else {
                txtUsuarioEstudiante.setText("Datos Incorrectos  ");
                txtContraseña.setText("Datos Incorrectos  ");
            }

        } catch (IOException ex) {
            Logger.getLogger(InicioSesionFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void registrarEstudiante(MouseEvent event) {

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RegistarEstudianteFXML.fxml"));
            this.stageInicio.setScene(new Scene((Pane) loader.load()));
            RegistarEstudianteFXMLController registarEstudianteFXMLController = loader.<RegistarEstudianteFXMLController>getController();
            registarEstudianteFXMLController.setStageInicioSesion(stageInicio);

        } catch (IOException ex) {
            Logger.getLogger(InicioSesionFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
