package com.tsg.co.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.tsg.co.Sincronizacion.Inicio;
import com.tsg.co.model.CustomImage;
import com.tsg.co.model.Estudiante;
import com.tsg.co.model.InfoGrado;
import com.tsg.co.model.Materias;
import com.tsg.co.model.Tareas;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * FXML Controller class
 *
 * @author Ferney
 */
public class TableViewController implements Initializable {

    private  EntityManager manager;
    private  EntityManagerFactory enf;
    private Materias materias;
    private Estudiante estudiante;
    private List<Tareas> tareaselec = new ArrayList<Tareas>();

    @FXML
    private Label progress;
    public static Label label;
    @FXML
    private ProgressBar progressBar;
    public static ProgressBar statProgressBar;
    @FXML
    private Button btnActualizar;
    @FXML
    private Button btnRecargar;
    @FXML
    private BorderPane panelInfo;
    @FXML
    private Pane panelInfoBotones;
    @FXML
    private Label labelTareasPendientes;
    @FXML
    private Label labelContadortareas;
    @FXML
    private Label codigoEstudiante;
    @FXML
    private Label nombreEstudiante;
    @FXML
    private ImageView imagenViewGrado;
    @FXML
    private Label institucionName;
    @FXML
    private Label ubicacionInstitucion;
    @FXML
    private BorderPane panelInfoGrado;
    @FXML
    private AnchorPane panelNotificaciones;
    @FXML
    private Pane panelLateral;
    @FXML
    private JFXListView<Materias> jlistMaterias = new JFXListView<Materias>();
    @FXML
    private BorderPane panelPrincipal;
    @FXML
    private TableView<CustomImage> tablatareass = new TableView<>();

    @FXML
    private TableColumn<CustomImage, String> columnTareasPendientes = new TableColumn<>();
    @FXML
    private TableColumn<CustomImage, Image> imageColumn = new TableColumn<>();
    @FXML
    private Button btnVerMas;
    @FXML
    private Label labelMaterias;

    private InfoGrado infoGrado;
    @FXML
    private Button btnNotificaciones;
    @FXML
    private Button btnListado;
    @FXML
    private Pane panelPerfilEstudiante;
    @FXML
    private Label labelEvaluacionesPendientes;
    @FXML
    private Label labelContadorevaluacion;
    @FXML
    private Image imagenGrado;

    private ClasesMaterialController clasesMaterialController;

    private Stage stagePantallaPrincipal;
    private Scene scenePrincipal;
    private Scene sceneInicioSesion;
    private Stage stageInicioSesion;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //   llenarEstudiante();
        //   listarMaterias();
    }

    public EntityManager getManager() {
        return manager;
    }

    public void setManager(EntityManager manager) {
        this.manager = manager;
    }

    public EntityManagerFactory getEnf() {
        return enf;
    }

    public void setEnf(EntityManagerFactory enf) {
        this.enf = enf;
    }

   
    
    
    public ClasesMaterialController getClasesMaterialController() {
        return clasesMaterialController;
    }

    public void setClasesMaterialController(ClasesMaterialController clasesMaterialController) {
        this.clasesMaterialController = clasesMaterialController;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;

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

    public Stage getStagePantallaPrincipal() {
        return stagePantallaPrincipal;
    }

    public void setStagePantallaPrincipal(Stage stagePantallaPrincipal) {
        this.stagePantallaPrincipal = stagePantallaPrincipal;
    }

    public void llenarEstudiante() {
     //   enf = Persistence.createEntityManagerFactory("tsg");
       // manager = enf.createEntityManager();
        CustomImage item_1 = new CustomImage(new ImageView(new Image("img/librotareas.png")));

        imageColumn.setGraphic(item_1.getImage());
        imageColumn.setCellValueFactory(new PropertyValueFactory<CustomImage, Image>("image"));

        //Estudiante estudiantes = (Estudiante) manager.createQuery("SELECT ma FROM Estudiante ma WHERE ma.idEstudiante= :id ").setParameter("id", 209L).getSingleResult();
        if (estudiante != null) {

            codigoEstudiante.setText("Codigo: " + estudiante.getIdEstudiante().toString());
            nombreEstudiante.setText(estudiante.getNombres() + " " + estudiante.getApellidos());
            Set<InfoGrado> grado = estudiante.getInfoGrados();

            for (InfoGrado es : grado) {
                institucionName.setText(es.getInstitucion());
                ubicacionInstitucion.setText(es.getUbicacionInstitucion());

                imagenViewGrado.setImage(new Image("img/gradoMaterias" + es.getIdInfoGrado() + ".png"));

            }

        }

    }

    public void listarMaterias() {
        jlistMaterias.getItems().clear();
     //   enf = Persistence.createEntityManagerFactory("tsg");
       // manager = enf.createEntityManager();
        //listMaterias.getItems().add(materias)
        Set<Materias> materiases = estudiante.getMateriases();

        for (Materias materiase : materiases) {
            jlistMaterias.getItems().add(materiase);
        }

    }

    @FXML
    private void seleccionarTareas(MouseEvent event) {
        
        if (enf==null) {
           enf = Persistence.createEntityManagerFactory("tsg");
            System.out.println("com.tsg.co.controller.TableViewController.seleccionarTareas()");
            System.out.println("aca vengo null");
        }
     
        manager = enf.createEntityManager();

        tablatareass.getItems().clear();
        this.materias = jlistMaterias.getSelectionModel().getSelectedItem();
        System.out.println(this.materias.getTitulo());

        List<Tareas> tareas = manager.createQuery("SELECT ma FROM Tareas ma WHERE ma.materia.idMateria= :id and ma.estudiante.idEstudiante= :idEstudiante ").setParameter("id", this.materias.getIdMateria()).setParameter("idEstudiante", estudiante.getIdEstudiante()).getResultList();

        ObservableList<Tareas> observableListTareas = FXCollections.observableArrayList(tareas);
        int pendientes = 0;
        tareaselec.clear();
        tareaselec.addAll(observableListTareas);

        for (Tareas tar : observableListTareas) {
            //TableColumn<CustomImage, Image> imagecolumss  =  new TableColumn<>();
            CustomImage itemlist = new CustomImage();
            itemlist.setNombreTarea(tar.getNombreTarea());
            //validacion SI ESTA ENTREGADA  if(tar.)
            CustomImage item_1 = null;
            if (tar.getSubida().getEntregas() != null && tar.getSubida().getEntregas().size() != 0) {
                item_1 = new CustomImage(new ImageView(new Image("img/trueyes.png")));
                item_1.getImage().setFitHeight(25);
                item_1.getImage().setFitWidth(25);
                //tar.setImage(item_1);

            } else {
                item_1 = new CustomImage(new ImageView(new Image("img/dangerNo.png")));
                item_1.getImage().setFitHeight(30);
                item_1.getImage().setFitWidth(40);
                // tar.setImage(item_1);
                pendientes++;
            }
            itemlist.setCodigo(tar.getCodigo());
            itemlist.setImage(item_1.getImage());

            //imagecolum.setCellValueFactory((Callback<TableColumn.CellDataFeatures<CustomImage, Image>, ObservableValue<Image>>) imagecolumss);
            tablatareass.getItems().add(itemlist);
        }
        labelContadortareas.setText("" + pendientes);
        //columnEstadoTareas.setCellValueFactory(new PropertyValueFactory<Tareas, Long>("id"));
        //imagecolum.setCellValueFactory(new PropertyValueFactory<CustomImage, String>("image"));
        imageColumn.setCellValueFactory(new PropertyValueFactory<CustomImage, Image>("image"));
        columnTareasPendientes.setCellValueFactory(new PropertyValueFactory<CustomImage, String>("nombreTarea"));
        labelMaterias.setText(materias.getTitulo().toUpperCase());

    }

    
    public void listarTareas(Materias materias){
        enf = Persistence.createEntityManagerFactory("tsg");
        manager = enf.createEntityManager();

        tablatareass.getItems().clear();
        //this.materias = jlistMaterias.getSelectionModel().getSelectedItem();
        //System.out.println(this.materias.getTitulo());

        List<Tareas> tareas = manager.createQuery("SELECT ma FROM Tareas ma WHERE ma.materia.idMateria= :id and ma.estudiante.idEstudiante= :idEstudiante ").setParameter("id", materias.getIdMateria()).setParameter("idEstudiante", estudiante.getIdEstudiante()).getResultList();

        ObservableList<Tareas> observableListTareas = FXCollections.observableArrayList(tareas);
        int pendientes = 0;
        tareaselec.clear();
        tareaselec.addAll(observableListTareas);

        for (Tareas tar : observableListTareas) {
            //TableColumn<CustomImage, Image> imagecolumss  =  new TableColumn<>();
            CustomImage itemlist = new CustomImage();
            itemlist.setNombreTarea(tar.getNombreTarea());
            //validacion SI ESTA ENTREGADA  if(tar.)
            CustomImage item_1 = null;
            if (tar.getSubida().getEntregas() != null && tar.getSubida().getEntregas().size() != 0) {
                item_1 = new CustomImage(new ImageView(new Image("img/trueyes.png")));
                item_1.getImage().setFitHeight(25);
                item_1.getImage().setFitWidth(25);
                //tar.setImage(item_1);

            } else {
                item_1 = new CustomImage(new ImageView(new Image("img/dangerNo.png")));
                item_1.getImage().setFitHeight(30);
                item_1.getImage().setFitWidth(40);
                // tar.setImage(item_1);
                pendientes++;
            }
            itemlist.setCodigo(tar.getCodigo());
            itemlist.setImage(item_1.getImage());

            //imagecolum.setCellValueFactory((Callback<TableColumn.CellDataFeatures<CustomImage, Image>, ObservableValue<Image>>) imagecolumss);
            tablatareass.getItems().add(itemlist);
        }
        labelContadortareas.setText("" + pendientes);
        //columnEstadoTareas.setCellValueFactory(new PropertyValueFactory<Tareas, Long>("id"));
        //imagecolum.setCellValueFactory(new PropertyValueFactory<CustomImage, String>("image"));
        imageColumn.setCellValueFactory(new PropertyValueFactory<CustomImage, Image>("image"));
        columnTareasPendientes.setCellValueFactory(new PropertyValueFactory<CustomImage, String>("nombreTarea"));
        labelMaterias.setText(materias.getTitulo().toUpperCase());
        
    }
    
    
    @FXML
    private void actionClases(ActionEvent event) {

        if (materias != null) {
            System.out.println(materias.getTitulo());
            try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ClasesMaterialFXML.fxml"));
                //Stage stage = new Stage(StageStyle.DECORATED);

                this.scenePrincipal.setRoot((Pane) loader.load());
                this.stagePantallaPrincipal.setScene(this.scenePrincipal);
                clasesMaterialController = loader.<ClasesMaterialController>getController();
                clasesMaterialController.setEnf(enf);
                clasesMaterialController.setManager(manager);
                clasesMaterialController.setEstudiante(estudiante);
                clasesMaterialController.setMaterias(materias);
                clasesMaterialController.informacionMateria();
                clasesMaterialController.ClasesMateriales(materias);
                clasesMaterialController.setScenePrincipal(scenePrincipal);
                clasesMaterialController.setStageInicioSesion(stageInicioSesion);
                clasesMaterialController.setSceneInicioSesion(sceneInicioSesion);
                clasesMaterialController.setStagePrincipal(stagePantallaPrincipal);
                this.stagePantallaPrincipal.getIcons().add(new Image("/img/TOT-Icon.png"));
                this.stagePantallaPrincipal.setTitle("TOT Learning System - Tarea");
                this.stagePantallaPrincipal.setResizable(false);
                this.stagePantallaPrincipal.show();

                // this.stagePantallaPrincipal.close();
            } catch (IOException e) {

            }
        } else {
            labelMaterias.setText("SELECCIONE UNA MATERIA ");
        }
    }

    @FXML
    private void tareaSelecionada(MouseEvent event) {

        try {
            Tareas tarea = null;
            for (Tareas tar : tareaselec) {
                if (tar.getCodigo().equals(tablatareass.getSelectionModel().getSelectedItem().getCodigo())) {
                    tarea = tar;
                }
            }
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TareasArchivosFXML.fxml"));
            //Stage stage = new Stage(StageStyle.DECORATED);

            this.scenePrincipal.setRoot((Pane) loader.load());
            
            this.stagePantallaPrincipal.setScene(this.scenePrincipal);
           // this.stagePantallaPrincipal.setWidth(878);
          //  this.stagePantallaPrincipal.setHeight(688);
            CustomImage obj = (CustomImage) tablatareass.getSelectionModel().getSelectedItem();
            Materias mat = jlistMaterias.getSelectionModel().getSelectedItem();
            TareasArchivosFXMLController controller = loader.<TareasArchivosFXMLController>getController();
            controller.setEnf(enf);
            controller.setManager(manager);
            
            controller.setEstudiante(estudiante);
            controller.setScenePrincipal(scenePrincipal);
            controller.setStagePantallaPrincipal(stagePantallaPrincipal);
            controller.setSceneInicioSesion(sceneInicioSesion);
            controller.setStageInicioSesion(stageInicioSesion);
            controller.setTarea(tarea);
            controller.setMateria(mat);
            this.stagePantallaPrincipal.getIcons().add(new Image("/img/TOT-Icon.png"));

            this.stagePantallaPrincipal.setTitle("TOT Learning System - Tarea");
            this.stagePantallaPrincipal.setResizable(false);
            this.stagePantallaPrincipal.show();

            System.out.println(tablatareass.getSelectionModel().getSelectedItem());
        } catch (IOException ex) {
            Logger.getLogger(TableViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Materias getMaterias() {
        return materias;
    }

    public void setMaterias(Materias materias) {
        this.materias = materias;
    }
    
    
    
    
    @FXML
    private void sincronizar(MouseEvent event) {

       // manager.close();
        Inicio inicio = new Inicio();
        inicio.run();
        llenarEstudiante();
       // inicio.setEnf(enf);
       // inicio.setManager(manager);
        listarMaterias();

    }

}
