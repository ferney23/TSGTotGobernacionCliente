/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.co.controller;

import static com.ibm.icu.impl.PluralRulesLoader.loader;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.tsg.co.Sincronizacion.Inicio;
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
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * FXML Controller class
 *
 * @author Ferney
 */
public class InicioSesionFXMLController implements Initializable {

    // private EntityManager manager;
    private EntityManagerFactory enf;
    private Estudiante estudiante;
    private Inicio inicio;
    @FXML
    private JFXButton btnIniciarSesion;
    // private Stage stageInicio;
    private Stage stagePantallaPrincipal;
    @FXML
    private JFXButton btnRegistrarEstudiante;
    @FXML
    private TextField txtUsuarioEstudiante;
    @FXML
    private PasswordField txtContraseña;
    private TableViewController tableViewController;
    private Scene scenePantallaPrincipal;
    private Scene sceneInicioSesion;
    private Stage stageInicioSesion;
    @FXML
    private Label labelinfoConexion;
    private boolean conectado;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.estudiante = null;
        //  this.scene = new Scene(null);
        // TODO
    }

    public void IniciarSesion() {

    }

    public EntityManagerFactory getEnf() {
        return enf;
    }

    public void setEnf(EntityManagerFactory enf) {
        this.enf = enf;
    }

    public Stage getStagePantallaPrincipal() {
        return stagePantallaPrincipal;
    }

    public void setStagePantallaPrincipal(Stage stagePantallaPrincipal) {
        this.stagePantallaPrincipal = stagePantallaPrincipal;
    }

    public TableViewController getTableViewController() {
        return tableViewController;
    }

    public void setTableViewController(TableViewController tableViewController) {
        this.tableViewController = tableViewController;
    }

    public Scene getScenePantallaPrincipal() {
        return scenePantallaPrincipal;
    }

    public void setScenePantallaPrincipal(Scene scenePantallaPrincipal) {
        this.scenePantallaPrincipal = scenePantallaPrincipal;
    }

    public Scene getSceneInicioSesion() {
        return sceneInicioSesion;
    }

    public void setSceneInicioSesion(Scene sceneInicioSesion) {
        this.sceneInicioSesion = sceneInicioSesion;
    }

    public Stage getStageInicioSesion() {
        return stageInicioSesion;
    }

    public void setStageInicioSesion(Stage stageInicioSesion) {
        this.stageInicioSesion = stageInicioSesion;
    }

    public boolean isConectado() {

        return conectado;
    }

    public void setConectado(boolean conectado) {
        this.conectado = conectado;
        if (this.conectado == true) {
            labelinfoConexion.setText("ONLINE");
        } else {
            labelinfoConexion.setText("OFLINE");
        }

    }

    @FXML
    private void ObtenerUsuario(MouseEvent event) {

        EntityManager manager = enf.createEntityManager();
        try {

            Usuario usuariosExistentes = null;

            try {
                usuariosExistentes = (Usuario) manager.createQuery("SELECT ma FROM  Usuario ma WHERE ma.username =:usuario and ma.password =:contraseña ").setParameter("usuario", txtUsuarioEstudiante.getText()).setParameter("contraseña", txtContraseña.getText()).getSingleResult();
            } catch (Exception e) {

            }
            if (usuariosExistentes == null) {
                txtUsuarioEstudiante.setText("Usuario Incorrecto ");
                txtContraseña.setText("Usuario Incorrecto  ");
            } else {
                this.estudiante = usuariosExistentes.getEstudiante();
                if (estudiante != null) {
                    stagePantallaPrincipal = new Stage();
                    stagePantallaPrincipal.getIcons().add(new Image("img/TOT-Icon.png"));
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TableView.fxml"));
                    this.scenePantallaPrincipal = new Scene((Pane) loader.load());
                    stagePantallaPrincipal.setScene(this.scenePantallaPrincipal);
                    tableViewController = loader.<TableViewController>getController();
                    this.tableViewController.setEnf(enf);
                    //   this.tableViewController.setManager(manager);
                    tableViewController.setEstudiante(estudiante);
                    tableViewController.llenarEstudiante();
                    tableViewController.listarMaterias();
                    tableViewController.setStagePantallaPrincipal(stagePantallaPrincipal);
                    tableViewController.setScenePrincipal(scenePantallaPrincipal);
                    tableViewController.setStageInicioSesion(stageInicioSesion);
                    tableViewController.setSceneInicioSesion(sceneInicioSesion);
                    stagePantallaPrincipal.setTitle("TOT Learning System - Client");
                    stagePantallaPrincipal.setResizable(false);
                    stagePantallaPrincipal.show();
                    this.stageInicioSesion.close();

                } else {
                    txtUsuarioEstudiante.setText("Usuario no sincronizado  ");
                    txtContraseña.setText("Datos Incorrectos  ");
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(InicioSesionFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        manager.close();

    }

    @FXML
    private void registrarEstudiante(MouseEvent event) {
        EntityManager manager = enf.createEntityManager();
        Usuario usuariosExistentes = null;

        if ((txtUsuarioEstudiante.getText().length() > 0) && (txtUsuarioEstudiante.getText().length() <= 30)) {
            try {
                usuariosExistentes = (Usuario) manager.createQuery("SELECT ma FROM  Usuario ma WHERE ma.username =:usuario and ma.password =:contraseña ").setParameter("usuario", txtUsuarioEstudiante.getText()).setParameter("contraseña", txtContraseña.getText()).getSingleResult();
            } catch (Exception e) {

            }

            String nombre = txtUsuarioEstudiante.getText();
            String contraseña = txtContraseña.getText();
            if (usuariosExistentes == null) {
                Usuario usuarioRegistrar = new Usuario(nombre, contraseña);
                usuarioRegistrar.persist(usuarioRegistrar, manager);
                txtUsuarioEstudiante.setText("Usuario creado: " + nombre);
            } else {
                txtUsuarioEstudiante.setText("Este usuario ya esta registrado");
            }

        }
        manager.close();

    }

}
