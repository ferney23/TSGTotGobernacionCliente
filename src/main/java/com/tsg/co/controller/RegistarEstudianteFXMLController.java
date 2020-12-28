/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.co.controller;

import com.tsg.co.model.Usuario;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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

/**
 * FXML Controller class
 *
 * @author Ferney
 */
public class RegistarEstudianteFXMLController implements Initializable {

    @FXML
    private Label labelContraseña;
    @FXML
    private TextField txtUsuario;
    @FXML
    private PasswordField txtContraseña;
    @FXML
    private Label labelUsuario;
    @FXML
    private Button btnRegistrarse;
    @FXML
    private Button btnIniciarSesion;
    private Stage stageInicioSesion;
    @FXML
    private AnchorPane panelInferior;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void registroEstudiante(MouseEvent event) {
        Usuario usuarioRegistrar = new Usuario(txtUsuario.getText(), txtContraseña.getText());
        usuarioRegistrar.persist(usuarioRegistrar);

    }

    @FXML
    private void inicioSesion(MouseEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/InicioSesionFXML.fxml"));
            stageInicioSesion.setScene(new Scene((Pane) loader.load()));
            InicioSesionFXMLController inicioFXMLController = loader.<InicioSesionFXMLController>getController();
            inicioFXMLController.setStageInicio(stageInicioSesion);

        } catch (IOException ex) {
            Logger.getLogger(RegistarEstudianteFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public Stage getStageInicioSesion() {
        return stageInicioSesion;
    }

    public void setStageInicioSesion(Stage stageInicioSesion) {
        this.stageInicioSesion = stageInicioSesion;
    }

}
