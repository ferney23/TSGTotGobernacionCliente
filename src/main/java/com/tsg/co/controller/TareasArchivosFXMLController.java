package com.tsg.co.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.tsg.co.model.AchivosTot;
import com.tsg.co.model.CustomImage;
import com.tsg.co.model.Entregas;
import com.tsg.co.model.Estudiante;
import com.tsg.co.model.Materias;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

    private static EntityManager manager;
    private static EntityManagerFactory enf;
    private String codigotarea;
    @FXML
    private TableView<CustomImage> tableArchivo = new TableView<CustomImage>();
    @FXML
    private TableView<CustomImage> tableArchivo1 = new TableView<CustomImage>();
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
    private TableColumn<?, ?> optionstable;
    @FXML
    private TableColumn<?, ?> options;
    @FXML
    private Pane dibujo;
    @FXML
    private Button dibujoitem;
    @FXML
    private Button botoncargar;

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
        mostrarArchivo();
    }

    public Materias getMateria() {
        return materia;
    }

    public void setMateria(Materias materia) {
        this.materia = materia;
        materianombre.setText(materia.getTitulo());
    }

    public void setCodigotarea(String codigotarea) {
        this.codigotarea = codigotarea;
        //  codigotarealabel.setText(codigotarea);
    }

    @FXML
    private void cargararchivo(MouseEvent event) throws IOException {
        Button b = (Button) event.getSource();
        if (b.getText().equals("Subir Archivos")) {
            FileChooser fileChooser = new FileChooser();
            Stage stage = (Stage) anchorpane.getScene().getWindow();
            File file = fileChooser.showOpenDialog(stage);
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

                //int i= sql.getIdSubidaTarea(id);
                //String Codigo = "9"+id+""+i+""+est.getIdEst()+""+materiaid+positcion;
                // Long l = Long.parseLong(Codigo);
                //boolean save = sql.saveRuteBlob(dest.toString(),i,id,l);
                System.out.println(this.tarea.getSubida());

                Path dest = Paths.get("Data/" + "Tareas/" + this.tarea.getNombreTarea() + "/" + file.getName());
                Files.copy(file.toPath(), dest,
                        StandardCopyOption.REPLACE_EXISTING);

                Entregas nuevaEntrega = new Entregas(0L, this.estudiante, this.tarea.getSubida(), this.tarea, this.tarea.getRegistroTarea());
                nuevaEntrega.persist(nuevaEntrega);
                String archivoTotcodigo = nuevaEntrega.getId() + "" + this.estudiante.getIdEstudiante() + "" + this.tarea.getSubida().getIdSubida();

                Long idArchivoTot = Long.parseLong(archivoTotcodigo);

                AchivosTot achivosNuevo = new AchivosTot(idArchivoTot, archivoTotcodigo, dest.toString(), this.tarea.getSubida(), nuevaEntrega);
                achivosNuevo.persist(achivosNuevo);

                tableArchivo1.getItems().add(itemlist);
                imagentype1.setCellValueFactory(new PropertyValueFactory<CustomImage, Image>("image"));
                nombrematerial.setCellValueFactory(new PropertyValueFactory<CustomImage, String>("nombreTarea"));

            }
        }
    }

    public void mostrarArchivo() {
        enf = Persistence.createEntityManagerFactory("tsg");
        manager = enf.createEntityManager();

        List<AchivosTot> achivosTots1 = manager.createQuery("SELECT ma FROM AchivosTot ma WHERE ma.subida.tareas.id= :id").setParameter("id", tarea.getId()).getResultList();

        List<AchivosTot> achivosTots = new ArrayList<>();
        for (AchivosTot arch : achivosTots1) {
            System.out.println(arch);
            achivosTots.add(arch);
            /**
             *
             * try { if
             * (arch.getSubida().getIdSubida().equals(tarea.getSubida().getIdSubida()))
             * { System.out.println(arch); achivosTots.add(arch); } } catch
             * (Exception e) { }
             *
             */
        }
        ObservableList<AchivosTot> observableListaArchivos = FXCollections.observableArrayList(achivosTots);
        List<CustomImage> obj = new ArrayList<>();
        for (AchivosTot arch : observableListaArchivos) {
            //TableColumn<CustomImage, Image> imagecolumss  =  new TableColumn<>();
            CustomImage itemlist = new CustomImage();
            String[] arch1 = arch.getRuta().split("\\\\");
            itemlist.setNombreTarea(arch1[arch1.length - 1]);
            itemlist.setRuta(arch.getRuta());
            //validacion SI ESTA ENTREGADA  if(tar.)
            CustomImage item_1 = null;

            item_1 = new CustomImage(new ImageView(new Image("img/trueyes.png")));
            item_1.getImage().setFitHeight(25);
            item_1.getImage().setFitWidth(25);
            //tar.setImage(item_1);

            itemlist.setCodigo(arch.getCodigo());
            itemlist.setImage(item_1.getImage());

            //imagecolum.setCellValueFactory((Callback<TableColumn.CellDataFeatures<CustomImage, Image>, ObservableValue<Image>>) imagecolumss);
            obj.add(itemlist);
        }
        ObservableList<CustomImage> archivos = FXCollections.observableArrayList(obj);
        tableArchivo.setItems(archivos);
        //lbtnArchivo.setCellValueFactory(new PropertyValueFactory<AchivosTot, Long>("idAchivosTot"));
        imagetype.setCellValueFactory(new PropertyValueFactory<CustomImage, Image>("image"));
        nombrematerial1.setCellValueFactory(new PropertyValueFactory<CustomImage, String>("nombreTarea"));
        //labelMaterias.setText(materias.getTitulo().toUpperCase());

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

        System.err.println(tableArchivo1.getSelectionModel().getSelectedItem().getRuta() + "Ferney");
        String ruta = tableArchivo1.getSelectionModel().getSelectedItem().getRuta();
        File f = new File(ruta);
        try {
            Desktop.getDesktop().open(f);
        } catch (IOException e) {
            e.printStackTrace();
        }

        /**
         * tableArchivo1.getSelectionModel().getSelectedItem(); String ruta =
         * tableArchivo1.getSelectionModel().getSelectedItem().getRuta(); File f
         * = new File(".\\" + ruta); try { Desktop.getDesktop().open(f); } catch
         * (IOException e) { e.printStackTrace(); }
         *
         */
    }

}
