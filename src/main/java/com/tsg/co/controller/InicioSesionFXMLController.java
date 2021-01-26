/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.co.controller;

import static com.ibm.icu.impl.PluralRulesLoader.loader;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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

    @FXML
    private GridPane gridPrincipal;
    @FXML
    private StackPane stackPaneInicio;
    @FXML
    private JFXButton btnSincronizar;

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

    public JFXDialog jdialogoLogin(String content, String title) {
        // String title = "Inicio de Sesion";
        // String content = "Usuario incorrecto";
        JFXDialogLayout jFXDialogLayout = new JFXDialogLayout();
        jFXDialogLayout.setHeading(new Text(title));
        jFXDialogLayout.setBody(new Text(content));
        JFXButton close = new JFXButton("close");
        close.setButtonType(JFXButton.ButtonType.RAISED);
        jFXDialogLayout.setActions(close);

        JFXDialog jFXDialog = new JFXDialog(stackPaneInicio, jFXDialogLayout, JFXDialog.DialogTransition.BOTTOM);
        jFXDialog.setMaxSize(50, 100);
        close.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                jFXDialog.close();
            }
        });
        jFXDialog.show();
        return jFXDialog;

    }

    @FXML
    private void ObtenerUsuario(MouseEvent event) {
        EntityManager manager = enf.createEntityManager();
        Usuario usuariosExistentes = null;
        try {
            usuariosExistentes = (Usuario) manager.createQuery("SELECT ma FROM  Usuario ma WHERE ma.username =:usuario").setParameter("usuario", txtUsuarioEstudiante.getText()).getSingleResult();
            if (!usuariosExistentes.getPassword().equals(txtContraseña.getText())) {
                jdialogoLogin("Contraseña Incorrecta", "Inicio de Sesion");
            } else {
                this.estudiante = usuariosExistentes.getEstudiante();
                if (estudiante != null) {
                    stagePantallaPrincipal = new Stage();
                    stagePantallaPrincipal.getIcons().add(new Image("img/TOT-Icon.png"));
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TableView.fxml"));
                    try {
                        this.scenePantallaPrincipal = new Scene((Pane) loader.load());
                       
                    } catch (IOException ex) {
                        Logger.getLogger(InicioSesionFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                 //   scenePantallaPrincipal.setFill(new javafx.scene.paint.Color(250, 250, 250, 0.8));
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
                   // stagePantallaPrincipal.initStyle(StageStyle.TRANSPARENT);
                    stagePantallaPrincipal.setTitle("TOT Learning System - Client");
                    stagePantallaPrincipal.setResizable(false);
                    stagePantallaPrincipal.show();
                    this.stageInicioSesion.close();
                } else {
                    jdialogoLogin("Usuario no sincronizado : " + txtUsuarioEstudiante.getText(), "Inicio de Sesion");
                }
            }

        } catch (Exception e) {
            jdialogoLogin("Usuario Incorrecto", "Inicio de Sesion");
        }

        manager.close();

    }

    @FXML
    private void registrarEstudiante(MouseEvent event) {
        EntityManager manager = enf.createEntityManager();
        Usuario usuariosExistentes = null;
        if ((txtUsuarioEstudiante.getText().length() > 0) && (txtUsuarioEstudiante.getText().length() <= 30)) {
            String nombre = txtUsuarioEstudiante.getText();
            String contraseña = txtContraseña.getText();
            try {
                usuariosExistentes = (Usuario) manager.createQuery("SELECT ma FROM  Usuario ma WHERE ma.username =:usuario ").setParameter("usuario", txtUsuarioEstudiante.getText()).getSingleResult();
                jdialogoLogin("Este usuario ya esta registrado :  " + usuariosExistentes.getUsername(), "Registro");
            } catch (Exception e) {
                Usuario usuarioRegistrar = new Usuario(nombre, contraseña);
                usuarioRegistrar.persist(usuarioRegistrar, manager);
                jdialogoLogin("Usuario creado: " + nombre + contraseña, "Registro");
            }
        }
        manager.close();
    }

    @FXML
    private void Sincronizar(MouseEvent event) {
        Inicio inicio = new Inicio();
        inicio.run();

    }
}
