/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.co.controller;

import com.tsg.co.model.ArchivoMensajeKiosco;
import com.tsg.co.model.Clases;
import com.tsg.co.model.Estudiante;
import com.tsg.co.model.Materias;
import com.tsg.co.model.MensajeKiosco;
import com.tsg.co.vista.ViewMensaje;
import com.tsg.co.vista.VistaModeloClases;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 * FXML Controller class
 *
 * @author Ferney
 */
public class MensajesFxmlController implements Initializable {

    @FXML
    private Button volverInicio;
    private Scene sceneInicioSesion;
    private Stage stageInicioSesion;
    private Stage stagePrincipal;
    private Scene scenePrincipal;

    private EntityManagerFactory enf;

    private Estudiante estudiante;
    @FXML
    private AnchorPane anchorPrincipal;
    @FXML
    private TableView<ViewMensaje> tableMensaje = new TableView<>();
    @FXML
    private TableColumn<ViewMensaje, String> columnNombre = new TableColumn<>();
    @FXML
    private TableColumn<ViewMensaje, String> columnMaterias = new TableColumn<>();
    @FXML
    private TableColumn<ViewMensaje, HBox> columnAdjuntos = new TableColumn<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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

    public Stage getStagePrincipal() {
        return stagePrincipal;
    }

    public void setStagePrincipal(Stage stagePrincipal) {
        this.stagePrincipal = stagePrincipal;
    }

    public Scene getScenePrincipal() {
        return scenePrincipal;
    }

    public void setScenePrincipal(Scene scenePrincipal) {
        this.scenePrincipal = scenePrincipal;
    }

    public EntityManagerFactory getEnf() {
        return enf;
    }

    public void setEnf(EntityManagerFactory enf) {
        this.enf = enf;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    @FXML
    private void pantallaPrincipal(MouseEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TableView.fxml"));
            this.scenePrincipal.setRoot((Pane) loader.load());
            // this.inicioSesionFXMLController.getStagePantallaPrincipal().setScene(this.scenePrincipal);
            this.stagePrincipal.setScene(scenePrincipal);
            TableViewController tableViewController = loader.<TableViewController>getController();
            tableViewController.setScenePrincipal(scenePrincipal);
            tableViewController.setStagePantallaPrincipal(stagePrincipal);
            tableViewController.setEnf(enf);
            // tableViewController.setManager(manager);

            tableViewController.setEstudiante(estudiante);
            tableViewController.llenarEstudiante();
            tableViewController.listarMaterias();
            tableViewController.resumenTareasPendintes();
            //  tableViewController.seleccionarTareas(materias);
            tableViewController.setSceneInicioSesion(sceneInicioSesion);
            tableViewController.setStageInicioSesion(stageInicioSesion);

        } catch (IOException ex) {
            Logger.getLogger(ClasesMaterialController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void viewMensajes() {

        EntityManager em = enf.createEntityManager();
        List<MensajeKiosco> mensajeKioscos = em.createQuery("SELECT ma FROM MensajeKiosco ma WHERE ma.estudiante.idEstudiante= : idEstudiante").setParameter("idEstudiante", estudiante.getIdEstudiante()).getResultList();
        ObservableList<MensajeKiosco> observableListMensajesKioscos = FXCollections.observableArrayList(mensajeKioscos);

        for (MensajeKiosco obs : observableListMensajesKioscos) {
            ViewMensaje vistaModeloMensajes = new ViewMensaje();
            vistaModeloMensajes.setNombre(obs.getNombre());
            vistaModeloMensajes.setMateria(obs.getMateria().getTitulo());
            for (ArchivoMensajeKiosco archivoMensaje : obs.getArchivoMensajeKioscos()) {
                Button buttonVerMas = new Button("Ver Mas");
                //buttonVerMas.setMinHeight(30);
                // buttonVerMas.setMaxSize(100, 30);
                buttonVerMas.getStylesheets().add(getClass().getResource("/styles/botones.css").toExternalForm());
                buttonVerMas.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                        viewArchivos(archivoMensaje.getUrl());
                    }
                });

                vistaModeloMensajes.getVerMas().getChildren().add(buttonVerMas);
            }
            tableMensaje.getItems().add(vistaModeloMensajes);
        }

        // vistaModeloMensajes.setVerMas(buttons);
        columnNombre.setCellValueFactory(new PropertyValueFactory<ViewMensaje, String>("nombre"));
        columnMaterias.setCellValueFactory(new PropertyValueFactory<ViewMensaje, String>("materia"));
        columnAdjuntos.setCellValueFactory(new PropertyValueFactory<ViewMensaje, HBox>("verMas"));

        em.close();

    }

    @FXML
    private void mensajesSeleccionar(MouseEvent event) {
        ViewMensaje viewMensaje = tableMensaje.getSelectionModel().getSelectedItem();
        System.out.println(viewMensaje.getNombre());

    }

    public void viewArchivos(String ruta) {
        try {
            File f = new File(ruta);
            System.out.println(ruta);
            try {
                Desktop.getDesktop().open(f);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
        }
    }

}
