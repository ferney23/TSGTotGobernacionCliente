/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.co.controller;

import com.tsg.co.model.Clases;
import com.tsg.co.model.Estudiante;
import com.tsg.co.model.MaterialEstudio;
import com.tsg.co.model.Materias;
import com.tsg.co.vista.ViewMaterialEstudio;
import com.tsg.co.vista.VistaModeloClases;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * FXML Controller class
 *
 * @author Ferney
 */
public class MaterialEstudioFXMLController implements Initializable {

    private Clases clases;
    private Estudiante estudiante;

    private static EntityManager manager;
    private static EntityManagerFactory enf;

    @FXML
    private AnchorPane panelInformacion;
    @FXML
    private TableView<ViewMaterialEstudio> tableMaterialEstudio;
    @FXML
    private TableColumn<ViewMaterialEstudio, String> colClase;
    @FXML
    private TableColumn<ViewMaterialEstudio, String> colTema;
    @FXML
    private TableColumn<ViewMaterialEstudio, String> ColNombre;
    @FXML
    private TableColumn<ViewMaterialEstudio, String> colDescripcion;
    @FXML
    private TableColumn<ViewMaterialEstudio, Button> colAdjunto;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public Clases getClases() {
        return clases;
    }

    public void setClases(Clases clases) {
        this.clases = clases;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public void ViewMaterialEstudio() {
        // this.clases = clases;
        enf = Persistence.createEntityManagerFactory("tsg");
        manager = enf.createEntityManager();

        List<MaterialEstudio> materialEstudios = manager.createQuery("SELECT ma FROM MaterialEstudio ma WHERE ma.clase.idClases= :id").setParameter("id", this.clases.getIdClases()).getResultList();
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

            tableMaterialEstudio.getItems().add(viewMaterialEstudio);

        }

        ColNombre.setCellValueFactory(new PropertyValueFactory<ViewMaterialEstudio, String>("nombre"));
        colTema.setCellValueFactory(new PropertyValueFactory<ViewMaterialEstudio, String>("tema"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory<ViewMaterialEstudio, String>("descripcion"));
        colClase.setCellValueFactory(new PropertyValueFactory<ViewMaterialEstudio, String>("clase"));
        colAdjunto.setCellValueFactory(new PropertyValueFactory<ViewMaterialEstudio, Button>("btnArchivoAdjunto"));

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
