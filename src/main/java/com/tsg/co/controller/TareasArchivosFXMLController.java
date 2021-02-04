package com.tsg.co.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.tsg.co.model.AchivosTot;
import com.tsg.co.model.CustomImage;
import com.tsg.co.model.Entregas;
import com.tsg.co.model.Estudiante;
import com.tsg.co.model.Materias;
import com.tsg.co.model.Profesor;
import com.tsg.co.model.Subida;
import com.tsg.co.model.Tareas;
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * FXML Controller class
 *
 * @author Ferney
 */
public class TareasArchivosFXMLController implements Initializable {

    //  private EntityManager manager;
    private EntityManagerFactory enf;
    private String codigotarea;
    @FXML
    private TableView<CustomImage> tableArchivo = new TableView<CustomImage>();
    @FXML
    private TableView<CustomImage> tableEntregas = new TableView<CustomImage>();
    @FXML
    private TableColumn<CustomImage, Image> imagetype = new TableColumn<>();
    @FXML
    private TableColumn<CustomImage, String> nombrematerial1 = new TableColumn<>();
    @FXML
    private TableColumn<CustomImage, Image> imagentype1 = new TableColumn<>();
    @FXML
    private TableColumn<CustomImage, String> nombrematerial = new TableColumn<>();
    @FXML
    private AnchorPane anchorpane;
    @FXML
    private Label nombretarea;
    @FXML
    private Label nombreestudiante;
    @FXML
    private Label materianombre;
    private Materias materia;
    private Estudiante estudiante;
    private Tareas tarea;
    @FXML
    private TableColumn<CustomImage, String> optionstable = new TableColumn<>();
    @FXML
    private Pane dibujo;
    @FXML
    private Button dibujoitem;
    @FXML
    private Button botoncargar;
    @FXML
    private Button btnPantallaPrincipal;

    private Stage stagePantallaPrincipal;
    private Scene scenePrincipal;
    private Scene sceneInicioSesion;
    private Stage stageInicioSesion;
    @FXML
    private StackPane stackPaneConfirmarTarea;
    @FXML
    private TableColumn<CustomImage, String> estadoEntrega;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
        nombreestudiante.setText(estudiante.getNombres() + " " + estudiante.getApellidos());
    }

    public String getCodigotarea() {
        return codigotarea;
    }

    public Tareas getTarea() {
        return tarea;

    }

    public void setTarea(Tareas tarea) {
        this.tarea = tarea;
        nombretarea.setText(tarea.getNombreTarea());
        materianombre.setText(tarea.getMateria().getTitulo());
        mostrarArchivo();
      
    }

    public Materias getMateria() {
        return materia;
    }

    public void setMateria(Materias materia) {
        this.materia = materia;

    }

    public void setCodigotarea(String codigotarea) {
        this.codigotarea = codigotarea;
        //  codigotarealabel.setText(codigotarea);
    }

    public Stage getStagePantallaPrincipal() {
        return stagePantallaPrincipal;
    }

    public void setStagePantallaPrincipal(Stage stagePantallaPrincipal) {
        this.stagePantallaPrincipal = stagePantallaPrincipal;
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

    @FXML
    private void cargararchivo(MouseEvent event) throws IOException {
        Button b = (Button) event.getSource();
        if (b.getText().equals("Subir Archivos")) {
            FileChooser fileChooser = new FileChooser();
            Stage stage = (Stage) anchorpane.getScene().getWindow();
            File file = fileChooser.showOpenDialog(stage);
            jdialogoLogin("Desea guardar el archivo ? " + file.getName(), "Archivo ", file);

        }
    }

    public void guardarTareaArchivo(File file) throws IOException {

        if (file != null) {
            CustomImage itemlist = new CustomImage();
            itemlist.setNombreTarea(file.getName());
            itemlist.setRuta(file.getPath());
            File Directorio = new File("Data/" + "Tareas/" + this.tarea.getNombreTarea() + "/");
            if (Directorio.exists()) {
                if (Directorio.isDirectory()) {
                    System.out.println("Es una carpeta");
                }
            } else {
                if (Directorio.mkdirs()) {
                    System.out.println("Directorio creado");
                } else {
                    System.out.println("Error al crear directorio");
                }
            }

            System.out.println(this.tarea.getSubida());

            Path dest = Paths.get("Data/" + "Tareas/" + this.tarea.getNombreTarea() + "/" + file.getName());
            Files.copy(file.toPath(), dest, StandardCopyOption.REPLACE_EXISTING);

            String archivoTotcodigo = "";
            Entregas entregaArchivos = guardarEntregas();
            archivoTotcodigo = entregaArchivos.getId() + "" + this.estudiante.getIdEstudiante() + "" + this.tarea.getSubida().getIdSubida();
            Long idArchivoTot = Long.parseLong(archivoTotcodigo);
            guardarArchivo(idArchivoTot, archivoTotcodigo, dest.toString(), entregaArchivos);

              tableEntregas.getItems().add(itemlist);
              imagentype1.setCellValueFactory(new PropertyValueFactory<CustomImage, Image>("image"));
              nombrematerial.setCellValueFactory(new PropertyValueFactory<CustomImage, String>("nombreTarea"));
        }

    }

    public void guardarArchivo(Long idArchivoTot, String archivoTotcodigo, String destino, Entregas entregaArchivos) {
        EntityManager manager = enf.createEntityManager();

        Long idArchivo = null;
        try {
            idArchivo = (long) manager.createQuery("Select MAX(ID) FROM AchivosTot").getSingleResult();
            if (idArchivo >= 1) {
                Long idGuardarArchivo = idArchivo + 1L;
                AchivosTot achivosNuevo = new AchivosTot(idGuardarArchivo, idArchivoTot, archivoTotcodigo, destino, this.tarea.getSubida(), entregaArchivos);
                achivosNuevo.persist(achivosNuevo, manager);
                idArchivo = (long) manager.createQuery("Select MAX(ID) FROM AchivosTot").getSingleResult();
                System.err.println("Ferney");
            }

        } catch (Exception e) {
            AchivosTot achivosNuevo = new AchivosTot(1L, idArchivoTot, archivoTotcodigo, destino, this.tarea.getSubida(), entregaArchivos);
            achivosNuevo.persist(achivosNuevo, manager);
            idArchivo = (long) manager.createQuery("Select MAX(ID) FROM AchivosTot").getSingleResult();

        }

        manager.close();

    }

    public JFXDialog jdialogoLogin(String content, String title, File file) {
        // String title = "Inicio de Sesion";
        // String content = "Usuario incorrecto";
        JFXDialogLayout jFXDialogLayout = new JFXDialogLayout();
        jFXDialogLayout.setHeading(new Text(title));
        jFXDialogLayout.setBody(new Text(content));
        JFXButton aceptar = new JFXButton("Aceptar");
        aceptar.setButtonType(JFXButton.ButtonType.RAISED);
        //jFXDialogLayout.setActions(aceptar);

        JFXButton cancelar = new JFXButton("Cancelar");
        aceptar.setButtonType(JFXButton.ButtonType.RAISED);
        jFXDialogLayout.setActions(cancelar, aceptar);

        JFXDialog jFXDialog = new JFXDialog(stackPaneConfirmarTarea, jFXDialogLayout, JFXDialog.DialogTransition.BOTTOM);
        jFXDialog.setMaxSize(50, 100);
        aceptar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    guardarTareaArchivo(file);

                } catch (IOException ex) {
                    Logger.getLogger(TareasArchivosFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
                jFXDialog.close();

                JFXDialogLayout jFXDialogLayoutAceptar = new JFXDialogLayout();
                jFXDialogLayoutAceptar.setHeading(new Text(title));
                jFXDialogLayoutAceptar.setBody(new Text(" Se guardo el archivo exitosamente"));
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

    public Entregas guardarEntregas() {
        EntityManager manager = enf.createEntityManager();
        Entregas nuevaEntrega = null;
        Long idEntrega = null;
        try {
            idEntrega = (long) manager.createQuery("Select MAX(ID) FROM Entregas").getSingleResult();
            if (idEntrega >= 1) {
                Long idGuardarEntrega = idEntrega + 1L;
                nuevaEntrega = new Entregas(idGuardarEntrega, 0L, this.estudiante, this.tarea.getSubida(), this.tarea, this.tarea.getRegistroTarea());
                nuevaEntrega.persist(nuevaEntrega, manager);
                //      archivoTotcodigo = nuevaEntrega.getId() + "" + this.estudiante.getIdEstudiante() + "" + this.tarea.getSubida().getIdSubida();
                idEntrega = (long) manager.createQuery("Select MAX(ID) FROM Entregas").getSingleResult();
                System.err.println("Ferney");
            }
        } catch (Exception e) {
            System.out.println(e.getCause());
            nuevaEntrega = new Entregas(1L, 0L, this.estudiante, this.tarea.getSubida(), this.tarea, this.tarea.getRegistroTarea());
            nuevaEntrega.persist(nuevaEntrega, manager);
            idEntrega = (long) manager.createQuery("Select MAX(ID) FROM Entregas").getSingleResult();
        }

        manager.close();
        return nuevaEntrega;

    }

    public void mostrarArchivo() {
        //  enf = Persistence.createEntityManagerFactory("tsg");
        //  manager = enf.createEntityManager();
        EntityManager manager = enf.createEntityManager();
        List<AchivosTot> achivosTots1 = manager.createQuery("SELECT ma FROM AchivosTot ma WHERE ma.subida.tareas.id= :id").setParameter("id", tarea.getId()).getResultList();

        List<AchivosTot> achivosTots = new ArrayList<>();
        for (AchivosTot arch : achivosTots1) {
            System.out.println(arch);
            achivosTots.add(arch);
        }
        ObservableList<AchivosTot> observableListaArchivos = FXCollections.observableArrayList(achivosTots);
        List<CustomImage> obj = new ArrayList<>();
        for (AchivosTot arch : observableListaArchivos) {

            CustomImage itemlist = new CustomImage();
            String[] arch1 = arch.getRuta().split("\\\\");
            itemlist.setNombreTarea(arch1[arch1.length - 1]);
            itemlist.setRuta(arch.getRuta());
            //validacion SI ESTA ENTREGADA  if(tar.)
            CustomImage item_1 = null;
            if (arch.getEntrega() == null) {

                item_1 = new CustomImage(new ImageView(new Image("img/trueyes.png")));
                item_1.getImage().setFitHeight(25);
                item_1.getImage().setFitWidth(25);
                //tar.setImage(item_1);

                itemlist.setCodigo(arch.getCodigo());
                itemlist.setEstadoTarea("ARCHIVO TAREA");
                itemlist.setImage(item_1.getImage());

                obj.add(itemlist);

            } else if (arch.getEntrega().getUpp() == 1L) {
                
                item_1 = new CustomImage(new ImageView(new
                 Image("img/trueyes.png")));
                 item_1.getImage().setFitHeight(25);
                 item_1.getImage().setFitWidth(25);
                 itemlist.setCodigo(arch.getCodigo());
                 itemlist.setEstadoTarea("ENTREGADO");
                  itemlist.setImage(item_1.getImage());
                 
                 obj.add(itemlist);
                 
                 
            } else if (arch.getEntrega().getUpp() == 0L) {
                
               item_1 = new CustomImage(new ImageView(new Image("img/trueyes.png")));
                item_1.getImage().setFitHeight(25);
                item_1.getImage().setFitWidth(25);
                //tar.setImage(item_1);
                itemlist.setCodigo(arch.getCodigo());
                itemlist.setEstadoTarea("SIN ENTREGAR ");
                itemlist.setImage(item_1.getImage());

                obj.add(itemlist);
               
            }

            //TableColumn<CustomImage, Image> imagecolumss  =  new TableColumn<>();
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

  

    @FXML
    private void mouseSelArchivo(MouseEvent event) {
            System.out.println("com.tsg.co.controller.TareasArchivosFXMLController.mouseSelArchivo()");
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

        //   System.err.println(tableArchivo1.getSelectionModel().getSelectedItem().getRuta() + "Ferney");
        
            System.err.println("Hola");
            CustomImage customImage= tableEntregas.getSelectionModel().getSelectedItem();
            String ruta = customImage.getRuta();
            File f = new File(ruta);
            try {
                Desktop.getDesktop().open(f);
            } catch (IOException e) {
                e.printStackTrace();
            }
        

    }

    @FXML
    private void clickedPantallaPrincipal(MouseEvent event) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TableView.fxml"));
        try {
            this.scenePrincipal.setRoot((Pane) loader.load());
        } catch (IOException ex) {
            Logger.getLogger(TareasArchivosFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        // this.inicioSesionFXMLController.getStagePantallaPrincipal().setScene(this.scenePrincipal);
        this.stagePantallaPrincipal.setScene(scenePrincipal);
        TableViewController tableViewController = loader.<TableViewController>getController();

        tableViewController.setEnf(enf);
        // tableViewController.setManager(manager);
        tableViewController.setEstudiante(estudiante);
        tableViewController.llenarEstudiante();
        tableViewController.listarMaterias();
        tableViewController.seleccionarTareas(this.tarea.getMateria());
        // tableViewController.setMaterias(materia);
        //   tableViewController.listarTareas(materia);
        System.out.println(materia);
        tableViewController.setScenePrincipal(scenePrincipal);
        tableViewController.setStagePantallaPrincipal(stagePantallaPrincipal);
        tableViewController.setSceneInicioSesion(sceneInicioSesion);
        tableViewController.setStageInicioSesion(stageInicioSesion);

        // tableViewController.setSceneInicioSesion(sceneInicioSesion);
        // tableViewController.setStageInicioSesion(stageInicioSesion);
    }

}
