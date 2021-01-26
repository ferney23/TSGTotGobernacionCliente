/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.co.controller;

import com.jfoenix.controls.JFXButton;
import com.tsg.co.model.Clases;
import com.tsg.co.model.CustomImage;
import com.tsg.co.model.Estudiante;
import com.tsg.co.model.InfoGrado;
import com.tsg.co.model.MaterialEstudio;
import com.tsg.co.model.Materias;
import com.tsg.co.model.Tareas;
import com.tsg.co.vista.ViewMaterialEstudio;
import com.tsg.co.vista.VistaModeloClases;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
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
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * FXML Controller class
 *
 * @author Ferney
 */
public class ClasesMaterialController implements Initializable {

  //  private  EntityManager manager;
    private  EntityManagerFactory enf;
    private Materias materias;
    private Estudiante estudiante;

    @FXML
    private BorderPane l;
    @FXML
    private Label labelNombres, labelMaterias;

    @FXML
    private Pane paneIcono;
    @FXML
    private Label labelIcono;
    @FXML
    private TableColumn<VistaModeloClases, String> cellClase = new TableColumn<>();
    @FXML
    private TableColumn<VistaModeloClases, String> cellTema = new TableColumn<>();
    @FXML
    private TableView<VistaModeloClases> tableMaterialEstudio = new TableView<>();
    //private ObservableList<VistaModeloClases> observaMaterial;
    @FXML
    private Label panelTableClases;
    @FXML
    private TableColumn<VistaModeloClases, Button> cellMaterialEstudio = new TableColumn<>();
    @FXML
    private JFXButton btnPantallaInicio;

    private TableViewController tableViewController;

    private Stage stagePrincipal;
    private Scene scenePrincipal;
    @FXML
    private JFXButton btnCerrarSesion;

    private Scene sceneInicioSesion;
    private Stage stageInicioSesion;
    @FXML
    private TableView<ViewMaterialEstudio> tableArchivos;
    @FXML
    private TableColumn<ViewMaterialEstudio, String> ColNombre;
    @FXML
    private TableColumn<ViewMaterialEstudio, String> colDescripcion;
    @FXML
    private TableColumn<ViewMaterialEstudio, Button> colAdjunto;
    @FXML
    private Pane paneltitulo;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public TableViewController getTableViewController() {
        return tableViewController;
    }

    public void setTableViewController(TableViewController tableViewController) {
        this.tableViewController = tableViewController;
    }

    
    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Materias getMaterias() {
        return materias;
    }

    public void setMaterias(Materias materias) {
        this.materias = materias;
    }

    public void informacionMateria() {
        labelNombres.setText(estudiante.getNombres() + " " + estudiante.getApellidos());
        labelMaterias.setText(materias.getTitulo());

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

   

    public EntityManagerFactory getEnf() {
        return enf;
    }

    public void setEnf(EntityManagerFactory enf) {
        this.enf = enf;
    }

    
    
    public void ClasesMateriales(Materias materias) {

      //  enf = Persistence.createEntityManagerFactory("tsg");
        EntityManager   manager = enf.createEntityManager();

        List<Clases> clases = manager.createQuery("SELECT ma FROM Clases ma WHERE ma.materia.idMateria= :id").setParameter("id", materias.getIdMateria()).getResultList();
        ObservableList<Clases> observableListClases = FXCollections.observableArrayList(clases);

        for (Clases observableListClase : observableListClases) {
            System.out.println(observableListClase.getNombre() + "Estoy dentro del observable");
            Button buttonVerMas = new Button("Ver Mas");
            buttonVerMas.setMinHeight(30);
            buttonVerMas.setMaxSize(100, 30);
            buttonVerMas.getStylesheets().add(getClass().getResource("/styles/botones.css").toExternalForm());

            VistaModeloClases vistaModeloClases = new VistaModeloClases();
            vistaModeloClases.setNombre(observableListClase.getNombre());
            vistaModeloClases.setTema(observableListClase.getTema());
            vistaModeloClases.setVerMas(buttonVerMas);

            buttonVerMas.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent e) {

                    viewMaterialEstudio(observableListClase);

                }
            });

            tableMaterialEstudio.getItems().add(vistaModeloClases);

        }

        cellClase.setCellValueFactory(new PropertyValueFactory<VistaModeloClases, String>("nombre"));
        cellTema.setCellValueFactory(new PropertyValueFactory<VistaModeloClases, String>("tema"));
        cellMaterialEstudio.setCellValueFactory(new PropertyValueFactory<VistaModeloClases, Button>("verMas"));
        manager.close();
    }

    public void viewMaterialEstudio(Clases clases) {

       // enf = Persistence.createEntityManagerFactory("tsg");
        EntityManager manager = enf.createEntityManager();
        tableArchivos.getItems().clear();
        List<MaterialEstudio> materialEstudios = manager.createQuery("SELECT ma FROM MaterialEstudio ma WHERE ma.clase.idClases= :id").setParameter("id", clases.getIdClases()).getResultList();
        ObservableList<MaterialEstudio> observableListMaterialEstudios = FXCollections.observableArrayList(materialEstudios);

        for (MaterialEstudio observableListEstudio : observableListMaterialEstudios) {

            Button buttonVerMas = new Button(observableListEstudio.getNombreArchivo());
            buttonVerMas.getStylesheets().add(getClass().getResource("/styles/botones.css").toExternalForm());
            ViewMaterialEstudio viewMaterialEstudio = new ViewMaterialEstudio();

            viewMaterialEstudio.setNombre(observableListEstudio.getNombre());
            viewMaterialEstudio.setTema(observableListEstudio.getClase().getTema());
            viewMaterialEstudio.setClase(observableListEstudio.getClase().getNombre());
            viewMaterialEstudio.setDescripcion(observableListEstudio.getDescripcion());
            viewMaterialEstudio.setBtnArchivoAdjunto(buttonVerMas);
            buttonVerMas.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent e) {
                    viewArchivos(observableListEstudio.getRuta());
                }
            });

            tableArchivos.getItems().add(viewMaterialEstudio);

        }

        ColNombre.setCellValueFactory(new PropertyValueFactory<ViewMaterialEstudio, String>("nombre"));
        //  colTema.setCellValueFactory(new PropertyValueFactory<ViewMaterialEstudio, String>("tema"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory<ViewMaterialEstudio, String>("descripcion"));
        // colClase.setCellValueFactory(new PropertyValueFactory<ViewMaterialEstudio, String>("clase"));
        colAdjunto.setCellValueFactory(new PropertyValueFactory<ViewMaterialEstudio, Button>("btnArchivoAdjunto"));

        manager.close();
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
            tableViewController.seleccionarTareas(materias);
            tableViewController.setSceneInicioSesion(sceneInicioSesion);
            tableViewController.setStageInicioSesion(stageInicioSesion);
            
            

        } catch (IOException ex) {
            Logger.getLogger(ClasesMaterialController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void eventCerrarSesion(MouseEvent event) {

        //  this.stageInicioSesion.getIcons().add(new Image("img/TOT-Icon.png"));
      
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
