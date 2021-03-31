/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.co.controller;

import com.jfoenix.controls.JFXListView;
import com.tsg.co.model.ArchivoMensajeKiosco;
import com.tsg.co.model.CustomImage;
import com.tsg.co.model.Estudiante;
import com.tsg.co.model.MensajeKiosco;
import com.tsg.co.vista.ViewMensaje;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
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
public class MensajesController implements Initializable {

    @FXML
    private Button volverInicio;
    private Scene sceneInicioSesion;
    private Stage stageInicioSesion;
    private Stage stagePrincipal;
    private Scene scenePrincipal;

    private EntityManagerFactory enf;

    private Estudiante estudiante;

    @FXML
    private BorderPane panelInfo;
    @FXML
    private BorderPane panelInfoGrado;
    @FXML
    private Pane panelPerfilEstudiante;
    @FXML
    private Label codigoEstudiante;
    @FXML
    private Label nombreEstudiante;
    @FXML
    private Label institucionName;
    @FXML
    private Label ubicacionInstitucion;
    @FXML
    private Pane panelInfoBotones;
    @FXML
    private Button btnActualizar;
    @FXML
    private Pane panelLateral;
    @FXML
    private BorderPane panelPrincipal;
    @FXML
    private TableView<ViewMensaje> tablatareass = new TableView<>();
    @FXML
    private TableColumn<ViewMensaje, String> imageColumn = new TableColumn<>();
    @FXML
    private TableColumn<ViewMensaje, String> columnTareasPendientes = new TableColumn<>();

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
    private void volverPantallaPrincipal(MouseEvent event) {

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
            Logger.getLogger(MensajesController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void mensajeSeleccionado(MouseEvent event) {
        ViewMensaje viewMensaje = tablatareass.getSelectionModel().getSelectedItem();
        System.out.println(viewMensaje.getNombre());

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RespuestaMensajes.fxml"));
        //Stage stage = new Stage(StageStyle.DECORATED);
        try {
            this.scenePrincipal.setRoot((Pane) loader.load());
        } catch (IOException ex) {
            Logger.getLogger(MensajesController.class.getName()).log(Level.SEVERE, null, ex);
        }

        RespuestaMensajesController controller = loader.<RespuestaMensajesController>getController();
        controller.setEnf(enf);
        controller.setEstudiante(estudiante);
        controller.setMensajeKiosco(viewMensaje.getMensajeKiosco());

        controller.setScenePrincipal(scenePrincipal);
        controller.setStagePantallaPrincipal(stagePrincipal);
        controller.setSceneInicioSesion(sceneInicioSesion);
        controller.setStageInicioSesion(stageInicioSesion);
        controller.mostrarArchivo();
        controller.respuestas();

        // controller.setTarea(tarea);
        // controller.setMateria(mat);
        this.stagePrincipal.getIcons().add(new Image("/img/TOT-Icon.png"));

        this.stagePrincipal.setTitle("KOT Learning System - Tarea");
        this.stagePrincipal.setResizable(false);
        this.stagePrincipal.show();

    }

    public void viewMensajes() {

        codigoEstudiante.setText("Codigo: " + estudiante.getIdEstudiante().toString());
        nombreEstudiante.setText(estudiante.getNombres() + " " + estudiante.getApellidos());
       //institucionName.setText(es.getInstitucion());
       // ubicacionInstitucion.setText(es.getUbicacionInstitucion());
        CustomImage item_1 = new CustomImage(new ImageView(new Image("img/librotareas.png")));

        imageColumn.setGraphic(item_1.getImage());

        EntityManager em = enf.createEntityManager();
        List<MensajeKiosco> mensajeKioscos = em.createQuery("SELECT ma FROM MensajeKiosco ma WHERE ma.estudiante.idEstudiante= : idEstudiante").setParameter("idEstudiante", estudiante.getIdEstudiante()).getResultList();
        ObservableList<MensajeKiosco> observableListMensajesKioscos = FXCollections.observableArrayList(mensajeKioscos);

        for (MensajeKiosco obs : observableListMensajesKioscos) {
            ViewMensaje vistaModeloMensajes = new ViewMensaje();
            vistaModeloMensajes.setNombre(obs.getNombre());
            vistaModeloMensajes.setMateria(obs.getMateria().getTitulo());
            vistaModeloMensajes.setMensajeKiosco(obs);

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
            tablatareass.getItems().add(vistaModeloMensajes);
        }

        // vistaModeloMensajes.setVerMas(buttons);
        imageColumn.setCellValueFactory(new PropertyValueFactory<ViewMensaje, String>("materia"));
        columnTareasPendientes.setCellValueFactory(new PropertyValueFactory<ViewMensaje, String>("nombre"));
        // columnAdjuntos.setCellValueFactory(new PropertyValueFactory<ViewMensaje, HBox>("verMas"));

        em.close();

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
