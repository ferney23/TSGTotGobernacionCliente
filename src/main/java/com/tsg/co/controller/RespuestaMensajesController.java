/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.co.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.tsg.co.model.AchivosTot;
import com.tsg.co.model.ArchivoMensajeKiosco;
import com.tsg.co.model.CustomImage;
import com.tsg.co.model.Estudiante;
import com.tsg.co.model.Estudiante_;
import com.tsg.co.model.MensajeKiosco;
import com.tsg.co.model.RespuestaMensaje;
import com.tsg.co.model.RespuestaMensaje_;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 * FXML Controller class
 *
 * @author Ferney
 */
public class RespuestaMensajesController implements Initializable {

    private EntityManagerFactory enf;
    private Scene scenePrincipal;
    private Scene sceneInicioSesion;
    private Stage stagePantallaPrincipal;
    private Stage stageInicioSesion;
    private Estudiante estudiante;
    private MensajeKiosco mensajeKiosco;

    @FXML
    private AnchorPane anchorpane;
    @FXML
    private TableView<CustomImage> tableArchivo;
    @FXML
    private TableColumn<CustomImage, Image> imagetype;
    @FXML
    private TableColumn<CustomImage, String> nombrematerial1;
    @FXML
    private TableColumn<CustomImage, String> optionstable;
    @FXML
    private TableView<CustomImage> tableEntregas;
    @FXML
    private TableColumn<CustomImage, Image> imagentype1;
    @FXML
    private TableColumn<CustomImage, String> nombrematerial;
    @FXML
    private TableColumn<CustomImage, String> estadoEntrega;
    @FXML
    private Pane dibujo;
    @FXML
    private Button dibujoitem;
    @FXML
    private Label nombretarea;
    @FXML
    private Label nombreestudiante;
    @FXML
    private Label materianombre;
    @FXML
    private Button botoncargar;
    @FXML
    private Button btnPantallaPrincipal;
    @FXML
    private StackPane stackPaneConfirmarTarea;

    private MensajesController mensajesFxmlController;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public EntityManagerFactory getEnf() {
        return enf;
    }

    public void setEnf(EntityManagerFactory enf) {
        this.enf = enf;
    }

    public Scene getScenePrincipal() {
        return scenePrincipal;
    }

    public void setScenePrincipal(Scene scenePrincipal) {
        this.scenePrincipal = scenePrincipal;
    }

    public Scene getSceneInicioSesion() {
        return sceneInicioSesion;
    }

    public void setSceneInicioSesion(Scene SceneInicioSesion) {
        this.sceneInicioSesion = SceneInicioSesion;
    }

    public Stage getStagePantallaPrincipal() {
        return stagePantallaPrincipal;
    }

    public void setStagePantallaPrincipal(Stage stagePantallaPrincipal) {
        this.stagePantallaPrincipal = stagePantallaPrincipal;
    }

    public Stage getStageInicioSesion() {
        return stageInicioSesion;
    }

    public void setStageInicioSesion(Stage stageInicioSesion) {
        this.stageInicioSesion = stageInicioSesion;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        nombreestudiante.setText(estudiante.getNombres() + " " + estudiante.getApellidos());

        this.estudiante = estudiante;
    }

    public MensajeKiosco getMensajeKiosco() {
        return mensajeKiosco;
    }

    public void setMensajeKiosco(MensajeKiosco mensajeKiosco) {

        this.mensajeKiosco = mensajeKiosco;

        nombretarea.setText(mensajeKiosco.getNombre());
        materianombre.setText(mensajeKiosco.getMateria().getTitulo());
    }

    @FXML
    private void mouseSelArchivo(MouseEvent event) {
        try {
            tableArchivo.getSelectionModel().getSelectedItem();
            String ruta = tableArchivo.getSelectionModel().getSelectedItem().getRuta();
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

    @FXML
    private void mouseArchivoNuevo(MouseEvent event) {
    }

    @FXML
    private void cargararchivo(MouseEvent event) {
        jdialogoLogin(mensajeKiosco.getNombre());

    }

    @FXML
    private void clickedPantallaPrincipal(MouseEvent event) {
        System.out.println("com.tsg.co.controller.TableViewController.verMensajes()");
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Mensajes.fxml"));
            //Stage stage = new Stage(StageStyle.DECORATED);

            this.scenePrincipal.setRoot((Pane) loader.load());
            //    this.scenePrincipal.setFill(Color.TRANSPARENT);
            this.stagePantallaPrincipal.setScene(this.scenePrincipal);
            mensajesFxmlController = loader.<MensajesController>getController();
            mensajesFxmlController.setEnf(enf);
            mensajesFxmlController.setEstudiante(estudiante);
            mensajesFxmlController.viewMensajes();
            mensajesFxmlController.setScenePrincipal(scenePrincipal);
            mensajesFxmlController.setStageInicioSesion(stageInicioSesion);
            mensajesFxmlController.setSceneInicioSesion(sceneInicioSesion);
            mensajesFxmlController.setStagePrincipal(stagePantallaPrincipal);

            this.stagePantallaPrincipal.getIcons().add(new Image("/img/TOT-Icon.png"));
            this.stagePantallaPrincipal.setTitle("KOT Learning System - Tarea");
            this.stagePantallaPrincipal.setResizable(false);
            //  this.stagePantallaPrincipal.initStyle(StageStyle.TRANSPARENT);
            this.stagePantallaPrincipal.show();

            // this.stagePantallaPrincipal.close();
        } catch (IOException e) {

        }

    }

    public void mostrarArchivo() {
        //  enf = Persistence.createEntityManagerFactory("tsg");
        //  manager = enf.createEntityManager();
        EntityManager manager = enf.createEntityManager();
        List<ArchivoMensajeKiosco> achivosTots1 = manager.createQuery("SELECT ma FROM ArchivoMensajeKiosco ma WHERE ma.mensajeKiosco.id= :id").setParameter("id", this.mensajeKiosco.getId()).getResultList();

        /**
         * List<ArchivoMensajeKiosco> achivosTots = new ArrayList<>(); for
         * (ArchivoMensajeKiosco arch : achivosTots1) {
         * System.out.println(arch); achivosTots.add(arch); }
         *
         */
        ObservableList<ArchivoMensajeKiosco> observableListaArchivos = FXCollections.observableArrayList(achivosTots1);
        List<CustomImage> obj = new ArrayList<>();
        for (ArchivoMensajeKiosco arch : observableListaArchivos) {

            CustomImage itemlist = new CustomImage();
            String[] arch1 = arch.getUrl().split("\\\\");
            itemlist.setNombreTarea(arch1[arch1.length - 1]);
            itemlist.setRuta(arch.getUrl());
            //validacion SI ESTA ENTREGADA  if(tar.)
            CustomImage item_1 = null;
            item_1 = new CustomImage(new ImageView(new Image("img/trueyes.png")));
            item_1.getImage().setFitHeight(25);
            item_1.getImage().setFitWidth(25);
            //tar.setImage(item_1);

            itemlist.setCodigo(arch.getNombre());
            itemlist.setEstadoTarea("ARCHIVO MENSAJE");
            itemlist.setImage(item_1.getImage());

            obj.add(itemlist);

        }
        ObservableList<CustomImage> archivos = FXCollections.observableArrayList(obj);
        tableArchivo.setItems(archivos);
        //lbtnArchivo.setCellValueFactory(new PropertyValueFactory<AchivosTot, Long>("idAchivosTot"));
        // optionstable.setCellValueFactory(new PropertyValueFactory<CustomImage, Image>("image"));
        optionstable.setCellValueFactory(new PropertyValueFactory<CustomImage, String>("estadoTarea"));
        imagetype.setCellValueFactory(new PropertyValueFactory<CustomImage, Image>("image"));
        nombrematerial1.setCellValueFactory(new PropertyValueFactory<CustomImage, String>("nombreTarea"));
        //labelMaterias.setText(materias.getTitulo().toUpperCase());
        manager.close();
    }

    public void respuestas() {
        System.out.println("com.tsg.co.controller.RespuestaMensajesController.respuestas()");

        EntityManager manager = enf.createEntityManager();
        List<RespuestaMensaje> achivosTots1 = manager.createQuery("SELECT ma FROM RespuestaMensaje ma WHERE ma.mensajeKiosco.id =:id").setParameter("id", this.mensajeKiosco.getId()).getResultList();

        ObservableList<RespuestaMensaje> observableListaArchivos = FXCollections.observableArrayList(achivosTots1);
        List<CustomImage> obj = new ArrayList<>();
        for (RespuestaMensaje arch : observableListaArchivos) {

            if (arch.getEstado() == 1L) {
                CustomImage itemlist = new CustomImage();
                itemlist.setNombreTarea(arch.getBody());
                CustomImage item_1 = null;
                item_1 = new CustomImage(new ImageView(new Image("img/trueyes.png")));
                item_1.getImage().setFitHeight(25);
                item_1.getImage().setFitWidth(25);
                //tar.setImage(item_1);

                itemlist.setEstadoTarea("ENTREGADO");
                itemlist.setImage(item_1.getImage());

                obj.add(itemlist);

            } else if (arch.getEstado() == 0L) {
                CustomImage itemlist = new CustomImage();
                itemlist.setNombreTarea(arch.getBody());
                CustomImage item_1 = null;
                item_1 = new CustomImage(new ImageView(new Image("img/trueyes.png")));
                item_1.getImage().setFitHeight(25);
                item_1.getImage().setFitWidth(25);
                //tar.setImage(item_1);

                itemlist.setEstadoTarea("SIN ENTREGAR");
                itemlist.setImage(item_1.getImage());

                obj.add(itemlist);

            }

        }
        ObservableList<CustomImage> archivos = FXCollections.observableArrayList(obj);
        tableEntregas.setItems(archivos);
        //lbtnArchivo.setCellValueFactory(new PropertyValueFactory<AchivosTot, Long>("idAchivosTot"));
        // optionstable.setCellValueFactory(new PropertyValueFactory<CustomImage, Image>("image"));
        estadoEntrega.setCellValueFactory(new PropertyValueFactory<CustomImage, String>("estadoTarea"));
        imagentype1.setCellValueFactory(new PropertyValueFactory<CustomImage, Image>("image"));
        nombrematerial.setCellValueFactory(new PropertyValueFactory<CustomImage, String>("nombreTarea"));
        //labelMaterias.setText(materias.getTitulo().toUpperCase());
        manager.close();

    }

    public JFXDialog jdialogoLogin(String title) {
        // String title = "Inicio de Sesion";
        // String content = "Usuario incorrecto";
        JFXDialogLayout jFXDialogLayout = new JFXDialogLayout();

        jFXDialogLayout.setHeading(new Text(title));
        TextField txtRespuesta = new TextField();
        jFXDialogLayout.setBody(txtRespuesta);
        JFXButton aceptar = new JFXButton("Aceptar");
        aceptar.setButtonType(JFXButton.ButtonType.RAISED);
        //jFXDialogLayout.setActions(aceptar);

        JFXButton cancelar = new JFXButton("Cancelar");
        aceptar.setButtonType(JFXButton.ButtonType.RAISED);
        jFXDialogLayout.setActions(cancelar, aceptar);

        JFXDialog jFXDialog = new JFXDialog(stackPaneConfirmarTarea, jFXDialogLayout, JFXDialog.DialogTransition.BOTTOM);
        jFXDialog.setMinSize(50, 100);
        aceptar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                EntityManager manager = enf.createEntityManager();
                RespuestaMensaje respuestaMensaje = new RespuestaMensaje(0L, txtRespuesta.getText(), mensajeKiosco);
                respuestaMensaje.persist(respuestaMensaje, manager);
                manager.close();
                respuestas();

                jFXDialog.close();

                JFXDialogLayout jFXDialogLayoutAceptar = new JFXDialogLayout();
                jFXDialogLayoutAceptar.setHeading(new Text(title));
                jFXDialogLayoutAceptar.setBody(new Text(" Se guardo la respuesta exitosamente"));
                JFXButton btnCerrar = new JFXButton("Aceptar");
                btnCerrar.setButtonType(JFXButton.ButtonType.RAISED);

                jFXDialogLayoutAceptar.setActions(btnCerrar);

                JFXDialog jFXDialogAceptar = new JFXDialog(stackPaneConfirmarTarea, jFXDialogLayoutAceptar, JFXDialog.DialogTransition.BOTTOM);
                jFXDialogAceptar.setMaxSize(50, 100);
                btnCerrar.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        jFXDialogAceptar.close();
                    }
                });

                jFXDialogAceptar.show();

            }
        });

        cancelar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                jFXDialog.close(); //To change body of generated methods, choose Tools | Templates.
            }
        });
        jFXDialog.show();
        return jFXDialog;

    }
}
