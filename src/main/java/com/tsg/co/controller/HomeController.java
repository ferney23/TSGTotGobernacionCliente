/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.co.controller;

import static com.ibm.icu.impl.PluralRulesLoader.loader;
import com.tsg.co.Constantes.Constantes;
import com.tsg.co.model.Estudiante;
import com.tsg.co.model.InfoGrado;
import com.tsg.co.model.Materias;
import com.tsg.co.model.Tareas;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ferney
 */
public class HomeController implements Initializable {

    private HomeController homeController;
    private Materias materias;
    private static EntityManager manager;
    private static EntityManagerFactory enf;
    String aux;
    @FXML
    private AnchorPane panellstudent, Home;
    @FXML
    private ListView<Materias> listMaterias = new ListView<Materias>();
    @FXML
    private ListView<Tareas> listprincipal = new ListView<Tareas>();
    @FXML
    private VBox VboxPprincipal;
   
    @FXML
    private Button btnActualizar, btnRecargar, btnvermasinfomaterias;
    @FXML
    private BorderPane panelnotificaciones, panelFoto;
    @FXML
    private Label labelcodigo, labelNombre, labelInstitucion, labelUbicacion, labelGrado, labelTareasPendientes,
            labelprincipalmaterias, labelnotificacionestareas;

    @FXML
    private Pane panelMaterias;
    private int tareasPendientes;
    private InfoGrado infoGrado;

    //private ClasesMaterialController controller;
    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //controller = new ClasesMaterialController();
        
        this.Home.getStyleClass().add("panel-primary"); 
        this.homeController = this;
        this.tareasPendientes = 0;
        listarMaterias();
        infoEstudiante();

    }

    public void infoEstudiante() {
        try {
            this.infoGrado = manager.find(InfoGrado.class, Constantes.Estudiante);
            this.labelcodigo.setText("Codigo :" + infoGrado.getCodigoGrado());
            this.labelNombre.setText("Nombres :" + infoGrado.getEstudiante().getNombres() +" "+ infoGrado.getEstudiante().getApellidos());
            this.labelInstitucion.setText(infoGrado.getInstitucion());
            this.labelUbicacion.setText(infoGrado.getUbicacionInstitucion());
            this.labelGrado.setText(infoGrado.getNombre());
        } catch (Exception e) {
        }

    }

    public void listarMaterias() {
        enf = Persistence.createEntityManagerFactory("tsg");
        manager = enf.createEntityManager();
        ArrayList<Materias> materias = (ArrayList<Materias>) manager.createQuery("FROM Materias").getResultList();
        //listMaterias.getItems().add(materias)
        for (Materias materia : materias) {
            listMaterias.getItems().add(materia);

        }

    }

    @FXML
    private void clickActualizar(ActionEvent event) {
        System.err.println("Actualizar");

    }

    @FXML
    private void clickRecargar(ActionEvent event) {
        System.err.println("Recargar");

    }

    @FXML
    private void handleMouseClick(javafx.scene.input.MouseEvent event) {

        listprincipal.getItems().clear();
        Materias value = listMaterias.getSelectionModel().getSelectedItem();
        materias = manager.find(Materias.class, value.getIdMateria());
        Set<Tareas> tareas = materias.getTareas();
        for (Tareas tarea : tareas) {
            listprincipal.getItems().add(tarea);
            if (tarea.getSubida() != null) {
                System.out.println(tarea.getSubida().getIdSubida());
            }

        }
        labelprincipalmaterias.setText(materias.getTitulo());

    }

    @FXML
    private void handleMouseClickMaterias(javafx.scene.input.MouseEvent event) throws IOException {

        Tareas value = listprincipal.getSelectionModel().getSelectedItem();
        Stage stageMaterialesClases = new Stage();
        FXMLLoader loader = new FXMLLoader();
        AnchorPane root = (AnchorPane) loader.load(getClass().getResource("/fxml/TareasPendientesFXML.fxml").openStream());
        TareasPendientesFXMLController tareasPendientesFXMLController = (TareasPendientesFXMLController) loader.getController();
        Scene scena = new Scene(root);
        stageMaterialesClases.setTitle("Tareas Pendientes");
        stageMaterialesClases.setScene(scena);
        stageMaterialesClases.show();

        System.out.println(value);

    }

    @FXML
    void clickvermas(ActionEvent event) throws IOException {

        try {
            Stage stageMaterialesClases = new Stage();
            FXMLLoader loader = new FXMLLoader();
            AnchorPane root = (AnchorPane) loader.load(getClass().getResource("/fxml/ClasesMaterialFXML.fxml").openStream());
            ClasesMaterialController clasesMaterialController = (ClasesMaterialController) loader.getController();
            //clasesMaterialController.recibirHome(this.homeController, materias, infoGrado);
            Scene scena = new Scene(root);
            stageMaterialesClases.setTitle(materias.getTitulo().toUpperCase());
            stageMaterialesClases.setScene(scena);
            stageMaterialesClases.show();
        } catch (Exception e) {
        
        }

        //FXMLLoader.load(getClass().getResource("/fxml/Home.fxml"));
    }

    public Materias getMaterias() {
        return materias;
    }

    public void setMaterias(Materias materias) {
        this.materias = materias;
    }

}
