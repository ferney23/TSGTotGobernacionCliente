/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.co.controller;

import com.tsg.co.model.Clases;
import com.tsg.co.model.Estudiante;
import com.tsg.co.model.InfoGrado;
import com.tsg.co.model.MaterialEstudio;
import com.tsg.co.model.Materias;
import com.tsg.co.model.Tareas;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * FXML Controller class
 *
 * @author Ferney
 */
public class ClasesMaterialController implements Initializable {

    private static EntityManager manager;
    private static EntityManagerFactory enf;
    private Materias materias;
    TableViewController tableViewController;
    @FXML
    private BorderPane l;
    @FXML
    private Label labelNombres, labelMaterias;
    private ListView<MaterialEstudio> listviewListaMaterias = new ListView<MaterialEstudio>();
    @FXML
    private Pane paneIcono;
    @FXML
    private Label labelIcono;
    @FXML
    private TableColumn<MaterialEstudio, String> cellClase;
    @FXML
    private TableColumn<MaterialEstudio, String> cellMaterial;
    @FXML
    private TableView<MaterialEstudio> tableMaterialEstudio;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // TODO
    }

    public void recibirHome(TableViewController tableViewController, Materias materias, Estudiante estudiante) {

        try {
            this.materias = materias;
            this.tableViewController = tableViewController;
            this.labelMaterias.setText(this.materias.getTitulo());
            this.labelNombres.setText(estudiante.getNombres() + " " + estudiante.getApellidos());
            // System.out.println(infoGrado.getEstudiante().getNombres() );
            ClasesMateriales(materias);
        } catch (Exception e) {
        }

    }

    private void Hola(ActionEvent event) {
        System.out.println("com.tsg.co.controller.ClasesMaterialController.Hola()");
    }

    public void ClasesMateriales(Materias materias) {
        enf = Persistence.createEntityManagerFactory("tsg");
        manager = enf.createEntityManager();

        List<MaterialEstudio> materialEstudio = manager.createQuery("SELECT ma FROM MaterialEstudio ma WHERE ma.clase.materia.idMateria= :id").setParameter("id", materias.getIdMateria()).getResultList();
        System.out.println(materialEstudio);

        listviewListaMaterias.getItems().clear();
        for (MaterialEstudio materialEstudio1 : materialEstudio) {
            System.out.println(materialEstudio1.getClase());
            listviewListaMaterias.getItems().add(materialEstudio1);
        }

        
    }

    private void seleccionarMaterialEstudio(MouseEvent event) {
        try {
               System.out.println(listviewListaMaterias.getSelectionModel().getSelectedItem().getClase());
        } catch (Exception e) {
            System.out.println("CELDA VACIA");
        }
     
        
    }

}
